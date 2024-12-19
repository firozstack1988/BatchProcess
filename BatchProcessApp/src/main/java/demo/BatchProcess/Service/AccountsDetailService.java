package demo.BatchProcess.Service;

import java.util.List;

import org.springframework.data.domain.Page;

import demo.BatchProcess.Entity.AccountsDetail;

public interface AccountsDetailService {

	public AccountsDetail findAccountsDetailByAccNum(String accountNum)throws Exception;
	public Page<AccountsDetail> findAllAccountDetailsWithPagination(int offset, int pageSize)throws Exception;
	public List<AccountsDetail> findAllAccountDetails()throws Exception;
	public Page<AccountsDetail> findAllAccountDetailsWithPaginationAndSorting(int offset, int pageSize,String fieldData)throws Exception;
	public AccountsDetail modifyDescription(AccountsDetail accountsDetail)throws Exception;

}
