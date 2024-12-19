package demo.BatchProcess.Model;

import lombok.Data;

@Data
public class ResponseModel {

	private String success;
	private String errorMsg;
	private Object content;

}
