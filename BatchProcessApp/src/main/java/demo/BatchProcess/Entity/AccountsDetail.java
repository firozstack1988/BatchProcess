package demo.BatchProcess.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ACCOUNT_DETAIL")
public class AccountsDetail {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	 
	  private Long id;
	  @Column(name="ACCOUNT_NUMBER")
	  private String accountNum; 
	  @Column(name="TRX_AMOUNT")
	  private double trxAmount;
	  @Column(name="DESCRIPTION")
	  private String description;  
	  @Column(name="TRX_DATE")
	  private String trxDate;
	  @Column(name="TRX_TIME")
	  private String trxTime;
	  @Column(name="CUSTOMER_ID")
	  private String customerId;
}
