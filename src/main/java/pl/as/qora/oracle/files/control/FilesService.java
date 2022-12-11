package pl.as.qora.oracle.files.control;

import java.util.Collection;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.enterprise.context.ApplicationScoped;

import pl.as.qora.oracle.files.entity.DataFile;
import pl.as.qora.oracle.files.entity.OracleFile;
import pl.as.qora.oracle.files.entity.TempFile;

@ApplicationScoped
public class FilesService {

    public Collection<OracleFile> files() {
    	List<OracleFile> files = DataFile.listAll();
    	files.addAll(TempFile.listAll());
    	return files
    			.stream()
    			.sorted(Comparator.comparing(f -> f.fileName))
    			.collect(Collectors.toList());
    }
}
