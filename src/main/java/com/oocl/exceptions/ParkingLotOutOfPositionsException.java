package com.oocl.exceptions;

public class ParkingLotOutOfPositionsException extends RuntimeException{
    public ParkingLotOutOfPositionsException() {
        super("Not enough position.");
    }
}
