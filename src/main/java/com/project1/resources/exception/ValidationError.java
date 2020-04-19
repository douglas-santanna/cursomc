package com.project1.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;
	
	List<FieldMessage> error = new ArrayList<FieldMessage>();
	
	public ValidationError(Integer status, String msg, long timeStamp) {
		super(status, msg, timeStamp);
	}

	public List<FieldMessage> getError(){
		return error;
	}
	
	public void addError(String fieldName, String message) {
		error.add( new FieldMessage(fieldName, message) );
	}
	
	
}
