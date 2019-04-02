package com.hexad.parkingLot.controller;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.shell.Availability;
import org.springframework.shell.standard.ShellComponent;
import org.springframework.shell.standard.ShellMethod;

import com.hexad.parkingLot.model.Slot;
import com.hexad.parkingLot.service.ConsoleService;
import com.hexad.parkingLot.service.ParkingLotService;

/**
 * Provides all commands to interact with the parking lot services.
 * 
 * @author nushrivastava
 *
 */
@ShellComponent
public class ParkingLotCommands {

	@Autowired
	private ParkingLotService parkingLotService;
	
	@Autowired
	private ConsoleService consoleService;
	
	@Autowired
    private ConfigurableApplicationContext context;

	@ShellMethod("exit parking lot application")
	public void bye() {
		System.exit(SpringApplication.exit(context));
	}
	
	@ShellMethod(value = "creates a parking lot with the given number of slots", key = "create_parking_lot")
	public void createParkingLot(Integer totalSlots) {
		parkingLotService.createParkingLotWithSlots(totalSlots);
		consoleService.write("Created a parking lot with %s slots", totalSlots);
	}

	@ShellMethod("parks a car in a slot")
	public void park(String registrationId, String colour) {
		Slot slot = parkingLotService.parkCar(registrationId, colour);
		if(null != slot)
			consoleService.write("Allocated slot number: %s", slot.getId());
		else
			consoleService.write("Sorry, parking lot is full");
	}
	
	/*Availability parkAvailability() {
		if(parkingLotService.getParkingLotCapacity() > 0){
			return parkingLotService.getParkingLotCurrentCapacity() > 0 ? Availability.available()
					: Availability.unavailable("Sorry, parking lot is full");	
		}else{
			return Availability.unavailable("Parking Lot is not available");
		}
	}*/

	@ShellMethod("vacates a parking slot")
	public void leave(Integer slotNumber) {
		parkingLotService.removeCarFromSlot(slotNumber);
		consoleService.write("Slot number %s is free", slotNumber);
	}

	@ShellMethod("gets status of all slots in the parking lot")
	public void status() {
		consoleService.writeStatus("Status", parkingLotService.getParkingLotStatus());
	}
	
	@ShellMethod(value = "lists all car registration ids with the given colour", key = "registration_numbers_for_cars_with_colour")
	public Set<String> getCarsByColor(String colour) {
		return parkingLotService.getCarsByColor(colour);
	}

	@ShellMethod(value = "lists all slots with cars of given colour", key = "slot_numbers_for_cars_with_colour")
	public Set<Integer> getSlotsByColor(String colour) {
		return parkingLotService.getSlotsByColor(colour);
	}
	
	@ShellMethod(value = "returns slot number of a car registration id", key = "slot_number_for_registration_number")
	public void getSlotByCarRegistrationId(String registrationId) {
		consoleService.write("",parkingLotService.getSlotByCarRegistrationId(registrationId));
	}
}
