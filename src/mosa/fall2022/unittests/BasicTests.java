package mosa.fall2022.unittests;


import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import mosa.fall2022.processor.Processor;
import mosa.fall2022.utils.Employee;
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
        Processor processor = new Processor();
        processor.initAvailabilityMap(Arrays.asList(e1, e2), 30);
        Map<Integer, Set<Employee>>  results = processor.availabilityMap;

        for(Integer day: processor.availabilityMap.keySet() ){
            System.out.println( day + ":" + processor.availabilityMap.get(day).size() );
        }

    }

}
