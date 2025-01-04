package com.furidaweb.server.controller;

import com.furidaweb.server.entity.Message;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/api/messages")
public interface MessageController {

    @GetMapping
    ResponseEntity<?> getAllMessages();

    @GetMapping("/{id}")
    ResponseEntity<?> getMessage(@PathVariable int id);

    @PostMapping
    ResponseEntity<?> sendMessage(@RequestBody Message msg);

    @PutMapping("/{id}")
    ResponseEntity<?> toggleArchive(@PathVariable int id);

    @DeleteMapping("/{id}")
    ResponseEntity<?> deleteMessage(@PathVariable int id);

    @DeleteMapping
    ResponseEntity<?> deleteAllMessages();
}
