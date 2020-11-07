package tainv13.app.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import tainv13.app.model.Account;

public abstract class AccountService {

	public abstract Page<Account> findAllAccountAvailable(Pageable pageable);

	public abstract Account findAccountByAccountId(Long accountId);

	public abstract Account findAccountByUserName(String userName);

	public abstract Boolean checkAccountByAccountIdAndUserName(Long accountId, String userName);

	public abstract Boolean checkAccountByUserName(String userName);

	public abstract Boolean addOneAccount(Account account);

	public abstract Boolean updateAccount(Account account);

	public abstract Boolean deleteAccount(List<Long> listAccountId, String deleteBy);

	public abstract Account lockAndUnlockPost(Account account);
}
