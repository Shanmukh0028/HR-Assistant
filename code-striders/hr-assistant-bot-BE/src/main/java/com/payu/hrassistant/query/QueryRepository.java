package com.payu.hrassistant.query;


import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QueryRepository extends JpaRepository<com.payu.hrassistant.query.model.Query,Long> {
    @Query("SELECT q FROM Query q WHERE q.assignee.id = :assigneeId")
    List<com.payu.hrassistant.query.model.Query> findQueryByAssigneeId(@Param("assigneeId") Long assigneeId);

    @Query("SELECT q From Query q where q.createdBy.id = :createdById and q.status = 'OPEN'")
    List<com.payu.hrassistant.query.model.Query> findOpenQueriesOfEmployee(@Param("createdById") Long createdById);

    @Query("SELECT q From Query q where q.createdBy.id = :createdById and q.status = 'CLOSED'")
    List<com.payu.hrassistant.query.model.Query> findClosedQueriesOfEmployee(@Param("createdById") Long createdById);
}
