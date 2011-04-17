 package tennis_two;

 /**
  * A tie-break set is played like a normal set until the game score reaches 6-6.
  * Then the next game to be played is a tie-break game.
  * The winner of the tie-break game wins the set (with a score of 7-6 or 6-7).
  * The receiver for the last game becomes the first server in the tie-break game.
  *
  */
public class TieBreakSet extends SerialContest implements Set {
	
	protected TieBreakSet(Player firstServer, int targetScore, int advantage) {
		super(firstServer, targetScore, advantage);
	}

	/* (non-Javadoc)
	 * @see tennis_two.Contest#getContestName()
	 */
	@Override
	public String getContestName() {
		return "TieBreakSet";
	}

	/**
	 * Makes sure a Tie-Break Game has played. 
	 * If a the set has reached a Tie-Break then the Tie-Break Game is called with the receiver of the current Set.
	 */
	@Override
	protected Contest makeSubContest() {
		if(this.currentSubContest == null) {
			return TennisFactory.instance().makeSinglesGame(firstServer);
		} else if (!isSixAllTieBreaker()) {
			//return TennisFactory.instance().makeSinglesGame(this.currentSubContest.players().other(this.currentSubContest.server()));
			return TennisFactory.instance().makeSinglesGame(this.currentSubContest.receiver());
		} else {
			return TennisFactory.instance().makeTieBreakGame(this.currentSubContest.receiver());
		}
	}
	
	/**
	 * Check the score to see if the Set qualifies as a Six all Tie-Breaker
	 * @return The result as a boolean
	 */
	private boolean isSixAllTieBreaker() {
		if((this.score_1 >= 6 && this.score_2 >= 6) && Math.abs(this.score_1 - this.score_2) < 2)
			return true;
		else
			return false;
	}

	/**
	 * This method was overridden to meet the requirements for testing if a Tie-Break Set is over.
	 */
	@Override
    public boolean isOver () {
		if(this.score_1 == 6 && this.score_2 == 7 || this.score_1 == 7 && this.score_2 == 6)
			return true;
        return Math.max (score_1, score_2) >= targetScore && Math.abs (score_1 - score_2) >= targetAdvantage;
    }
}