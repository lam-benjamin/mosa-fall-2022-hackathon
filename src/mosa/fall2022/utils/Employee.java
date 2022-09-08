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

    
    public void assign(int day) {
    	//update this employee object's fields
    	this.shiftCount++;
    	this.schedule.add(day);
    	
    	//and update the public availabilityMap;
    	Data.availabilityMap.get(day).remove(this);
    	Data.availabilityMap.get(day-1).remove(this);
    	Data.availabilityMap.get(day+1).remove(this);
    	
    	//NOTE: I think it might be best to keep each Employee's availability unchanged for now, so the original availabilityMap can be reconstructed
    	//if a certain schedule turns out to not be valid
    	
    	
    	//TODO edit this when we get to fine tuning how the quotas work, not sure if this is the best approach
    	//if a shift assignment makes an employee meet their quota, then remove that employee from all days of the availabilityMap so they don't 
    	//receive any more shifts
    	if (shiftCount == quota) {
    		for (Integer d : Data.availabilityMap.keySet()) {
    			Data.availabilityMap.get(d).remove(this);
    		}
    	}
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