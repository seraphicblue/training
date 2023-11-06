package stockm.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import stockm.dto.AccountDto;
import stockm.dto.MainaccountDto;

@Mapper
public interface AccountMapper {
	    AccountDto findByAccountNumber(String accountNumber);
	    int updateBalance(AccountDto account);
	    List<AccountDto> wholeAccount();
	    int insertmainaccount(String accountNumber);
		List<AccountDto> findByemail(String email);
}
