package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager {
    List<ParkingBoy> managementList;

    public ParkingLotServiceManager(){
        managementList = new ArrayList<>();
    }

    public void assignParkingLot(ParkingBoy parkingBoy, ParkingLot parkingLot) {
        parkingBoy.addParkingLotToList(parkingLot);
    }

    public void addToManagementList(ParkingBoy parkingBoy) {
        managementList.add(parkingBoy);
    }

    public boolean isInManagementList(ParkingBoy parkingBoy) {
        return managementList.indexOf(parkingBoy) != -1;
    }

    public void addParkingLotToParkingLotList(ParkingLot parkingLot) {
    }

    public boolean isInParkingLotList(ParkingLot parkingLot) {
        return false;
    }

    public ParkingTicket assignParkingBoyToParkCar(ParkingBoy parkingBoy, Car car) {
        return isInManagementList(parkingBoy) ? parkingBoy.park(car) : null;
    }

    public Car assignParkingBoyToFetchCar(ParkingBoy parkingBoy, ParkingTicket parkingTicket) {
        return isInManagementList(parkingBoy) ? parkingBoy.fetchCar(parkingTicket) : null;
    }
}
