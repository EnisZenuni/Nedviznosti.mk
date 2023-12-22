package ez.ndvz.realestateservice.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

public interface FileService {
    String addFile(MultipartFile multipartFile) throws IOException;
    List<String> addFiles(List<MultipartFile> uploads) throws IOException;
}
