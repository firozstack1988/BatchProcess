package demo.BatchProcess.Service;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import demo.BatchProcess.Entity.AccountsDetail;
import demo.BatchProcess.Repository.AccountsDetailRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class AccountsDetailServiceImpl implements AccountsDetailService{

	@Autowired
	private AccountsDetailRepository accountsDetailRepository;
		 		 	 
	@Override
	public AccountsDetail findAccountsDetailByAccNum(String accountNum) throws Exception {
		AccountsDetail accountsDetail=null;
        log.info("getting accounts detail by account number started");		
		try {
			accountsDetail=accountsDetailRepository.findByAccountNum(accountNum);
			 
		}
		catch(Exception e) {
			log.info(e.getLocalizedMessage());	
			throw new Exception(e.getLocalizedMessage());
		}
		return accountsDetail;
	}

	@Override
	public Page<AccountsDetail> findAllAccountDetailsWithPagination(int offset, int pageSize) throws Exception {
		Page<AccountsDetail> accountsDetailList=null;
		log.info("getting accounts detail List with pagination started");
		try {
			accountsDetailList=accountsDetailRepository.findAll(PageRequest.of(offset, pageSize));
		}
		catch(Exception e) {
			log.info(e.getLocalizedMessage());	
			throw new Exception(e.getLocalizedMessage());
		}
		return accountsDetailList;
	}
	
	@Override
	public List<AccountsDetail> findAllAccountDetails() throws Exception {
		List<AccountsDetail> accountsDetailList=null;
		log.info("getting accounts detail List started");
		try {
			accountsDetailList=accountsDetailRepository.findAll();
		}
		catch(Exception e) {
			log.info(e.getLocalizedMessage());
			throw new Exception(e.getLocalizedMessage());
		}
		return accountsDetailList;
	}

	@Override
	public Page<AccountsDetail> findAllAccountDetailsWithPaginationAndSorting(int offset,int pageSize,String fieldData) throws Exception {
		Page<AccountsDetail> accountsDetailList=null;
		log.info("getting accounts detail List with pagination and sorting started");
		try {
			accountsDetailList=accountsDetailRepository.findAll(PageRequest.of(offset, pageSize).withSort(Sort.by(fieldData)));
		}
		catch(Exception e) {
			log.info(e.getLocalizedMessage());
			throw new Exception(e.getLocalizedMessage());
		}
		return accountsDetailList;
	}

	@Override
	@Transactional
	public AccountsDetail modifyDescription(AccountsDetail accountsDetail) throws Exception {
		AccountsDetail accDetail=null;
		log.info("Description modification started");
		try {
			accountsDetailRepository.updateDescription(accountsDetail.getDescription(), accountsDetail.getAccountNum());
			accDetail=findAccountsDetailByAccNum(accountsDetail.getAccountNum());
		}
		catch(Exception e) {
			log.info(e.getLocalizedMessage());
			throw new Exception(e.getLocalizedMessage());
		} 
		return accDetail;
		
	}

}
