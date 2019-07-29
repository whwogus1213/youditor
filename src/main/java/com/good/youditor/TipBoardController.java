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

import com.good.dto.NoticeCategoryVO;
import com.good.dto.RecruitCategoryVO;
import com.good.dto.SearchBoard;
import com.good.dto.TipBoardVO;
import com.good.dto.TipCategoryVO;
import com.good.dto.VideoCategoryVO;
import com.good.service.HomeService;
import com.good.service.TipBoardService;

@Controller
@RequestMapping("/tipboard")
public class TipBoardController {

	@Inject
	TipBoardService tipBoardService;
	
	@Inject
	HomeService homeService;

	// 게시물 목록 + 페이징 + 검색
	@RequestMapping(value = "/tipBoardList", method = RequestMethod.GET)
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
		int listCnt = tipBoardService.getBoardListCnt(search);

		System.out.println(" listCnt : " + listCnt);
		System.out.println(" categoryId : " + categoryId);
		
		int rangeSize = search.getRangeSize();
		int range = ((page - 1) / rangeSize) + 1;
		
		search.pageInfo(page, range, listCnt);
		
		TipCategoryVO tCatVO = new TipCategoryVO();
		if(categoryId != 0) {
			System.out.println(" 카테고리 정보 취득 ");
			tCatVO = tipBoardService.getCatInfo(categoryId);
			System.out.println(" TipCategoryVO : " + tCatVO);
		} else {
			tCatVO.setCategoryName("전체 팁");
			tCatVO.setEditAuthority(4);
			tCatVO.setViewAuthority(3);
		}
		
		List<NoticeCategoryVO> nCatList = homeService.bringNoticeCategory();
		List<VideoCategoryVO> vCatList = homeService.bringVideoCategory();
		List<TipCategoryVO> tCatList = homeService.bringTipCategory();
		List<RecruitCategoryVO> rCatList = homeService.bringRecruitCategory();
		
		session.setAttribute("nCatList", nCatList);
		session.setAttribute("vCatList", vCatList);
		session.setAttribute("tCatList", tCatList);
		session.setAttribute("rCatList", rCatList);

		model.addAttribute("pagination", search);
		model.addAttribute("categoryInfo", tCatVO);
		model.addAttribute("TipBoardList", tipBoardService.listAll(search));
		System.out.println("TipBoardController TipBoardList open");
		return "tipboard/tipBoardList";
	}

	// 게시물 상세정보
	@RequestMapping(value = "/tipBoardView", method = RequestMethod.GET)
	public ModelAndView view(HttpSession session, @RequestParam("boardId") int boardId) throws Exception {
		System.out.println("*************************************************");
		TipBoardVO row = tipBoardService.view(boardId);
		
		List<NoticeCategoryVO> nCatList = homeService.bringNoticeCategory();
		List<VideoCategoryVO> vCatList = homeService.bringVideoCategory();
		List<TipCategoryVO> tCatList = homeService.bringTipCategory();
		List<RecruitCategoryVO> rCatList = homeService.bringRecruitCategory();
		
		session.setAttribute("nCatList", nCatList);
		session.setAttribute("vCatList", vCatList);
		session.setAttribute("tCatList", tCatList);
		session.setAttribute("rCatList", rCatList);

		ModelAndView mav = new ModelAndView();
		mav.setViewName("tipboard/tipBoardView");
		mav.addObject("row", row);
		System.out.println("TipBoardController boardView open");
		System.out.println(row);
		return mav;
	}

	// 게시물 쓰기
	@RequestMapping(value = "/write.do", method = RequestMethod.GET)
	public ModelAndView writedo() throws Exception {
		System.out.println("*************************************************");

		ModelAndView mav = new ModelAndView();
		mav.setViewName("tipboard/tipBoardWrite");
		System.out.println("TipBoardController boardWrite open");
		return mav;
	}

	// 글작성 완료
	@RequestMapping(value = "/insertTipBoardPro", method = RequestMethod.POST)
	public String insertTipBoardPro(@RequestParam("accountId") int accountId,
									@RequestParam("categoryId") int categoryId,
									@RequestParam("subject") String subject,
									@RequestParam("object") String object) throws Exception {
		System.out.println("============insertTipBoardPro 성공==============");
		TipBoardVO vo = new TipBoardVO();
		
		vo.setAccountId(accountId);
		vo.setCategoryId(categoryId);
		vo.setSubject(subject);
		vo.setObject(object);
		
		System.out.println("acc" + accountId);
		System.out.println("cate" + categoryId);
		System.out.println("sub" + subject);
		System.out.println("obj" + object);

		tipBoardService.insertTipBoard(vo);
		
		return "redirect:/tipboard/tipBoardList";
	}
	
	// 글 수정
	@RequestMapping(value = "/updateTipBoardPro")
	public String updateNoticeBoardPro(TipBoardVO vo) throws Exception {
		tipBoardService.updateTipBoard(vo);
		System.out.println("============ updateTipBoard 성공==============");
		return "redirect:/tipboard/tipBoardList";
	}

	// 파일 이동
	// 게시글 수정
	@RequestMapping(value = "/updateTipBoard.do", method = RequestMethod.GET)
	public ModelAndView joinUpdate(Locale locale, Model model, @RequestParam("boardId") int boardId) throws Exception {
		TipBoardVO update = tipBoardService.view(boardId);
		ModelAndView mav = new ModelAndView();
		mav.setViewName("tipboard/tipBoardUpdate");
		mav.addObject("tipBoardUpdate", update);
		System.out.println("String tipBoardUpdate open");
		return mav;
	}

	// 글 수정
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public void getUpdatey(@RequestParam("boardId") int boardId, Model model) throws Exception {
		TipBoardVO vo = tipBoardService.view(boardId);

		model.addAttribute("updateTipBoard", vo);

	}

	// 글 삭제
	@RequestMapping(value = "/deleteTipBoardPro")
	public String deleteTipBoardPro(TipBoardVO vo, RedirectAttributes rttr) throws Exception {
		tipBoardService.deleteTipBoard(vo);
		rttr.addFlashAttribute("result","deleteOK");
		System.out.println("============ deleteVideoBoard 성공==============");

		return "redirect:/tipboard/tipBoardList";
	}
	
	// 최신 게시물 목록
	@ResponseBody
	@RequestMapping(value = "/newTip", method = {RequestMethod.GET,RequestMethod.POST})
	public List<TipBoardVO> newList() throws Exception {
		System.out.println("Start tipBoard List");
		List<TipBoardVO> newList = tipBoardService.newList();
		System.out.println("newList : " + newList);
		return newList;
	}
}
