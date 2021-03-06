package com.good.youditor;

import java.util.List;
import java.util.Locale;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.good.dto.AccountsVO;
import com.good.dto.NoticeBoardVO;
import com.good.dto.NoticeCategoryVO;
import com.good.dto.RecruitCategoryVO;
import com.good.dto.SearchBoard;
import com.good.dto.TipCategoryVO;
import com.good.dto.VideoCategoryVO;
import com.good.service.HomeService;
import com.good.service.NoticeBoardService;

@Controller
@RequestMapping("/noticeboard")
public class NoticeBoardController {

	@Inject
	NoticeBoardService noticeBoardService;
	
	@Inject
	HomeService homeService;

	// 게시물 목록 + 페이징 + 검색
	@RequestMapping(value = "/noticeBoardList", method = RequestMethod.GET)
	public String list(Model model, HttpSession session,
						@RequestParam(required = false, defaultValue = "0") int categoryId,
						@RequestParam(required = false, defaultValue = "1") int page,
						@RequestParam(required = false, defaultValue = "object") String searchType,
						@RequestParam(required = false) String keyword) throws Exception {
		SearchBoard search = new SearchBoard();
		search.setSearchType(searchType);
		search.setKeyword(keyword);
		search.setCategoryId(categoryId);
		
		// 전체 게시글 개수
		int listCnt = noticeBoardService.getBoardListCnt(search);
		
		System.out.println(" listCnt : " + listCnt);
		System.out.println(" categoryId : " + categoryId);
		
		int rangeSize = search.getRangeSize();
		int range = ((page - 1) / rangeSize) + 1;
		
		search.pageInfo(page, range, listCnt);
		
		NoticeCategoryVO nCatVO = new NoticeCategoryVO();
		if(categoryId != 0) {
			System.out.println(" 카테고리 정보 취득 ");
			nCatVO = noticeBoardService.getCatInfo(categoryId);
			System.out.println(" VideoCategoryVO : " + nCatVO);
		} else {
			nCatVO.setCategoryName("전체공지");
			nCatVO.setCategoryPicture("all.jpg");
			nCatVO.setEditAuthority(4);
			nCatVO.setViewAuthority(3);
		}

		List<NoticeCategoryVO> nCatList = homeService.bringNoticeCategory();
		List<VideoCategoryVO> vCatList = homeService.bringVideoCategory();
		List<TipCategoryVO> tCatList = homeService.bringTipCategory();
		List<RecruitCategoryVO> rCatList = homeService.bringRecruitCategory();
		
		session.setAttribute("nCatList", nCatList);
		session.setAttribute("vCatList", vCatList);
		session.setAttribute("tCatList", tCatList);
		session.setAttribute("rCatList", rCatList);

		AccountsVO login = (AccountsVO) session.getAttribute("login");
		
		if(login != null) {
			int accountId = login.getAccountId();
			
			session.setAttribute("mCount", homeService.newMessageCnt(accountId));
			session.setAttribute("fCount", homeService.newFollowerCnt(accountId));
		}
		
		model.addAttribute("pagination", search);
		model.addAttribute("categoryInfo", nCatVO);
		model.addAttribute("NoticeBoardList", noticeBoardService.listAll(search));
		System.out.println("NoticeBoardController NoticeBoardList open");
		return "noticeboard/noticeBoardList";
	}

	// 게시물 상세정보
	@RequestMapping(value = "/noticeBoardView", method = RequestMethod.GET)
	public ModelAndView view(HttpSession session, @RequestParam("boardId") int boardId) throws Exception {
		System.out.println("*************************************************");
		NoticeBoardVO row = noticeBoardService.view(boardId);
		
		List<NoticeCategoryVO> nCatList = homeService.bringNoticeCategory();
		List<VideoCategoryVO> vCatList = homeService.bringVideoCategory();
		List<TipCategoryVO> tCatList = homeService.bringTipCategory();
		List<RecruitCategoryVO> rCatList = homeService.bringRecruitCategory();
		
		session.setAttribute("nCatList", nCatList);
		session.setAttribute("vCatList", vCatList);
		session.setAttribute("tCatList", tCatList);
		session.setAttribute("rCatList", rCatList);

		AccountsVO login = (AccountsVO) session.getAttribute("login");
		
		if(login != null) {
			int accountId = login.getAccountId();
			
			session.setAttribute("mCount", homeService.newMessageCnt(accountId));
			session.setAttribute("fCount", homeService.newFollowerCnt(accountId));
		}
		
		ModelAndView mav = new ModelAndView();
		mav.setViewName("noticeboard/noticeBoardView");
		mav.addObject("row", row);
		System.out.println("NoticeBoardController boardView open");
		return mav;
	}

	// 게시물 쓰기
	@RequestMapping(value = "/write.do", method = RequestMethod.GET)
	public String writedo(HttpSession session) throws Exception {
		System.out.println("*************************************************");

		String result = "";
		
		AccountsVO login = (AccountsVO) session.getAttribute("login");
		
		if(4 <= login.getAuthority()) {
			result = "noticeboard/noticeBoardWrite";
			System.out.println("NoticeBoardController boardWrite open");
		} else {
			result = "noticeboard/noticeBoardList";
		}
		
		return result; 
	}


	// 글작성 완료
	@RequestMapping(value = "/insertNoticeBoardPro", method = RequestMethod.POST)
	public String insertNoticeBoardPro(@RequestParam("accountId") int accountId,
										@RequestParam("categoryId") int categoryId,
										@RequestParam("subject") String subject,
										@RequestParam("object") String object,
										HttpSession session) throws Exception {
		String result = "";
		
		AccountsVO login = (AccountsVO) session.getAttribute("login");
		
		if(4 <= login.getAuthority()) {
			System.out.println("============insertNoticeBoardPro 성공==============");
			NoticeBoardVO vo = new NoticeBoardVO();
			vo.setAccountId(accountId);
			vo.setCategoryId(categoryId);
			vo.setSubject(subject);
			vo.setObject(object);
			
			noticeBoardService.insertNoticeBoard(vo);
			
			result = "redirect:/noticeboard/noticeBoardView?boardId=" + vo.getBoardId();
		} else {
			result = "redirect:/noticeboard/noticeBoardList";
		}
		
		return result;

	}
	// 글 수정
	@RequestMapping(value = "/updateNoticeBoardPro", method = RequestMethod.POST)
	public String updateNoticeBoardPro(@RequestParam("boardId") int boardId,
										@RequestParam("categoryId") int categoryId,
										@RequestParam("subject") String subject,
										@RequestParam("object") String object,
										HttpSession session) throws Exception {
		String result = "";
		
		AccountsVO login = (AccountsVO) session.getAttribute("login");
		int auth = noticeBoardService.getEditAuth(boardId);
		
		if(auth <= login.getAuthority()) {
			NoticeBoardVO vo = new NoticeBoardVO();
			vo.setBoardId(boardId);
			vo.setCategoryId(categoryId);
			vo.setSubject(subject);
			vo.setObject(object);
			
			noticeBoardService.updateNoticeBoard(vo);
			result = "redirect:/noticeboard/noticeBoardView?boardId=" + vo.getBoardId();
			System.out.println("============ updateNoticeBoard 성공 ==============");
		} else {
			System.out.println("============ 권한없음 ==============");
			result = "redirect:/noticeboard/noticeBoardList";
		}
		
		return result;
	}
	
	// 파일 이동
	// 게시글 수정
	@RequestMapping(value = "/update.do", method = RequestMethod.GET)
	public String joinUpdate(Locale locale, Model model, HttpSession session,
							@RequestParam("boardId") int boardId) throws Exception {
		String result = "";
		
		AccountsVO login = (AccountsVO) session.getAttribute("login");
		int auth = noticeBoardService.getEditAuth(boardId);
		
		if(auth <= login.getAuthority()) {
			NoticeBoardVO row = noticeBoardService.view(boardId);
			
			model.addAttribute("row", row);
			
			result = "noticeboard/noticeBoardWrite";
			System.out.println("String noticeBoardUpdate open");
		} else {
			result = "redirect:/noticeboard/noticeBoardList";
			System.out.println("권한없음 리스트로 돌아감");
		}
		return result;
	}

	// 글 삭제
	@RequestMapping(value = "/deleteNoticeBoardPro")
	public String deleteNoticeBoardPro(NoticeBoardVO vo, RedirectAttributes rttr, HttpSession session) throws Exception {
		AccountsVO login = (AccountsVO) session.getAttribute("login");
		int auth = noticeBoardService.getEditAuth(vo.getBoardId());
		
		if(auth <= login.getAuthority()) {
			noticeBoardService.deleteNoticeBoard(vo);
			rttr.addFlashAttribute("result","deleteOK");
			System.out.println("============ deleteVideoBoard 성공==============");
		} else {
			System.out.println("권한없음 리스트로 돌아감");
		}

		return "redirect:/noticeboard/noticeBoardList";
	}
	
	@ResponseBody
	@RequestMapping(value = "/newNotice", method = {RequestMethod.GET,RequestMethod.POST})
	public List<NoticeBoardVO> rankList() throws Exception {
		System.out.println("Start videostar List");
		List<NoticeBoardVO> rankList = noticeBoardService.rankList();
		System.out.println("List : " + rankList);
		return rankList;
	}
}
