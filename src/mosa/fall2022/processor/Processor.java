package mosa.fall2022.processor;

import mosa.fall2022.utils.Employee;
import java.util.*;

public class Processor {

    public Map<Integer, Set<Employee>> availabilityMap = new TreeMap<>();

    public Processor(){


    }


    public void initAvailabilityMap(List<Employee> employees, int numOfDays){
        for (int d = 1; d <= numOfDays; d++){
            availabilityMap.put(d, new HashSet<Employee>());
        }
        addEmployeesToAvailabilityMap(employees);
    }

    public void addEmployeesToAvailabilityMap(List<Employee> employees){
        for(Employee e: employees){
            for(Integer day: e.getAvailability()){
                Set availableOnDay = availabilityMap.get(day);
                availableOnDay.add(e);
            }
        }
    }
}
