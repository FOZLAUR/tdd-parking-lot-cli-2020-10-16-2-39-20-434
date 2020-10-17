package com.oocl.cultivation;

public class ParkingLotServiceManager {
    public void assignParkingLot(ParkingBoy parkingBoy, ParkingLot parkingLot) {
        parkingBoy.addParkingLotToList(parkingLot);
    }
}
