package mosa.fall2022.utils.exceptions;

import java.util.List;

public class InsufficientEmployeeException extends RuntimeException{
    public InsufficientEmployeeException(List<Integer> days){
        super("There were no employees for the following days:" + days);
    }
}
