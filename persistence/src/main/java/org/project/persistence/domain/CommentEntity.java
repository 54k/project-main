package org.project.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Date;

@JsonIgnoreProperties(value = {"new"}, ignoreUnknown = true)
@Entity
@Table(name = "COMMENT")
public class CommentEntity extends AbstractPersistable<Long> {
    @NotNull
    @Size(max = 1000)
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