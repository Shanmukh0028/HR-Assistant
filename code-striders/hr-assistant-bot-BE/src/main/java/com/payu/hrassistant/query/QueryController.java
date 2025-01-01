package com.payu.hrassistant.query;

import com.payu.hrassistant.common.APIResponse;
import com.payu.hrassistant.query.dto.QueryDto;
import jakarta.websocket.server.PathParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/query")
public class QueryController {

    @Autowired
    QueryService queryService;

    @PostMapping("/create")
    ResponseEntity<?> postQuery(@RequestBody QueryDto queryRequest){
        return (ResponseEntity<?>)queryService.createQuery(queryRequest);
    }

    @GetMapping("/assigned")
    ResponseEntity<?> getAssignedQueries(@RequestParam Long hrId){
        return (ResponseEntity<?>) queryService.getAssignedQueries(hrId);
    }

    @GetMapping("/open")
    ResponseEntity<?> getOpenQueries(@RequestParam Long employeeId){
        return (ResponseEntity<?>) queryService.getOpenQueries(employeeId);
    }

    @GetMapping("/closed")
    ResponseEntity<?> getClosedQueries(@RequestParam Long employeeId){
        return (ResponseEntity<?>) queryService.getClosedQueries(employeeId);
    }

    @GetMapping("/detail/{queryId}")
    ResponseEntity<?> getQueryDetails(@PathVariable Long queryId){
        return (ResponseEntity<?>) queryService.getQueryDetails(queryId);
    }

    @PutMapping("/reassign/{queryId}")
    ResponseEntity<?> reassignQuery(@PathVariable Long queryId,@RequestParam Long newAssigneId){
        return (ResponseEntity<?>)queryService.reAssignQuery(queryId,newAssigneId);
    }

    @PutMapping("/{queryId}")
    ResponseEntity<?> markQueryResolved(@PathVariable Long queryId){
        return (ResponseEntity<?>) queryService.markQueryResolved(queryId);
    }
}
