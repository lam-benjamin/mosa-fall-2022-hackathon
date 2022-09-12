package mosa.fall2022.ui;

import mosa.fall2022.utils.Employee;
import mosa.fall2022.utils.Schedule;

public class Printer {

	public Printer() {}
	
	public void print(Employee e) {
		StringBuilder stringBuilder = new StringBuilder();
		
		stringBuilder.append("Name: "        + e.getName()       +
					       ", Quota: "       + e.getQuota()      +
				           ", # of Shifts: " + e.getShiftCount() + "\n" +
				             "Schedule: "    + e.getSchedule()   + "\n");
		
		System.out.println(stringBuilder.toString());
	}
	
	public void print(Schedule schedule) {
		System.out.println(schedule);
	}
	
}
