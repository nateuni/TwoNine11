package tennis_two.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import tennis_two.*;
import util.Two;

public abstract class AbstractTest
{
    
    Player        federer = new NamedPlayer ("Federer");
    Player        nadal   = new NamedPlayer ("Nadal");
    TennisFactory factory;
    Contest       contest;
    
    long          seed;
    int           score_1;
    int           score_2;
    
    public AbstractTest (long seed, int score_1, int score_2)
    {
        this.seed = seed;
        this.score_1 = score_1;
        this.score_2 = score_2;        
    }
    
    @Before
    public void setUp () throws Exception
    {
        factory = TennisFactory.make (Two.two (federer, nadal), seed);
        contest = contest ();
    }
    
    protected abstract Contest contest ();

    @Test
    public void testBefore ()
    {
        assertTrue ("is new", contest.isNew ());
        assertFalse ("is not over", contest.isOver ());
        assertTrue ("Federer in contest", contest.players ().has (federer));
        assertTrue ("Nadal in contest", contest.players ().has (nadal));
        assertEquals ("Federer score", 0, contest.score (federer));
        assertEquals ("Nadal score", 0, contest.score (nadal));
    }
    
    @Test
    public void testDisplay ()
    {
        factory.setDisplayOn ();
        contest.play ();
    }
    
    @Test
    public void testPlay ()
    {
        contest.play ();
        assertFalse ("is not new", contest.isNew ());
        assertTrue ("is over", contest.isOver ());
        
    }
    
    @Test
    public void testScore ()
    {
        contest.play ();
        assertEquals ("score_1 after play", score_1,
                contest.score (contest.players ()._1 ()));
        assertEquals ("score_2 after play", score_2,
                contest.score (contest.players ()._2 ()));
    }
    
    @Test
    public void testWinner ()
    {
        contest.play ();
        Player expectedWinner = federer;
        Player expectedLoser = nadal;
        if (score_2 > score_1) {
            expectedWinner = nadal;
            expectedLoser = federer;
        }
        assertEquals ("winner", expectedWinner, contest.winner ());
        assertEquals ("loser", expectedLoser, contest.loser ());
    }
}
