package tennis_two.test;

import java.util.Arrays;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import tennis_two.Contest;

@RunWith (Parameterized.class)
public class SinglesGameTest extends AbstractTest
{
    @Parameters
    public static Collection <Object []> data()
    {
        return Arrays.asList (
                new Object [] [] {
                    {987654321, 2, 4},
                    {1234, 4, 6},
                    {111, 3, 5},
                    {33333, 7, 5}
                }
            );
    }
    
    public SinglesGameTest (long seed, int score_1, int score_2)
    {
        super (seed, score_1, score_2);        
    }

    @Override
    protected Contest contest ()
    {
        return factory.makeSinglesGame (federer);
    }
    
}
