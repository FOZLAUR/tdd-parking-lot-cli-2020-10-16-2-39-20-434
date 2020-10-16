package com.oocl.cultivation;

import java.util.List;

public class ParkingBoy {
    private ParkingLot parkingLot;

    public ParkingBoy(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
    }

    public ParkingBoy(List<ParkingLot> parkingLotList){

    }

    public ParkingTicket park(Car car) {
        return parkingLot.park(car);
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        return parkingLot.fetchCar(parkingTicket);
    }
}
