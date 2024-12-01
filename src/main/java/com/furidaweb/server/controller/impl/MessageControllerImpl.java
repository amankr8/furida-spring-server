package com.furidaweb.server.controller.impl;

import com.furidaweb.server.controller.MessageController;
import com.furidaweb.server.dto.StatusResponse;
import com.furidaweb.server.entity.Message;
import com.furidaweb.server.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class MessageControllerImpl implements MessageController {

    @Autowired
    private final MessageService messageService;

    @Override
    public ResponseEntity<?> getAllMessages() {
        return ResponseEntity.ok(messageService.getAllMessages());
    }

    @Override
    public ResponseEntity<?> getMessage(int id) {
        return ResponseEntity.ok(messageService.getMessageById(id));
    }

    @Override
    public ResponseEntity<?> sendMessage(Message msg) {
        return ResponseEntity.ok(messageService.sendMessage(msg));
    }

    @Override
    public ResponseEntity<?> changeReadStatus(int id) {
        return ResponseEntity.ok(messageService.toggleReadStatusById(id));
    }

    @Override
    public ResponseEntity<?> deleteMessage(int id) {
        messageService.deleteMessageById(id);
        return ResponseEntity.ok(new StatusResponse(HttpStatus.OK.value(), "Message deleted successfully!"));
    }

    @Override
    public ResponseEntity<?> deleteAllMessages() {
        messageService.deleteAllMessages();
        return ResponseEntity.ok(new StatusResponse(HttpStatus.OK.value(), "All messages deleted successfully!"));
    }
}
