package com.example.vidplay.media;

import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/media")
public class MediaController {

    @Autowired
    private MediaService mediaService;

    @PostMapping("/stream")
    public ResponseEntity<Void> streamMedia(@RequestBody MediaRequest mediaRequest, HttpServletResponse response) throws IOException {
        String path = mediaRequest.getPath();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(mediaService.getMediaType(path)));
        headers.setContentLength(mediaService.getMediaFileSize(path));

        byte[] mediaBytes = mediaService.getMediaBytes(path);

        response.getOutputStream().write(mediaBytes);
        response.getOutputStream().flush();

        return new ResponseEntity<>(headers, HttpStatus.OK);
    }
}