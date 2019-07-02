package com.good.dao;

import java.util.List;
import com.good.dto.AccountsVO;

public interface AccountsDAO {

	
	public List<AccountsVO> selectAccounts() throws Exception;

<<<<<<< Updated upstream
	public void insertAccounts(AccountsVO vo) throws Exception;
	
	public AccountsVO login(AccountsVO vo) throws Exception;
=======
	public void insertAccounts(AccountsVO accountsVO) throws Exception;

	public int checkEmail(String email) throws Exception;

	public int checkNickname(String nickname) throws Exception;

	public AccountsVO login(AccountsVO accountsVO) throws Exception;
>>>>>>> Stashed changes
}
