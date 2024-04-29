package com.example.vidplay.contentSource;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@Slf4j
public class ContentSourceService {

    public ContentSourceService(ContentSourceRepo repository) {
        this.repository = repository;
    }

    private ContentSourceRepo repository;

    public List<ContentSourceEntity> getAllContentSources() {
        return repository.findAll();
    }

    public List<String> getFilesByContentSourceId(Long id) {

        try {
            ContentSourceEntity contentSourceEntity = repository.findById(id).orElse(null);
            if (contentSourceEntity == null) {
                return null;
            }
            Path path = Paths.get(contentSourceEntity.getPath());
            if (Files.isDirectory(path)) {
                //Stream<Path> pathStream =
                return Files.walk(path)
                        .filter(Files::isRegularFile)
                        .map(Path::toString)
                        .collect(Collectors.toList());
            }
        } catch (IOException ioe) {
            log.error("ERROR: An error occurred while trying to list the files");
            return null;
        } catch (Exception e) {
            log.error("Error getting file List. " + e.getMessage());
        }

        return null;
    }
}
