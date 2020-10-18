package com.oocl.cultivation;

import java.util.List;
import java.util.stream.Collectors;

public class ParkingBoy {
    protected List<ParkingLot> parkingLotList;

    public ParkingBoy(List<ParkingLot> parkingLotList){
        this.parkingLotList = parkingLotList;
    }

    public ParkingTicket park(Car car) {
        ParkingLot parkingLotWithPosition = parkingLotList.stream().filter(parkingLot -> !parkingLot.isFull() && !parkingLot.containsCar(car)).findFirst().orElse(null);
        if(car!=null){
            return parkingLotWithPosition == null ? parkingLotList.get(0).park(car) : parkingLotWithPosition.park(car);
        } else {
            return null;
        }
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        List<ParkingLot> parkingLotsWithTicket = parkingLotList.stream().filter(parkingLot -> parkingLot.containsTicket(parkingTicket)).collect(Collectors.toList());
        return parkingLotsWithTicket.size() == 0 ? parkingLotList.get(0).fetchCar(parkingTicket) : parkingLotsWithTicket.get(0).fetchCar(parkingTicket) ;
    }

    public void addParkingLotToList(ParkingLot parkingLot){
        parkingLotList.add(parkingLot);
    }

    public boolean isAssignedParkingLot(ParkingLot parkingLot){
        return parkingLotList.indexOf(parkingLot) != -1;
    }
}
