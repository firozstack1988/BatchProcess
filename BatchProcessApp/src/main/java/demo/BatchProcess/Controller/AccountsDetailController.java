package demo.BatchProcess.Controller;
 
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import demo.BatchProcess.Entity.AccountsDetail;
import demo.BatchProcess.Model.ResponseModel;
import demo.BatchProcess.Service.AccountsDetailService;

@RestController
@RequestMapping("/processBatch")
public class AccountsDetailController {

	@Autowired
	private JobLauncher  jobLauncher;
	@Autowired
	private Job job;
	@Autowired
	private AccountsDetailService accountsDetailService;
	
	
	@PostMapping("/insertBatch")
	public ResponseModel insertBatch()throws Exception {
		ResponseModel response=new ResponseModel();
		JobParameters jobParamers=new JobParametersBuilder().
				addLong("startAt",System.currentTimeMillis()).toJobParameters();
		try {     
			jobLauncher.run(job, jobParamers);
		}
		catch(Exception e) {
			response.setErrorMsg(e.getLocalizedMessage());
		}
	return response;
	}
	
	@GetMapping("/accountDetails/{accountNum}")
	public ResponseModel getAccountDetails(@PathVariable String accountNum)throws Exception {
		ResponseModel response=new ResponseModel();
		try {     
			response.setContent(accountsDetailService.findAccountsDetailByAccNum(accountNum));
		}
		catch(Exception e) {
			response.setErrorMsg(e.getLocalizedMessage());
		}
 	  return response;
	}
	
	@GetMapping("/pagination/{offset}/{pageSize}")
	public ResponseModel accountDetailsList(@PathVariable int offset,@PathVariable int pageSize)throws Exception {
		ResponseModel response=new ResponseModel();
		try {     
			response.setContent(accountsDetailService.findAllAccountDetailsWithPagination(offset,pageSize));
		}
		catch(Exception e) {
			response.setErrorMsg(e.getLocalizedMessage());
		}
 	  return response;
	}
	
	@GetMapping("/paginationWithSort/{offset}/{pageSize}/{fieldData}")
	public ResponseModel accountDetailsList(@PathVariable int offset,@PathVariable int pageSize,@PathVariable String fieldData)throws Exception {
		ResponseModel response=new ResponseModel();
		try {     
			response.setContent(accountsDetailService.findAllAccountDetailsWithPaginationAndSorting(offset,pageSize,fieldData));
		}
		catch(Exception e) {
			response.setErrorMsg(e.getLocalizedMessage());
		}
 	  return response;
	}
	
	@PutMapping("/modifyDescription")
	public ResponseModel modifyAccountsDetail(@RequestBody AccountsDetail accountsDetail)throws Exception {
		ResponseModel response=new ResponseModel();
		try {     
			response.setContent(accountsDetailService.modifyDescription(accountsDetail));
		}
		catch(Exception e) {
			response.setErrorMsg(e.getLocalizedMessage());
		}
 	  return response;
	}
	
	  
}
