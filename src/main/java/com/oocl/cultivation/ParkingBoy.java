package com.oocl.cultivation;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(){
        parkingLot = new ParkingLot();
    }

    public ParkingTicket park(Car car) {
        return parkingLot.park(car);
    }
}
