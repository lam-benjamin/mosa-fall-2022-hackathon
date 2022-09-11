package mosa.fall2022.utils.exceptions;

public class InsufficientQuotasException extends RuntimeException {
	public InsufficientQuotasException (int sumOfQuotas, int daysInMonth) {
		super("The employees' shift quotas are not enough to fill the schedule. Please increase one or some employees' quotas by " + (daysInMonth - sumOfQuotas));
	}
}
