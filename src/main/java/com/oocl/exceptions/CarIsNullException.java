package com.oocl.exceptions;

public class CarIsNullException extends RuntimeException{
    public CarIsNullException() {
        super("Car is null.");
    }
}
