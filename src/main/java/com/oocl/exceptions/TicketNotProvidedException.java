package com.oocl.exceptions;

public class TicketNotProvidedException extends RuntimeException{
    public TicketNotProvidedException() {
        super("Please provide your parking ticket.");
    }
}
