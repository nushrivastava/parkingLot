package com.hexad.parkingLot.service;

import java.util.ArrayList;import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;

import com.hexad.parkingLot.model.Car;
import com.hexad.parkingLot.model.ParkingLotStatusResponse;
import com.hexad.parkingLot.model.Slot;

/**
 * Provides implementation for use cases given in {@code ParkingLotService}
 * 
 * @author nushrivastava
 *
 */
@Service
public class ParkingLotServiceImpl implements ParkingLotService {

	private static Integer capacity = 0;
	public static final Set<Slot> parkingSlots = new HashSet<>();
	// private final Map<String, Integer> parkedCars = new HashMap<>();

	@Override
	public Integer getParkingLotCapacity() {
		return capacity;
	}
	
	@Override
	public Integer getParkingLotCurrentCapacity() {
		return parkingSlots.size();
	}
	
	@Override
	public Set<Slot> createParkingLotWithSlots(Integer totalSlots) {
		parkingSlots.clear();
		capacity = totalSlots;
		while (totalSlots > 0) {
			parkingSlots.add(new Slot());
			totalSlots--;
		}
		return parkingSlots;
	}

	@Override
	public Slot parkCar(String registrationId, String color) {
		Slot targetSlot = parkingSlots.stream().filter(slot -> (null == slot.getParkedCar()))
				.min(Comparator.comparingInt(Slot::getId)).orElse(null);
		if (null != targetSlot) {
			Car car = new Car(registrationId, color, targetSlot.getId());
			targetSlot.setParkedCar(car);
			capacity--;
			// parkedCars.put(registrationId, targetSlot.getId());
		}
		return targetSlot;
	}

	@Override
	public void removeCarFromSlot(Integer slotNumber) {
		Slot targetSlot = parkingSlots.parallelStream()
				.filter(slot -> (null != slot.getParkedCar() && slot.getId() == slotNumber)).findFirst().orElse(null);
		if (null != targetSlot) {
			targetSlot.setParkedCar(null);
			capacity++;
			// parkedCars.remove(targetSlot.getParkedCar().getRegistrationId());
		}
	}

	@Override
	public List<ParkingLotStatusResponse> getParkingLotStatus() {
		List<ParkingLotStatusResponse> parkingLotStatusResponse = new ArrayList<>();
		parkingSlots.forEach(parkingSlot -> {
			if (null != parkingSlot.getParkedCar()) {
				ParkingLotStatusResponse statusResponse = new ParkingLotStatusResponse();
				Car parkedCar = parkingSlot.getParkedCar();
				statusResponse.setRegistrationId(parkedCar.getRegistrationId());
				statusResponse.setColor(parkedCar.getColor());
				statusResponse.setSlotNumber(parkingSlot.getId());
				parkingLotStatusResponse.add(statusResponse);
			}
		});
		return parkingLotStatusResponse;
	}

	@Override
	public Set<String> getCarsByColor(String color) {
		Set<String> carRegistrationIds = new HashSet<>();
		parkingSlots.parallelStream()
				.filter(slot -> (null != slot.getParkedCar() && slot.getParkedCar().getColor().equals(color)))
				.forEach(slot -> {
					carRegistrationIds.add(slot.getParkedCar().getRegistrationId());
				});
		return carRegistrationIds;
	}

	@Override
	public Set<Integer> getSlotsByColor(String color) {
		Set<Integer> slotsIds = new HashSet<>();
		parkingSlots.parallelStream()
				.filter(slot -> (null != slot.getParkedCar() && slot.getParkedCar().getColor().equals(color)))
				.forEach(slot -> {
					slotsIds.add(slot.getId());
				});
		return slotsIds;
	}

	@Override
	public Integer getSlotByCarRegistrationId(String registrationId) {
		return parkingSlots.parallelStream().filter(
				slot -> (null != slot.getParkedCar() && slot.getParkedCar().getRegistrationId().equals(registrationId)))
				.findFirst().get().getId();
	}

}
