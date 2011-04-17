package tennis_two.test;

import java.util.Arrays;
import java.util.Collection;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import tennis_two.Contest;

@RunWith (Parameterized.class)
public class AdvantageMatchTest extends AbstractTest
{
    @Parameters
    public static Collection <Object []> data()
    {
        return Arrays.asList (
                new Object [] [] {
                    {3333, 0, 2},
                    {22, 2, 0},
                    {1111, 1, 2},
                    {9876, 2, 1}
                }
            );
    }
    
    public AdvantageMatchTest (long seed, int score_1, int score_2)
    {
        super (seed, score_1, score_2);        
    }

    @Override
    protected Contest contest ()
    {
        return factory.makeAdvantageMatch (nadal, 3);
    }
    
}
