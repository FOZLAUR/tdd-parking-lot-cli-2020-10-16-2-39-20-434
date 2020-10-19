package com.oocl.exceptions;

public class ParkingBoyNotInListException extends RuntimeException{
    public ParkingBoyNotInListException() {
        super("Parking Boy not found in your Management List.");
    }
}
