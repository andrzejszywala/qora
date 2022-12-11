package pl.as.qora.oracle.tablespace.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "DBA_TABLESPACES")
@Immutable
public class Tablespace extends PanacheEntityBase {

	@Id
	@Column(name = "TABLESPACE_NAME")
	public String tablespaceName;
	
	@Column(name = "BLOCK_SIZE")
	public Long blockSize;
	
	@Column(name = "INITIAL_EXTENT")
	public Long initialExtent;
	
	@Column(name = "NEXT_EXTENT")
	public Long nextExtent;
	
	@Column(name = "MIN_EXTENTS")
	public Long minExtents;
	
	@Column(name = "MAX_EXTENTS")
	public Long maxExtents;
	
	@Column(name = "MAX_SIZE")
	public Long maxSize;
	
	@Column(name = "PCT_INCREASE")
	public Long pctIncrease;
	
	@Column(name = "MIN_EXTLEN")
	public Long minExtlen;
	
	@Column(name = "STATUS")
	public String status;
	
	@Column(name = "CONTENTS")
	public String contents;
	
	@Column(name = "LOGGING")
	public String logging;
	
	@Column(name = "FORCE_LOGGING")
	public String forceLogging;
	
	@Column(name = "EXTENT_MANAGEMENT")
	public String extentManagement;
	
	@Column(name = "ALLOCATION_TYPE")
	public String allocationType;
	
	@Column(name = "PLUGGED_IN")
	public String pluggedIn;
	
	@Column(name = "SEGMENT_SPACE_MANAGEMENT")
	public String segmentSpaceManagement;
	
	@Column(name = "DEF_TAB_COMPRESSION")
	public String defTabCompression;
	
	@Column(name = "RETENTION")
	public String retention;
	
	@Column(name = "BIGFILE")
	public String bigfile;
	
	@Column(name = "PREDICATE_EVALUATION")
	public String predicateEvaluation;
	
	@Column(name = "ENCRYPTED")
	public String encrypted;
	
	@Column(name = "COMPRESS_FOR")
	public String compressFor;	
}
