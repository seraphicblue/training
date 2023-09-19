package stockm.dao;

import org.apache.ibatis.annotations.Mapper;

import stockm.dto.AccountDto;

@Mapper
public interface AccountMapper {
	    AccountDto findByAccountNumber(String accountNumber);
	    int updateBalance(AccountDto account);
}
