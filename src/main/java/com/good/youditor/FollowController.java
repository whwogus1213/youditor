package com.good.youditor;

import java.util.List;

import javax.inject.Inject;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.good.dto.AccountsVO;
import com.good.dto.FollowListVO;
import com.good.dto.FollowingListVO;
import com.good.dto.NoticeCategoryVO;
import com.good.dto.RecruitCategoryVO;
import com.good.dto.TipCategoryVO;
import com.good.dto.VideoCategoryVO;
import com.good.service.FollowService;
import com.good.service.HomeService;
import com.good.service.VideoBoardService;

@Controller
@RequestMapping("/follow")
public class FollowController {

	@Inject
	FollowService followService;

	@Inject
	HomeService homeService;

	// 팔로잉 (로그인유저가 팔로우하는 사람 리스트)
	@RequestMapping(value = "/followingList")
	public String followingList(Model model, HttpSession session) throws Exception {
		System.out.println("Start following List");

		AccountsVO login = (AccountsVO) session.getAttribute("login");
		int accountId = login.getAccountId();

		List<FollowingListVO> followingList = followService.followingList(accountId);

		for(int i = 0; i < followingList.size(); i++) {
			followingList.get(i).setFollowCnt(followService.followCnt(followingList.get(i).getFollowAccountId()));
			followingList.get(i).setStarCnt(followService.starCnt(followingList.get(i).getFollowAccountId()));
			followingList.get(i).setStarRank(followService.starRank(followingList.get(i).getFollowAccountId()));
		}

		System.out.println(followingList);

		List<NoticeCategoryVO> nCatList = homeService.bringNoticeCategory();
		List<VideoCategoryVO> vCatList = homeService.bringVideoCategory();
		List<TipCategoryVO> tCatList = homeService.bringTipCategory();
		List<RecruitCategoryVO> rCatList = homeService.bringRecruitCategory();

		session.setAttribute("nCatList", nCatList);
		session.setAttribute("vCatList", vCatList);
		session.setAttribute("tCatList", tCatList);
		session.setAttribute("rCatList", rCatList);

		if(login != null) {
			session.setAttribute("mCount", homeService.newMessageCnt(accountId));
			session.setAttribute("fCount", homeService.newFollowerCnt(accountId));
		}

		model.addAttribute("followingList", followingList);
		return "follow/followingList";
	}

	// 팔로워(로그인유저를 팔로우하는 사람 리스트)
	@RequestMapping(value = "/followerList")
	public String followerList(Model model, FollowListVO vo, HttpSession session) throws Exception {
		System.out.println("Start follower List");

		AccountsVO login = (AccountsVO) session.getAttribute("login");
		int accountId = login.getAccountId();

		List<FollowingListVO> followerList = followService.followerList(accountId);
		for(int i = 0; i < followerList.size(); i++) {
			followerList.get(i).setFollowCnt(followService.followCnt(followerList.get(i).getFollowerAccountId()));
			followerList.get(i).setStarCnt(followService.starCnt(followerList.get(i).getFollowerAccountId()));
			followerList.get(i).setStarRank(followService.starRank(followerList.get(i).getFollowerAccountId()));
		}
		System.out.println(followerList);

		model.addAttribute("followerList", followerList);

		if(login != null) {
			session.setAttribute("mCount", homeService.newMessageCnt(accountId));
			session.setAttribute("fCount", homeService.newFollowerCnt(accountId));
			followService.updateLastFollowerCheck(accountId);
		}

		return "follow/followerList";
	}

	// 팔로잉 추가
	@RequestMapping(value = "/insert", method = RequestMethod.POST)
	public @ResponseBody String insert(HttpSession session, FollowListVO vo) throws Exception {

		System.out.println("----------Start follow insert");
		System.out.println("글쓴이 아이디 정보 : " + vo);

		AccountsVO loginVO = (AccountsVO) session.getAttribute("login");

		// 로그인 유저 아이디
		vo.setFollowerAccountId(loginVO.getAccountId());

		System.out.println("로그인 유저 아이디 -> followerAccountId : " + vo.getFollowerAccountId());
//		System.out.println("accountId" + accountId);
		System.out.println("followList : " + vo);

		// followService.insert(vo);
		try {
			System.out.println("followService.insert 진입");
			followService.insert(vo);
			System.out.println("followService.insert 성공");

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("----------End follow insert");
		return "success";
	}

	// 팔로잉 삭제
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody String delete(HttpSession session, FollowListVO vo) throws Exception {

		System.out.println("-------------End follow delete");
		System.out.println("글쓴이 아이디 정보 : " + vo);

		// 로그인 유저 아이디
		AccountsVO loginVO = (AccountsVO) session.getAttribute("login");

		if(vo.getFollowerAccountId() == 0) {
			// followerAccountId에 로그인 아이디 세팅
			vo.setFollowerAccountId(loginVO.getAccountId());
		} else {
			vo.setFollowAccountId(loginVO.getAccountId());
		}

		System.out.println("로그인 유저 아이디 -> followerAccountId : " + vo.getFollowerAccountId());
//		System.out.println("accountId" + accountId);
		System.out.println("followList : " + vo);

		// followService.insert(vo);
		try {
			System.out.println("followService.delete 진입");
			followService.delete(vo);
			System.out.println("followService.delete 성공");

		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("-------------End follow delete");
		return "success1";
	}

	@Inject
	VideoBoardService videoBoardService;

	// 팔로잉 check
	@RequestMapping(value = "/check", method = RequestMethod.POST)
	public @ResponseBody int check(FollowListVO vo) throws Exception {
		System.out.println(vo);
		int fc = videoBoardService.followCheck(vo.getFollowerAccountId(), vo.getFollowAccountId());
		System.out.println(fc + "@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@");
		return fc;
	}

//	// 팔로우 게시물 목록
//	@RequestMapping(value = "followBoardList")
//	public ModelAndView followBoardList(@RequestParam("followAccountId") int followAccountId, HttpServletRequest request) throws Exception {
//		System.out.println("팔로우 게시물 목록 시작");
//		System.out.println("   followAccountId   " + followAccountId);
//
//
//		List<VideoBoardVO> VideoBoardList = videoBoardService.followBoardList(followAccountId);
//
//
//
//		ModelAndView mv = new ModelAndView();
//		mv.setViewName("videoboard/videoBoardList");
//		mv.addObject("VideoBoardList", VideoBoardList);
//		request.setAttribute("nickname", VideoBoardList.get(0).getNickname());
//
//		System.out.println("팔로우 게시물 목록 끝");
//		return mv;
//	}

}
