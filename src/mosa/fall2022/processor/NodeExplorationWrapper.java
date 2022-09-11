package mosa.fall2022.processor;

import mosa.fall2022.utils.Employee;

public class NodeExplorationWrapper implements Comparable<NodeExplorationWrapper>{
    final Employee employee;
    final int currentShiftCount;

    public NodeExplorationWrapper(Employee employee, int currentShiftCount){
        this.employee = employee;
        this.currentShiftCount = currentShiftCount;
    }

    public int compareTo(NodeExplorationWrapper otherNode){
        double ratioA = (double) currentShiftCount/ employee.getQuota();
        double ratioB = (double) otherNode.currentShiftCount/ otherNode.employee.getQuota();

        if (ratioA > ratioB){
            return 1;
        }
        if (ratioA < ratioB){
            return -1;
        }
        return employee.hashCode() - otherNode.employee.hashCode();
    }
}
