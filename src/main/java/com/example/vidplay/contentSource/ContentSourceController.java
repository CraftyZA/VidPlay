package com.example.vidplay.contentSource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ContentSourceController {
    private ContentSourceService contentSourceService;

    public ContentSourceController(ContentSourceService contentSourceService) {
        this.contentSourceService = contentSourceService;
    }

    @GetMapping("/content/sources/")
    public List<ContentSourceEntity> contentSources() {
        return contentSourceService.getAllContentSources();
    }

    @GetMapping("/content/sources/{id}/files")
    public List<String> getFilesByContentSourceId(@PathVariable Long id) {
        return contentSourceService.getFilesByContentSourceId(id);
    }

}
