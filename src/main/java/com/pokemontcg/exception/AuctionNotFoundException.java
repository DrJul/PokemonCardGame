package com.pokemontcg.exception;

public class AuctionNotFoundException extends RuntimeException{
    public AuctionNotFoundException(String message){
        super(message);
    }
}
