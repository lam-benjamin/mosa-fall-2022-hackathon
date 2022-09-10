package mosa.fall2022.utils.exceptions;

import java.util.List;

public class InsufficientEmployeeException extends RuntimeException{
    public InsufficientEmployeeException(List<Integer> days){
        super("There are not enough employees for the following days:" + days);
    }
}
