package tennis_two;

import static util.Contract.require;

import util.Two;

public abstract class AbstractContest implements Contest
{
    
    public final Player             firstServer;
    
    /**
     * Constructs the Contest and initialises the server
     * @param firstServer - passed in to initialise the ivar firstServer.
     */
    public AbstractContest (Player firstServer)
    {
        this.firstServer = firstServer;
    }
    
    /* (non-Javadoc)
     * @see tennis_two.Contest#players()
     */
    public Two <Player> players ()
    {
        return TennisFactory.instance ().players ();
    }
    
    /* (non-Javadoc)
     * @see tennis_two.Contest#score(tennis_two.Player)
     */
    public abstract int score (Player player);
    

    public boolean isNew ()
    {
        return score (server ()) == 0 && score (receiver ()) == 0;
    }
    
    /* (non-Javadoc)
     * @see tennis_two.Contest#scoreAsString(tennis_two.Player)
     */
    public String scoreAsString (Player player)
    {
        return Integer.toString (score (player));
    }
    
    /* (non-Javadoc)
     * @see tennis_two.Contest#isOver()
     */
    public abstract boolean isOver ();
    
    /* (non-Javadoc)
     * @see tennis_two.Contest#winner()
     */
    public Player winner ()
    {
        require (isOver (), "isOver");
        if (score (players ()._1) > score (players ()._2))
            return players ()._1;
        else
            return players ()._2;
    }
    
    
    /* (non-Javadoc)
     * @see tennis_two.Contest#loser()
     */
    public Player loser ()
    {
        return players ().other (winner ());
    }
    
    /* (non-Javadoc)
     * @see tennis_two.Contest#server()
     */
    public Player server ()
    {
        return firstServer;
    }
    
    /* (non-Javadoc)
     * @see tennis_two.Contest#receiver()
     */
    public Player receiver ()
    {
        return players ().other (server ());
    }
    
    /* (non-Javadoc)
     * @see tennis_two.Contest#play()
     */
    public abstract void play ();

    /* (non-Javadoc)
     * @see tennis_two.Contest#display()
     */
    public void display ()
    {
        if (TennisFactory.instance ().displayIsEnabled())
            overridableDisplay ();
    }

    /**
     * Calls helper method displayPlayerScore with each player, which results in the player's score being output to console. 
     */
    protected void overridableDisplay ()
    {
        displayPlayerScore (players()._1);
        displayPlayerScore (players()._2);
    }

    /**
     * Prints the passed players score to screen as a formatted string. 
     * @param player - takes the player who's details will be printed to screen. 
     */
    protected void displayPlayerScore (Player player)
    {
        System.out.format ("%-15s%s%n", player, scoreAsString (player));
    }
    
}