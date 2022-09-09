package mosa.fall2022.processor;

import mosa.fall2022.utils.Employee;
import mosa.fall2022.utils.Schedule;
import mosa.fall2022.utils.exceptions.InsufficientEmployeeException;

import java.util.*;

public class Processor {

    public Schedule schedule;
    public Map<Integer, Set<Employee>> availabilityMap;

    public Processor(List<Employee> employees, int days){
        schedule = new Schedule(days);
        availabilityMap = new TreeMap<>();
        initAvailabilityMap(employees, days);
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

    public void checkIfAssignmentIsImpossible(){
        int totalDays = schedule.getTotalDays();
        List<Integer> emptyDays = new ArrayList<Integer>();
        for(int d = 1; d <= totalDays; d++){
            if( schedule.getEmployeeAssignedForGivenDay(d) != null){
                continue;
            }
            if ( availabilityMap.get(d).size() == 0){
                emptyDays.add(d);
            }
        }

        if (!emptyDays.isEmpty() ){
            throw new InsufficientEmployeeException(emptyDays);
        }
    }

    public Schedule assignEmployeesDeterministically(){
        int filledDays = -1;
        while(filledDays != schedule.getFilledDays()){
            filledDays = schedule.getFilledDays();

            Integer day = findDayWithLoneCandidate();
            if (day == null){
                return schedule;
            }

            assignLoneCandidate(day);
            checkIfAssignmentIsImpossible();
        }

        return schedule;
    }

    public Integer findDayWithLoneCandidate(){
        for(int day: availabilityMap.keySet()){
            if (
                schedule.getEmployeeAssignedForGivenDay(day) == null
                &&  availabilityMap.get(day).size() == 1
            ){
                return day;
            }
        }
        return null;
    }

    public void assignLoneCandidate(int day){
        for(Employee e: availabilityMap.get(day) ){
            if ( !schedule.assignEmployee(day, e) ){
                throw new RuntimeException("Lone employee quota met for day:" + day );
            }
            removeEmployeeForNeighboringDays(e, day);
        }
    }

    public void removeEmployeeForNeighboringDays(Employee employee, int day){
        int dayBefore = day - 1;
        int dayAfter = day + 1;
        if (dayBefore >= 1){
            availabilityMap.get(dayBefore).remove(employee);
        }
        if (dayAfter <= schedule.getTotalDays() ) {
            availabilityMap.get(dayAfter).remove(employee);
        }
    }

}
