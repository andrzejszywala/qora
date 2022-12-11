package pl.as.qora.oracle.files.entity;

import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.annotations.Immutable;

@Entity
@Table(name = "DBA_DATA_FILES")
@Immutable
public class DataFile extends OracleFile {

	
	
}
