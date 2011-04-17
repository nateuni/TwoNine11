package tennis_two.test;

import java.util.Arrays;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import tennis_two.Contest;

@RunWith (Parameterized.class)
public class PointTest extends AbstractTest
{
    @Parameters
    public static Collection <Object []> data()
    {
        return Arrays.asList (
                new Object [] [] {
                    {987654321, 0, 1},
                    {1234, 1, 0},
                    {111, 1, 0},
                    {3456789, 0, 1}
                }
            );
    }
    
    public PointTest (long seed, int score_1, int score_2)
    {
        super (seed, score_1, score_2);        
    }

    @Override
    protected Contest contest ()
    {
        return factory.makePoint (federer);
    }
    
}
