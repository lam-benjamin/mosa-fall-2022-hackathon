package mosa.fall2022.utils;

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
        if (employee.equals(this.schedule[index])){
            return true;
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
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < schedule.length; i++){
            if (schedule[i] != null){
                stringBuilder.append((i+1) + ": " + schedule[i].getName() + "\n");
            } else {
                stringBuilder.append((i+1) + " is empty\n");
            }
        }
        return stringBuilder.toString();
    }

}
