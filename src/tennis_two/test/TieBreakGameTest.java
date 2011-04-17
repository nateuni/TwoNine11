package tennis_two.test;

import java.util.Arrays;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import tennis_two.Contest;

@RunWith (Parameterized.class)
public class TieBreakGameTest extends AbstractTest
{
    
    @Parameters
    public static Collection <Object []> data ()
    {
        return Arrays.asList (
                new Object [] [] {
                    {111, 3, 7},
                    {444, 7, 5},
                    {1234567, 6, 8},
                    {9876, 10, 8}
                }
            );
    }
    
    public TieBreakGameTest (long seed, int score_1, int score_2)
    {
        super (seed, score_1, score_2);
    }
    
    @Override
    protected Contest contest ()
    {
        return factory.makeTieBreakGame (federer);
    }
}
