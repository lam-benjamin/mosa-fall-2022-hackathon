package mosa.fall2022.ui;

import java.util.Scanner;

public class UserInput {
	
	public UserInput() {}

	static Scanner scanner;

	private static Scanner getScanner(){
		if (scanner == null){
			scanner = new Scanner(System.in);
		}
		return scanner;
	}

	private static String getInputString(){
		Scanner scannerInstance = getScanner();
		return scannerInstance.nextLine();
	}

	
	public static boolean timeOutPrompt() {
		System.out.print("This is taking a long time. Sometimes adding randomness into the algorithm can help it\n"
				+ "run faster, though it might also come at the expense of equal shift distribution. Would you\n"
				+ "like to try again with added randomness? Enter y or n\n");
		System.out.print("> ");
		System.out.flush();
		String input = getInputString();
		if ("y".equals(input)) {
			return true;
		}
		return false;
	}

}
