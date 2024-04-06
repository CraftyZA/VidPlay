package com.example.vidplay.controler;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

@RestController
public class VideoController {

    @GetMapping("/video/{videoName}")
    public ResponseEntity<StreamingResponseBody> streamVideo(@PathVariable String videoName) {
        System.out.println(System.getProperty("user.dir") + "\\" + videoName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", videoName);

        File videoFile = new File(System.getProperty("user.dir") + "\\" + videoName);

        StreamingResponseBody stream = out -> {
            try (InputStream inputStream = new FileInputStream(videoFile)) {
                byte[] buffer = new byte[1024];
                int bytesRead;
                while ((bytesRead = inputStream.read(buffer)) != -1) {
                    out.write(buffer, 0, bytesRead);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        };

        return new ResponseEntity<>(stream, headers, HttpStatus.OK);
    }
}