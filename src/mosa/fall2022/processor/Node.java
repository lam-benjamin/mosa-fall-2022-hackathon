package mosa.fall2022.processor;

import java.util.*;

import mosa.fall2022.utils.Employee;

public class Node {
	
	public int day;
	public Node parent;
	public Employee assignedEmployee;
	final GraphTraversalHelper graphTraversalHelper;
	private boolean randomness;

	
	boolean valid = true;
	Set<NodeExplorationWrapper> toExplore = new TreeSet<NodeExplorationWrapper>();
	Set<Employee> explored = new HashSet<Employee>();
	public HashMap<Employee, Integer> shiftCountsCart = new HashMap<Employee, Integer>();
	
	public Node(GraphTraversalHelper graphTraversalHelper, int day, Node parent, Employee assignedEmployee, boolean randomness) {
		this.graphTraversalHelper = graphTraversalHelper;
		this.day = day;
		this.parent = parent;
		this.assignedEmployee = assignedEmployee;
		this.randomness = randomness;
		initNode();
	}

	private void initNode(){
		if (parent == null || assignedEmployee == null){
			//root nodes are initialized as "Node root = new Node(this, from-1, null, null)" for day 0, no assignedEmployee and no parent
			initRootNode();
		} else {
			initNonRootNode();
		}

		if (valid && day != graphTraversalHelper.numOfDays) {
			//if node is valid, save the set of employees to explore assigning for the next day
			Set<Employee> employeesToExplore = graphTraversalHelper.availabilityMap.get(day+1);
			//TODO edit in shuffle again if taking a long time
			for(Employee e: employeesToExplore){
				NodeExplorationWrapper node = new NodeExplorationWrapper(e, shiftCountsCart.get(e), randomness);
				this.toExplore.add(node);
			}
		}
	}

	private void initRootNode(){
		for (Employee e : graphTraversalHelper.employeeList) {
			shiftCountsCart.put(e, 0);
		}
	}

	@SuppressWarnings("unchecked")
	private void initNonRootNode(){
		parent.explored.add(assignedEmployee); //mark this employee as explored

		this.shiftCountsCart = (HashMap<Employee, Integer>) parent.shiftCountsCart.clone();
		Integer updatedShiftCount = this.shiftCountsCart.get(assignedEmployee) + 1; //this employee's tentative shift count is now their tentative shift count from the previous node + 1
		this.shiftCountsCart.put(assignedEmployee, updatedShiftCount); //save that new tentative shift count in this node for access by child node

		if (assignedEmployee.equals(parent.assignedEmployee) || updatedShiftCount > assignedEmployee.getQuota()) { //check for validity i.e. consecutive days or new shift count is over employee's quota
			this.valid = false;
		}
	}
}