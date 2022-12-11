package pl.as.qora.tree.control;

import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.sql.DataSource;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import pl.as.qora.tree.entity.ExplainPlanRow;

@ApplicationScoped
public class ExplainPlan {

    @Inject
    DataSource dataSource;

    public List<ExplainPlanRow> execute(String sql) {
        QueryRunner queryRunner = new QueryRunner(dataSource);

        try {
            String statementId = SecureRandom.getInstanceStrong().nextLong(30) + "";

            queryRunner.execute("EXPLAIN PLAN SET STATEMENT_ID = '" + statementId + "' FOR " + sql);
            List<ExplainPlanRow> rows = queryRunner.query(
                    """
                            SELECT OPERATION
                                    || DECODE(OTHER_TAG,NULL,'','*')
                                    || DECODE(OPTIONS,NULL,'','('||OPTIONS||')')
                                    || DECODE(OBJECT_NAME,NULL,'',' OF '''||OBJECT_NAME||'''')
                                    || DECODE(OBJECT_TYPE,NULL,'',' ('||OBJECT_TYPE||')')
                                    || DECODE(ID,0, DECODE(OPTIMIZER,NULL,'',' Tryb optymalizatora='||OPTIMIZER))||DECODE(COST,NULL,'','
                                        (Koszt='||COST||DECODE(CARDINALITY,NULL,'',' Liczba wierszy='||CARDINALITY)
                                    ||DECODE(BYTES,NULL,'',' Liczba bajtów='||BYTES)||')') AS OPERATION,
                                POSITION,
                                PARENT_ID,
                                ID
                            FROM PLAN_TABLE
                            WHERE STATEMENT_ID = ? ORDER BY ID, PARENT_ID, POSITION
                            """,
                    (ResultSetHandler<List<ExplainPlanRow>>) rs -> {
                        List<ExplainPlanRow> wynik = new ArrayList<ExplainPlanRow>();
                        while (rs.next()) {
                            wynik.add(
                                    new ExplainPlanRow(
                                            rs.getString("OPERATION"),
                                            rs.getLong("POSITION"),
                                            rs.getBigDecimal("PARENT_ID") == null ? null : rs.getLong("PARENT_ID"),
                                            rs.getLong("ID")));
                        }
                        return wynik;
                    },
                    statementId);
            queryRunner.execute("DELETE FROM PLAN_TABLE WHERE STATEMENT_ID = ?", statementId);
            return rows;
        } catch (SQLException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e.getMessage());
        }

    }
}