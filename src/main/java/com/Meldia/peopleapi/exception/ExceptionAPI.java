package com.Meldia.peopleapi.exception;

public class ExceptionAPI extends Exception{

	private static final long serialVersionUID = 1L;
    private String errorCode;
    private String errorMessage;
    
	public ExceptionAPI(String errorCode, String errorMessage) {
		super();
		this.errorCode = errorCode;
		this.errorMessage = errorMessage;
	}

	public String getErrorCode() {
		return errorCode;
	}

	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	@Override
	public String toString() {
		return "ExceptionAPI [ERROR CODE: " + errorCode + ", ERROR MESSAGE: " + errorMessage + "]";
	}
    
    
}
