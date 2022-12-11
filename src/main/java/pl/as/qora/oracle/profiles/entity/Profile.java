package pl.as.qora.oracle.profiles.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "DBA_PROFILES")
@Immutable
public class Profile extends PanacheEntityBase {

	@Id
	@Column(name = "PROFILE")
	public String profile;
		
}
