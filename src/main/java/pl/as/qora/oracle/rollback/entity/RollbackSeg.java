package pl.as.qora.oracle.rollback.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "DBA_ROLLBACK_SEGS")
@Immutable
@IdClass(RollbackSegId.class)
public class RollbackSeg extends PanacheEntityBase {
	@Id
	@Column(name = "SEGMENT_NAME") 
	public String segmentName;
	@Id
	@Column(name = "OWNER")
	public String owner;
	@Column(name = "TABLESPACE_NAME")
	public String tablespaceName;
	@Column(name = "SEGMENT_ID")
	public Long segmentId;
	@Column(name = "FILE_ID")       
	public Long fileId;
	@Column(name = "BLOCK_ID")       
	public Long blockId;
	@Column(name = "INITIAL_EXTENT")       
	public Long initialExtent;
	@Column(name = "NEXT_EXTENT")       
	public Long nextExtent;
	@Column(name = "MIN_EXTENTS")       
	public Long minExtents;
	@Column(name = "MAX_EXTENTS")       
	public Long maxExtents;
	@Column(name = "PCT_INCREASE")       
	public Long pctIncrease;
	@Column(name = "STATUS") 
	public String status;
	@Column(name = "INSTANCE_NUM") 
	public String instanceNum;
	@Column(name = "RELATIVE_FNO")
	public String relativeFno;
}
