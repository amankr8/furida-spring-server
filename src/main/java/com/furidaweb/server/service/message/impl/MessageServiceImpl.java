package com.furidaweb.server.service.message.impl;

import com.furidaweb.server.entity.Message;
import com.furidaweb.server.exception.ResourceNotFoundException;
import com.furidaweb.server.repository.MessageRepository;
import com.furidaweb.server.service.message.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MessageServiceImpl implements MessageService {

    @Autowired
    private final MessageRepository messageRepository;

    @Override
    public List<Message> getAllMessages() {
        return messageRepository.findAll().stream()
                .sorted((m1, m2) -> m2.getSendDate().compareTo(m1.getSendDate()))
                .toList();
    }

    @Override
    public Message getMessageById(int id) {
        return messageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found"));
    }

    @Override
    public Message sendMessage(Message msg) {
        msg.setSendDate(new Date());
        msg.setArchive(false);

        return messageRepository.save(msg);
    }

    @Override
    public Message toggleArchiveById(int id) {
        Message msg = messageRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Message not found"));
        msg.setArchive(!msg.getArchive());

        return messageRepository.save(msg);
    }

    @Override
    public void deleteMessageById(int id) {
        messageRepository.deleteById(id);
    }

    @Override
    public void deleteAllMessages() {
        messageRepository.deleteAll();
    }
}
