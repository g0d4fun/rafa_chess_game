package rafa_chess_game.gui;

import java.net.URL;

/**
 * Created by henri on 5/15/2017.
 */
public class Resources
{
    public static URL getResourceFile(String name)
    {
        // opens file with path relative to location of the Resources class
        URL url = Resources.class.getResource(name);
        return url;
    }
}
