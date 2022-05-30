package com.Meldia.peopleapi.exception;

import org.apache.logging.log4j.LogManager;

public class ExceptionCustom {

	private String errorCode;
    private String errorMessage;
    private ExceptionAPI exception;
    
    public ExceptionAPI personNotFoundException() {
        errorMessage = "User data does not exist/cannot be found";
        errorCode = "NTE-1997";
        exception = new ExceptionAPI(errorMessage, errorCode);
        
        LogManager.getLogger().info(exception);
        return exception;
    }
    
    public ExceptionAPI nullAttributteException() {
        errorMessage = "Some attributes  are null";
        errorCode = "NAE-1998";
        exception = new ExceptionAPI(errorMessage, errorCode);
        
        LogManager.getLogger().info(exception);
        return exception;
    }
    
    public ExceptionAPI dataBaseIsEmptyException(){
        errorMessage = "The information couldn't process";
        errorCode = "DBE-1999";
        exception = new ExceptionAPI(errorMessage, errorCode);
        
        LogManager.getLogger().info(exception);
        return exception;
    }
}
