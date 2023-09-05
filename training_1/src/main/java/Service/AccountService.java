package Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import Dao.AccountMapper;
import Dto.AccountDto;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AccountService { 

    private final Dao.AccountMapper accountMapper;
    
    @Autowired
    public AccountService(Dao.AccountMapper accountMapper) {
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