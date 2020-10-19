package com.oocl.cultivation;

import com.oocl.exceptions.ParkingBoyNotInListException;
import com.oocl.exceptions.ParkingLotOutOfPositionsException;
import com.oocl.exceptions.TicketNotProvidedException;
import com.oocl.exceptions.UnrecognizedTicketException;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ParkingManagerTest {


    //Story 6 (AC1) - Test Case 1:
    // Given Parking Lot Service Manager
    // When Manager Adds Parking Boy to Management List
    // Then Parking Boy is Successfully Added
    @Test
    public void should_be_added_to_management_list_when_add_parking_boy_given_parking_lot_service_manager() {
        //given
        ParkingManager parkingManager = new ParkingManager();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);

        //when
        parkingManager.addToManagementList(parkingBoy);

        //then
        assertTrue(parkingManager.isInManagementList(parkingBoy));
    }

    //Story 6 (AC1) - Test Case 2:
    // Given Parking Lot Service Manager
    // When Manager Assigns Parking Boy to Park Car
    // Then Car is Parked and Ticket is Returned
    @Test
    public void should_return_ticket_when_parking_boy_park_car_given_parking_lot_service_manager() {
        //given
        ParkingManager parkingManager = new ParkingManager();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingManager.addToManagementList(parkingBoy);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingManager.assignParkingBoyToParkCar(parkingBoy, car);

        //then
        assertTrue(parkingLot.containsTicket(parkingTicket));
    }

    //Story 6 (AC1) - Test Case 3:
    // Given Parking Lot Service Manager and Ticket
    // When Manager Assigns Parking Boy to fetch Car
    // Then Car is Fetched and Ticket is Returned
    @Test
    public void should_return_fetched_car_when_parking_boy_fetch_car_given_parking_lot_service_manager() {
        //given
        ParkingManager parkingManager = new ParkingManager();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingManager.addToManagementList(parkingBoy);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingManager.assignParkingBoyToParkCar(parkingBoy, car);

        //when
        Car fetchedCar = parkingManager.assignParkingBoyToFetchCar(parkingBoy, parkingTicket);

        //then
        assertSame(car, fetchedCar);
    }

    //Story 6 (AC1) - Test Case 4:
    // Given Parking Lot Service Manager and Parking Boy
    // When Manager Assigns Parking Boy who is not in management list to park Car
    // Then Car is not Parked
    @Test
    public void should_not_return_ticket_when_parking_boy_not_in_list_fetch_car_given_parking_lot_service_manager() {
        //given
        ParkingManager parkingManager = new ParkingManager();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        Car car = new Car();

        //when-then
        assertThrows(ParkingBoyNotInListException.class, () -> {
            parkingManager.assignParkingBoyToParkCar(parkingBoy, car);
        });
    }

    //Story 6 (AC2) - Test Case 1:
    // Given Parking Lot Service Manager and Parking Lot
    // When Manager is Assigned a Parking Lot
    // Then Parking Lot is Successfully Added
    @Test
    public void should_return_parking_lot_assigned_to_parking_manager_when_add_parking_lot_given_parking_lot_service_manager() {
        //given
        ParkingManager parkingManager = new ParkingManager();
        ParkingLot parkingLot = new ParkingLot();

        //when
        parkingManager.addParkingLotToParkingLotList(parkingLot);

        // then
        assertTrue(parkingManager.isInParkingLotList(parkingLot));
    }

    //Story 6 (AC2) - Test Case 2:
    // Given Parking Lot Service Manager and Parking Lot
    // When Manager Parks Car
    // Then Parking Ticket is Returned
    @Test
    public void should_return_parking_ticket_when_parking_lot_service_manager_parks_car_given_parking_lot_service_manager() {
        //given
        ParkingManager parkingManager = new ParkingManager();
        ParkingLot parkingLot = new ParkingLot();
        parkingManager.addParkingLotToParkingLotList(parkingLot);
        Car car = new Car();

        //when
        ParkingTicket parkingTicket = parkingManager.park(car);

        // then
        assertNotNull(parkingTicket);
    }

    //Story 6 (AC2) - Test Case 3:
    // Given Parking Lot Service Manager and Parking Lot
    // When Manager Fetches Car
    // Then Car is Returned
    @Test
    public void should_return_car_when_parking_lot_service_manager_fetch_car_given_parking_lot_service_manager() {
        //given
        ParkingManager parkingManager = new ParkingManager();
        ParkingLot parkingLot = new ParkingLot();
        parkingManager.addParkingLotToParkingLotList(parkingLot);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingManager.park(car);

        //when
        Car fetchedCar = parkingManager.fetchCar(parkingTicket);

        //then
        assertSame(car, fetchedCar);
    }

    //Story 6 (AC2) - Test Case 3:
    // Given Two Parking Lot Service Managers and Two Parking Lots
    // When Manager Fetches Car from other Lot owned by other manager
    // Then No Car is Returned (UnrecognizedTicketException)
    @Test
    public void should_return_no_car_when_parking_lot_service_manager_fetch_car_given_ticket_from_another_parking_lot() {
        //given
        ParkingManager parkingManager1 = new ParkingManager();
        ParkingManager parkingManager2 = new ParkingManager();
        ParkingLot parkingLot1 = new ParkingLot();
        ParkingLot parkingLot2 = new ParkingLot();
        parkingManager1.addParkingLotToParkingLotList(parkingLot1);
        parkingManager2.addParkingLotToParkingLotList(parkingLot2);
        Car car1 = new Car();
        Car car2 = new Car();
        parkingManager1.park(car1);
        ParkingTicket parkingTicket2 = parkingManager2.park(car2);

        //when-then
        assertThrows(UnrecognizedTicketException.class, () -> {
            parkingManager1.fetchCar(parkingTicket2);
        });
    }

    //Story 6 (AC3) - Test Case 1:
    // Given Parking Lot Service Manager and Wrong Ticket
    // When Manager Assigns Parking Boy to Fetch Car
    // Then No Car is Returned (UnrecognizedTicketException)
    @Test
    public void should_return_no_car_when_parking_boy_fetch_car_given_parking_lot_service_manager_wrong_ticket() {
        //given
        ParkingManager parkingManager = new ParkingManager();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingManager.addToManagementList(parkingBoy);
        Car car = new Car();

        //when
        parkingManager.assignParkingBoyToParkCar(parkingBoy, car);
        ParkingTicket wrongTicket = new ParkingTicket();

        //then
        assertThrows(UnrecognizedTicketException.class, () -> {
            parkingManager.assignParkingBoyToFetchCar(parkingBoy, wrongTicket);
        });
    }

    //Story 6 (AC3) - Test Case 2:
    // Given Parking Lot Service Manager and Used Ticket
    // When Manager Assigns Parking Boy to Fetch Car
    // Then No Car is Returned (UnrecognizedTicketException)
    @Test
    public void should_return_no_car_when_parking_boy_fetch_car_given_parking_lot_service_manager_asks_parking_boy_to_park_car_with_used_ticket() {
        //given
        ParkingManager parkingManager = new ParkingManager();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingManager.addToManagementList(parkingBoy);
        Car car = new Car();
        ParkingTicket parkingTicket = parkingManager.assignParkingBoyToParkCar(parkingBoy, car);

        //when
        parkingManager.assignParkingBoyToFetchCar(parkingBoy, parkingTicket);

        //then
        assertThrows(UnrecognizedTicketException.class, () -> {
            parkingManager.assignParkingBoyToFetchCar(parkingBoy, parkingTicket);
        });
    }

    //Story 6 (AC3) - Test Case 3:
    // Given Parking Lot Service Manager and No Ticket
    // When Manager Assigns Parking Boy to Fetch Car
    // Then No Car is Returned (TicketNotProvidedException)
    @Test
    public void should_return_no_car_when_parking_boy_fetch_car_given_parking_lot_service_manager_no_ticket() {
        //given
        ParkingManager parkingManager = new ParkingManager();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot();
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingManager.addToManagementList(parkingBoy);

        //when-then
        assertThrows(TicketNotProvidedException.class, () -> {
            parkingManager.assignParkingBoyToFetchCar(parkingBoy, null);
        });
    }

    //Story 6 (AC3) - Test Case 4:
    // Given Parking Lot Service Manager and Car Parked in Lot with Capacity 1
    // When Manager Assigns Parking Boy to Fetch Another Car in Full Parking Lot
    // Then No Ticket is Returned (ParkingLotOutOfPositionsException)
    @Test
    public void should_not_return_ticket_when_park_car_given_parking_boy_to_park_car_when_parking_lot_is_full() {
        //given
        ParkingManager parkingManager = new ParkingManager();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingLot parkingLot = new ParkingLot(1);
        parkingLotList.add(parkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        parkingManager.addToManagementList(parkingBoy);
        Car car1 = new Car();
        Car car2 = new Car();
        parkingManager.assignParkingBoyToParkCar(parkingBoy,car1);

        //when-then
        assertThrows(ParkingLotOutOfPositionsException.class, () -> {
            parkingManager.assignParkingBoyToParkCar(parkingBoy, car2);
        });
    }

    //Story 6 - Extra Test Case: Given Parking Manager and Parking Lot When Parking Manager assigns Parking Lot to Parking Boy Then Parking Lot is successfully assigned to Parking Boy
    @Test
    public void should_be_assigned_properly_when_assign_parking_lot_to_parking_boy_given_parking_lot_service_manager() {
        //given
        ParkingManager parkingManager = new ParkingManager();
        List<ParkingLot> parkingLotList = new ArrayList<>();
        ParkingBoy parkingBoy = new ParkingBoy(parkingLotList);
        ParkingLot parkingLot = new ParkingLot();

        //when
        parkingManager.assignParkingLot(parkingBoy, parkingLot);

        //then
        assertTrue(parkingBoy.isAssignedParkingLot(parkingLot));
    }
}
