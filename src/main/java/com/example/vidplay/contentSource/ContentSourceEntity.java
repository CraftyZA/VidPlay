package com.example.vidplay.contentSource;

import com.example.vidplay.contentType.ContentTypeEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "content_source")
@Data
public class ContentSourceEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String path;

    @ManyToOne
    @JoinColumn(name = "content_type_id")
    private ContentTypeEntity contentTypeEntity;

}
