package mosa.fall2022;
import java.util.Arrays;
import mosa.fall2022.processor.Node;
import mosa.fall2022.processor.Processor;
import mosa.fall2022.utils.Employee;
public class Main {

    public static void main(String[] args) {
        
//    	Employee e1 = new Employee("A", 10, new int[] {1,    4,5,  7,8,9,      12,13,14,15} );
//        Employee e2 = new Employee("B", 10, new int[] {  2,      6,7,             13,14   } );
//        Employee e3 = new Employee("C", 10, new int[] {1,2,            9,10,11,12         } );
//        Employee e4 = new Employee("D", 10, new int[] {    3,4,5,6,            12,13      } );
//        Employee e5 = new Employee("E", 10, new int[] {              8,     11,12,13,14   } );
//        Processor processor = new Processor();
//        processor.initAvailabilityMap(Arrays.asList(e1, e2, e3, e4, e5), 15);
//        
//        processor.dfs(1, 15);
    	
    	int q = 3;
    	
    	Employee e1 = new Employee("A", q, new int[] {1,2,3,4,5,  7,8,9,      12,13,14,15,16,17,18,   20,21,22,23,24,25,          29,30} );
        Employee e2 = new Employee("B", q, new int[] {1,2,3,4,  6,7,  9,10,   12,13,14,15,16,17,18,   20,21,22,23,24,25,26,            } );
        Employee e3 = new Employee("C", q, new int[] {1,2,3,4,5,6,7,8,9,10,   12,   14,15,16,17,18,   20,21,22,23,24,25                } );
        Employee e4 = new Employee("D", q, new int[] {1,2,3,4,5,6,7,8,9,10,   12,13,   15,16,17,18,   20,21,22,23,24,25,    27,28      } );
        Employee e5 = new Employee("E", q, new int[] {1,2,3,4,5,6,7,8,        12,13,14,15,16,17,18,   20,21,22,23,24,25                } );
    	Employee e6 = new Employee("F", q, new int[] {1,    4,5,  7,8,9,      12,13,14,15,16,   18,   20,21,22,23,24,25,26,            } );
        Employee e7 = new Employee("G", q, new int[] {  2,3,4,5,6,7,8,9,10,   12,13,14,15,   17,18,19,20,21,22,23,24,25                } );
        Employee e8 = new Employee("H", q, new int[] {1,2,3,4,5,6,7,  9,10,   12,            17,         21,   23,                     } );
        Employee e9 = new Employee("I", q, new int[] {1,2,3,4,5,6,      10,   12,   14,   16,   18,   20,   22,         26,27          } );
        Employee e10= new Employee("J", q, new int[] {1,2,  4,5,  7,8,9,10,11,12,13,14,   16,17,18,   20,21,22,   24,25                } );
        Processor processor = new Processor();
        processor.initAvailabilityMap(Arrays.asList(e1, e2, e3, e4, e5, e6, e7, e8, e9, e10), 30);
        
        processor.dfs(1, 30);
        
        Node root = Processor.validSubSchedules.peek()[0];
        Node node = Processor.validSubSchedules.peek()[1];
        
        String output = String.valueOf(node.day) + ": " + node.assignedEmployee.getName() + " - " + node.shiftCountsCart.get(node.assignedEmployee);
        while (!(node = node.parent).equals(root)) {
        	
        	output = String.valueOf(node.day) + ": " + node.assignedEmployee.getName() + " - " + node.shiftCountsCart.get(node.assignedEmployee) + "\n" + output;

        }
        
        System.out.println(output);
        
    }
}
