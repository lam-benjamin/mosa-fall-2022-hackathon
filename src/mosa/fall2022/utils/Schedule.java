package mosa.fall2022.utils;

import java.util.*;

public class Schedule {

    int totalDays;
    int filledDays;
    Employee[] schedule;

    public Schedule(int days){
        this.totalDays = days;
        this.filledDays = 0;
        this.schedule = new Employee[days];
    }

    public int getTotalDays(){
        return this.totalDays;
    }


    public int getFilledDays(){
        return this.filledDays;
    }

    public boolean assignEmployee(int day, Employee employee){
        int index = day -1;
        if ( !employee.assignDay(day) ){
            return false;
        }
        this.filledDays++;
        this.schedule[index] = employee;
        return true;
    }

    public Employee getEmployeeAssignedForGivenDay(int day){
        return this.schedule[day-1];
    }

    @Override
    public String toString() {
        return "Schedule{" +
                "totalDays=" + totalDays +
                ", filledDays=" + filledDays +
                ", schedule=" + Arrays.toString(schedule) +
                '}';
    }
}
