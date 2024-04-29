package com.example.vidplay.contentType;

import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class ContentTypeService {

    public ContentTypeService(ContentTypeRepo repository) {
        this.repository = repository;
    }

    private ContentTypeRepo repository;

    public List<ContentTypeEntity> getAllContentTypes() {
        return repository.findAll();
    }
}