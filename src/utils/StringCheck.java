package utils;

public class StringCheck {
    public static boolean IsBlankOrNotNumelic(String str)
    {
        if(str.isEmpty())
        {
            return true;
        }

        if(str==" " ||str=="　")
        {
            return true;
        }

        if(!str.matches("[0-9]+"))
        {
            return true;
        }

        return false;
    }
}
