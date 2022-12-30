package pl.as.qora.oracle.files.boundary;

import java.util.Collection;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import pl.as.qora.oracle.files.control.FilesService;
import pl.as.qora.oracle.files.entity.LogFile;
import pl.as.qora.oracle.files.entity.OracleFile;

@Path("files")
public class FilesResource {

    @Inject
    FilesService filesService;

    @GET
    public Collection<OracleFile> files() {
        return filesService.files();
    }
    
    @GET
    @Path("log")
    public Collection<LogFile> logFiles() {
        return filesService.logFiles();
    }
}
