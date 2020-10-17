package com.oocl.cultivation;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {
    protected List<ParkingLot> parkingLotList;

    public ParkingBoy(List<ParkingLot> parkingLotList){
        this.parkingLotList = parkingLotList;
    }

    public ParkingTicket park(Car car) {
        List<ParkingLot> parkingLotsWithPosition = parkingLotList.stream().filter(parkingLot -> !parkingLot.isFull()).collect(Collectors.toList());
        return parkingLotsWithPosition.size() == 0 ? parkingLotList.get(0).park(car) : parkingLotsWithPosition.get(0).park(car);
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        List<ParkingLot> parkingLotsWithTicket = parkingLotList.stream().filter(parkingLot -> parkingLot.containsTicket(parkingTicket)).collect(Collectors.toList());
        return parkingLotsWithTicket.size() == 0 ? parkingLotList.get(0).fetchCar(parkingTicket) : parkingLotsWithTicket.get(0).fetchCar(parkingTicket) ;
    }
}
