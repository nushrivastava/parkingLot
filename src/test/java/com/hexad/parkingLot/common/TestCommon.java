package com.hexad.parkingLot.common;

import java.util.HashSet;
import java.util.Set;

import com.hexad.parkingLot.model.Slot;

public class TestCommon {
	protected static final Integer SAMPLE_PARKING_SLOTS = 6;

	protected static final String SAMPLE_REGISTRATION_ID = "KA-01-HH-9999";

	protected static final String SAMPLE_CAR_COLOR = "White";
	
	protected Set<Slot> buildParkingLot() {
		int totalSlots = SAMPLE_PARKING_SLOTS;
		Set<Slot> parkingSlots = new HashSet<>(totalSlots);
		while (totalSlots > 0) {
			parkingSlots.add(new Slot());
			totalSlots--;
		}
		return parkingSlots;
	}
}
