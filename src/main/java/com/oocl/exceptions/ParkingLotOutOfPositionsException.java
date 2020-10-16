package com.oocl.exceptions;

public class ParkingLotOutOfPositionsException extends Exception{
    public ParkingLotOutOfPositionsException() {
        super("Not enough position.");
    }
}
