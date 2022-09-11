package mosa.fall2022;
import java.util.*;

import mosa.fall2022.datamanagement.TextFileParser;
import mosa.fall2022.processor.Processor;
import mosa.fall2022.ui.Printer;
import mosa.fall2022.utils.Employee;
import mosa.fall2022.utils.Schedule;

public class Main {

    public static void main(String[] args) {

        TextFileParser parser = new TextFileParser("sampledata2.txt");
        List<Employee> employeeList = parser.getEmployees();
        int days = parser.getNumberOfDays();

        System.out.println(employeeList);

        Processor processor = new Processor(employeeList, days);
        Schedule schedule = processor.run();
        System.out.println(schedule);
        Printer p = new Printer();
        
        for (Employee e : processor.employees) {
        	p.printEmployeeSchedule(e);
        }

        
    }
}
