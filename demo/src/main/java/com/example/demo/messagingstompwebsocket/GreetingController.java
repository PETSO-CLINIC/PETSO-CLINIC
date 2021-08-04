package com.example.demo.messagingstompwebsocket;

import com.example.demo.infrastructure.AccountRepository;
import com.example.demo.infrastructure.GeneralChatRepository;
import com.example.demo.model.Account;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.util.HtmlUtils;

import java.security.Principal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


@Controller
public class GreetingController {
    @Autowired
    AccountRepository accountRepository;

    @Autowired
    GeneralChatRepository generalChatRepository;

    @MessageMapping("/hello")
    @SendTo("/topic/greetings")
    public GeneralChat greeting(HelloMessage message, Principal p ) throws Exception {
        Thread.sleep(1000);

        GeneralChat greeting = new GeneralChat();
        greeting.setContent(message.getName());
        generalChatRepository.save(greeting);

        Account account = accountRepository.getUserByUsername(p.getName());
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter format = DateTimeFormatter.ofPattern(" dd-MM-yyyy HH:mm");
        String formatDateTime = now.format(format);

        return new GeneralChat(  account + ",  "+ formatDateTime+ " : " + HtmlUtils.htmlEscape(message.getName()));

    }


    @GetMapping("/GeneralChat")
    public String messagePage(Principal p, Model model){
        Account account = accountRepository.getUserByUsername(p.getName());

        model.addAttribute("AccountName", account);

        return "GeneralLivechat";
    }
}
