package pl.as.qora.oracle.roles.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "DBA_ROLES")
@Immutable
public class Role extends PanacheEntityBase {

	@Id
	@Column(name = "ROLE")
	public String role;
		
}
