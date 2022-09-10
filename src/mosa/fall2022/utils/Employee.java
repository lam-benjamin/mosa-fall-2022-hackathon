package mosa.fall2022.utils;

import java.util.*;
import java.util.stream.Collectors;

public class Employee {
    private String name;
    private int quota;
    private int shiftCount = 0;
    private SortedSet<Integer> availability;
    private SortedSet<Integer> schedule = new TreeSet<Integer>();

    public Employee(String name, int quota, int[] availability){
        this.name = name;
        this.quota = quota;
        this.availability = Arrays.stream(availability)
                .boxed()
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public SortedSet getSchedule(){
        return this.schedule;
    }

    public boolean assignDay(int day){
        if (schedule.contains(day)){
            return true;
        }
        if (
            !availability.contains(day) ||
            schedule.size() >= quota ||
            schedule.contains(day-1) ||
            schedule.contains(day+1)
        ){
            return false;
        }
        schedule.add(day);
        return true;
    }


    /**
     * Getters
     */
    public String getName() {
        return this.name;
    }
    public int getQuota() {
    	return this.quota;
    }
    public int getShiftCount() {
    	return this.shiftCount;
    }
    public SortedSet<Integer> getAvailability(){
        return availability;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "name='" + name + '\'' +
                ", quota=" + quota +
                ", availability=" + availability +
                ", schedule=" + schedule +
                '}';
    }
}