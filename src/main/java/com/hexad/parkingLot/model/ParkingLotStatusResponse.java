package com.hexad.parkingLot.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * Parking lot status response object.
 * 
 * @author nushrivastava
 *
 */
public class ParkingLotStatusResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	@JsonProperty("Slot No.")
	private Integer slotNumber;
	@JsonProperty("Registration No ")
	private String registrationId;
	@JsonProperty("Colour")
	private String color;

	public Integer getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(Integer slotNumber) {
		this.slotNumber = slotNumber;
	}

	public String getRegistrationId() {
		return registrationId;
	}

	public void setRegistrationId(String registrationId) {
		this.registrationId = registrationId;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "ParkingLotStatusResponse [slotNumber=" + slotNumber + ", registrationId=" + registrationId + ", color="
				+ color + "]";
	}
}