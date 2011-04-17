package tennis_two;

import static util.Contract.require;

public class SinglesPoint extends AbstractContest implements Point
{
    private Player winner = null;
    
    public SinglesPoint (Player firstServer)
    {
        super (firstServer);
    }

    @Override
    public boolean isOver ()
    {
        return winner != null;
    }
    
    @Override
    public void play ()
    {
        winner = TennisFactory.instance ().nextPointWinner ();
        display ();
    }
    
    @Override
    public int score (Player player)
    {
        require (players ().has (player), "player is one of players in match");
        if (winner != null && winner.equals (player))
            return 1;
        else
            return 0;
    }

    @Override
    protected void overridableDisplay ()
    {
        System.out.print (firstServer);
        System.out.print (" serves and ");
        String result = firstServer.equals (winner) ? "wins" : "loses";
        System.out.println (result);
    }

    @Override
    public String getContestName ()
    {
        return "SinglesPoint";
    }
    
}
