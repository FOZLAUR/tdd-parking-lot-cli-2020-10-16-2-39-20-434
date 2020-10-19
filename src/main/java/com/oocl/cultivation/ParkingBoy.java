package com.oocl.cultivation;

import com.oocl.exceptions.CarIsAlreadyParkedException;
import com.oocl.exceptions.CarIsNullException;
import com.oocl.exceptions.ParkingLotOutOfPositionsException;

import java.util.List;

public class ParkingBoy {
    protected List<ParkingLot> parkingLotList;

    public ParkingBoy(List<ParkingLot> parkingLotList){
        this.parkingLotList = parkingLotList;
    }

    public ParkingTicket park(Car car) {
        ParkingLot parkingLotWithPosition = parkingLotList.stream().filter(parkingLot -> !parkingLot.isFull()).findFirst().orElse(null);
        if (car==null){
            throw new CarIsNullException();
        } else if(isCarParkedInAnyParkingLots(car)){
            throw new CarIsAlreadyParkedException();
        } else if (parkingLotWithPosition==null) {
            throw new ParkingLotOutOfPositionsException();
        }
        return parkingLotWithPosition.park(car);
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
