package pl.as.qora.oracle.objects.entity;

import java.time.LocalDateTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@NamedQueries({
	@NamedQuery(name = "Objects.findByUserAndType", 
			query =	"""
			   SELECT o 
				 FROM Objects o
				WHERE o.owner = ?1
				  AND o.objectType = ?2""")

})
@Entity
@Table(name = "DBA_OBJECTS")
@IdClass(ObjectsId.class)
public class Objects extends PanacheEntityBase {

	@Id
	@Column(name = "OBJECT_NAME") 
	public String objectName;
	
	@Id
	@Column(name = "OWNER")
	public String owner;
	
	@Column(name = "OBJECT_TYPE")
	public String objectType;
	
	@Column(name = "STATUS")
	public String status;

	@Column(name = "CREATED")
	public LocalDateTime created;
	
	@Column(name = "LAST_DDL_TIME")
	public LocalDateTime lastDdlTime;

	public static List<Objects> findByUserAndType(String user, String type) {
		return find("#Objects.findByUserAndType", user, type).list();
	}
}
