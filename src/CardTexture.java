import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by christopher on 10/26/14.
 */





public class CardTexture extends JLabel implements Serializable{



    private static String[] cardNames = {
            "2_of_clubs.png","2_of_diamonds.png","2_of_spades.png","2_of_hearts.png",
            "3_of_clubs.png","3_of_diamonds.png","3_of_spades.png","3_of_hearts.png",
            "4_of_clubs.png","4_of_diamonds.png","4_of_spades.png","4_of_hearts.png",
            "5_of_clubs.png","5_of_diamonds.png","5_of_spades.png","5_of_hearts.png",
            "6_of_clubs.png","6_of_diamonds.png","6_of_spades.png","6_of_hearts.png",
            "7_of_clubs.png","7_of_diamonds.png","7_of_spades.png","7_of_hearts.png",
            "8_of_clubs.png","8_of_diamonds.png","8_of_spades.png","8_of_hearts.png",
            "9_of_clubs.png","9_of_diamonds.png","9_of_spades.png","9_of_hearts.png",
            "10_of_clubs.png","10_of_diamonds.png","10_of_spades.png","10_of_hearts.png",
            "jack_of_clubs.png","jack_of_diamonds.png","jack_of_spades.png","jack_of_hearts.png",
            "queen_of_clubs.png","queen_of_diamonds.png","queen_of_spades.png","queen_of_hearts.png",
            "king_of_clubs.png","king_of_diamonds.png","king_of_spades.png","king_of_hearts.png",
            "ace_of_clubs.png","ace_of_diamonds.png","ace_of_spades.png","ace_of_hearts.png","card_back.png"};

    private static int defaultCardXSize = 500;
    private static int defaultCardYSize = 726;
    private static double cardRatioX = 5;
    private static double cardRatioY = 3;
    //Ratio 3.84x, 1.65y

    private static int cardXSize;
    private static int cardYSize;
    private static double cardDimensionModifier = .50;


    private BufferedImage imgUp = null;
    private Image sizedImgUp = null;
    private static BufferedImage imgDown = null;
    private static Image sizedImgDown = null;



    private static SolitaireWindow gameWindow;

    private ImageIcon faceUp;
    private static ImageIcon faceDown;

    private String cardName;


    CardTexture(int i, SolitaireWindow s){
        gameWindow = s;
        resizeCard(i);
    }

    private String getDir(int i){
        String pwd = System.getProperty("user.dir");
        //System.out.println(pwd);
        cardName = cardNames[i];
        return pwd + "/src/textures/" + cardNames[i];
    }

    public String toString(){
        return cardName;
    }




    private void resizeCard(int i){
        try{
            imgDown = ImageIO.read(new File(getDir(52)));
            imgUp = ImageIO.read(new File(getDir(i)));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        //Create faceUp once
        faceUp = new ImageIcon();
        faceDown = new ImageIcon();
        ResizeImageListener();


        //Re-add later
        /*this.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentMoved(ComponentEvent e) {
                ResizeImageListener();
            }
        });*/


    }


    public void ResizeImageListener() {
        cardXSize = CardConstants.getNewCardSizeX();
        cardYSize = CardConstants.getNewCardSizeY();

        sizedImgUp = imgUp.getScaledInstance(cardXSize, cardYSize, Image.SCALE_SMOOTH);
        sizedImgDown = imgDown.getScaledInstance(cardXSize, cardYSize, Image.SCALE_SMOOTH);

        faceUp.setImage(sizedImgUp);
        faceDown.setImage(sizedImgDown);
        setIcon(faceDown);
        Dimension d = new Dimension();
        d.setSize(cardXSize, cardYSize);
        setPreferredSize(d);
    }

    public void showCard(){
        setIcon(faceUp);
    }

    public void hideCard(){
        setIcon(faceDown);
    }

    @Override
    public int getWidth(){
        return cardXSize;
    }

    @Override
    public int getHeight(){
        return cardYSize;
    }


    public static Dimension getCardDimensions(){
        return null;
    }
}
