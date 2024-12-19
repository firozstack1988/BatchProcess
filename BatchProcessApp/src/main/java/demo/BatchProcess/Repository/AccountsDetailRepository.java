package demo.BatchProcess.Repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import demo.BatchProcess.Entity.AccountsDetail;
@Repository
public interface AccountsDetailRepository extends JpaRepository<AccountsDetail,Long>{
 
	    @Transactional
	    @Modifying
		@Query(value="update account_detail set description=:description where account_number=:accountNumber",nativeQuery=true)
		public void updateDescription(@Param("description") String description,@Param("accountNumber") String accountNumber);
		
		public AccountsDetail findByAccountNum(@Param("accountNum") String accountNum);			
}
