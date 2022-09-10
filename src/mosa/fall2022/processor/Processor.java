package mosa.fall2022.processor;

import mosa.fall2022.utils.Employee;
import mosa.fall2022.utils.Data;
import mosa.fall2022.utils.Schedule;
import mosa.fall2022.utils.exceptions.InsufficientEmployeeException;

import java.util.*;
import java.util.stream.Collectors;

public class Processor {

	//initialize empty stack to keep track of valid sub-schedules. We want a stack because we'll find a valid schedule for the biggest, most time consuming
	//sub-schedules first. If any valid sub-schedule makes it impossible to find a valid sub-schedule in a different range of days, then we'll pop the most
	//recent valid sub-schedule (the least time-consuming one to dfs again) from the stack and using the [root, leaf] nodes from that sub-schedule, find
	//a new valid sub-schedule in that tree.

    int day;
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

    public void checkIfAssignmentIsImpossible(){
        int totalDays = schedule.getTotalDays();
        List<Integer> emptyDays = new ArrayList<Integer>();
        for(int d = 1; d <= totalDays; d++){
            if( schedule.getEmployeeAssignedForGivenDay(d) != null){
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

    public Schedule run(){
        assignEmployeesDeterministically();
        if (schedule.getFilledDays() == daysInMonth){
            return schedule;
        }

        GraphTraversalHelper graphTraversalHelper = new GraphTraversalHelper(availabilityMap, employees, daysInMonth);
        graphTraversalHelper.dfs(1, daysInMonth);


        Node root = graphTraversalHelper.rootNode;
        Node node = graphTraversalHelper.endNode;

        for(
            Node currentNode = graphTraversalHelper.endNode;
            currentNode.parent != null;
            currentNode = currentNode.parent
        ){
            schedule.assignEmployee(currentNode.day, currentNode.assignedEmployee);
        }

        String output = String.valueOf(node.day) + ": " + node.assignedEmployee.getName() + " - " + node.shiftCountsCart.get(node.assignedEmployee);
        while (!(node = node.parent).equals(root)) {

        	output = String.valueOf(node.day) + ": " + node.assignedEmployee.getName() + " - " + node.shiftCountsCart.get(node.assignedEmployee) + "\n" + output;
        }
        System.out.println(output);

        return schedule;
    }
}
