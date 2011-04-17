package tennis_two;

import java.util.ArrayList;

/**
 * An advantage match is made up of advantage sets. 
 * Typically a match will be best of 3 or best of 5 sets, as determined by the maxSetCount argument of the factory method.
 * The first server in the match serves in the first game of the first set. 
 * The serve then alternates between the players in successive games.
 */
public class AdvantageMatch extends SerialContest implements Match {
	
	/**
	 * This array list is used to keep Contest pointers for later use in printing contest scores.
	 */
	private static ArrayList<Contest> matchList;
	
	/**
	 * Used to aggregate the total games played, which then will define the next server.
	 */
	private static int totalGamesPlayed;

	protected AdvantageMatch(Player firstServer, int targetScore, int advantage) {
		super(firstServer, targetScore, advantage);
		matchList = new ArrayList<Contest>();
	}
	
	/* (non-Javadoc)
	 * @see tennis_two.Contest#getContestName()
	 */
	@Override
	public String getContestName() {
			return "AdvantageMatch";
	}
	
	/**
	 * Adds the Contest reference to a list and aggregates total games played. 
	 */
	protected void addContestToMatchList() {
		matchList.add(this.currentSubContest);
		totalGamesPlayed += this.currentSubContest.score(this.currentSubContest.server()) + this.currentSubContest.score(this.currentSubContest.receiver());
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
			return TennisFactory.instance().makeAdvantageSet(firstServer);
		} else if (totalGamesPlayed % 2 == 0) {
			return TennisFactory.instance().makeAdvantageSet(this.server());
		} else {
			return TennisFactory.instance().makeAdvantageSet(this.receiver());
		}
	}
	
	/**
	 * Method is overridden to include to see if the definition of a the end of Tie-Break Match has been met.
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
