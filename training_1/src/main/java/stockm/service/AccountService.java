package stockm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stockm.dao.AccountMapper;
import stockm.dto.AccountDto;
import stockm.dto.MainaccountDto;

@Service
public class AccountService { 

    private final stockm.dao.AccountMapper accountMapper;
	private String email;
    
    @Autowired
    public AccountService(stockm.dao.AccountMapper accountMapper) {
        this.accountMapper = accountMapper; 
    }

    @Transactional
    public void transfer(String fromAccountNumber, String toAccountNumber, Double amount) {
        AccountDto fromAccount = accountMapper.findByAccountNumber(fromAccountNumber);
        AccountDto toAccount = accountMapper.findByAccountNumber(toAccountNumber);

        fromAccount.setBalance(fromAccount.getBalance() - amount);
        toAccount.setBalance(toAccount.getBalance() + amount);

        accountMapper.updateBalance(fromAccount);
        accountMapper.updateBalance(toAccount);
    }
    
    public AccountDto findByAccountNumber(String accountNumber) {
        return accountMapper.findByAccountNumber(accountNumber);
    }

    public int updateBalance(AccountDto account) {
        return accountMapper.updateBalance(account);
    }
    
    public List<AccountDto> wholeAccount() {
    	return accountMapper.wholeAccount();
    }
    
    public int insertmainaccount(String accountNumber) {
    	return accountMapper.insertmainaccount(accountNumber);
    }

	public List<AccountDto> getAccountForUser(String userEmailFromToken) {
	     email = userEmailFromToken;
		return accountMapper.findByemail(email);
		
	}
}