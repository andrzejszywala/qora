package pl.as.qora.oracle.sessions.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Formula;
import org.hibernate.annotations.Immutable;

import io.quarkus.hibernate.orm.panache.PanacheEntityBase;

@Entity
@Table(name = "V$SESSION")
@Immutable
public class Session extends PanacheEntityBase {
    
    @Id
    @Column(name = "SID")
    public Long sid;
    
    @Formula("DECODE(TYPE,'BACKGROUND','BACKGROUND','USER', DECODE(USERNAME, NULL, 'BACKGROUND', USERNAME))")
    public String name;
    
    @Column(name = "SERIAL#")
    public Long serial;

    @Column(name = "STATE")
    public String state;

    @Column(name = "SCHEMANAME")
    public String schemaName;

    @Column(name = "USERNAME")
    public String userName;

    @Column(name = "LOGON_TIME")
    public LocalDateTime logonTime;

    @Column(name = "PROGRAM")
    public String program;

    @Column(name = "OSUSER")
    public String osUser;

    @Column(name = "TERMINAL")
    public String terminal;

    @Column(name = "MACHINE")
    public String machine;

}
