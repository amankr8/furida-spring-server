package com.furidaweb.server.service.message;

import com.furidaweb.server.entity.Message;

import java.util.List;

public interface MessageService {

    List<Message> getAllMessages();

    Message getMessageById(int id);

    Message sendMessage(Message msg);

    Message toggleArchiveById(int id);

    void deleteMessageById(int id);

    void deleteAllMessages();
}
