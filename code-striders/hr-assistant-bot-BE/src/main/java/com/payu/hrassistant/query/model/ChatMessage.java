package com.payu.hrassistant.query.model;

import com.payu.hrassistant.usermanagement.model.User;
import jakarta.persistence.*;

import java.util.Date;
 
@Entity
@Table(name = "chats")
public class ChatMessage {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "query_id", nullable = false)
    private Query query; // The query this chat is associated with
 
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sender_id", nullable = false)
    private User sender; // The user who sent the message (either Employee or HR)
 
    @Column(nullable = false)
    private String message; // The chat message content
 
    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date sentAt; // Timestamp when the message was sent
 
    // Constructors, Getters, and Setters
    public ChatMessage() {
    }
 
    public ChatMessage(Query query, User sender, String message, Date sentAt) {
        this.query = query;
        this.sender = sender;
        this.message = message;
        this.sentAt = sentAt;
    }
 
    public Long getId() {
        return id;
    }
 
    public void setId(Long id) {
this.id = id;
    }
 
    public Query getQuery() {
        return query;
    }
 
    public void setQuery(Query query) {
        this.query = query;
    }
 
    public User getSender() {
        return sender;
    }
 
    public void setSender(User sender) {
        this.sender = sender;
    }
 
    public String getMessage() {
        return message;
    }
 
    public void setMessage(String message) {
        this.message = message;
    }
 
    public Date getSentAt() {
        return sentAt;
    }
 
    public void setSentAt(Date sentAt) {
        this.sentAt = sentAt;
    }
}