package tennis_two;

/**
 * An advantage set is won by the first player to get to 6 games with an advantage of 2.
 */
public class AdvantageSet extends SerialContest implements Set {
	
	protected AdvantageSet(Player firstServer, int targetScore, int advantage) {
		super(firstServer, targetScore, advantage);
	}

	/* (non-Javadoc)
	 * @see tennis_two.Contest#getContestName()
	 */
	@Override
	public String getContestName() {
			return "AdvantageSet";
	}
	
	/**
	 * Plays an initial SinglesGame before then alternating servers. 
	 */
	@Override
	protected Contest makeSubContest() {
		if(this.currentSubContest == null) {
			return TennisFactory.instance().makeSinglesGame(firstServer);
		} else if(this.server() == this.currentSubContest.server()) {
			return TennisFactory.instance().makeSinglesGame(this.receiver());
		} else {
			return TennisFactory.instance().makeSinglesGame(this.server());
		}
	}
}