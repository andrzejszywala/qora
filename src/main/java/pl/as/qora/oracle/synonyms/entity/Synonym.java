package pl.as.qora.oracle.synonyms.entity;

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
	@NamedQuery(name = "Synonym.findByUser", 
			query =	"""
			   SELECT s 
				 FROM Synonym s
				WHERE s.owner = ?1
				ORDER BY s.synonymName """)

})
@Entity
@Table(name = "DBA_SYNONYMS")
@Immutable
@IdClass(SynonymId.class)
public class Synonym extends PanacheEntityBase {

	@Id
	@Column(name = "SYNONYM_NAME") 
	public String synonymName;
	
	@Id
	@Column(name = "OWNER")
	public String owner;
	
	@Column(name = "TABLE_OWNER")
	public String tableOwner;
	
	@Column(name = "TABLE_NAME")
	public String tableName;
	
	public static List<Synonym> findByUser(String user) {
		return find("#Synonym.findByUser", user).list();
	}
}
