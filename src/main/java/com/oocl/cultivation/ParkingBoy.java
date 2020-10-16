package com.oocl.cultivation;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(){
        parkingLot = new ParkingLot();
    }

    public ParkingTicket park(Car car) {
        return parkingLot.park(car);
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        return parkingLot.fetchCar(parkingTicket);
    }
}
