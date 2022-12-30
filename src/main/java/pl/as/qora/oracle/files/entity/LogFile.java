package pl.as.qora.oracle.files.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "V$LOGFILE")
@Immutable
public class LogFile extends PanacheEntityBase {
	@Id
	@Column(name = "MEMBER")
	public String member;
	@Column(name = "GROUP#")                       
	public Long group;
	@Column(name = "STATUS")
	public String status;
	@Column(name = "TYPE")
	public String type;
	@Column(name = "IS_RECOVERY_DEST_FILE")
	public String isRecoveryDestFile;
	@Column(name = "CON_ID")
	public Long conId;
}
