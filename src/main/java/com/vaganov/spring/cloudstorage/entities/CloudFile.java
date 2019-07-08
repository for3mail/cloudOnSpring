package com.vaganov.spring.cloudstorage.entities;

import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Entity
@Table(name = "cloud_files")
@Data
public class CloudFile {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    public void setPath(String path) {
        this.path = path;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPath() {
        return path;
    }

    public String getTitle() {
        return title;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Column(name = "title")
    @NotNull(message = "не может быть пустым")
    @Size(min = 1, max = 250, message = "требуется минимум 1 символ")
    private String title;

    @Column(name = "file_path")
    @NotNull(message = "path not null")
    @Size(min = 1, max = 250, message = "требуется минимум 1 символ")
    private String path;

    @Column(name = "created_at")
    @CreationTimestamp
    private LocalDateTime createdAt;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;



}
