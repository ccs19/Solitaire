import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by christopher on 10/28/14.
 */
public class DiscardStack extends JPanel {


    private int sizeX = CardTexture.defaultCardX();
    private int sizeY = CardTexture.defaultCardX();
    private int numCards = 0;
    private Dimension dimension = new Dimension();
    public DiscardStack(){
        super();
        this.setLayout(new CardLayout());
        dimension.setSize(1,1);
        this.setMaximumSize(dimension);


    }


    public void addCard(CardTexture c){
        this.add(c);
        showBottomCard();
        repaint();
        revalidate();
    }

    public CardTexture removeCard(){
        if(getNumCards() == 0)
               return null;
        CardTexture c = (CardTexture)this.getComponent(getComponentCount()-1);
        this.redraw();
        return c;
    }

    private void showBottomCard(){
        CardLayout cl = (CardLayout) this.getLayout();
        cl.last(this);
    }

    public void redraw(){
        showBottomCard();
        repaint();
        revalidate();
    }

    public int getSizeX(){
        return sizeX;
    }

    public int getSizeY(){
        return sizeY;
    }

    public int getNumCards(){
        return this.getComponentCount();
    }

    public String toString(){
        return "DiscardStack";
    }

}
