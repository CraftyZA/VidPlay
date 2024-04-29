package com.example.vidplay.contentType;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "content_type")
@Data
public class ContentTypeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String typeDesc;

}