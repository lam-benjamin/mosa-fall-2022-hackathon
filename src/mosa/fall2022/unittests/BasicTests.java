package mosa.fall2022.unittests;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import mosa.fall2022.processor.Node;
import mosa.fall2022.processor.Processor;
import mosa.fall2022.utils.Data;
import mosa.fall2022.utils.Employee;
import mosa.fall2022.utils.Schedule;
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
        Map<Integer, Set<Employee>>  results = processor.availabilityMap;

        for(Integer day: results.keySet() ){
            System.out.println( day + ":" + results.get(day).size() );
        }

    }

    @Test
    public void testInsufficientEmployeesException(){
        List<Integer> days = new ArrayList<Integer>();
        days.add(1);
        days.add(10);
        days.add(20);
        try {
            throw new InsufficientEmployeeException(days);
        }catch(RuntimeException ex){
            Assert.assertEquals(
                "Unexpected error message",
                "There are not enough employees for the following days:[1, 10, 20]",
                ex.getMessage()
            );
        }

    }
    
    
//    @Test
//    //TODO getting weird "source not found" error
//    public void testDoDFS() {
//    	Employee e1 = new Employee("Ben", 10, new int[]{1, 2, 3, 4, 5, 6, 7, 8} );
//        Employee e2 = new Employee("Joe", 10, new int[]{1, 2, 4, 5, 6, 8} );
//        Processor processor = new Processor(Arrays.asList(e1, e2), 8);
//
//        processor.dfs(1, 8);
//
//        Node root = processor.rootNode;
//        Node node = processor.endNode;
//        System.out.println(node.assignedEmployee.getName());
//
//        while (!(node = node.parent).equals(root)) {
//        	System.out.println(node.assignedEmployee.getName());
//        }
//    }

    @Test
    public void testRunDeterministic_success1(){
        Employee e1 = new Employee("Ben", 10, new int[]{1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12} );
        Employee e2 = new Employee("Joe", 10, new int[]{2, 4, 6, 8, 9, 10, 11, 12} );
        List<Employee> employees = Arrays.asList(e1, e2);
        Processor processor = new Processor(employees, 12);

        Schedule result = processor.assignEmployeesDeterministically();
        System.out.println(result);
        Integer[] e1Schedule = new Integer[]{1, 3, 5, 7, 9, 11};
        Integer[] e2Schedule = new Integer[]{2, 4, 6, 8, 10, 12};
        Assert.assertArrayEquals(e1Schedule, e1.getSchedule().toArray());
        Assert.assertArrayEquals(e2Schedule, e2.getSchedule().toArray());

        Assert.assertEquals("Filled days mismatch", 12, processor.schedule.getFilledDays());
        Assert.assertEquals("Total days mismatch", 12, processor.schedule.getTotalDays());
    }

    @Test
    public void testRunDeterministic_success2(){
        Employee e1 = new Employee("Ben", 10, new int[]{1, 12} );
        Employee e2 = new Employee("Joe", 10, new int[]{2, 4, 6, 8, 9, 10, 11, 12} );
        Employee e3 = new Employee("Sam", 10, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12} );
        List<Employee> employees = Arrays.asList(e1, e2, e3);
        Processor processor = new Processor(employees, 12);

        Schedule result = processor.assignEmployeesDeterministically();
        System.out.println(result);
        Integer[] e1Schedule = new Integer[]{};
        Integer[] e2Schedule = new Integer[]{2, 4, 6, 8, 10}; // leftovers after e3 takes all the relevant odd slots
        Integer[] e3Schedule = new Integer[]{3, 5, 7, 9, 11}; // Assigned unique days for e3 first

        Assert.assertArrayEquals(e1Schedule, e1.getSchedule().toArray());
        Assert.assertArrayEquals(e2Schedule, e2.getSchedule().toArray());
        Assert.assertArrayEquals(e3Schedule, e3.getSchedule().toArray());

        Assert.assertEquals("Filled days mismatch", 10, processor.schedule.getFilledDays());
        Assert.assertEquals("Total days mismatch", 12, processor.schedule.getTotalDays());
    }

    @Test
    public void testRunDeterministic_fail1(){
        Employee e1 = new Employee("Ben", 4, new int[]{1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12} );
        Employee e2 = new Employee("Joe", 10, new int[]{2, 4, 6, 8, 9, 10, 11, 12} );
        List<Employee> employees = Arrays.asList(e1, e2);
        Processor processor = new Processor(employees, 12);

        try {
            processor.assignEmployeesDeterministically();
            Assert.fail();
        }catch(RuntimeException ex){
            Assert.assertEquals("Unexpected error message",
                    "Lone employee quota met for day:9",
                    ex.getMessage()
            );
        }
    }

    @Test
    public void testRunDeterministic_fail2(){
        Employee e1 = new Employee("Ben", 10, new int[]{2, 3, 4, 5, 7, 8, 9, 10, 11, 12} );
        Employee e2 = new Employee("Joe", 10, new int[]{2, 4, 8, 9, 10, 11, 12} );
        List<Employee> employees = Arrays.asList(e1, e2);
        Processor processor = new Processor(employees, 12);

        try {
            processor.assignEmployeesDeterministically();
            Assert.fail();
        }catch(InsufficientEmployeeException ex){
            Assert.assertEquals("Unexpected error message",
                    "There are not enough employees for the following days:[1, 6]",
                    ex.getMessage()
            );
        }
    }

    @Test
    public void testRunDeterministic_fail3() {
        Employee e1 = new Employee("Ben", 10, new int[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12});
        Employee e2 = new Employee("Joe", 10, new int[]{1, 2, 4, 8, 9, 10, 11, 12});
        List<Employee> employees = Arrays.asList(e1, e2);
        Processor processor = new Processor(employees, 12);

        try {
            processor.assignEmployeesDeterministically();
            Assert.fail();
        } catch (InsufficientEmployeeException ex) {
            Assert.assertEquals("Unexpected error message",
                    "There are not enough employees for the following days:[6]",
                    ex.getMessage()
            );
        }

    }
}
