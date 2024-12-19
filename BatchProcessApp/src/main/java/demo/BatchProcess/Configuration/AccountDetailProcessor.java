package demo.BatchProcess.Configuration;


import org.springframework.batch.item.ItemProcessor;

import demo.BatchProcess.Entity.AccountsDetail;

public class AccountDetailProcessor implements ItemProcessor<AccountsDetail,AccountsDetail>{
	
	@Override
	public AccountsDetail process(AccountsDetail accountsDetail) throws Exception{
		
		return accountsDetail;
	}

}
