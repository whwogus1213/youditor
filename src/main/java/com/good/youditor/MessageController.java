package com.good.youditor;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.dto.MessageVO;
import com.good.dto.AccountsVO;
import com.good.dto.MessageList;
import com.good.dto.MessageSearch;
import com.good.service.MessageService;

@Controller
@RequestMapping("/message")
public class MessageController {
	
	@Inject
	MessageService service;
	
	// 받은 메세지 목록 + 페이징 + 검색
	@RequestMapping(value = "/messageReceiveList", method = RequestMethod.GET)
	public String receiveList(Model model, @RequestParam(required = false, defaultValue = "1") int page,
									@RequestParam(required = false, defaultValue = "1") int range,
									@RequestParam(required = false, defaultValue = "object") String searchType,
									@RequestParam(required = false) String keyword,
									HttpSession session) throws Exception {
		MessageSearch search = new MessageSearch();
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		AccountsVO vo = (AccountsVO) session.getAttribute("login");
		search.setAccountId(vo.getAccountId());
		List<MessageList> messageReceiveList = service.receiveListAll(search);
		
		// 전체 게시글 개수
		int listCnt = service.getReceiveListCnt(search);
		
		search.pageInfo(page, range, listCnt);

		model.addAttribute("pagination", search);
		model.addAttribute("MessageReceiveList", messageReceiveList);
		System.out.println("MessageController MessageReceiveList open");
		return "message/messageReceiveList";
	}
	
	// 보낸 메세지 목록 + 페이징 + 검색
	@RequestMapping(value = "/messageSendList", method = RequestMethod.GET)
	public String sendList(Model model, @RequestParam(required = false, defaultValue = "1") int page,
									@RequestParam(required = false, defaultValue = "1") int range,
									@RequestParam(required = false, defaultValue = "object") String searchType,
									@RequestParam(required = false) String keyword,
									HttpSession session) throws Exception {
		MessageSearch search = new MessageSearch();
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		AccountsVO vo = (AccountsVO) session.getAttribute("login");
		search.setAccountId(vo.getAccountId());
		List<MessageList> messageSendList = service.sendListAll(search);
		// 전체 게시글 개수
		int listCnt = service.getSendListCnt(search);
		
		search.pageInfo(page, range, listCnt);

		model.addAttribute("pagination", search);
		model.addAttribute("MessageSendList", messageSendList);
		System.out.println("MessageController MessageSendList open");
		return "message/messageSendList";
	}

	// 받은 메시지 읽기
	@RequestMapping(value = "/messageReceiveView", method = RequestMethod.GET)
	public String receiveMessageView(Model model, @RequestParam("messageId") int messageId) throws Exception {
		System.out.println("*************************************************");
		MessageList rMessage = service.receiveMessageView(messageId);
		if(rMessage.getRead_date() == null) {
			service.updateReadDate(messageId);
			rMessage = service.receiveMessageView(messageId);
		}

		model.addAttribute("rMessage", rMessage);
		System.out.println("MessageController messageReceiveView open");
		return "message/messageReceiveView";
	}

	// 보낸 메시지 읽기
	@RequestMapping(value = "/messageSendView", method = RequestMethod.GET)
	public String sendMessageView(Model model, @RequestParam("messageId") int messageId) throws Exception {
		System.out.println("*************************************************");
		MessageList rMessage = service.sendMessageView(messageId);

		model.addAttribute("rMessage", rMessage);
		System.out.println("MessageController messageSendView open");
		return "message/messageSendView";
	}
	
	// 메시지 쓰기
	@RequestMapping(value = "/write.do")
	public String writedo() throws Exception {
		System.out.println("*************************************************");
		
		System.out.println("MessageController messageWrite open");
		return "message/messageWrite";
	}
	
	// 메시지 답장 쓰기
	@RequestMapping(value = "/reply.do", method = RequestMethod.GET)
	public String replydo(Model model, @RequestParam("messageId") int messageId) throws Exception {
		System.out.println("*************************************************");
		// messageId로 nickname 추출, subject & object 추출
		MessageList replyInfo = service.getReplyInfo(messageId);
		
		model.addAttribute("replyInfo", replyInfo);
		System.out.println("MessageController messageWrite open");
		return "message/messageWrite";
	}
	
	// 메시지 보내기
	@ResponseBody
	@RequestMapping(value = "/sendMessagePro", method = RequestMethod.POST)
	public int sendMessagePro(@RequestParam(value="senderAccountId") String senderAccountId,
								@RequestParam(value="nickname") String nickname,
								@RequestParam(value="subject") String subject,
								@RequestParam(value="object") String object) throws Exception {
		System.out.println(senderAccountId + ", " + nickname + ", " + subject + ", " + object);
		
		int result = 0;
		
		int count = 0;
		count = service.checkNickname(nickname);
		
		if(count >= 1) {
			int receiverAccountId = service.getAccountIdByNickname(nickname);
			MessageVO vo = new MessageList();
			vo.setSenderAccountId(Integer.parseInt(senderAccountId));
			vo.setReceiverAccountId(receiverAccountId);
			vo.setSubject(subject);
			vo.setObject(object);
			service.sendMessage(vo);
			
			result = 1;
		}
		
		return result;
	}
	
	// 받은 메시지 숨기기
	@ResponseBody
	@RequestMapping(value="/hideReceivedMessage", method=RequestMethod.POST)
	public int hideReceivedMessage(@RequestParam(value = "chBox[]") List<String> chArr) throws Exception {
		int result = 0;
		
		for(String i : chArr) {
			int mId = Integer.parseInt(i);
			
			service.hideReceivedMessage(mId);
			
			result = 1;
		}
		
		return result;
	}
	
	// 보낸 메시지 숨기기
	@ResponseBody
	@RequestMapping(value="/hideSendMessage", method=RequestMethod.POST)
	public int hideSendMessage(@RequestParam(value = "chBox[]") List<String> chArr) throws Exception {
		int result = 0;
		
		for(String i : chArr) {
			int mId = Integer.parseInt(i);
			
			service.hideSendMessage(mId);
			
			result = 1;
		}
		
		return result;
	}
	
	// 메세지 팝업 쓰기
	@RequestMapping(value="/writePopup.do", method=RequestMethod.GET)
	String replydo(Model model, @RequestParam("nickname") String nickname) throws Exception {
		System.out.println("*************************************************");
		model.addAttribute("to", nickname);
		System.out.println("MessageController messageWritePopup open");
		return "message/messageWritePopup";
	}
}
