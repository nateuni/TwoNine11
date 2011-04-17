package tennis_two;

import util.Two;

public interface Contest
{
	/**
     * Used to access the pair of players for the associated instance. 
     * @return Returns "Two" which contains a pair of players
     */
    Two <Player> players ();
    
    /**
     * Returns the score of the associated player as an int. 
     * This methods takes an argument of type Player and then returns that player's score as an int. 
     * @param player - takes an argument of a particular player.
     * @return Returns the passed players score as an int value.
     */
    int score (Player player);
    
    /**
     * Returns the score of the associated player as a String. 
     * This methods takes an argument of type Player and then returns that player's score as a String. 
     * @param player - takes an argument of a particular player.
     * @return Returns the passed players score as a String.
     */
    String scoreAsString (Player player);
    
    /**
     * Checks if the contest instance is new
     * @return returns true if both players scores are equal to 0 or else returns false.
     */
    boolean isNew ();
    
    /**
     * Checks if the contest instance is over
     * @return returns true if the restraints of the particular contest instance have been met.  
     */
    boolean isOver ();
    
    /**
     * Returns the winner
     * @return Returns winning player as type Player.
     */
    Player winner ();
    
    /**
     * Returns the loser
     * @return Returns loosing player as type Player.
     */
    Player loser ();
    
    /**
     * Returns the server of the contest instance. 
     * @return Returns serving player as type Player.
     */
    Player server ();
    
    /**
     * Returns the receiver of the contest instance. . 
     * @return Returns receiving player as type Player.
     */
    Player receiver ();
    
    /**
     * Get the current contest's name
     * @return Returns the contest's name as a String.
     */
    String getContestName ();
    
    /**
     * Display the scores of the associated Contest
     */
    void display ();
    
    /**
     * Initiates the playing of the current contest
     */
    void play ();
    
}
