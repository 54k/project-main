package org.project.persistence.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.validator.constraints.Email;
import org.springframework.data.jpa.domain.AbstractPersistable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@JsonIgnoreProperties(value = {"new"}, ignoreUnknown = true)
@Entity
@Table(name = "USER")
public class UserEntity extends AbstractPersistable<Long> {
    @NotNull
    @Email
    @Size(max = 1000)
    @Column(name = "EMAIL", nullable = false, length = 1000)
    private String email;
    @NotNull
    @Size(max = 255)
    @Column(name = "USERNAME", nullable = false, length = 255)
    private String username;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
