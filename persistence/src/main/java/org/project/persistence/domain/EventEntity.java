package org.project.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@JsonIgnoreProperties(value = {"new"}, ignoreUnknown = true)
@Entity
@Table(name = "EVENT")
public class EventEntity extends AbstractPersistable<Long> {
    @NotNull
    @Size(max = 255)
    @Column(name = "TITLE", length = 255, nullable = false)
    private String title;
    @NotNull
    @Size(max = 4000)
    @Column(name = "DESCRIPTION", length = 4000, nullable = false)
    private String description;
    @ElementCollection
    @Column(name = "TAG", length = 255)
    @CollectionTable(name = "EVENT_TAGS", joinColumns = @JoinColumn(name = "EVENT_ID"))
    private Set<String> tags = new HashSet<>();
    @OneToMany(orphanRemoval = true, cascade = CascadeType.ALL)
    private List<CommentEntity> comments = new ArrayList<>();
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity creator;
    @ManyToMany
    @JoinTable(name = "EVENT_PARTICIPANT",
            joinColumns = @JoinColumn(name = "EVENT_ID"),
            inverseJoinColumns = @JoinColumn(name = "USER_ID"))
    private List<UserEntity> participants = new ArrayList<>();
    @OneToMany
    @JoinColumn(name = "EVENT_ID")
    private List<AttachmentEntity> attachments = new ArrayList<>();
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

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

    public void setTags(Collection<String> tags) {
        this.tags.addAll(tags);
    }

    public void addTag(String tag) {
        tags.add(tag);
    }

    public List<CommentEntity> getComments() {
        return comments;
    }

    public void setComments(Collection<CommentEntity> comments) {
        this.comments.addAll(comments);
    }

    public void addComment(CommentEntity comment) {
        comments.add(comment);
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public List<UserEntity> getParticipants() {
        return participants;
    }

    public void setParticipants(Collection<UserEntity> participants) {
        this.participants.addAll(participants);
    }

    public void addParticipant(UserEntity participant) {
        participants.add(participant);
    }

    public List<AttachmentEntity> getAttachments() {
        return attachments;
    }

    public void setAttachments(List<AttachmentEntity> attachments) {
        this.attachments.addAll(attachments);
    }

    public void addAttachment(AttachmentEntity attachment) {
        attachments.add(attachment);
    }

    public Date getCreated() {
        return created;
    }
}