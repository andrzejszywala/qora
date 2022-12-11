package pl.as.qora.oracle.users.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;
import pl.as.qora.enums.YesNo;

@Entity
@Table(name = "DBA_USERS")
@Immutable
public class User extends PanacheEntityBase {

	@Id
	@Column(name = "USER_ID")
	public Long userId;
	
	@Column(name = "USERNAME")
	public String userName;
	
	@Column(name = "ACCOUNT_STATUS")
	public String accountStatus;
	
	@Column(name = "LOCK_DATE")
	public LocalDateTime lockDate;
	
	@Column(name = "EXPIRY_DATE")
	public LocalDateTime expiryDate;
	
	@Column(name = "DEFAULT_TABLESPACE")   
	public String defaultTablespace;
	
	@Column(name = "TEMPORARY_TABLESPACE")
	public String temporaryTablespace;
	
	@Column(name = "CREATED")    
	public LocalDateTime created;
	
	@Column(name = "PROFILE")   
	public String profile;
	
	@Column(name = "EXTERNAL_NAME") 
	public String externalName;
	
	@Column(name = "EDITIONS_ENABLED")
	@Enumerated(EnumType.STRING)
	public YesNo editionsEnabled;
}
