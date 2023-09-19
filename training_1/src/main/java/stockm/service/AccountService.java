package stockm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import stockm.dao.AccountMapper;
import stockm.dto.AccountDto;

@Service
public class AccountService { 

    private final stockm.dao.AccountMapper accountMapper;
    
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
}