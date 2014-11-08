import java.awt.*;

/**
 * Created by christopher on 11/7/14.
 */
public final class CardConstants {

    /*==============================
        Card Size Constants
     ===============================*/

    private static int defaultCardXSize = 500;
    private static int defaultCardYSize = 726;
    //Ratio 3.84x, 1.65y

    private static int cardXSize;
    private static int cardYSize;
    private static double cardDimensionModifier = .20;



    public static int getNewCardSizeX(){
        return (int)((double)defaultCardXSize*cardDimensionModifier);
    }

    public static int getNewCardSizeY(){
        return (int)((double)defaultCardYSize*cardDimensionModifier);
    }

    public static int getDefaultCardXSize(){
        return defaultCardXSize;
    }

    public static int getDefaultCardYSize(){
        return defaultCardYSize;
    }

    public static Dimension getScreenResolution(){
        GraphicsDevice g = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();
        Dimension d = new Dimension();
        d.setSize(g.getDisplayMode().getWidth(), g.getDisplayMode().getHeight());
        return d;
    }

}
