package com.hexad.parkingLot.service;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;

import java.lang.reflect.Field;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import com.hexad.parkingLot.common.TestCommon;
import com.hexad.parkingLot.model.Car;
import com.hexad.parkingLot.model.Slot;

/**
 * Unit tests for {@code ParkingLotServiceImpl}
 * 
 * @author nushrivastava
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(ParkingLotServiceImpl.class)
public class ParkingLotServiceImplTest extends TestCommon {

	@InjectMocks
	private ParkingLotServiceImpl parkingLotService;

	/**
	 * Tests if parking lot service is able to create a parking lot with the
	 * given number of slots.
	 */
	@Test
	public void test_Success_CreateParkingLotWithSlots() {
		// execute
		Set<Slot> parkingSlot = parkingLotService.createParkingLotWithSlots(SAMPLE_PARKING_SLOTS);

		// assert
		parkingSlot.forEach(slot -> {
			Assert.assertNull(slot.getParkedCar());
			Assert.assertTrue(slot.getId() > 0);
		});
	}

	/**
	 * Tests Park car functionality when the parking lot is full.
	 * 
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test_Success_ParkCar()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		// setup
		Field mockedParkingSlots = ParkingLotServiceImpl.class.getField("parkingSlots");
		mockedParkingSlots.setAccessible(true);
		mockedParkingSlots.set(null, buildParkingLot());

		// execute
		Slot parkingSlot = parkingLotService.parkCar(SAMPLE_REGISTRATION_ID, SAMPLE_CAR_COLOR);

		// assert
		Assert.assertTrue(parkingSlot.getId() == 1);
		Assert.assertEquals(parkingSlot.getParkedCar().getRegistrationId(), SAMPLE_REGISTRATION_ID);
	}

	/**
	 * Tests Park car functionality for the parking lot
	 * 
	 * @throws NoSuchFieldException
	 * @throws SecurityException
	 * @throws IllegalArgumentException
	 * @throws IllegalAccessException
	 */
	@Test
	public void test_Failure_ParkCar_NoSlotPresent()
			throws NoSuchFieldException, SecurityException, IllegalArgumentException, IllegalAccessException {
		// setup
		Set<Slot> fullParkingSlot = buildParkingLot();
		fullParkingSlot.forEach(slot -> slot.setParkedCar(new Car(anyString(), anyString(), anyInt())));

		Field mockedParkingSlots = ParkingLotServiceImpl.class.getField("parkingSlots");
		mockedParkingSlots.setAccessible(true);
		mockedParkingSlots.set(null, fullParkingSlot);

		// execute
		Slot parkingSlot = parkingLotService.parkCar(SAMPLE_REGISTRATION_ID, SAMPLE_CAR_COLOR);

		// assert
		Assert.assertNull(parkingSlot);
	}

}
