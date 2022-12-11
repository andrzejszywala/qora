package pl.as.qora.oracle.files.entity;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@MappedSuperclass
public class OracleFile extends PanacheEntityBase {
	@Id
	@Column(name = "FILE_ID")     
	public Integer fileId;

	@Column(name = "FILE_NAME") 
	public String fileName;
	
	@Column(name = "TABLESPACE_NAME")  
	public String tablespaceName;
	
	@Column(name = "BYTES")
	public Long bytes;
	
	@Column(name = "BLOCKS")        
	public Long blocks;
	
	@Column(name = "STATUS")   
	public String status;
	
	@Column(name = "RELATIVE_FNO")        
	public Long relativeFno;
	
	@Column(name = "AUTOEXTENSIBLE")   
	public String autoextensible;
	
	@Column(name = "MAXBYTES")        
	public Long maxbytes;
	
	@Column(name = "MAXBLOCKS")        
	public Long maxblocks;
	
	@Column(name = "INCREMENT_BY")        
	public Long incrementBy;
	
	@Column(name = "USER_BYTES")        
	public Long userBytes;
	
	@Column(name = "USER_BLOCKS")        
	public Long userBlocks;
	
}
