package com.pokemontcg.exception;

public class LoginServiceException extends RuntimeException{

    public LoginServiceException(String message){
        super(message);
    }
}
