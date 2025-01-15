package com.payu.hrassistant.query;

import com.payu.hrassistant.common.APIResponse;
import com.payu.hrassistant.common.CommonUtil;
import com.payu.hrassistant.common.ErrorCodeConstants;
import com.payu.hrassistant.query.dto.ChatMessageInfo;
import com.payu.hrassistant.query.dto.QueryDetails;
import com.payu.hrassistant.query.dto.QueryDto;
import com.payu.hrassistant.query.dto.QueryInfo;
import com.payu.hrassistant.query.enums.QueryStatus;
import com.payu.hrassistant.query.model.ChatMessage;
import com.payu.hrassistant.query.model.Query;
import com.payu.hrassistant.usermanagement.dtos.UserInfo;
import com.payu.hrassistant.usermanagement.model.User;
import com.payu.hrassistant.usermanagement.repository.UserRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static com.payu.hrassistant.common.CommonUtil.buildErrorResponse;
import static com.payu.hrassistant.common.CommonUtil.convertDateToString;

@Service
@Log4j2
public class QueryService {

    @Autowired
    QueryRepository queryRepository;

    @Autowired
    UserRepository userRepository;

    //todo: need to add auditing

    public Object createQuery(QueryDto queryRequest){
        try {
            Query query = Query.builder()
                    .assignee(userRepository.findById(queryRequest.getAssigneeId()).orElse(null))
                    .createdBy(userRepository.findById(queryRequest.getCreatedById()).orElse(null))
                    .domain(queryRequest.getDomain())
                    .title(queryRequest.getTitle())
                    .question(queryRequest.getQuestion())
                    .status(QueryStatus.OPEN.name())
                    .createdAt(new Date())
                    .chats(new ArrayList<>())
                    .build();

//            Query query = createQueryFromRequest(queryRequest);

            queryRepository.save(query);

            //future case to implement logic to assign anyone
        }catch (Exception e){
            return buildErrorResponse(ErrorCodeConstants.INTERNAL_SERVER_ERROR_CODE,e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(HttpStatus.OK);
    }

    public Object getAssignedQueries(Long assigneeId){
        APIResponse resp = new APIResponse();

        List<Query> assignedQueries = queryRepository.findQueryByAssigneeId(assigneeId);
        if(assignedQueries.isEmpty()){
            log.info("No Assigned Queries for the hr {}",assigneeId);
            resp.setStatusMsg("No Assigned Queries");
            resp.setStatusCode(HttpStatus.NOT_FOUND.value());
            resp.setError("No Assigned Queries");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
        }
        List<QueryInfo> assignedQueriesInfo = new ArrayList<>();
        for(Query assignedQuery: assignedQueries){
            assignedQueriesInfo.add(QueryInfo.builder().Id(assignedQuery.getId())
                    .title(assignedQuery.getTitle())
                    .resolvedAt(convertDateToString(assignedQuery.getResolvedAt()))
                    .createdAt(convertDateToString(assignedQuery.getCreatedAt()))
                    .status(assignedQuery.getStatus()).build());

        }
        resp.setResponse(assignedQueriesInfo);
        resp.setStatusCode(HttpStatus.OK.value());
        resp.setStatusMsg("Success");

        return ResponseEntity.ok(resp);
    }

    public Object getOpenQueries(Long createdById){
        APIResponse resp = new APIResponse();

        List<Query> openQueries = queryRepository.findOpenQueriesOfEmployee(createdById);
        if(openQueries.isEmpty()){
            log.info("No Opened Queries for the user {}",createdById);
            resp.setStatusMsg("No Open Queries");
            resp.setStatusCode(HttpStatus.NOT_FOUND.value());
            resp.setError("No Open Queries");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
        }

        List<QueryInfo> openQueriesInfo = new ArrayList<>();
        for(Query openQuery: openQueries){
            openQueriesInfo.add(QueryInfo.builder().Id(openQuery.getId())
                    .title(openQuery.getTitle())
                    .resolvedAt(convertDateToString(openQuery.getResolvedAt()))
                    .createdAt(convertDateToString(openQuery.getCreatedAt()))
                    .status(openQuery.getStatus()).build());

        }

        resp.setResponse(openQueriesInfo);
        resp.setStatusCode(HttpStatus.OK.value());
        resp.setStatusMsg("Success");

        return ResponseEntity.ok(resp);
    }

    public Object getClosedQueries(Long createdById){
        APIResponse resp = new APIResponse();

        List<Query> closedQueries = queryRepository.findClosedQueriesOfEmployee(createdById);
        if(closedQueries.isEmpty()){
            log.info("No Closed Queries for the user {}",createdById);
            resp.setStatusMsg("No Closed Queries");
            resp.setStatusCode(HttpStatus.NOT_FOUND.value());
            resp.setError("No Closed Queries");
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(resp);
        }

        List<QueryInfo> closedQueriesInfo = new ArrayList<>();
        for(Query openQuery: closedQueries){
            closedQueriesInfo.add(QueryInfo.builder().Id(openQuery.getId())
                    .title(openQuery.getTitle())
                    .resolvedAt(convertDateToString(openQuery.getResolvedAt()))
                    .createdAt(convertDateToString(openQuery.getCreatedAt()))
                    .status(openQuery.getStatus()).build());
        }

        resp.setResponse(closedQueriesInfo);
        resp.setStatusCode(HttpStatus.OK.value());
        resp.setStatusMsg("Success");

        return ResponseEntity.ok(resp);
    }

    public Object acceptQuery(String hrId,String queryId){
        //check if he is hr

        //send a message via socket to user
        return null;
    }

    public Object getQueryDetails(Long queryId) {
        Optional<Query> query = queryRepository.findById(queryId);
        if(query.isEmpty()){
            return CommonUtil.buildErrorResponse(ErrorCodeConstants.NOT_FOUND,"Query Not Found",HttpStatus.NOT_FOUND );
        }

        QueryDetails queryDetails = createQueryDetails(query.get());

        return ResponseEntity.ok(queryDetails);
    }

    public Object reAssignQuery(Long queryId, Long newAssigneId) {
        Optional<Query> query = queryRepository.findById(queryId);
        if(query.isEmpty()){
            return CommonUtil.buildErrorResponse(ErrorCodeConstants.NOT_FOUND,"Query Not Found",HttpStatus.NOT_FOUND );
        }
        if(query.get().getStatus().equals("CLOSED")){
            return CommonUtil.buildErrorResponse(ErrorCodeConstants.BAD_REQUEST,"Already closed queries can not be re-assigned",HttpStatus.BAD_REQUEST );
        }
        try {
            Optional<User> newAssignee = userRepository.findById(newAssigneId);
            if(newAssignee.isEmpty()){
                return CommonUtil.buildErrorResponse(ErrorCodeConstants.NOT_FOUND,"Not A Valid new Assignee",HttpStatus.NOT_FOUND );
            }

            query.get().setAssignee(userRepository.findById(newAssigneId).get());

            queryRepository.save(query.get());
        }catch (Exception e){
            return CommonUtil.buildErrorResponse(ErrorCodeConstants.INTERNAL_SERVER_ERROR_CODE,e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return ResponseEntity.ok("Success");
    }

    public Object markQueryResolved(Long queryId) {
        Optional<Query> query = queryRepository.findById(queryId);
        if(query.isEmpty()){
            return CommonUtil.buildErrorResponse(ErrorCodeConstants.NOT_FOUND,"Query Not Found",HttpStatus.NOT_FOUND );
        }

        try {
            query.get().setStatus(QueryStatus.CLOSED.name());
            query.get().setResolvedAt(new Date());

            queryRepository.save(query.get());
        }catch (Exception e){
            return CommonUtil.buildErrorResponse(ErrorCodeConstants.INTERNAL_SERVER_ERROR_CODE,e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

        //todo: have to close the websocket
        return ResponseEntity.ok("Success");
    }

    public QueryDetails createQueryDetails(Query query){
        UserInfo assigneeInfo = UserInfo.builder()
                .username(query.getAssignee().getUsername())
                .id(query.getAssignee().getId())
                .build();

        UserInfo createeInfo = UserInfo.builder()
                .id(query.getCreatedBy().getId())
                .username(query.getCreatedBy().getUsername())
                .build();

        List<ChatMessageInfo> chatMessageInfos = new ArrayList<>();

        for(ChatMessage chatMessage: query.getChats()){
            chatMessageInfos.add(createChatMessageInfoObject(chatMessage));
        }

        QueryDetails details = QueryDetails.builder()
                .id(query.getId())
                .domain(query.getDomain())
                .resolvedAt(convertDateToString(query.getResolvedAt()))
                .createdAt(convertDateToString(query.getCreatedAt()))
                .title(query.getTitle())
                .status(query.getStatus())
                .chatMessagesInfo(chatMessageInfos)
                .assignee(assigneeInfo)
                .createdBy(createeInfo)
                .build();

        return details;
    }

    public ChatMessageInfo createChatMessageInfoObject(ChatMessage chatMessage){
        return ChatMessageInfo.builder()
                .sender(chatMessage.getSender().getUsername())
                .sentAt(convertDateToString(chatMessage.getSentAt()))
                .message(chatMessage.getMessage())
                .build();
    }

    public Query createQueryFromRequest(QueryDto queryRequest) {
        // Create a new Query instance
        Query query = new Query();

        // Set properties using setters
        query.setTitle(queryRequest.getTitle());
        query.setQuestion(queryRequest.getQuestion());
        query.setDomain(queryRequest.getDomain());
        query.setStatus(QueryStatus.OPEN.name()); // Assuming QueryStatus is an enum
        query.setCreatedBy(userRepository.findById(queryRequest.getCreatedById()).get()); // Fetching the User
        query.setAssignee(userRepository.findById(queryRequest.getAssigneeId()).get()); // Fetching the User
        query.setCreatedAt(new Date()); // Set current date and time
        query.setResolvedAt(null); // Set to null or a specific date if applicable
        query.setChats(new ArrayList<>()); // Initialize with an empty list

        return query; // Return the constructed Query object
    }


}

