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

    public ParkingTicket assignParkingBoyToParkCar(ParkingBoy parkingBoy, Car car) {
        return null;
    }
}
