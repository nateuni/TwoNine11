package tennis_two;

/**
 * A two-player contest played as a sequence of sub-contests. A serial contest
 * is over when one player reaches the target score, with a specified advantage
 * over the other player. For most types of contest, this advantage should be
 * set to 1 or 2. Subclasses should define the actual sub-contest type. To
 * specify use the SerialContest constructor
 */
public abstract class SerialContest extends AbstractContest
{
    
    final int targetScore;
    final int targetAdvantage;
    int       score_1;
    int       score_2;
    Contest   currentSubContest;
    
    /**
     * Initialise this instance. SerialContest is abstract, and so this
     * constructor is intended for use via calls to super(...) in subclasses.
     * This is where the target score and target advantage should be specified
     * for the particular type of contest being defined.
     * 
     * @param firstServer
     *            the player that starts serving in this contest
     * @param targetScore
     *            the minimum score required to win the contest
     * @param advantage
     *            the minimum lead required to win the contest
     */
    protected SerialContest (Player firstServer, int targetScore, int advantage)
    {
        super (firstServer);
        this.targetScore = targetScore;
        this.targetAdvantage = advantage;
        score_1 = 0;
        score_2 = 0;
    }
    
    /* (non-Javadoc)
     * @see tennis_two.AbstractContest#score(tennis_two.Player)
     */
    public int score (Player player)
    {
        if (players ()._1 ().equals (player))
            return score_1;
        if (players ()._2 ().equals (player))
            return score_2;
        throw new IllegalArgumentException (player + " is not a valid player");
    }
    
    /* (non-Javadoc)
     * @see tennis_two.AbstractContest#isOver()
     */
    public boolean isOver ()
    {
        return Math.max (score_1, score_2) >= targetScore
               && Math.abs (score_1 - score_2) >= targetAdvantage;
    }
    
    /** 
     * Called on a Contest instance (could be a Match, Set or Game or Point).
     * Then continues to play the associated Sub-Contests until this particular contest instance has a winner. 
     * Upon obtaining a winner finish() prints winner and contest information. 
     */
    public void play ()
    {
        start ();
        while (!isOver ())
            playSubContest ();
        finish ();
        
    }
    
    /**
     * Displays the details of the starting contest 
     */
    protected void start ()
    {
        if (TennisFactory.instance ().displayIsEnabled ()) {
            System.out.print (getContestName ());
            System.out.println (" starts");
        }
    }
    
    /**
     * This method internally initialises an instance of a a particular Sub-Contest and then calls play on this Sub-Contest instance.
     * This method calling between play() and playSubContest() results in tree like calling of contests and it's associated subcontests. 
     * 
     * 										 	 |Match|
     * 										   		|
     * 				  					  ---------------------
     * 				  					 |			|		  |
     * 									|Set|	  |Set| 	|Set|
     * 				  					  |							  						  
     * 							  ------------------		
     * 							  |		  |		   |
     * 							|Game|  |Game|  |Game| 			
     * 							  |
     * 				-------------------------
     * 				|		|		|		|
     * 			 |Point| |Point| |Point| |Point| 							
     */
    protected void playSubContest ()
    {
        currentSubContest = makeSubContest ();
        currentSubContest.play ();
        
        onSubContestOver ();
    }
    
    /**
     * This methods is uniquely implemented in each Contest Type and returns an instantiated sub-Contest.
     * 
     * @return Returns a Contest that is a sub-Contest of the calling Contest. 
     */
    protected abstract Contest makeSubContest ();
    
    /**
     * Checks Sub-Contest for winner and updates the players score of THIS instance, before displaying the results. 
     */
    protected void onSubContestOver ()
    {
        Player subWinner = currentSubContest.winner ();
        assert players ().has (subWinner);
        
        if (players ()._1.equals (subWinner))
            ++score_1;
        else
            ++score_2;
        
        display ();
    }

    /**
     * Checks for a winner and displays the both the winner and contest name. 
     */
    protected void finish ()
    {
        if (TennisFactory.instance ().displayIsEnabled ()) {
            System.out.print (winner ());
            System.out.print (" wins ");
            System.out.println (getContestName ());
            System.out.println ();
        }
    }
}
