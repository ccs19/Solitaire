/**
 * Created by christopher on 11/6/14.
 */
import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Created by christopher on 10/26/14.
 */





public class CardTest extends JPanel implements Serializable{



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
            "ace_of_clubs.png","ace_of_diamonds.png","ace_of_spades.png","ace_of_hearts.png"};

    private static int defaultCardXSize = 500;
    private static int defaultCardYSize = 726;
    private static double cardRatioX = 5;
    private static double cardRatioY = 3;
    //Ratio 3.84x, 1.65y

    private static int cardXSize;
    private static int cardYSize;
    private static double cardDimensionModifier = .50;
    private static BufferedImage img = null;
    private static Image sizedImg = null;

   // private static TestWindow gameWindow;

    private static ImageIcon imgIcon;
    private String cardName;


    private int temp = 0;




    public CardTest(int i, int num){
        temp = num;

        resizeCard(i);
        System.out.println("Adding "+ cardName);
    }


    private String getDir(int i){
        String pwd = System.getProperty("user.dir");
        cardName = cardNames[i];
        return pwd + "/src/textures/" + cardNames[i];
    }

    public String toString(){
        return cardName;
    }

    public void positionCard(int x, int y){
        this.setLocation(x,y);
    }


    public static int defaultCardX(){
        return defaultCardXSize;
    }
    public static int defaultCardY(){
        return defaultCardYSize;
    }


    private void resizeCard(int i){
        try{
            img = ImageIO.read(new File(getDir(i)));
        }
        catch(IOException e){
            e.printStackTrace();
        }

        //Create imgIcon once
        imgIcon = new ImageIcon();
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

        cardXSize = 250;
        cardYSize = 250;



        sizedImg = img.getScaledInstance(cardXSize, cardYSize, Image.SCALE_SMOOTH);
        imgIcon.setImage(sizedImg);
        Dimension d = new Dimension();
        d.setSize(250,250);
        setPreferredSize(d);
    }

    public void paintComponent(Graphics g){
        super.paintComponent(g);
        imgIcon.paintIcon(this, g, 0, temp);
    }





}



