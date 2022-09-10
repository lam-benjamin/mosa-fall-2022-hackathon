package mosa.fall2022.ui;

import mosa.fall2022.utils.Employee;

public class Printer {

	public Printer() {}
	
	public void printEmployeeSchedule(Employee e) {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("Name: "        + e.getName()       +
					       ", Quota: "       + e.getQuota()      +
				           ", # of Shifts: " + e.getShiftCount() + "\n" +
				             "Schedule: "    + e.getSchedule()   + "\n");
		
		System.out.println(stringBuilder.toString());
	}
	
	public void printSchedule() {
		System.out.println();
	}
	
}
