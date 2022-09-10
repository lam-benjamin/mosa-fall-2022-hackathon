package mosa.fall2022.processor;

import mosa.fall2022.utils.Employee;
import mosa.fall2022.utils.Schedule;
import mosa.fall2022.utils.exceptions.InsufficientEmployeeException;

import java.util.*;

public class Processor {

    //int day;
    public Schedule schedule;
    public Map<Integer, Set<Employee>> availabilityMap = new TreeMap<>();
    public final List<Employee> employees;
    public final int daysInMonth;
	
    public Processor(List<Employee> employeeList, int numOfDays){
        employees = employeeList;
        daysInMonth = numOfDays;
        schedule = new Schedule(numOfDays);
        initAvailabilityMap();
    }
    
    public void initAvailabilityMap(){
        for (int d = 1; d <= daysInMonth; d++){
            availabilityMap.put(d, new HashSet<Employee>());
        }
        addEmployeesToAvailabilityMap();
    }

    public void addEmployeesToAvailabilityMap(){
        for(Employee e: employees){
            for(Integer day: e.getAvailability()){
                Set<Employee> availableOnDay = availabilityMap.get(day);
                availableOnDay.add(e);
            }
        }
    }

    public void checkIfAssignmentIsImpossible(){;
        List<Integer> emptyDays = new ArrayList<Integer>();
        for(int d = 1; d <= daysInMonth; d++){
            if (schedule.getEmployeeAssignedForGivenDay(d) != null){
                continue;
            }
            if (availabilityMap.get(d).size() == 0){
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
        for(int day = 1; day <= daysInMonth; day++){
            if (
            	availabilityMap.get(day).size() == 1 &&
                schedule.getEmployeeAssignedForGivenDay(day) == null
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
    

    public Schedule run(){
        assignEmployeesDeterministically();
        if (schedule.getFilledDays() == daysInMonth){
            return schedule;
        }

        GraphTraversalHelper graphTraversalHelper = new GraphTraversalHelper(availabilityMap, employees, daysInMonth);
        graphTraversalHelper.dfs(1, daysInMonth);

        for(
            Node currentNode = graphTraversalHelper.endNode;
            currentNode.parent != null;
            currentNode = currentNode.parent
        ){
            schedule.assignEmployee(currentNode.day, currentNode.assignedEmployee);
        }

        return schedule;
    }
}
