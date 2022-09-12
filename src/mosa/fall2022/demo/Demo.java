package mosa.fall2022.demo;

import mosa.fall2022.Main;

public class Demo {
	
	private final String path = "src/mosa/fall2022/demo/";

	String input0 = path + "demo0.txt";
	String input1 = path + "demo1.txt";
	String input2 = path + "demo2.txt";
	String input3 = path + "demo3.txt";
	String input4 = path + "demo4.txt";

	
	public Demo() {}
	
	public void run() {

		System.out.println("Output 0:\n");
		Main.run(input0);

// 		No Possible Solution
//		Joe: 2;   2,3,4
//		Ben: 2; 1,2,3,4,5
//		Ken: 2;       4,5,6

		System.out.println("\n-----------------------------------------\n");

		




		
		
		
		
		
		




























		System.out.println("Output 1:\n");
		Main.run(input1);
		
//		Joe: 2;   2,3,4,5
//		Ben: 2; 1,2,3,4,5
//		Ken: 2;       4,5,6
		
		//Here is a simple input of availabilities for three people, each with a target quota of 2.
		
		//It is relatively quick to build a schedule from such a small input, but even inputs of this size can require some careful focus.
		
		//Here, Ken must be assigned day 6 and Ben must be assigned day 1 because they are the only ones available
		
		//Joe must then be assigned day 2 because he is the only one available after Ben was assigned day 1.
		
		//Ben is then the only one available for day 3, since Joe is now assigned day 2.
		
		//Ben has now reached his target quota of 2 shifts, so Joe is the only one available for day 5.
		
		//And finally, Ken is assigned day 4.
		
		System.out.println("\n-----------------------------------------\n");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		System.out.println("Output 2:\n");
		Main.run(input2);
//		Brandon: 1; 1,2,3,4,      8,  10
//		Val:     2;   2,      6,    9
//		Tom:     3; 1,  3,4,  6,7,8,
//		Rafi:    4;     3,4,5,  7,  9,10
		
		//As the size of the input gets larger, however, it becomes less and less obvious how to build a schedule.
		
		System.out.println("\n-----------------------------------------\n");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		



		System.out.println("Output 3:\n");
		Main.run(input3);
//		Ann: 3; 1,2,3,      7,  9,      12,13,                           23,24,25,         29,30
//		Ben: 3; 1,  3,4,  6,7,  9,10,               16,17,18,   20,21,22,      25,26,      29,30
//		Cid: 3; 1,  3,4,          10,   12,   14,   16,17,18,   20,         24,25
//		Dan: 3; 1,  3,4,5,                 13,   15,            20,21,22,            27,28
//		Edd: 3;     3,4,5,           11,12,13,14,15,   17,18,   20,21,          25
//		Flo: 3; 1,    4,5,  7,8,9,      12,   14,15,16,         20,            25,26,
//		Gab: 3;   2,3,                        14,15,   17,18,               24,25,         29
//		Hal: 3; 1,  3,      7,  9,10,11,12,            17,   19,   21,   23,
//		Ian: 3; 1,  3,4,5,        10,   12,   14,   16,   18,   20,   22,         26,27
//		Joe: 3; 1,    4,5,  7,             13,14,   16,17,18,   20,21,22,   24,25
		
		//How long would it take you to manually build a schedule for a month with availabilities for 10 people?
		
		System.out.println("\n-----------------------------------------\n");
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		
		System.out.println("Output 4:\n");
		Main.run(input4);
		
//		Ann: 3; 1,2,3,7,9,12,13,23,24,25,29,30,40,50,60,70,80,90
//		Ben: 3; 1,3,4,6,7,9,10,16,17,18,20,21,22,25,26,29,30,41,52,63,74,85,89
//		Cid: 3; 1,3,4,10,12,14,16,17,18,20,24,25,43,54,55,66,77,88
//		Dan: 3; 1,3,4,5,13,15,20,21,22,27,28,44,56,64,75,86
//		Edd: 3; 3,4,5,11,12,13,14,15,17,18,20,21,25,45,54,65,76,87
//		Flo: 3; 1,4,5,7,8,9,12,14,15,16,20,25,26,31,46,57,68,78,85
//		Gab: 3; 2,3,14,15,17,18,24,25,29,32,47,58,69,77,84,53,75,12,56,83,1,47,9,2
//		Hal: 3; 1,3,7,9,10,11,12,17,19,21,23,33,48,59,60,74,83,32,41,51,61,71,87,39
//		Ian: 3; 1,3,4,5,10,12,14,16,18,20,22,26,27,34,56,75,82,31,40,50,60,70,80,90
//		Joe: 3; 1,4,5,7,13,14,16,17,18,20,21,22,24,25,35,53,54,81,39,49,59,69,79,89
//		Ken: 6; 2,4,7,4,12,14,16,18,19,36,37,38,39,40,51,61,71,87,38,48,58,68,78,88
//		Lou: 6; 1,6,23,32,52,62,63,64,65,77,79,81,82,83,86,89,37,47,57,67,77,87,12,4
//		Meg: 6; 3,4,7,9,10,14,16,18,25,36,31,53,65,62,85,90,36,46,56,66,76,86,23,1,42
//		Ned: 6; 74,24,86,34,12,64,13,12,3,4,5,6,8,9,83,35,45,55,65,75,85,31,46,8,2,5
//		Obi: 6; 1,6,12,17,21,27,34,37,46,49,53,57,67,68,74,76,82,87,34,44,54,64,74,84
//		Peg: 6; 12,63,13,56,43,11,1,4,33,43,53,63,73,83,1,53,75,23,5,3,5,9,34,52,12
//		Qui: 6; 2,3,15,16,23,24,34,35,45,46,57,58,61,62,72,73,84,85,32,42,52,62,72,82
//		Rey: 6; 1,6,13,45,38,35,73,23,52,88,32,10,40,50,24,4,12,54,31,41,51,61,71,81
//		Seb: 6; 1,3,6,8,42,58,34,62,73,53,23,52,20,60,70,12,52,73,68,34,14,86,34,75,2
//		Ted: 6; 9,24,64,33,66,44,55,66,77,88,3,6,23,12,64,42,21,63,12,42,12,44,22,55,7
		
		//And what if you have to fill 90 days of a schedule for 20 monkeys who have typed in a bunch of random, out-of-order days into their availabilities?
		
		System.out.println("\n-----------------------------------------\n");














		
		
		
	}

}
