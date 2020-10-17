package com.oocl.cultivation;

public class ParkingLotServiceManager {
    public void assignParkingLot(ParkingBoy parkingBoy, ParkingLot parkingLot) {
        parkingBoy.addParkingLotToList(parkingLot);
    }

    public void addToManagementList(ParkingBoy parkingBoy) {
    }

    public boolean isInManagementList(ParkingBoy parkingBoy) {
        return false;
    }
}
