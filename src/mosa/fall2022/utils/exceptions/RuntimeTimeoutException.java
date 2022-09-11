package mosa.fall2022.utils.exceptions;

public class RuntimeTimeoutException extends RuntimeException { //TODO I think maybe this isn't supposed to be a RuntimeException
	public RuntimeTimeoutException(long timeOutAfterMS) {
		super("No schedule was able to be found in " + timeOutAfterMS + " ms.");
	}
}
