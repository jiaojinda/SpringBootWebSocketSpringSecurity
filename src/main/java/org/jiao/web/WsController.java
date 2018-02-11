package org.jiao.web;

import org.jiao.vo.RequestMessage;
import org.jiao.vo.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;

import java.security.Principal;

/**
 * Created by Administrator on 2018/2/9.
 */
@Controller
public class WsController {
    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @MessageMapping("/chat")
    public void handleChat(Principal principal, String msg) {
        if (principal.getName().equals("xiaoming")) {
            messagingTemplate.convertAndSendToUser("daming", "/queue/notifications", principal.getName() + "：" + msg);
        }else if(principal.getName().equals("daming")){
            messagingTemplate.convertAndSendToUser("xiaoming", "/queue/notifications", principal.getName() + "：" + msg);
        }
    }
}