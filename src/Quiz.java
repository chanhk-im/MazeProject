import java.util.*;
import java.io.*;

public class Quiz {

	private static ArrayList<String> quizList = new ArrayList<String>();
	private static HashMap<Integer, HashMap<String, Integer>> answerOptionList = new HashMap<Integer, HashMap<String, Integer>>(4);
	private static ArrayList<Integer> alreadyProvided = new ArrayList<Integer>(); // Save the already provided quiz's index.

	public static void setQuiz() {
		String fileName = "../Quiz/quiz.txt"; // quiz file's name should be 'quiz.txt'
		try {

			Scanner inputStream = new Scanner(new File(fileName));

			int quizCount = 0;
			String line;
			while(inputStream.hasNextLine()) {
				line = inputStream.nextLine();
				quizList.add(line); // this is a question of one quiz.

				// The number of answer options per one question should be 4.
				HashMap<String, Integer> ansOption = new HashMap<String, Integer>(4);

				// Below process is for inserting answer option data to HashMap structure.
				for(int i=1; inputStream.hasNextLine() && i<=4; i++) {

					line = inputStream.nextLine();
					String[] answer = line.split("\t");

					String availableChoice = answer[1]; // first element is just a index. So, pass.
					Integer isAnswer = Integer.parseInt(answer[2]);

					ansOption.put(availableChoice, isAnswer);
				}
				answerOptionList.put(quizCount, ansOption); // quizCount starts at 0 because the quizList's start index is 0.

				if(inputStream.hasNextLine())
					line = inputStream.nextLine(); // to pass space(enter) between quiz questions.

				quizCount++;
			}

			inputStream.close();

		}
		catch(FileNotFoundException e) {System.out.println("File doesn't exist!");}
	}

	public static boolean playQuiz(int quizIndex) {

		Scanner kb = new Scanner(System.in);

		int ansIndex;

		do{
			System.out.print("What is your answer? (1~4) >> ");
			ansIndex = kb.nextInt();
			if(ansIndex<1||ansIndex>4) {
				System.out.println("You entered invalid answer. Try again.\n");
				continue;
			}
			break;
		} while(true);

		kb.close();

		if(answerCheck(quizIndex, ansIndex)) {
			System.out.println("\nCongraturation! Correct.");
			return true;
		}
		else {
			System.out.println("\nNope. You're wrong.");
			System.out.println("The answer is " + findAnswer(quizIndex) + ".");
			return false;
		}

	}

	public static int showQuiz() {

		int quizIndex = generateRandomIndex();

		if(quizList.size() == alreadyProvided.size()) { // When there is no left quiz to provide.
			System.out.println("No more left quiz!");
			return -1;
		}

		HashMap<String, Integer> choiceOption = answerOptionList.get(quizIndex);

		System.out.println("\n" + quizList.get(quizIndex));

		int i = 1;
		for(String option : choiceOption.keySet()) {
			System.out.println(i + ". " + option);
			i++;
		}

		alreadyProvided.add(quizIndex);

		return quizIndex;
	}

	public static int generateRandomIndex() {

		boolean isProv;
		int index;

		do { // Make a index that is not once provided.
			Random generator = new Random();
			index = generator.nextInt(quizList.size());
			isProv = isProvided(index);
		} while(isProv);

		return index;
	}

	public static boolean isProvided(int index) {
		for(int i=0; i<alreadyProvided.size(); i++) {
			if(index == alreadyProvided.get(i).intValue())
				return true;
		}
		return false;
	}

	public static boolean answerCheck(int quizIndex, int ansIndex) {

		HashMap<String, Integer> givenAnswer = answerOptionList.get(quizIndex);

		int i=1;

		for(String answerOption : givenAnswer.keySet()) {
			if(i==ansIndex) {
				if(givenAnswer.get(answerOption).intValue() == 1) {
					return true;
				}
				else return false;
			}
			i++;
		}
		return false;
	}

	public static int findAnswer(int quizIndex) {

		HashMap<String, Integer> givenAnswer = answerOptionList.get(quizIndex);
		// String answer = "There is no answer in this quiz! Error."; // For Exception case.

		int i = 1;

		for(String answerOption : givenAnswer.keySet()) {
			if(givenAnswer.get(answerOption).intValue() == 1)
				return i;
			i++;
		}
		return -1;
	}
}
