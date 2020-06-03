import java.util.InputMismatchException;
import java.util.Scanner;

public class Updown {

/* // If you want to test this calss, just remove annotation
	public static void main(String[] ars){
		playUpdown();
	}
*/

	public static boolean playUpdown(){

		boolean isSucc = false;
		Scanner sc = new Scanner(System.in);

		int leftChance = 5;
		int answer = getRandomAnswer();

		System.out.println("---------------------------------------");
		System.out.println("\t<<Updown Game world>>");
		System.out.println("---------------------------------------");

		System.out.println("Try to guess the number!(1~50)\n");
		System.out.println("You have " + leftChance + " chances.");

		while(isLeft(leftChance)){ // while loop works while isLeft is true/

			try{
				System.out.print("Enter the number you guess >> ");
				int guessNum = sc.nextInt(); sc.nextLine();
				System.out.println();

				if(isBigger(answer, guessNum)){
					leftChance--;
					System.out.println("Wrong, the specified number is a Bigger number.\n");
        			System.out.println("You have "+ leftChance + " chances left.");
				}
				else if(isSmaller(answer, guessNum)){
					leftChance--;
					System.out.println("Wrong, the specified number is a smaller number.\n");
        			System.out.println("You have "+ leftChance + " chances left.");
				}
				else{ // answer == guessNum
					System.out.println("That's correct!");
					isSucc = true;
        			break;
				}
			}
			catch(InputMismatchException e) {
    	         sc = new Scanner(System.in);
    	         System.out.println("Please enter a 'number'.\n");
    	    }
		}
		if(!isSucc){
			System.out.println("\nThe answer is "+answer+".\n");
			System.out.println("-----!!!! Mission Failed !!!!-----");
			return isSucc;
		}
		else{
			System.out.println("-----!!!! Mission Success !!!!-----");
			return isSucc;
		}
	}

	// return random number by given range.
	private static int getRandomAnswer(){
		return (int)(Math.random()*50) +1;
	}

	// return 'the check' whether the leftChance is left or not.
	private static boolean isLeft(int leftChance){
		if(leftChance>=1) return true;
		return false;
	}

	// return 'the comparison' whether the ans is bigger than guess or not.
	private static boolean isBigger(int ans, int guess){
		if(ans>guess) return true;
		return false;
	}

	// return 'the comparison' whether the ans is smaller than guess or not.
	private static boolean isSmaller(int ans, int guess){
		if(ans<guess) return true;
		return false;
	}
}
