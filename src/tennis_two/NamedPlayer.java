package tennis_two;

public class NamedPlayer implements Player
{
    
    final public String name;
    
    public NamedPlayer (String name)
    {
        this.name = name;
    }
    
    @Override
    public String toString ()
    {
        return name;
    }
    
    @Override
    public boolean equals (Object other)
    {
        return other instanceof NamedPlayer
               && ((NamedPlayer) other).name.equals (name);
    }
    
}
