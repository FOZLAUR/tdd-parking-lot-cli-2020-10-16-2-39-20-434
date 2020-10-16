package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {
    private ParkingLot parkingLot;
    private List<ParkingLot> parkingLotList = new ArrayList<>();

    public ParkingBoy(ParkingLot parkingLot){
        this.parkingLot = parkingLot;
    }

    public ParkingBoy(List<ParkingLot> parkingLotList){
        this.parkingLotList = parkingLotList;
    }

    public ParkingTicket park(Car car) {
        List<ParkingLot> parkingLotsWithPosition = parkingLotList.stream().filter(parkingLot -> !parkingLot.isFull()).collect(Collectors.toList());
        return parkingLotsWithPosition.size() == 0 ? parkingLotList.get(0).park(car) : parkingLotsWithPosition.get(0).park(car);
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        return parkingLot.fetchCar(parkingTicket);
    }

    public void printParkingLots(){
        parkingLotList.stream().forEach(parkingLot -> System.out.println(parkingLot.getMapSize()));
    }
}
