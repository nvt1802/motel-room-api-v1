package tainv13.app.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import tainv13.app.model.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

	@Query(nativeQuery = true, value = "SELECT * FROM account WHERE account_id = :accountId AND delete_at IS NULL")
	Account findByAccountId(@Param("accountId") Long accountId);

	@Query(nativeQuery = true, value = "SELECT * FROM account WHERE user_name LIKE :userName AND delete_at IS NULL")
	Account findByUserName(@Param("userName") String userName);

	@Query(nativeQuery = true, value = "SELECT user_name FROM account WHERE user_name LIKE :userName AND delete_at IS NULL")
	String checkByUserName(@Param("userName") String userName);

	@Query(nativeQuery = true, value = "SELECT user_name FROM account WHERE account_id = :accountId and user_name LIKE :userName AND delete_at IS NULL")
	String checkAccountByAccountIdAndUserName(@Param("accountId") Long accountId, @Param("userName") String userName);

	@Query(nativeQuery = true, value = "SELECT * FROM account WHERE delete_at IS NULL")
	Page<Account> findAllByDeleteAt(Pageable pageable);
}
