package org.example.jsfdemo.entities;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "ATTACHMENTS")
@SequenceGenerator(name = "attachment_seq", sequenceName = "ATTACH_ID_SEQ", allocationSize = 1)
public class Attachment {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "attachment_seq")
    @Column(name = "ATTACH_ID", nullable = false)
    private Long id;

    @OneToOne(mappedBy = "attach")
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Size(max = 100)
    @Column(name = "FILE_NAME", length = 100)
    private String fileName;

    @Size(max = 240)
    @Column(name = "FULL_PATH", length = 240)
    private String fullPath;

    public String getFullPath() {
        return fullPath;
    }

    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}