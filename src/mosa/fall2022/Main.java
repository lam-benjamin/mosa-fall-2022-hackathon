package mosa.fall2022;
import java.util.*;

import mosa.fall2022.datamanagement.TextFileParser;
import mosa.fall2022.processor.Processor;
import mosa.fall2022.ui.Printer;
import mosa.fall2022.utils.Employee;
import mosa.fall2022.utils.Schedule;
import mosa.fall2022.demo.*;

public class Main {
	
	
	
    public static void main(String[] args) {
    	
    	
    	String filename;
    	if (args.length != 0 && args[0].equals("demo")) { //if runtime arg is "demo", run the demo
    		Demo demo = new Demo();
    		demo.run();
    		return;
    		
    	} else if (args.length != 0) { //if a runtime arg is supplied but not equal to "demo", read that file
    		filename = args[0];
    	
    	} else { //if no runtime arg is supplied, read input.txt
    		filename = "input.txt";
    	}

        TextFileParser parser = new TextFileParser(filename);
        List<Employee> employeeList = parser.getEmployees();
        int days = parser.getNumberOfDays();
        
        boolean randomness = false;
        Processor processor = new Processor(employeeList, days, randomness);
        Schedule schedule = processor.run();
        
        Printer p = new Printer();
        p.print(schedule);
        for (Employee employee : processor.employees) {
        	p.print(employee);
        }

    }
    
}
