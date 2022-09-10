package mosa.fall2022;
import java.util.*;
import mosa.fall2022.processor.Processor;
import mosa.fall2022.ui.Printer;
import mosa.fall2022.utils.Employee;
import mosa.fall2022.utils.Schedule;

public class Main {

    public static void main(String[] args) {
        

    	
    	int q = 4;
    	
    	Employee e1 = new Employee("A", q, new int[] {1,2,3,      7,  9,      12,13,                           23,24,25,         29,30} );
        Employee e2 = new Employee("B", q, new int[] {1,  3,4,  6,7,  9,10,               16,17,18,   20,21,22,      25,26,      29,30} );
        Employee e3 = new Employee("C", q, new int[] {1,  3,4,          10,   12,   14,   16,17,18,   20,         24,25               } );
        Employee e4 = new Employee("D", q, new int[] {1,  3,4,5,                 13,   15,            20,21,22,            27,28      } );
        Employee e5 = new Employee("E", q, new int[] {    3,4,5,           11,12,13,14,15,   17,18,   20,21,          25              } );
    	Employee e6 = new Employee("F", q, new int[] {1,    4,5,  7,8,9,      12,   14,15,16,         20,            25,26,           } );
        Employee e7 = new Employee("G", q, new int[] {  2,3,                        14,15,   17,18,               24,25,         29   } );
        Employee e8 = new Employee("H", q, new int[] {1,  3,      7,  9,10,11,12,            17,   19,   21,   23,                    } );
        Employee e9 = new Employee("I", q, new int[] {1,  3,4,5,        10,   12,   14,   16,   18,   20,   22,         26,27         } );
        Employee e10= new Employee("J", q, new int[] {1,    4,5,  7,             13,14,   16,17,18,   20,21,22,   24,25               } );
        List<Employee> employees = Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10);

        Processor processor = new Processor(employees, 30);
        Schedule schedule = processor.run();
        System.out.println(schedule);
        Printer p = new Printer();
        
        for (Employee e : processor.employees) {
        	p.printEmployeeSchedule(e);
        }

        
    }
}
