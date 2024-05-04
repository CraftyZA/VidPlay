package com.example.vidplay.media;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

@Service
@Slf4j
public class MediaService {

    public byte[] getMediaBytes(String fileName) throws IOException {
        log.info("Streaming " + fileName);
        Path filePath = Path.of(fileName);
        log.debug("Sending Bytes for " + filePath);
        return Files.readAllBytes(filePath);
    }

    public String getMediaType(String fileName) {
        String contentType = null;
        try {
            Path filePath = Path.of(fileName);
            contentType = Files.probeContentType(filePath);
        } catch (IOException e) {
            log.error("ERROR: IOException occurred in getMediaType()");
        }
        return contentType;
    }

    public long getMediaFileSize(String fileName) throws IOException {
        Path filePath = Path.of(fileName);
        return Files.size(filePath);
    }

    public String getBaseDir(String[] filePaths) {
        if (filePaths != null && filePaths.length > 0) {
            String firstPath = filePaths[0];
            int lastSeparatorIndex = firstPath.lastIndexOf(System.getProperty("file.separator"));
            if (lastSeparatorIndex > 0) {
                return firstPath.substring(0, lastSeparatorIndex);
            }
        }
        return null;
    }
}