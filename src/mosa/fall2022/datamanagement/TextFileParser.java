package mosa.fall2022.datamanagement;

import mosa.fall2022.utils.Employee;
import mosa.fall2022.utils.exceptions.InvalidFileException;

import java.io.*;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class TextFileParser {

    private final String fileName;
    private final File file;
    private final List<Employee> employees;
    private int numberOfDays;

    public static final String inputDelimiter = "INPUT STARTS HERE";

    public TextFileParser(String fileName){
        this.numberOfDays = -1;
        this.fileName = fileName;
        this.file = new File(fileName);
        checkFile();
        this.employees = getEmployeeListFromFile();
    }

    public List<Employee> getEmployees(){
        return this.employees;
    }

    public int getNumberOfDays(){
        return this.numberOfDays;
    }

    public List<Employee> getEmployeeListFromFile(){
        List<Employee> employees = new ArrayList<Employee>();
        List<String> lines = readLines();

        String regex = "^(?<name>[A-za-z]*)(\\s)*:(\\s)*(?<quota>[0-9]*)(\\s)*;(?<availability>([0-9\\s,])*)";
        Pattern p = Pattern.compile(regex);

        boolean inputStarted = false;
        for(String line: lines){
            if ( inputDelimiter.equals(line) ){
                inputStarted = true;
            }
            if (!inputStarted || line == null || "".equals(line) ){
                continue;
            }
            Matcher m = p.matcher(line);
            if(!m.matches()){
                continue;
            }
            String name = m.group("name");
            int quota = parseInt(m.group("quota"));
            List<Integer> availability = new ArrayList<Integer>();
            for(String dayString : m.group("availability").split("\\s|,") ){
                int day = parseInt(dayString);
                if (day < 1){
                    continue;
                }
                if (day > numberOfDays){
                    numberOfDays = day;
                }
                availability.add(day);
            }
            Employee employee = new Employee(name, quota, availability);
            employees.add(employee);
        }

        return employees;
    }

    private int parseInt(String string){
        try{
            return Integer.parseInt(string);
        } catch(NumberFormatException ex){
            return 0;
        }
    }

    private void checkFile(){
        if (!this.file.exists() || !this.file.canRead()){
            throw new InvalidFileException(this.fileName +" cannot be read");
        }
    }

    private List<String> readLines(){
        List<String> lines = new ArrayList<String>();
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;
        try {
            fileReader = new FileReader(this.file);
            bufferedReader = new BufferedReader(fileReader);

            String line;
            while((line = bufferedReader.readLine()) != null) {
                lines.add(line.trim());
            }
        } catch (FileNotFoundException fnfEx) {
            fnfEx.printStackTrace();
        } catch (IOException ioEx) {
            ioEx.printStackTrace();
        } finally {
            try {
                fileReader.close();
                bufferedReader.close();
            } catch (IOException ioEx2) {
                ioEx2.printStackTrace();
            }
        }
        return lines;
    }

}
