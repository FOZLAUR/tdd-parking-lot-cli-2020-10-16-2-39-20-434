package com.oocl.cultivation;

import java.util.ArrayList;
import java.util.List;

public class ParkingLotServiceManager {
    List<ParkingBoy> managementList;
    List<ParkingLot> parkingLotsList;

    public ParkingLotServiceManager(){
        managementList = new ArrayList<>();
        parkingLotsList = new ArrayList<>();
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
        parkingLotsList.add(parkingLot);
    }

    public boolean isInParkingLotList(ParkingLot parkingLot) {
        return parkingLotsList.indexOf(parkingLot) != -1;
    }

    public ParkingTicket assignParkingBoyToParkCar(ParkingBoy parkingBoy, Car car) {
        return isInManagementList(parkingBoy) ? parkingBoy.park(car) : null;
    }

    public Car assignParkingBoyToFetchCar(ParkingBoy parkingBoy, ParkingTicket parkingTicket) {
        return isInManagementList(parkingBoy) ? parkingBoy.fetchCar(parkingTicket) : null;
    }

    public ParkingTicket park(Car car) {
        ParkingLot parkingLotWithPosition = parkingLotsList.stream().filter(parkingLot -> !parkingLot.isFull()).findFirst().orElse(null);
        return parkingLotWithPosition == null ? parkingLotsList.get(0).park(car) : parkingLotWithPosition.park(car);
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        ParkingLot parkingLotWithTicket = parkingLotsList.stream().filter(parkingLot -> parkingLot.containsTicket(parkingTicket)).findFirst().orElse(null);
        return parkingLotWithTicket == null ? parkingLotsList.get(0).fetchCar(parkingTicket) : parkingLotWithTicket.fetchCar(parkingTicket) ;
    }
}
