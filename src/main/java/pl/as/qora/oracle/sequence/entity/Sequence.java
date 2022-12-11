package pl.as.qora.oracle.sequence.entity;

import java.math.BigInteger;
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
	@NamedQuery(name = "Sequence.findByUser", 
			query =	"""
			   SELECT s 
				 FROM Sequence s
				WHERE s.sequenceOwner = ?1
				ORDER BY s.sequenceName""")

})
@Entity
@Table(name = "DBA_SEQUENCES")
@Immutable
@IdClass(SequenceId.class)
public class Sequence extends PanacheEntityBase {

	@Id
	@Column(name = "SEQUENCE_NAME") 
	public String sequenceName;
	
	@Id
	@Column(name = "SEQUENCE_OWNER")
	public String sequenceOwner;
	
	@Column(name = "MIN_VALUE")
	public BigInteger minValue;
	
	@Column(name = "MAX_VALUE")
	public BigInteger maxValue;
	
	@Column(name = "LAST_NUMBER")
	public BigInteger lastNumber;
	
	public static List<Sequence> findByUser(String user) {
		return find("#Sequence.findByUser", user).list();
	}
}
