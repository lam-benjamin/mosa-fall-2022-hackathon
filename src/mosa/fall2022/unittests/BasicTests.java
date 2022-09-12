package mosa.fall2022.unittests;

import mosa.fall2022.processor.GraphTraversalHelper;
import mosa.fall2022.processor.Processor;
import mosa.fall2022.utils.Employee;
import mosa.fall2022.utils.Schedule;
import mosa.fall2022.utils.exceptions.InsufficientEmployeeException;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

public class BasicTests {
	
	boolean randomness = false;

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
        Processor processor = new Processor(Arrays.asList(e1, e2), 30, randomness);
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

    @Test
    public void testRunDeterministic_success1(){
        Employee e1 = new Employee("Ben", 10, new int[]{1, 2, 3, 4, 5, 7, 8, 9, 10, 11, 12} );
        Employee e2 = new Employee("Joe", 10, new int[]{2, 4, 6, 8, 9, 10, 11, 12} );
        List<Employee> employees = Arrays.asList(e1, e2);
        Processor processor = new Processor(employees, 12, randomness);

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
        Processor processor = new Processor(employees, 12, randomness);

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
        Processor processor = new Processor(employees, 12, randomness);

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
        Processor processor = new Processor(employees, 12, randomness);

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
        Processor processor = new Processor(employees, 12, randomness);

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
    
    @Test
    public void testRunTimes() {
    	int q = 3;//availability with lone candidates:  |       |           |          |            |           |                 |  
    	Employee e1 = new Employee("A", q, new int[] {1,2,3,      7,  9,      12,13,                              24,25,      28,29,30} );
        Employee e2 = new Employee("B", q, new int[] {1,  3,4,  6,7,  9,10,               16,17,18,   20,21,22,      25,26,   28,   30} );
        Employee e3 = new Employee("C", q, new int[] {1,  3,4,          10,   12,   14,   16,17,18,   20,         24,25               } );
        Employee e4 = new Employee("D", q, new int[] {1,  3,4,5,    8,           13,                  20,21,22,            27,28      } );
        Employee e5 = new Employee("E", q, new int[] {    3,4,5,              12,13,14,      17,18,   20,21,         25,            30} );
    	Employee e6 = new Employee("F", q, new int[] {1,    4,5,  7,8,9,      12,   14,   16,         20,            25,26,   28,   30} );
        Employee e7 = new Employee("G", q, new int[] {    3,                        14,15,   17,18,               24,25               } );
        Employee e8 = new Employee("H", q, new int[] {1,  3,      7,  9,10,11,12,            17,   19,   21,   23,                  30} );
        Employee e9 = new Employee("I", q, new int[] {1,  3,4,5,        10,   12,   14,   16,   18,   20,   22,         26,27,28      } );
        Employee e10= new Employee("J", q, new int[] {1,    4,5,  7,             13,14,   16,17,18,   20,21,22,   24,25               } );
        
        //availability with no lone candidates:     
    	Employee e11 = new Employee("A", q, new int[] {1,2,3,      7,  9,      12,13,               19,         23,24,25,      28,29,30} );
        Employee e12 = new Employee("B", q, new int[] {1,  3,4,  6,7,  9,10,               16,17,18,   20,21,22,      25,26,   28,29,30} );
        Employee e13 = new Employee("C", q, new int[] {1,  3,4,          10,   12,   14,   16,17,18,   20,         24,25               } );
        Employee e14 = new Employee("D", q, new int[] {1,  3,4,5,    8,           13,                  20,21,22,            27,28      } );
        Employee e15 = new Employee("E", q, new int[] {    3,4,5,           11,12,13,14,      17,18,   20,21,         25,            30} );
    	Employee e16 = new Employee("F", q, new int[] {1,    4,5,  7,8,9,      12,   14,15,16,         20,            25,26,   28,   30} );
        Employee e17 = new Employee("G", q, new int[] {  2,3,    6,                  14,15,   17,18,               24,25,              } );
        Employee e18 = new Employee("H", q, new int[] {1,  3,      7,  9,10,11,12,            17,   19,   21,   23,                  30} );
        Employee e19 = new Employee("I", q, new int[] {1,  3,4,5,        10,   12,   14,   16,   18,   20,   22,         26,27,28      } );
        Employee e20 = new Employee("J", q, new int[] {1,    4,5,  7,             13,14,   16,17,18,   20,21,22,   24,25               } );
        
        List<Employee> employees1 = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);
        List<Employee> employees2 = Arrays.asList(e11, e12, e13, e14, e15, e16, e17, e18, e19, e20); 
      
        Processor processor1 = new Processor(employees1, 30, randomness);
        GraphTraversalHelper gth = new GraphTraversalHelper(processor1.availabilityMap, employees1, 30, randomness);
        
        long start1 = System.currentTimeMillis();
        gth.dfs(1, 30); //test the runtime of dfs exclusively, before deterministic algorithm has run & altered the availability map
        long end1 = System.currentTimeMillis();
        long runTime1 = end1 - start1;
        
        long start2 = System.currentTimeMillis();
        processor1.run(); //test the runtime of deterministic + dfs
        long end2 = System.currentTimeMillis();
        long runTime2 = end2 - start2;
        

        
        Processor processor2 = new Processor(employees2, 30, randomness);
         
        long start3 = System.currentTimeMillis();
        processor2.run(); //test the runtime of dfs on the same schedule but with one more employee added to the availability for each day so there are no lone candidates
        long end3 = System.currentTimeMillis();
        long runTime3 = end3 - start3;

        System.out.println("Runtime on an availability map WITH lone candidates\n    only using graph approach: " + runTime1 + " ms\n");
        System.out.println("Runtime on an availability map WITH lone candidates\n    using deterministic + graph approach: " + runTime2 + " ms\n");
        System.out.println("Runtime on an availability map with NO lone candidates\n    thereby only using graph approach: " + runTime3 + " ms\n");

    }
    
}
