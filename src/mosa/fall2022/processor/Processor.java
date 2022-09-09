package mosa.fall2022.processor;

import mosa.fall2022.utils.Employee;
import mosa.fall2022.utils.Data;
import java.util.*;
import java.util.stream.Collectors;

public class Processor {

	//initialize empty stack to keep track of valid sub-schedules. We want a stack because we'll find a valid schedule for the biggest, most time consuming
	//sub-schedules first. If any valid sub-schedule makes it impossible to find a valid sub-schedule in a different range of days, then we'll pop the most
	//recent valid sub-schedule (the least time-consuming one to dfs again) from the stack and using the [root, leaf] nodes from that sub-schedule, find
	//a new valid sub-schedule in that tree. 
	public static Stack<Node[]> validSubSchedules = new Stack<Node[]>();
	
    public Processor(){
    }
    
    
    /**
     * Call dfs search on a section of the schedule using this method
     */
    public boolean dfs(int from, int to) {
    	Node root = new Node(from-1, null, null); //initialize a root node
    	validSubSchedules.push(new Node[] {root, null}); //save root node in outer scope for reference in other methods
    	return doDFS(root, to);
    }
    
    /**
     * Called from within dfs(int from, int to), recursively searches for the first valid schedule in that range of days
     */
    public boolean doDFS(Node start, int to) {
    	
    	//if the node that was passed into the last recursive call of doDFS is valid & the day is equal to the target day, that represents a
    	//successful schedule, so return true and save the root and valid leaf for use later if we need to find a new valid sub-schedule in this range
    	if (start.day == to && start.valid) { 
    		validSubSchedules.peek()[1] = start; //save the valid leaf node in outer scope for reference in other methods
    		return true; 
    	} 
    	
    	//shuffle and iterate through the set of Employees in the start Node's toExplore list
    	List<Employee> toExploreShuffled = start.toExplore.stream().collect(Collectors.toList());
    	Collections.shuffle(toExploreShuffled);
    	Iterator<Employee> it = toExploreShuffled.iterator();
    	while (it.hasNext()) {
    	    		
    		Employee nextEmployee = it.next();
    		
    		if (!start.explored.contains(nextEmployee)) {
    			Node newNode = new Node(start.day + 1, start, nextEmployee); //create a new node, which removes the Node's Employee from start.toExplore
    			
    			if (newNode.valid && doDFS(newNode, to)) { //if this new node is valid, doDFS on it
        			return true;
        		}
    		}    		
    	}
    	
    	return false;
    }
     
    
    /**
     * repeatedly fills the schedule for all days where only one Employee is available
     */
    int day;
    public void assignDaysWithOneEmployeeAvailable() {
    	
    	while ((day = findDaysWithOneEmployeeAvailable()) != -1) {
    		Data.availabilityMap.get(day).forEach(employee -> employee.assign(day)); //using forEach for just one employee because there is no way to get an element from a set
    	}

    }
    
    /**
     * @return day number with one employee available or -1 if there are no such days
     */
    public int findDaysWithOneEmployeeAvailable() {
    	for (Set<Employee> set : Data.availabilityMap.values()) {
    		if (set.size() == 1) {
    			return day;
    		}
    	}
    	return -1;
    }
    
    public void initAvailabilityMap(List<Employee> employees, int numOfDays){
        for (int d = 1; d <= numOfDays; d++){
            Data.availabilityMap.put(d, new HashSet<Employee>());
        }
        Data.employees = employees;
        Data.daysInMonth = numOfDays;
        addEmployeesToAvailabilityMap(employees);
    }

    public void addEmployeesToAvailabilityMap(List<Employee> employees){
        for(Employee e: employees){
            for(Integer day: e.getAvailability()){
                Set<Employee> availableOnDay = Data.availabilityMap.get(day);
                availableOnDay.add(e);
            }
        }
    }
}
