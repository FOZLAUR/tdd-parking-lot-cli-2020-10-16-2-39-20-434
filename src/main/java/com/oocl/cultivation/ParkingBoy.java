package com.oocl.cultivation;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {
    protected List<ParkingLot> parkingLotList;

    public ParkingBoy(List<ParkingLot> parkingLotList){
        this.parkingLotList = parkingLotList;
    }

    public ParkingTicket park(Car car) {
        ParkingLot parkingLotWithPosition = parkingLotList.stream().filter(parkingLot -> !parkingLot.isFull()).findFirst().orElse(null);
        if(car!=null && !isCarParkedInAnyParkingLots(car)){
            return parkingLotWithPosition == null ? parkingLotList.get(0).park(car) : parkingLotWithPosition.park(car);
        } else {
            return null;
        }
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        ParkingLot parkingLotWithTicket = parkingLotList.stream().filter(parkingLot -> parkingLot.containsTicket(parkingTicket)).findFirst().orElse(null);
        return parkingLotWithTicket == null ? parkingLotList.get(0).fetchCar(parkingTicket) : parkingLotWithTicket.fetchCar(parkingTicket) ;
    }

    public void addParkingLotToList(ParkingLot parkingLot){
        parkingLotList.add(parkingLot);
    }

    public boolean isAssignedParkingLot(ParkingLot parkingLot){
        return parkingLotList.indexOf(parkingLot) != -1;
    }

    public boolean isCarParkedInAnyParkingLots(Car car){
        return parkingLotList.stream().anyMatch(parkingLot -> parkingLot.containsCar(car));
    }
}
