package pl.as.qora.oracle.tables.entity;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@NamedQueries({
	@NamedQuery(name = "Tables.findByUser", 
			query =	"""
			   SELECT t 
				 FROM Tables t
				WHERE t.owner = ?1
				ORDER BY t.tableName """)
})
@Entity
@Table(name = "DBA_TABLES")
@Immutable
@IdClass(TablesId.class)
public class Tables extends PanacheEntityBase {

	@Id
	@Column(name = "OWNER")
	public String owner;
	
	@Id
	@Column(name = "TABLE_NAME")
	public String tableName;
	
	@Column(name = "TABLESPACE_NAME")
	public String tablespaceName;
	
	@Column(name = "PARTITIONED")
	public String partitioned;
	
	@Column(name = "NUM_ROWS")
	public Long numRows;
	
	@Column(name = "LAST_ANALYZED")
	public LocalDate lastAnalyzed;
	
	public static List<Tables> findByUser(String user) {
		return find("#Tables.findByUser", user).list();
	}
}
