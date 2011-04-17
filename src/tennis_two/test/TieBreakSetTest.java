package tennis_two.test;

import java.util.Arrays;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import tennis_two.Contest;

@RunWith (Parameterized.class)
public class TieBreakSetTest extends AbstractTest
{
    @Parameters
    public static Collection <Object []> data()
    {
        return Arrays.asList (
                new Object [] [] {
                    {3, 5, 7},
                    {1, 4, 6},
                    {8, 6, 7},
                    {9876, 7, 6}
                }
            );
    }
    
    public TieBreakSetTest (long seed, int score_1, int score_2)
    {
        super (seed, score_1, score_2);        
    }

    @Override
    protected Contest contest ()
    {
        return factory.makeTieBreakSet (federer);
    }
}
