package com.payu.hrassistant.query.model;


import com.payu.hrassistant.usermanagement.model.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Entity
@Table(name = "queries")
@Data
@Builder
@AllArgsConstructor
public class Query {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String question;

    @Column(nullable = false)
    private String title; // The title or summary of the query

    @Column(name="query_domain",nullable = false)
    private String domain; // e.g., Finance, Insurance, etc.

    @Column(nullable = false)
    private String status; // OPEN or CLOSED

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "created_by", nullable = false)
    private User createdBy; // Employee who created the query

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignee_id")
    private User assignee; // HR handling the query

    @OneToMany(mappedBy = "query", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ChatMessage> chats; // All chat messages related to the query

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(nullable = true)
    private Date resolvedAt;

//     Constructors, Getters, and Setters
    public Query() {
    }

//    public Query(String title, String domain, String status, User createdBy, User assignee, Date createdAt,List<ChatMessage> chats) {
//        this.title = title;
//        this.domain = domain;
//        this.status = status;
//        this.createdBy = createdBy;
//        this.assignee = assignee;
//        this.createdAt = createdAt;
//        this.chats = chats;
//    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public User getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(User createdBy) {
        this.createdBy = createdBy;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public List<ChatMessage> getChats() {
        return chats;
    }

    public void setChats(List<ChatMessage> chats) {
        this.chats = chats;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getResolvedAt() {
        return resolvedAt;
    }

    public void setResolvedAt(Date resolvedAt) {
        this.resolvedAt = resolvedAt;
    }
}
