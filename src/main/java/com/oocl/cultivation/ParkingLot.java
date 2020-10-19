package com.oocl.cultivation;

import com.oocl.exceptions.ParkingLotOutOfPositionsException;
import com.oocl.exceptions.TicketNotProvidedException;
import com.oocl.exceptions.UnrecognizedTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private Map<ParkingTicket, Car> parkingTicketCarMap;
    private int capacity;

    public ParkingLot() {
        this.capacity = 10;
        this.parkingTicketCarMap = new HashMap<>();
    }

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        this.parkingTicketCarMap = new HashMap<>();
    }

    public ParkingTicket park(Car car) {
        ParkingTicket parkingTicket  = new ParkingTicket();
        parkingTicketCarMap.put(parkingTicket, car);
        return parkingTicket;
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        Car fetchedCar = parkingTicketCarMap.remove(parkingTicket);
        if(parkingTicket!=null && fetchedCar==null) {
            throw new UnrecognizedTicketException();
        } else if(parkingTicket==null){
            throw new TicketNotProvidedException();
        }
        return fetchedCar;
    }

    public boolean isFull(){
        return getParkingTicketCarMapSize() >= getCapacity();
    }

    public boolean containsTicket(ParkingTicket parkingTicket){
        return parkingTicketCarMap.containsKey(parkingTicket);
    }

    public boolean containsCar(Car car){
        return parkingTicketCarMap.containsValue(car);
    }

    public int getCapacity() {
        return capacity;
    }

    public int getParkingTicketCarMapSize(){
        return parkingTicketCarMap.size();
    }

    public int getRemainingSpaces(){
        return getCapacity()-getParkingTicketCarMapSize();
    }

    public double getAvailableSpaceRatio(){
        return (double) getRemainingSpaces() / (double) getCapacity();
    }
}
