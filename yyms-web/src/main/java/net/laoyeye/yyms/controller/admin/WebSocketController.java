package net.laoyeye.yyms.controller.admin;

import lombok.extern.slf4j.Slf4j;
import net.laoyeye.yyms.pojo.vo.ClientMessage;
import net.laoyeye.yyms.pojo.vo.ResponseMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.messaging.simp.annotation.SubscribeMapping;
import org.springframework.stereotype.Controller;

@Slf4j
@Controller
public class WebSocketController {
	@Autowired
	SimpMessagingTemplate template;

	@MessageMapping("/send") // 浏览器发送请求通过@messageMapping 映射这个地址。
	@SendTo("/topic/response") // 服务器端有消息时,会订阅@SendTo 中的路径的浏览器发送消息。
	public ResponseMessage say(ClientMessage message) throws Exception {
		Thread.sleep(1000);
		return new ResponseMessage("接收到, " + message.getNoticeTitle() + "!");
	}

	@SubscribeMapping("/subscribe")
	public ResponseMessage sub() {
		log.info("XXX用户订阅了我。。。");
		return new ResponseMessage("感谢你订阅了我。。。");
	}

}