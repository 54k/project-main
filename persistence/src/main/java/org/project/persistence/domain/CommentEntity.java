package org.project.persistence.domain;

import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "COMMENT")
public class CommentEntity extends AbstractPersistable<Long> {
    @Column(name = "MESSAGE", length = 1000, nullable = false)
    private String message;
    @ManyToOne
    @JoinColumn(name = "USER_ID")
    private UserEntity creator;
    @Temporal(TemporalType.TIMESTAMP)
    private Date created;

    @PrePersist
    private void prePersist() {
        created = new Date();
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public UserEntity getCreator() {
        return creator;
    }

    public void setCreator(UserEntity creator) {
        this.creator = creator;
    }

    public Date getCreated() {
        return created;
    }
}