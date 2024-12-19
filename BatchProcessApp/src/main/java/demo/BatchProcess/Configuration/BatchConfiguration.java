package demo.BatchProcess.Configuration;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.data.RepositoryItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import demo.BatchProcess.Entity.AccountsDetail;
import demo.BatchProcess.Repository.AccountsDetailRepository;
import lombok.AllArgsConstructor;

@Configuration
@EnableBatchProcessing
@AllArgsConstructor
public class BatchConfiguration {

	private JobBuilderFactory jobBuilderFactory;
	
	private StepBuilderFactory stepBuilderFactory;
	
	private AccountsDetailRepository accountsDetailRepository;
	
	@Bean
	public FlatFileItemReader<AccountsDetail> reader(){
		FlatFileItemReader<AccountsDetail> itemReader= new FlatFileItemReader<AccountsDetail>();
		itemReader.setResource(new FileSystemResource("src/main/resources/dataSource.txt"));
		itemReader.setName("txtReader");
		itemReader.setLinesToSkip(1);
		itemReader.setLineMapper(lineMapper());
		return itemReader;
	}
	
	private LineMapper<AccountsDetail> lineMapper(){
		DefaultLineMapper<AccountsDetail> lineMapper= new DefaultLineMapper<AccountsDetail>();
		
		DelimitedLineTokenizer delimitedLineTokenizer=new DelimitedLineTokenizer();
		delimitedLineTokenizer.setDelimiter("|");
		delimitedLineTokenizer.setStrict(false);
		delimitedLineTokenizer.setNames("ACCOUNT_NUMBER","TRX_AMOUNT","DESCRIPTION","TRX_DATE","TRX_TIME","CUSTOMER_ID");		
	
		BeanWrapperFieldSetMapper<AccountsDetail> beanWrapperFieldMapper =new BeanWrapperFieldSetMapper<AccountsDetail>();
		beanWrapperFieldMapper.setTargetType(AccountsDetail.class);
		
		lineMapper.setFieldSetMapper(beanWrapperFieldMapper);
		lineMapper.setLineTokenizer(delimitedLineTokenizer);
		
		return lineMapper;		
	}
	
	@Bean
	public AccountDetailProcessor processor() {
		return new AccountDetailProcessor();
	}
	
	@Bean
	public RepositoryItemWriter<AccountsDetail> writer(){
		RepositoryItemWriter<AccountsDetail> itemWriter= new RepositoryItemWriter<AccountsDetail>();
		itemWriter.setRepository(accountsDetailRepository);
		itemWriter.setMethodName("save");
		return itemWriter;
	}
	
	@Bean
	public Step step1() {
		return stepBuilderFactory.get("csv-step").<AccountsDetail,AccountsDetail>chunk(10).
				reader(reader()).
				processor(processor()).
				writer(writer()).build();
	}
	
	@Bean
	public Job runJob() {
		return jobBuilderFactory.get("importAccountsDetails").
				flow(step1()).end().build();
				
	}
}
