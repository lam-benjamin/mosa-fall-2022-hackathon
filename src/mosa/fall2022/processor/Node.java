package mosa.fall2022.processor;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import mosa.fall2022.utils.Data;
import mosa.fall2022.utils.Employee;

public class Node {
	
	public int day;
	public Node parent;
	public Employee assignedEmployee;
	
	boolean valid = true;
	Set<Employee> toExplore = new HashSet<Employee>();
	Set<Employee> explored = new HashSet<Employee>();
	public HashMap<Employee, Integer> shiftCountsCart = new HashMap<Employee, Integer>();
	
	@SuppressWarnings("unchecked")
	public Node(int day, Node parent, Employee assignedEmployee) {
		
		this.day = day;
		this.parent = parent;
		this.assignedEmployee = assignedEmployee;
		
		if (parent != null && assignedEmployee != null) { //root nodes will have null parents and assignedEmployees, so only do the following for non-roots
			parent.explored.add(assignedEmployee); //mark this employee as explored
			
			this.shiftCountsCart = (HashMap<Employee, Integer>) parent.shiftCountsCart.clone();
			Integer updatedTentativeShiftCount = this.shiftCountsCart.get(assignedEmployee) + 1; //this employee's tentative shift count is now their tentative shift count from the previous node + 1
			this.shiftCountsCart.put(assignedEmployee, updatedTentativeShiftCount); //save that new tentative shift count in this node for access by child node
			
			if (assignedEmployee.equals(parent.assignedEmployee) || updatedTentativeShiftCount > assignedEmployee.getQuota()) { //check for validity i.e. consecutive days or new shift count is over employee's quota
				this.valid = false;
			}
			
		} else { //if root node is being constructed
			//and if there are no valid sub-schedules stored yet (i.e. dfs has just been called), initialize the tentative shift count as the "checked-out" or confirmed shiftCount from each employee object
			if (Processor.validSubSchedules.isEmpty()) { 
				for (Employee e : Data.employees) {
					shiftCountsCart.put(e, e.getShiftCount());
				}
			} else { //otherwise, there are previous valid sub-schedules with their own shiftCountsCarts, so get the top one on the stack
				this.shiftCountsCart = Processor.validSubSchedules.peek()[1].shiftCountsCart; //and save it as this one's cart because the tentative shift counts should be cumulative
			}			
		}
		
		if (valid && day != Data.daysInMonth) { //if node is valid, save the set of employees to explore assigning for the next day
			this.toExplore = Data.availabilityMap.get(day+1); 
		}
		
	}
  
	/**
	 * If a valid leaf node is found, constituting a valid sub-schedule, but a subsequent sub-schedule has no solution, use this.isInvalid() to set this
	 * previously valid leaf node to invalid. This ensures that further calls of DFS on the root node of this tree will not return true on this leaf node
	 * TODO implement when implementing pops off the stack of valid subscheduless
	 */
	public void isInvalid() {
		this.valid = false;
	}
	
}
