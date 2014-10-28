import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by christopher on 10/28/14.
 */
public class DrawStack extends JPanel {


    private int sizeX = CardTexture.defaultCardX();
    private int sizeY = CardTexture.defaultCardX();


    public DrawStack(){
        super(new CardLayout());
        this.addMouseListener(new clickStack());
    }


    private void nextCard(){
        CardLayout c = (CardLayout) this.getLayout();
        c.next(this);
    }


    private class clickStack extends MouseAdapter {


        @Override
        public void mouseClicked(MouseEvent m){
             
            nextCard();

        }

    }

}
