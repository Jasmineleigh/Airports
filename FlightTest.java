package prob2;

import java.time.LocalDate;

import prob1.Airport;

public class FlightTest {
	public static void main(String[]args) throws Exception {
		testGetters();
		testCreateFlight_InvalidFlightNum();
		testCreateFlight_ValidFlightNum();
	}
	
	public static void testGetters() throws Exception {
		System.out.println("-->testGetters<--");
		Airport origin = new Airport("VLD", 30.78, 83.28, "Valdosta", "GA");
		Airport destination = new Airport("JAX", 30.50, 81.70, "JacksonVille", "FL");
		Flight flight = new Flight("1243", LocalDate.of(2021, 11, 28), origin, destination, 680.29);
		
		System.out.println("Get Cost-> Expected: $680.29, Actual: $" + flight.getCost());
		System.out.println("Get Date-> Expected: 2021-11-28, Actual: " + flight.getDate());
		System.out.println("Get Destination-> Expected: JAX-JacksonVille, FL: 30.50, 81.70, Actual: " + flight.getDestination());
		System.out.printf("Get Distance-> Expected: 95.90 , Actual: %.2f\n", flight.getDistance());
		System.out.println("Get Number-> Expect: 1243, Actual: " + flight.getNumber());
		System.out.println("Get Origin-> Expected: VLD-Valdosta, GA: 30.78, 83.28, Actual: " + flight.getOrigin() + "\n");
	}
	
	public static void testCreateFlight_InvalidFlightNum() {
		System.out.println("-->testCreateFlight_InvalidFlightNum<--");
		String str = "";
		System.out.printf("\n%-15sExpected\n----------------------------------------\nInvalid Flight Number. Must be 4 digits.\n"
				+ "\n%-16sActual\n----------------------------------------\n", str, str);
		try {
			Flight flight1 = new Flight("123", LocalDate.of(2021, 11, 28), new Airport("VLD", 30.78, 83.28, "Valdosta", "GA"), 
										new Airport("JAX", 30.50, 81.70, "JacksonVille", "FL"), 680.29);
		}
		catch(Exception e) {
			System.out.println("Invalid Flight Number. Must be 4 digits.");
		}
		System.out.println();
	}
	
	public static void testCreateFlight_ValidFlightNum() {
		System.out.println("-->testCreateFlight_InvalidFlightNum<--");
		Airport origin = new Airport("VLD", 30.78, 83.28, "Valdosta", "GA");
		Airport destination = new Airport("JAX", 30.50, 81.70, "JacksonVille", "FL");
		String str = "";
		
		System.out.printf("\n%-12sExpected\n---------------------------------\n", str);
		
		String msg = String.format("%-2sFlight: 1243, Date: 2021-28-11\n%-4sFrom: VLD-Valdosta, GA\n "
				+ "%-4sTo: JAX-JacksonVille, FL\nDistance: 96 miles, Cost: $680.29", str, str, str);
		System.out.println(msg + "\n");
		System.out.printf("%-13sActual\n---------------------------------\n", str);
		
		try {
			Flight flight1 = new Flight("1243", LocalDate.of(2021, 11, 28), origin, destination, 680.29);
			System.out.println(flight1);
		}
		catch(Exception e) {
			System.out.println("Invalid Flight Number. Must be 4 digits.");
		}
		
		System.out.println();
	}
}
