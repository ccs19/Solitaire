import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

/**
 * Created by christopher on 10/28/14.
 */
public class DiscardStack extends CardStack implements Serializable {


    public DiscardStack(){
        super();
        this.setLayout(new CardLayout());
    }

    public String toString(){
        return "DiscardStack";
    }

    public void onContainerChange(){
        CardLayout cl = (CardLayout) this.getLayout();
        cl.last(this);
    }

    public CardTexture removeCard(){
        CardTexture c = removeCard(getNumCards()-1);
        return c;
    }

    /*
     * If user is allowed to drop cards here, return true, else false
     */
    public boolean allowUserDrop(){
        return false;
    }

    @Override
    public MoveStack getMoveStack() {
        MoveStack ms = new MoveStack();
        return ms;
    }
}
