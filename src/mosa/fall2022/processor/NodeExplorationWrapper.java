package mosa.fall2022.processor;

import mosa.fall2022.utils.Employee;

public class NodeExplorationWrapper implements Comparable<NodeExplorationWrapper>{
    final Employee employee;
    final int currentShiftCount;
    private boolean randomness;

    public NodeExplorationWrapper(Employee employee, int currentShiftCount, boolean randomness){
        this.employee = employee;
        this.currentShiftCount = currentShiftCount;
        this.randomness = randomness;
    }
    
    @Override
	public int compareTo(NodeExplorationWrapper otherNode){
    	if (!randomness) {
	        double ratioA = (double) currentShiftCount/ employee.getQuota();
	        double ratioB = (double) otherNode.currentShiftCount/ otherNode.employee.getQuota();
	
	        if (ratioA > ratioB){
	            return 1;
	        }
	        if (ratioA < ratioB){
	            return -1;
	        }
	        return employee.hashCode() - otherNode.employee.hashCode();
    	} else {
    		double randBetween0And1 = Math.random();
    		if (randBetween0And1 < 0.5) {
    			return -1;
    		} else {
    			return 1;
    		}
    	}
    }
}
