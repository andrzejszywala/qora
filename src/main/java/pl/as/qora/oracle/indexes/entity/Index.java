package pl.as.qora.oracle.indexes.entity;

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
	@NamedQuery(name = "Index.findByUser", 
			query =	"""
			   SELECT i 
				 FROM Index i
				WHERE i.owner = ?1
				ORDER BY i.indexName """)
})
@Entity
@Table(name = "DBA_INDEXES")
@Immutable
@IdClass(IndexId.class)
public class Index extends PanacheEntityBase {

	@Id
	@Column(name = "OWNER")
	public String owner;
	
	@Id
	@Column(name = "INDEX_NAME")
	public String indexName;

	@Column(name = "TABLE_OWNER")
	public String tableOwner;
	
	@Column(name = "TABLE_NAME")
	public String tableName;
	
	@Column(name = "TABLE_TYPE")
	public String tableType;
	
	@Column(name = "INDEX_TYPE")
	public String indexType;
	
	@Column(name = "STATUS")
	public String status;
	
	@Column(name = "PARTITIONED")
	public String partitioned;
	
	@Column(name = "LAST_ANALYZED")
	public LocalDate lastAnalyzed;
	
	public static List<Index> findByUser(String user) {
		return find("#Index.findByUser", user).list();
	}
}
