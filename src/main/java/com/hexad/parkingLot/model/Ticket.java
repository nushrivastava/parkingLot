package com.hexad.parkingLot.model;

import java.io.Serializable;

/**
 * This bean defines what attributes a parking ticket will hold.
 * 
 * @author nushrivastava
 *
 */
public class Ticket implements Serializable {

	private static final long serialVersionUID = 1L;

	private final long startTime = System.currentTimeMillis();
	private Double cost;
	private final Car car;

	public Ticket(Car car) {
		super();
		this.car = car;
	}

	public long calculateParkingDuration() {
		return System.currentTimeMillis() - startTime;
	}

	public double calculateCost(Double costFactor){
		return calculateParkingDuration() * costFactor;
    }

	public long getStartTime() {
		return startTime;
	}

	public Car getCar() {
		return car;
	}

	public Double getCost() {
		return cost;
	}

	public void setCost(Double cost) {
		this.cost = cost;
	}

	@Override
	public String toString() {
		return "Ticket [startTime=" + startTime + ", cost=" + cost + ", car=" + car + "]";
	}
}
