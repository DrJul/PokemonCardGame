package com.pokemontcg.exception;

public class DownloadException extends RuntimeException{
    public DownloadException(String message){
        super(message);
    }
}
