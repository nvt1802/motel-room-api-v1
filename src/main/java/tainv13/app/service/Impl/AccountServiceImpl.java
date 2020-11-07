package tainv13.app.service.Impl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import tainv13.app.model.Account;
import tainv13.app.repository.AccountRepository;
import tainv13.app.service.AccountService;

@Service
public class AccountServiceImpl extends AccountService {

	@Autowired
	private AccountRepository accountRepository;

	@Override
	public Account findAccountByUserName(String userName) {
		return accountRepository.findByUserName(userName);
	}

	@Override
	public Boolean checkAccountByUserName(String userName) {
		return accountRepository.checkByUserName(userName) != null ? true : false;
	}

	@Override
	public Boolean checkAccountByAccountIdAndUserName(Long accountId, String userName) {
		return accountRepository.checkAccountByAccountIdAndUserName(accountId, userName) != null ? true : false;
	}

	@Override
	public Account findAccountByAccountId(Long accountId) {
		return accountRepository.findByAccountId(accountId);
	}

	@Override
	public Boolean addOneAccount(Account account) {
		try {
			return accountRepository.save(account) == null ? false : true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean updateAccount(Account account) {
		try {
			return accountRepository.save(account) != null ? true : false;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Boolean deleteAccount(List<Long> listAccountId, String deleteBy) {
		try {
			for (Long id : listAccountId) {
				Account account = accountRepository.getOne(id);
				if (account != null) {
					if (!"admin".equals(account.getUserName()) && account.getRole() != 1l) {
						account.setDeleteAt(new Date());
						account.setDeleteBy(deleteBy);
						accountRepository.save(account);
					} else {
						return false;
					}
				} else {
					return false;
				}
			}
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	@Override
	public Page<Account> findAllAccountAvailable(Pageable pageable) {
		return accountRepository.findAllByDeleteAt(pageable);
	}

	@Override
	public Account lockAndUnlockPost(Account account) {
		try {
			Account account2 = accountRepository.findByAccountId(account.getAccountId());
			boolean status = account2.getAccountStatus();
			account2.setAccountStatus(!status);
			return accountRepository.save(account2);
		} catch (Exception e) {
			return null;
		}
	}
}
