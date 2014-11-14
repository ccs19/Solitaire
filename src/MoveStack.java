import java.awt.*;
import java.io.Serializable;

/**
 * Created by Christopher Schneider on 11/13/14.
 */
public class MoveStack extends CardStack implements Serializable {

    /**
     * Movable card stack
     */
    public MoveStack() {
        super();
        this.setLayout(null);
        this.revalidate();
        this.repaint();
    }

    public boolean allowUserDrop(){
        return false;
    }

    public MoveStack getMoveStack(){
        return this;
    }


    @Override
    public void addCard(CardTexture c){
        c.setBounds(0, 25 * getNumCards(), c.getWidth(), c.getHeight());
        this.add(c);
        this.setComponentZOrder(c, 0);
    }

    @Override
    public void addCard(CardTexture c, int n){
        c.setBounds(0, this.getCardClickedY(), c.getWidth(), c.getHeight());
        this.add(c, getCardClickedIndex());
    }

    //UNUSED
    public CardTexture removeCard(){
        return this.removeCard(this.getNumCards()-1);
    }

    //UNUSED
    public void onContainerChange(){}

}
