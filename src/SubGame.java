
public class SubGame{

	// through this mehtod, a player can play one of the 3 sub games; rsp, up-down, and quiz. If player win, this method return true. If not, returns false. This return statement can help the Maze to make the player characteristics (vision, life, moving range, etc - there are not-implemented characteristic)
	public boolean playSubgame(){
	    int choose = chooseGameRandomly();
		switch(choose){
			case 1:
				return Rps.playRps();
			case 2:
				return Updown.playUpdown();
			case 3:
				return Quiz.playQuiz();
		}
	}

	public boolean isSubgame(Player explorer, int[][] maze){

	    int pX = explorer.getX();
	    int pY = explorer.getY();

	    // 2 in maze txt file means "there is a sub game". it should be connected other 'opened' path (opened path means 1).
	    if(maze[pX][pY] == 2){
	        return true;
	    }
	    return false;
	}

	private int chooseGameRandomly(){
		Random generator = new Random();
		int index = generator.nextInt(3) + 1; // because the number of sub games is 3.
		return index;
}
