package com.payu.hrassistant.chatbot;

import com.payu.hrassistant.chatbot.dto.BotRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
@RequestMapping("/bot")
public class ChatBotController {

    @PostMapping("/ask")
    public ResponseEntity<?> askBot(@RequestParam Long userId,@RequestBody BotRequest chatRequest){

        //Interacting with embedding model and RAG
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
