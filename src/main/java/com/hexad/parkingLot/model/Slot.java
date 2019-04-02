package com.hexad.parkingLot.model;

import java.io.Serializable;

/**
 * This bean defines what a slot will contain.
 * 
 * @author nushrivastava
 *
 */
public class Slot implements Serializable {

	private static final long serialVersionUID = 1L;

	private static int idCounter = 0;
	private final int id = ++idCounter;
	private Car parkedCar;

	public Car getParkedCar() {
		return parkedCar;
	}

	public void setParkedCar(Car parkedCar) {
		this.parkedCar = parkedCar;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		return "Slot [id=" + id + ", parkedCar=" + parkedCar + "]";
	}
}
