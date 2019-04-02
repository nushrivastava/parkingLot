package com.hexad.parkingLot.service;

import java.util.List;
import java.util.Set;

import com.hexad.parkingLot.model.ParkingLotStatusResponse;
import com.hexad.parkingLot.model.Slot;

/**
 * Interface that define the functionaly of the parking lot.
 * 
 * @author nushrivastava
 *
 */
public interface ParkingLotService {

	/**
	 * Creates parking slot with the given number of slots. Each slot will
	 * contain exactly one car at any time.
	 * 
	 * @param totalSlots
	 * @return 
	 */
	Set<Slot> createParkingLotWithSlots(Integer totalSlots);

	/**
	 * Parks a car to earliest available slot and returns the allocated slot
	 * number.
	 * 
	 * @param registrationId
	 * @param color
	 * @return slot number
	 */
	Slot parkCar(String registrationId, String color);

	/**
	 * Removes a car from the given slot number.
	 * 
	 * @param slotNumber
	 */
	void removeCarFromSlot(Integer slotNumber);

	/**
	 * Returns the status of all the slots of the parking lot, with the
	 * registration id and color of the car.
	 * 
	 * @return
	 */
	List<ParkingLotStatusResponse> getParkingLotStatus();

	/**
	 * Returns all cars by the given color.
	 * 
	 * @param color
	 * @return List of registrationIds
	 */
	Set<String> getCarsByColor(String color);

	/**
	 * Returns all slot numbers with a car of the given color.
	 * 
	 * @param color
	 * @return List of slots
	 */
	Set<Integer> getSlotsByColor(String color);

	/**
	 * Returns the slot number where the given registration id car is parked.
	 * 
	 * @param registrationId
	 * @return slotNumber
	 */
	Integer getSlotByCarRegistrationId(String registrationId);

	/**
	 * Returns total capacity of the parking lot
	 * 
	 * @return
	 */
	Integer getParkingLotCapacity();

	/**
	 * Returns current capacity of the parking lot
	 * 
	 * @return
	 */
	Integer getParkingLotCurrentCapacity();
}