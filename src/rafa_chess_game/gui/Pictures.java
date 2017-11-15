package rafa_chess_game.gui;

import java.awt.Image;
import javax.imageio.ImageIO;;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by henri on 5/15/2017.
 */
public class Pictures implements UIConstants
{
    private static Map<String, Image> pictures = new HashMap<String,Image>();

    static
    {
        try
        {
            pictures.put(AIM_MOVE,ImageIO.read(Resources.getResourceFile(PATH_IMG_AIM_MOVE))
                    .getScaledInstance(PIECE_WIDTH,PIECE_HEIGHT,Image.SCALE_SMOOTH));
            
            pictures.put(DARK_PAWN, ImageIO.read(Resources.getResourceFile(PATH_IMG_DARK_PAWN))
                    .getScaledInstance(PIECE_WIDTH,PIECE_HEIGHT,Image.SCALE_SMOOTH));
            pictures.put(DARK_ROOK, ImageIO.read(Resources.getResourceFile(PATH_IMG_DARK_ROOK))
                    .getScaledInstance(PIECE_WIDTH,PIECE_HEIGHT,Image.SCALE_SMOOTH));
            pictures.put(DARK_KNIGHT, ImageIO.read(Resources.getResourceFile(PATH_IMG_DARK_KNIGHT))
                    .getScaledInstance(PIECE_WIDTH,PIECE_HEIGHT,Image.SCALE_SMOOTH));
            pictures.put(DARK_BISHOP, ImageIO.read(Resources.getResourceFile(PATH_IMG_DARK_BISHOP))
                    .getScaledInstance(PIECE_WIDTH,PIECE_HEIGHT,Image.SCALE_SMOOTH));
            pictures.put(DARK_KING, ImageIO.read(Resources.getResourceFile(PATH_IMG_DARK_KING))
                    .getScaledInstance(PIECE_WIDTH,PIECE_HEIGHT,Image.SCALE_SMOOTH));
            pictures.put(DARK_QUEEN, ImageIO.read(Resources.getResourceFile(PATH_IMG_DARK_QUEEN))
                    .getScaledInstance(PIECE_WIDTH,PIECE_HEIGHT,Image.SCALE_SMOOTH));
            
            pictures.put(LIGHT_PAWN, ImageIO.read(Resources.getResourceFile(PATH_IMG_LIGHT_PAWN))
                    .getScaledInstance(PIECE_WIDTH,PIECE_HEIGHT,Image.SCALE_SMOOTH));
            pictures.put(LIGHT_ROOK, ImageIO.read(Resources.getResourceFile(PATH_IMG_LIGHT_ROOK))
                    .getScaledInstance(PIECE_WIDTH,PIECE_HEIGHT,Image.SCALE_SMOOTH));
            pictures.put(LIGHT_KNIGHT, ImageIO.read(Resources.getResourceFile(PATH_IMG_LIGHT_KNIGHT))
                    .getScaledInstance(PIECE_WIDTH,PIECE_HEIGHT,Image.SCALE_SMOOTH));
            pictures.put(LIGHT_BISHOP,ImageIO.read(Resources.getResourceFile(PATH_IMG_LIGHT_BISHOP))
                    .getScaledInstance(PIECE_WIDTH,PIECE_HEIGHT,Image.SCALE_SMOOTH));
            pictures.put(LIGHT_KING,ImageIO.read(Resources.getResourceFile(PATH_IMG_LIGHT_KING))
                    .getScaledInstance(PIECE_WIDTH,PIECE_HEIGHT,Image.SCALE_SMOOTH));
            pictures.put(LIGHT_QUEEN,ImageIO.read(Resources.getResourceFile(PATH_IMG_LIGHT_QUEEN))
                    .getScaledInstance(PIECE_WIDTH,PIECE_HEIGHT,Image.SCALE_SMOOTH));
        } catch (Exception e)
        {
            System.out.println("Error Initializing Pictures.");
            e.printStackTrace();
        }
    }

    public static Image getPicture(String name)
    {
        return pictures.get(name);
    }
    public static Map<String, Image> getPicturesMap()
    {
        return pictures;
    }
}
