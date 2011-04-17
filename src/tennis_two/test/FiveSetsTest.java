package tennis_two.test;

import java.util.Arrays;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import tennis_two.Contest;

@RunWith (Parameterized.class)
public class FiveSetsTest extends AbstractTest
{
    @Parameters
    public static Collection <Object []> data()
    {
        return Arrays.asList (
                new Object [] [] {
                    {3333, 0, 3},
                    {22, 3, 0},
                    {1111, 1, 3},
                    {999, 3, 2}
                }
            );
    }
    
    public FiveSetsTest (long seed, int score_1, int score_2)
    {
        super (seed, score_1, score_2);        
    }

    @Override
    protected Contest contest ()
    {
        return factory.makeAdvantageMatch (nadal, 5);
    }
    
}
