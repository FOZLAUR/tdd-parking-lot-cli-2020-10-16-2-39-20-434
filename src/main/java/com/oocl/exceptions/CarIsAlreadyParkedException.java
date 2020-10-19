package com.oocl.exceptions;

public class CarIsAlreadyParkedException extends RuntimeException{
    public CarIsAlreadyParkedException() {
        super("Car is already parked here.");
    }
}
