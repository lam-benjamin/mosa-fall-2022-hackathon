package mosa.fall2022.unittests;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import mosa.fall2022.processor.Node;
import mosa.fall2022.processor.Processor;
import mosa.fall2022.utils.Data;
import mosa.fall2022.utils.Employee;
import mosa.fall2022.utils.exceptions.InsufficientEmployeeException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.*;

public class BasicTests {

    @Test
    public void testIfJUnitWorks(){
        Assert.assertTrue(true);
    }

    @Test
    public void testEmployeeConstructor(){
        Employee e1 = new Employee("Ben", 10, new int[]{1, 2, 3, 4, 5} );
        System.out.println(e1);
    }

    @Test
    public void testInitEmployeeAvailabilityMap(){
        Employee e1 = new Employee("Ben", 10, new int[]{1, 2, 3, 4, 5} );
        Employee e2 = new Employee("Joe", 10, new int[]{2, 4, 6, 8} );
        Processor processor = new Processor(Arrays.asList(e1, e2), 30);
        Map<Integer, Set<Employee>>  results = Data.availabilityMap;

        for(Integer day: Data.availabilityMap.keySet() ){
            System.out.println( day + ":" + Data.availabilityMap.get(day).size() );
        }

    }

    @Test
    public void testInsufficientEmployeesException(){
        List<Integer> days = new ArrayList<Integer>();
        days.add(1);
        days.add(10);
        days.add(20);
        throw new InsufficientEmployeeException(days);
    }
    
    
    @Test
    //TODO getting weird "source not found" error
    public void testDoDFS() {
    	Employee e1 = new Employee("Ben", 10, new int[]{1, 2, 3, 4, 5, 6, 7, 8} );
        Employee e2 = new Employee("Joe", 10, new int[]{1, 2, 4, 5, 6, 8} );
        Processor processor = new Processor(Arrays.asList(e1, e2), 8);
//        processor.initAvailabilityMap(Arrays.asList(e1, e2), 8);

        processor.dfs(1, 8);

        Node root = processor.validSubSchedules.peek()[0];
        Node node = processor.validSubSchedules.peek()[1];
        System.out.println(node.assignedEmployee.getName());
        
        while (!(node = node.parent).equals(root)) {
        	System.out.println(node.assignedEmployee.getName());
        }
    }
    
    

}
