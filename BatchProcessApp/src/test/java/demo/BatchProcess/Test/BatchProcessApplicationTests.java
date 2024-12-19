package demo.BatchProcess.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import demo.BatchProcess.Entity.AccountsDetail;
import demo.BatchProcess.Repository.AccountsDetailRepository;
import demo.BatchProcess.Service.AccountsDetailService;


@SpringBootTest
class BatchProcessApplicationTests {
    
	@Autowired
	private AccountsDetailService accountsDetailService;
	
	@MockBean
	private AccountsDetailRepository accountsDetailRepository;
	
	@Test
	public void findAccountsDetailList() throws Exception {
	 AccountsDetail obj1=new AccountsDetail();
	 obj1.setTrxAmount(1);
	 obj1.setAccountNum("8872838284");
	 obj1.setDescription("ATM WITHDRWAL");
	 obj1.setCustomerId("222");	
	 obj1.setTrxDate("");
	 obj1.setTrxTime("");
	 AccountsDetail obj2=new AccountsDetail();
	 obj2.setTrxAmount(1123.00);
	 obj2.setAccountNum("8872838283");
	 obj2.setDescription("FUND TRANSFER");
	 obj2.setCustomerId("222");
	 obj2.setTrxDate("");
	 obj2.setTrxTime("");
	
	 Mockito.when(accountsDetailRepository.findAll()).thenReturn(Stream.of(obj1,obj2).collect(Collectors.toList()));
	 assertEquals(2,accountsDetailService.findAllAccountDetails().size());			 
	}
	
	@Test
	public void findAccountsDetailByAccountNumber() throws Exception {
	 String accNumber="8872838284";
	 String description="ATM WITHDRWAL";
	 AccountsDetail obj1=new AccountsDetail();
	 obj1.setTrxAmount(1);
	 obj1.setAccountNum("8872838284");
	 obj1.setDescription("ATM WITHDRWAL");
	 obj1.setCustomerId("222");
	 obj1.setTrxDate("");
	 obj1.setTrxTime("");
	
	 Mockito.when(accountsDetailRepository.findByAccountNum(accNumber)).thenReturn(obj1);
	 assertEquals(description,accountsDetailService.findAccountsDetailByAccNum(accNumber).getDescription());			 
	}

	 
	 
}
