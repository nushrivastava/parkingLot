package com.hexad.parkingLot.model;

import java.io.Serializable;

/**
 * This bean contains Car attributes which will occupy a parking slot.
 * 
 * @author nushrivastava
 *
 */
public class Car implements Serializable{

	private static final long serialVersionUID = 1L;

	private String registrationId;
	private String color;
	private Integer slotNumber;

	public Car(String registrationId, String color, Integer slotNumber) {
		super();
		this.registrationId = registrationId;
		this.color = color;
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

	public Integer getSlotNumber() {
		return slotNumber;
	}

	public void setSlotNumber(Integer slotNumber) {
		this.slotNumber = slotNumber;
	}

	@Override
	public String toString() {
		return "Car [registrationId=" + registrationId + ", color=" + color + ", slotNumber=" + slotNumber + "]";
	}
}
