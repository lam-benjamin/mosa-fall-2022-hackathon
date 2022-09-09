package mosa.fall2022.utils;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.List;

public class Data {
	
	//keeping this in here because it will need to be used by various classes
	public static Map<Integer, Set<Employee>> availabilityMap = new TreeMap<>();
	
	public static List<Employee> employees = new ArrayList<Employee>();
	
	public static int daysInMonth;


}
