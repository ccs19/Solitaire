import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by christopher on 10/26/14.
 */





public class CardTexture extends JLabel{



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

    private static double defaultCardXSize = 500;
    private static double defaultCardYSize = 726;
    private static double cardRatioX = 3.84;
    private static double cardRatioY = 1.65;

    private static int cardXSize;
    private static int cardYSize;
    private static double cardDimensionModifier = .50;
    private static BufferedImage img = null;
    private static Image sizedImg = null;

    private static SolitaireWindow gameWindow;

    private static ImageIcon imgIcon;


    CardTexture(int i, SolitaireWindow s){
        gameWindow = s;
        resizeCard(i);
    }

    private String getDir(int i){
        String pwd = System.getProperty("user.dir");
        System.out.println(pwd);
        return pwd + "/src/textures/" + cardNames[i];
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

        cardXSize = (int)(cardDimensionModifier * (double) gameWindow.getXSize()/cardRatioX);
        cardYSize = (int)(cardDimensionModifier * (double) gameWindow.getYSize() / cardRatioY);

        System.out.println("Inside here!" + this.getWidth() + " " + this.getHeight());


        sizedImg = img.getScaledInstance(cardXSize, cardYSize, Image.SCALE_SMOOTH);
        imgIcon.setImage(sizedImg);
        setIcon(imgIcon);
        setSize(cardXSize,cardYSize);
    }





}
