package org.project.persistence.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "EVENT")
public class EventEntity extends AbstractPersistable<Long> {
    @Column(name = "TITLE", length = 255, nullable = false)
    private String title;
    @Column(name = "DESCRIPTION", length = 4000, nullable = false)
    private String description;
    @ElementCollection
    @Column(name = "TAG", length = 255)
    @CollectionTable(name = "EVENT_TAGS", joinColumns = @JoinColumn(name = "EVENT_ID"))
    private Set<String> tags = new HashSet<>();
    @Column(name = "PUBLIC", nullable = false)
    private boolean isPublic = true;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    public EventEntity() {
    }

    public EventEntity(String title, String description, Set<String> tags, boolean isPublic) {
        this.title = title;
        this.description = description;
        this.tags = tags;
        this.isPublic = isPublic;
    }

    @PrePersist
    private void prePersist() {
        created = new Date();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<String> getTags() {
        return tags;
    }

    public void setTags(Set<String> tags) {
        this.tags.addAll(tags);
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public boolean isPublic() {
        return isPublic;
    }

    public void setPublic(boolean isPublic) {
        this.isPublic = isPublic;
    }

    public Date getCreated() {
        return created;
    }
}