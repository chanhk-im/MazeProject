import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class Rps {

/* // If you want to test this calss, just remove annotation
	public static void main(String[] args){
		playRps();
	}
*/

	public static boolean playRps(){

		Scanner sc = new Scanner(System.in);

		String userRps, comRps; // these are just for 'show' what the picked is.
		int userPick, comPick; // 1 : Scissors, 2 : Rock, 3 : Paper.

		System.out.println("=============MINI GAME START=============");
		System.out.println("Choose one => Scissors[1] Rock[2] Paper[3]");

		// get Rps index for user until enter a right input.
		while(true){
			try{
				System.out.print(">> ");
				userPick = sc.nextInt();
				if(userPick<1 || userPick >3){
					System.out.println("Please choose a number in the range.");
					continue;
				}
				break;
			}
			catch(InputMismatchException e) {
				// initializaiton
				sc = new Scanner(System.in);
				System.out.println("Please choose only 'number'!\n");
			}
		}

		comPick = getRandomRps(); // get Rps index for computer.

		// below 2 variables is set just for printing.
		userRps = setUserRps(userPick);
		comRps = setComRps(comPick);

		// judge and print, and finally return true / false.
		switch(judge(userPick, comPick)){
			case 1:
				System.out.println("\n[User] "+ userRps + " -VS- " + comRps + " [Computer]");
				System.out.println("[YOU WIN]");
				System.out.println("========================================");
				return true;
			case 2:
				System.out.println("\n[User] " + userRps + " -VS- " + comRps + " [Computer]");
				System.out.println("[DRAW]");
				System.out.println("========================================");
				return false; // or playUpdown(); ?
			case 3:
				System.out.println("\n[User] " + userRps + " -VS- " + comRps + " [Computer]");
				System.out.println("[;-; YOU LOSE ;-;]");
				System.out.println("=========================================");
				return false;
			default: // this will not never occured. Just for base case.
				System.out.println("System error.");
				return false;
		}
	}

	// get a one pick of Scissors / Rock / Paper randomly.
	private static int getRandomRps(){
		return (int)(Math.random()*3)+1;
	}

	// return a certain string for setting userRps in playRps();
	private static String setUserRps(int userPick){
		switch(userPick){
			case 1:
				return "Scissors";
			case 2:
				return "Rock";
			case 3:
				return "Paper";
			default:
				return "fail";
		}
	}

	// return a certain string for setting comRps in playRps();
	private static String setComRps(int comPick){
		switch(comPick){
			case 1:
				return "Scissors";
			case 2:
				return "Rock";
			case 3:
				return "Paper";
			default:
				return "fail";
		}
	}

	// this method will return : if user win : return 1 / draw : 2 / lose : 3
	private static int judge(int userPick, int comPick){
		if((comPick ==1 && userPick ==2) || (comPick ==2 && userPick ==3) || (comPick == 3 && userPick ==1)) return 1;
		else if(comPick == userPick) return 2;
		else return 3;
	}
}
