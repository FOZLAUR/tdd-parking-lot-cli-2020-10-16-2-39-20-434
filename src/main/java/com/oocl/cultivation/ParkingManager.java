package com.oocl.cultivation;

import com.oocl.exceptions.*;

import java.util.ArrayList;
import java.util.List;

public class ParkingManager {
    List<ParkingBoy> managementList;
    List<ParkingLot> parkingLotsList;

    public ParkingManager(){
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
        if (!isInManagementList(parkingBoy) ) {
            throw new ParkingBoyNotInListException();
        }
        return parkingBoy.park(car);
    }

    public Car assignParkingBoyToFetchCar(ParkingBoy parkingBoy, ParkingTicket parkingTicket) {
        if (!isInManagementList(parkingBoy) ) {
            throw new ParkingBoyNotInListException();
        }
        return parkingBoy.fetchCar(parkingTicket);
    }

    public ParkingTicket park(Car car) {
        ParkingLot parkingLotWithPosition = parkingLotsList.stream().filter(parkingLot -> !parkingLot.isFull()).findFirst().orElse(null);
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
        ParkingLot parkingLotWithTicket = parkingLotsList.stream().filter(parkingLot -> parkingLot.containsTicket(parkingTicket)).findFirst().orElse(null);
        if(parkingTicket==null){
            throw new TicketNotProvidedException();
        } else if (parkingTicket!=null && parkingLotWithTicket == null) {
            throw new UnrecognizedTicketException();
        }
        return parkingLotWithTicket.fetchCar(parkingTicket);
    }

    public boolean isCarParkedInAnyParkingLots(Car car){
        return parkingLotsList.stream().anyMatch(parkingLot -> parkingLot.containsCar(car));
    }
}
