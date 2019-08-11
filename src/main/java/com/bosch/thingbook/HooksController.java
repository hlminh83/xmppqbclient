package com.bosch.thingbook;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
/**
 * Websocket handler
 */
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class HooksController {

//	@Autowired 
//	MessageForwarder forwarder ; 
	
	List<String> messages = new ArrayList<String>();
	@Autowired
	SimpMessagingTemplate template; 
	
    @MessageMapping("/publicaccount")
    @SendTo("/publicaccount/chat")
    public String greeting(String message) throws Exception {
    	return message;
    }
    
//    @MessageMapping("/draw")
//    @SendTo("/draw/getback")
    @PostMapping(path="/sendMessage")
    @ResponseBody
    public String postMessage(@RequestBody String message) throws Exception {
    	this.template.convertAndSend("/publicaccount/chat", message);
    	return message;
    }
    
    
}
