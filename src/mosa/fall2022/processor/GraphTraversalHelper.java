package mosa.fall2022.processor;

import mosa.fall2022.utils.Employee;

import java.util.*;
import java.util.stream.Collectors;

public class GraphTraversalHelper {

    final Map<Integer, Set<Employee>> availabilityMap;
    final List<Employee> employeeList;
    final int numOfDays;
    public Node rootNode;
    public Node endNode;

    public GraphTraversalHelper(Map<Integer, Set<Employee>> availabilityMap, List<Employee> employeeList, int numOfDays){
        this.availabilityMap =  availabilityMap;
        this.employeeList = employeeList;
        this.numOfDays = numOfDays;
    }


    /**
     * Call dfs search on a section of the schedule using this method
     */
    public boolean dfs(int from, int to) {
        Node root = new Node(this, from-1, null, null); //initialize a root node
        rootNode = root; //save root node in outer scope for reference in other methods
        return doDFS(root, to);
    }

    /**
     * Called from within dfs(int from, int to), recursively searches for the first valid schedule in that range of days
     */
    public boolean doDFS(Node start, int to) {

        //if the node that was passed into the last recursive call of doDFS is valid & the day is equal to the target day, that represents a
        //successful schedule, so return true and save the root and valid leaf for use later if we need to find a new valid sub-schedule in this range
        if (start.day == to && start.valid) {
            endNode = start; //save the valid leaf node in outer scope for reference in other methods
            return true;
        }

        //shuffle and iterate through the set of Employees in the start Node's toExplore list
        List<Employee> toExploreShuffled = start.toExplore.stream().collect(Collectors.toList());
        Collections.shuffle(toExploreShuffled);
        Iterator<Employee> it = toExploreShuffled.iterator();
        while (it.hasNext()) {

            Employee nextEmployee = it.next();

            if (!start.explored.contains(nextEmployee)) {
                Node newNode = new Node(this, start.day + 1, start, nextEmployee); //create a new node, which removes the Node's Employee from start.toExplore

                if (newNode.valid) { //if this new node is valid, doDFS on it
                    boolean result = doDFS(newNode, to);
                    if ( result ){
                        return result;
                    }
                }
            }
        }

        return false;
    }
}
