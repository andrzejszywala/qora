package pl.as.qora.tree.boundary;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.inject.Inject;
import javax.sql.DataSource;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;

import pl.as.qora.parser.KeywordLocator;
import pl.as.qora.parser.SqlParser;
import pl.as.qora.parser.Tokenizer;
import pl.as.qora.tree.control.ExplainPlan;
import pl.as.qora.tree.entity.ExplainPlanRow;
import pl.as.qora.tree.entity.Wiersz;
import pl.as.qora.tree.entity.WykonajSQLResult;
import pl.as.qora.tree.entity.ZbiorDanych;

@Path("sql")
public class SqlResource {
    private static final Logger LOG = Logger.getLogger(SqlResource.class.getName());

    @Inject
    DataSource dataSource;
    @Inject
    ExplainPlan explainPlan;

    @POST
    @Consumes(MediaType.TEXT_PLAIN)
    @Produces(MediaType.APPLICATION_JSON)
    public WykonajSQLResult executeSql(String sql) {
        return uruchomSkrypt(sql);
    }

    @POST
    @Path("explain_plan")
    public List<ExplainPlanRow> explainPlan(String sql) {
        return explainPlan.execute(sql);
    }

    private WykonajSQLResult uruchomSkrypt(String sql) {
        QueryRunner jdbcTemplate = new QueryRunner(dataSource);

        Collection<ZbiorDanych> zbiorDanych = new ArrayList<ZbiorDanych>();
        Collection<String> komunikaty = new ArrayList<String>();
        for (String polecenie : new SqlParser().getStatements(new Tokenizer(sql))) {
            try {
                if (czySelect(polecenie)) {
                    ZbiorDanych rezultat = wykonajSelect(jdbcTemplate, polecenie);
                    zbiorDanych.add(rezultat);
                    komunikaty.add("Pobrano " + rezultat.getDane().size() + " wierszy");
                } else if (czyDml(polecenie)) {
                    int ilosc = wykonajDml(jdbcTemplate, polecenie);
                    komunikaty.add("Wpłynięto na " + ilosc + " wierszy");
                } else {
                    jdbcTemplate.update(polecenie);
                    komunikaty.add("Wykonano polecenie");
                }
            } catch (Exception ex) {
                LOG.log(Level.SEVERE, "Błąd SQL", ex);
                komunikaty.add(ex.getLocalizedMessage());
            }
        }
        return new WykonajSQLResult(zbiorDanych, komunikaty);
    }

    private ZbiorDanych wykonajSelect(QueryRunner jdbcTemplate, String polecenie) throws SQLException {
        final Collection<String> kolumny = new LinkedHashSet<String>();
        List<Wiersz> dane = jdbcTemplate.query(polecenie, (ResultSetHandler<List<Wiersz>>) rs -> {
            List<Wiersz> wynik = new ArrayList<Wiersz>();
            for (int i1 = 1; i1 <= rs.getMetaData().getColumnCount(); i1++) {
                kolumny.add(rs.getMetaData().getColumnName(i1));
            }
            while (rs.next()) {
                Wiersz model = new Wiersz();
                for (int i2 = 1; i2 <= rs.getMetaData().getColumnCount(); i2++) {
                    model.put(rs.getMetaData().getColumnName(i2), rs.getObject(i2) + " ");
                }
                wynik.add(model);
            }
            return wynik;
        });
        return new ZbiorDanych(kolumny, dane);
    }

    private int wykonajDml(QueryRunner jdbcTemplate, String polecenie) throws SQLException {
        return jdbcTemplate.update(polecenie);
    }

    private boolean czySelect(String polecenie) {
        return "SELECT".equals(new KeywordLocator().GetWord(polecenie, false));
    }

    private boolean czyDml(String polecenie) {
        String keyword = new KeywordLocator().GetWord(polecenie, false);
        return "DELETE".equals(keyword) || "UPDATE".equals(keyword) || "INSERT".equals(keyword)
                || "MERGE".equals(keyword);
    }

}