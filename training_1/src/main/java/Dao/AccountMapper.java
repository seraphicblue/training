package Dao;

import org.apache.ibatis.annotations.Mapper;
import Dto.AccountDto;

@Mapper
public interface AccountMapper {
	    AccountDto findByAccountNumber(String accountNumber);
	    int updateBalance(AccountDto account);
}
