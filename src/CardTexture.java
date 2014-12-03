import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.URL;

/**
 * Created by Christopher Schneider on 10/26/14.
 */





public class CardTexture extends JLabel implements Serializable{


    //Card file names
    private static final String[] cardNames = {
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


    private static int cardXSize;
    private static int cardYSize;

    private transient BufferedImage imgUp = null;
    private transient Image sizedImgUp = null;
    private transient static BufferedImage imgDown = null;
    private transient static Image sizedImgDown = null;

    private ImageIcon faceUp;
    private static ImageIcon faceDown;

    private Card card = null;

    /**
     * Class to visibly show cards
     * @param i Value of card from 0-51
     */
    CardTexture(int i){
        resizeCard(i);
        setCard(i);
    }

    /**
     * Creates an instance of card class
     * @param i Value of card from 0-51
     */
    private void setCard(int i){
        card = new Card(i);
    }

    /**
     * Return suit of card
     * @return suit of card
     */
    public String getSuit(){
        return card.getSuit();
    }

    /**
     * Return numerical value of card from 1-13
     * @return value of card
     */
    public int getVal(){
        return card.getNumber();
    }

    /**
     * Return color of card
     * @return color of card
     */
    public String getColor(){
        return card.getColor();
    }


    /**
     * Return name of card. e.g. 2,3, ... , King, Ace
     * @return name of card
     */
    public String toString(){
        return card.toString();
    }

    /**
     * Open card and create icons
     * @param i Value of card from 0-51
     */
    private void resizeCard(int i){
        try{
            imgDown = ImageIO.read(getClass().getResourceAsStream("textures/" + cardNames[52]));
            imgUp = ImageIO.read(getClass().getResourceAsStream("textures/" + cardNames[i]));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        //Create faceUp once
        faceUp = new ImageIcon();
        faceDown = new ImageIcon();
        ResizeImageListener();
    }

    /**
     *   Sizes the card initially
     */
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

    /**
     * Shows the card's face value
     */
    public void showCard(){
        setIcon(faceUp);
    }


    /**
     * Shows the card face down.
     */
    public void hideCard(){
        setIcon(faceDown);
    }

    /**
     * Returns the card's X value in pixels
     * @return Card X size
     */
    @Override
    public int getWidth(){
        return cardXSize;
    }

    /**
     * Returns the card's Y value in pixels
     * @return Card Y size
     */
    @Override
    public int getHeight(){
        return cardYSize;
    }


    /**
     * Returns if card is faceup or not
     * @return True if faceup, false if face down
     */
    public boolean isCardFaceUp(){
        if(this.getIcon() == faceUp)
            return true;
        else
            return false;
    }



}
