package com.hexad.parkingLot.service;

import java.io.PrintStream;
import java.util.List;

import org.springframework.stereotype.Service;

import com.hexad.parkingLot.model.ParkingLotStatusResponse;

/**
 * Sends the output of a command back to standard output.
 * 
 * @author nushrivastava
 *
 */
@Service
public class ConsoleService {

	private final PrintStream out = System.out;
	public static final String format = "%-20s %-30s %s %n";

	public void write(String msg) {
		this.out.printf(msg);
		this.out.println();
	}
	
	public void write(String msg, String... args) {
		this.out.printf(msg, (Object[]) args);
		this.out.println();
	}
	
	public void write(String msg, Integer... args) {
		this.out.printf(msg, (Object[]) args);
		this.out.println();
	}
	
	public void writeStatus(String msg, List<ParkingLotStatusResponse> response) {
		Object [] header = new Object[] {"Slot No.", "Registration No", "Colour"};
		this.out.printf(format, header);
		response.forEach(element -> {
			Object [] data = new Object[] {element.getSlotNumber(), element.getRegistrationId(), element.getColor()};
			this.out.printf(format, data);
		});
		this.out.println();
	}
}
