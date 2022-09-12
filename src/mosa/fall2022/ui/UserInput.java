package mosa.fall2022.ui;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class UserInput {
	
	public UserInput() {}
	
	public boolean timeOutPrompt() {
		String input = null;
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		System.out.print("This is taking a long time. Sometimes adding randomness into the algorithm can help it\n"
				+ "run faster, though it might also come at the expense of equal shift distribution. Would you\n"
				+ "like to try again with added randomness? Enter y or n\n");
		System.out.print("> ");
		System.out.flush();
		while (input == null) {
			try {
				input = bufferedReader.readLine();
			} catch (IOException e) {
				System.out.println("Invalid input");
				e.printStackTrace();
			}
		}
		if (input.equals("y")) {
			return true;
		}
		return false;
	}

}
