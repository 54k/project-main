package org.project.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@JsonIgnoreProperties(value = {"new"}, ignoreUnknown = true)
@Entity
@Table(name = "ATTACHMENT")
public class AttachmentEntity extends AbstractPersistable<Long> {
    @Size(max = 255)
    @Column(name = "NAME", nullable = false)
    private String name;
    @NotNull
    @Size(max = 4000)
    @Column(name = "URL", nullable = false, length = 4000)
    private String url;
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false)
    private AttachmentType type;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @PrePersist
    private void prePersist() {
        created = new Date();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public AttachmentType getType() {
        return type;
    }

    public void setType(AttachmentType type) {
        this.type = type;
    }

    public Date getCreated() {
        return created;
    }

    public static enum AttachmentType {
        IMAGE, DOCUMENT, URL
    }
}
