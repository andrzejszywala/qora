package pl.as.qora.oracle.views.entity;

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
	@NamedQuery(name = "View.findByUser", 
			query =	"""
			   SELECT v 
				 FROM View v
				WHERE v.owner = ?1""")
})
@Entity
@Table(name = "DBA_VIEWS")
@Immutable
@IdClass(ViewId.class)
public class View extends PanacheEntityBase {

	@Id
	@Column(name = "OWNER")
	public String owner;
	
	@Id
	@Column(name = "VIEW_NAME")
	public String viewName;

	public static List<View> findByUser(String user) {
		return find("#View.findByUser", user).list();
	}
}
