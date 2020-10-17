package com.oocl.cultivation;

import java.util.List;

public class SuperSmartParkingBoy extends ParkingBoy{
    public SuperSmartParkingBoy(List<ParkingLot> parkingLotList){
        super(parkingLotList);
    }

    @Override
    public ParkingTicket park(Car car) {
        ParkingLot parkingLotWithPosition = parkingLotList
                .stream()
                .filter(parkingLot -> !parkingLot.isFull())
                .reduce((p1,p2) -> {
                    return p1.getAvailableSpaceRatio() < p2.getAvailableSpaceRatio() ? p2 : p1 ;
                }).orElse(null);
        return parkingLotWithPosition == null ? parkingLotList.get(0).park(car) : parkingLotWithPosition.park(car);
    }
}
