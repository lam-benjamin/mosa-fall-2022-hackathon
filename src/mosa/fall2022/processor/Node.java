package mosa.fall2022.processor;

import java.util.HashSet;
import java.util.Set;

import mosa.fall2022.utils.Data;
import mosa.fall2022.utils.Employee;

public class Node {
	
	int day;
	Node parent;
	Employee assignedEmployee;
	
	boolean valid = true;
	Set<Employee> toExplore = new HashSet<Employee>();
	
	public Node(int day, Node parent, Employee assignedEmployee) {
		
		this.day = day;
		this.parent = parent;
		this.assignedEmployee = assignedEmployee;
		
		if (parent != null && assignedEmployee != null) { //root nodes will have null parents and assignedEmployees, so only do the following for non-roots
			parent.toExplore.remove(assignedEmployee); //remove this employee from the parent's set of employees to explore
			
			if (assignedEmployee.equals(parent.assignedEmployee)) { //check for validity i.e. consecutive days TODO and check for quota
				this.valid = false;
			}
		}
		
		if (valid) { //if node is valid, save the set of employees to explore assigning for the next day
			this.toExplore = Data.availabilityMap.get(day+1); 
		}
		
	}
  
	/**
	 * If a valid leaf node is found, constituting a valid sub-schedule, but a subsequent sub-schedule has no solution, use this.isInvalid() to set this
	 * previously valid leaf node to invalid. This ensures that further calls of DFS on the root node of this tree will not return true on this leaf node
	 */
	public void isInvalid() {
		this.valid = false;
	}
	
}
