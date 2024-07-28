package com.example.notification;


import org.springframework.beans.factory.annotation.Autowired;//Контроллер получает сообщение от отправителя и передаёт их получателю
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

@Controller
public class MessageController {

    @Autowired
    SimpMessagingTemplate simpMessagingTemplate;// объект облегчает отправку сообщений через вебсокет

    @MessageMapping("/application")//метод send будет обрабатывать сообщения отправленные по данной ссылке
    @SendTo("/all/messages")//возвращаемое значение метода send будет отправлено юзерам из данной ссылки(канала)

    public Message send(final Message message) throws Exception{//метод обрабатывает сообщения отправленные с /application и возвращает их юзерам из /all/messages
        return message;
    }

    @MessageMapping("/private")

    public void sendToSpecificUser(@Payload Message message){//метод обрабатывает сообщения отправленные на адрес /private
        simpMessagingTemplate.convertAndSendToUser(message.getTo(), "/specific", message);//метод для отправки на /specific сообщения пользователю указанному в To
    }
}

//Резюмируя, этот контроллер обрабатывает два типа WebSocket-сообщений:
//
//1. Сообщения, отправленные на адрес /application, будут возвращены всем подписанным клиентам, слушающим адрес /all/messages.
//2. Сообщения, отправленные на адрес /private, будут отправлены только конкретному пользователю, указанному в поле to объекта Message, на адрес /specific.
