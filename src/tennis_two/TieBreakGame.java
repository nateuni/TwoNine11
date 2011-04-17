package tennis_two;

/**
 * A Tie-Break Game is made up of Points. In a tie-break game, serve alternates between players after each odd point. 
 * The server changes after the first point, third point, fifth point etc.
 */
public class TieBreakGame extends SerialContest implements Game {
	
	protected TieBreakGame(Player firstServer, int targetScore, int advantage) {
		super(firstServer, targetScore, advantage);
	}
	
	
	/* (non-Javadoc)
	 * @see tennis_two.Contest#getContestName()
	 */
	@Override
	public String getContestName() {
		return "TieBreakGame";
	}

	/**
	 * Returns an instantiated Tie-Break Point with the correct Server.
	 * If no Points have been played - Point is instantiated with the firstServer of the Game Contest instance.
	 * If Points have been played - The server passed into the Factory depends on the aggregate score of games played. 
	 * 
	 * @return - Instantiated Point Contest
	 */
	@Override
	protected Contest makeSubContest() {
		if(this.currentSubContest == null) {
			return TennisFactory.instance().makePoint(firstServer);
		} else {
			if((this.score_1 + this.score_2) % 2 != 0){
				return TennisFactory.instance().makePoint(players().other(this.currentSubContest.server()));
			} else {
				return TennisFactory.instance().makePoint(this.currentSubContest.server());
			}
		}
	}
}
