package com.oocl.cultivation;

import com.oocl.exceptions.CarIsAlreadyParkedException;
import com.oocl.exceptions.CarIsNullException;
import com.oocl.exceptions.ParkingLotOutOfPositionsException;

import java.util.List;

public class SmartParkingBoy extends ParkingBoy{
    public SmartParkingBoy(List<ParkingLot> parkingLotList){
        super(parkingLotList);
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingLot parkingLotWithPosition = parkingLotList
                .stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .reduce((p1,p2) -> {
                    return p1.getRemainingSpaces() < p2.getRemainingSpaces() ? p2 : p1 ;
                }).orElse(null);
        if (car==null){
            throw new CarIsNullException();
        } else if(isCarParkedInAnyParkingLots(car)){
            throw new CarIsAlreadyParkedException();
        } else if (parkingLotWithPosition==null) {
            throw new ParkingLotOutOfPositionsException();
        }
        return parkingLotWithPosition.park(car);
    }
}
