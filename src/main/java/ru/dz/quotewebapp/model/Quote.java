package ru.dz.quotewebapp.model;

import javax.validation.constraints.Size;
import javax.validation.Valid;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.PrePersist;
import javax.persistence.OneToOne;
import javax.persistence.JoinColumn;

import java.sql.Timestamp;
import java.util.Date;

@Entity
@Table(name = "quote")
public class Quote {

    @Id
    @Column(name = "quote_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Size(min = 1, message = "Please insert at least 1 characters")
    @Column(name = "content")
    private String content;

    @Column(name = "created_at", updatable = false)
    private Timestamp createdAt;

    @Valid
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private Timestamp getCurrentTimeStamp() {
        return new Timestamp(new Date().getTime());
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Timestamp createdAt) {
        this.createdAt = createdAt;
    }


    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @PrePersist
    protected void onCreate() {
        createdAt = this.getCurrentTimeStamp();
    }

    @Override
    public String toString() {
        return "Quote [id=" + this.getId() + ", user_name=" + this.getUser().getUserName() + ", content=" + this.getContent() + ", created_at = " + this.getCreatedAt() + "]";
    }

}
