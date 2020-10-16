package com.oocl.cultivation;

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
        ParkingTicket parkingTicket = new ParkingTicket();
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
        return parkingTicketCarMap.size() >= capacity;
    }
}
