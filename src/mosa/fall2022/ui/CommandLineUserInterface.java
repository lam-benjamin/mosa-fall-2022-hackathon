package mosa.fall2022.ui;

import mosa.fall2022.processor.Processor;
import mosa.fall2022.utils.Employee;
import mosa.fall2022.utils.Schedule;
import mosa.fall2022.utils.exceptions.NoPossibleScheduleException;
import mosa.fall2022.utils.exceptions.TimeoutExitException;

import java.util.*;

public class CommandLineUserInterface {
    Printer printer;

    public CommandLineUserInterface(){
        printer = new Printer();
    }

    public void run(List<Employee> employeeList, int numOfDays, boolean randomness){
        Schedule schedule;
        Processor processor = new Processor(employeeList, numOfDays, randomness);

        try{
            schedule = processor.run();
        } catch (TimeoutExitException ex){
            processor = new Processor(employeeList, numOfDays, true);
            schedule = processor.run();
        }

        if (schedule == null || schedule.getFilledDays() != numOfDays) {
            throw new NoPossibleScheduleException("We weren't able to find a schedule that works for all of your employees.");
        }

        printer.print(schedule);
        for (Employee employee : processor.employees) {
            printer.print(employee);
        }
    }

    public void printMessage(String message){
        printer.print(message);
    }
}
