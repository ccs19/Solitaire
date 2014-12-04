import java.awt.*;
import java.io.Serializable;

/**
 * Created by christopher on 11/7/14.
 */
public final class CardConstants implements Serializable{

    /*==============================
        Card Size Constants
     ===============================*/

    private static int defaultCardXSize = 400;
    private static int defaultCardYSize = 600;
    //Ratio 3.84x, 1.65y
    private static int numCards = 52;

    private static int cardXSize;
    private static int cardYSize;
    private static double cardDimensionModifier = .20;


    /**
     * Size card based on modifier
     * @return new card size x
     */
    public static int getNewCardSizeX(){
        return (int)((double)defaultCardXSize*cardDimensionModifier);
    }

    /**
     * Size card based on modifier
     * @return new card size y
     */
    public static int getNewCardSizeY(){
        return (int)((double)defaultCardYSize*cardDimensionModifier);
    }

    /**
     * Default card size X
     * @return Default card size X
     */
    public static int getDefaultCardXSize(){
        return defaultCardXSize;
    }

    /**
     * Return default number of cards in a deck
     * @return number of cards in a deck
     */
    public static int getNumCards(){
        return numCards;
    }

    /**
     * Return default card size y
     * @return default card size y
     */
    public static int getDefaultCardYSize(){
        return defaultCardYSize;
    }

    /**
     * Gets current screen resolution of primary monitor
     * @return Screen resolution of primary monitor
     */
    public static Dimension getScreenResolution(){
        GraphicsDevice g = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        Dimension d = new Dimension();
        d.setSize(g.getDisplayMode().getWidth(), g.getDisplayMode().getHeight());
        return d;
    }

}
