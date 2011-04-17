package tennis_two;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

/**
 * 
 * A tie-break match (for the purposes of this assignment) is one in which all the sets are tie-break sets. After a tie-break game 
 * (which ends a set), the first game of the next set, if any, is served by the player who did not serve first in the tie-break.
 *
 */
public class TieBreakMatch extends SerialContest implements Match {
	
	/**
	 * Used to aggregate the total games played, which then will define the next server.
	 */
	private static int totalGamesPlayed;
	
	/**
	 * This array list is used to keep Contest pointers for later use in printing contest scores.
	 */
	private static ArrayList<Contest> matchList;

	protected TieBreakMatch(Player firstServer, int targetScore, int advantage) {
		super(firstServer, targetScore, advantage);
		matchList = new ArrayList<Contest>();	
	}
	
	/**
	 * Adds the Contest reference to a list and aggregates total games played. 
	 */
	protected void addContestToMatchList() {
		matchList.add(this.currentSubContest);
		totalGamesPlayed += this.currentSubContest.score(this.currentSubContest.server()) + this.currentSubContest.score(this.currentSubContest.receiver());
	}

	/* (non-Javadoc)
	 * @see tennis_two.Contest#getContestName()
	 */
	@Override
	public String getContestName() {
		return "TieBreakMatch";
	}
	
	/**
	 * Instantiates a sub-Contest and then plays it.
	 * After Contest has been played it is added a list 
	 * Scores of the current Contest are then printed.
	 */
	@Override
	protected void playSubContest () {
		currentSubContest = makeSubContest ();
		currentSubContest.play ();
		
		addContestToMatchList();

		onSubContestOver ();
	}

	/**
	 * After one Set has been played with the first Server of the Match the server is then 
	 * alternated based on if the total number of games played are odd or even.
	 */
	@Override
	protected Contest makeSubContest() {
		if(this.currentSubContest == null){
			return TennisFactory.instance().makeTieBreakSet(firstServer);
		} else if (totalGamesPlayed % 2 == 0) {
			return TennisFactory.instance().makeTieBreakSet(this.server());
		} else {
			return TennisFactory.instance().makeTieBreakSet(this.receiver());
		}
	}
	
	/**
	 * Method is overridden to include to see if the definition of a the end of Tie-Break Match has been met.
	 */
	@Override
	public boolean isOver () {
		if((score_1 >= 2 || score_2 == 2) && Math.abs (score_1 - score_2) >= targetAdvantage) {
			assertTrue("Assert that either player has won 2 TieBreakMatches and leads by one - thus finishing the game", ((score_1 == 2 || score_2 == 2) && Math.abs (score_1 - score_2) >= targetAdvantage));
			return true;
		} else {
			return Math.max (score_1, score_2) >= targetScore && Math.abs (score_1 - score_2) >= targetAdvantage;
		}
    }
	
	/**
	 * Method is overridden to print out the scores of all Sets that have been played to date.
	 */
	@Override
	protected void overridableDisplay () {	
		System.out.format("%-15s", this.players()._1);
		for(Contest c : matchList){
			System.out.format("  %s", c.scoreAsString(c.players()._1));
		}
		
		System.out.println();
		
		System.out.format("%-15s", this.players()._2);
		for(Contest c : matchList){
			System.out.format("  %s", c.scoreAsString(c.players()._2));
		}
		
		System.out.println();
		
		
    }
}