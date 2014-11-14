import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.Serializable;

/**
 * Created by christopher on 10/28/14.
 */
public class DiscardStack extends CardStack implements Serializable {

    /**
     * Drawn cards are discarded to this stack, face up
     */
    public DiscardStack(){
        super();
        this.setLayout(new CardLayout());
    }


    public String toString(){
        return "DiscardStack";
    }

    /**
     * When card added, display top card
     */
    public void onContainerChange(){
        CardLayout cl = (CardLayout) this.getLayout();
        cl.last(this);
    }

    /**
     * Remove top visible card
     * @return visible card
     */
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

    /**
     * Return one card, top of stack
     * @return card stack
     */
    @Override
    public MoveStack getMoveStack() {
        MoveStack ms = new MoveStack();
        ms.addCard(this.removeCard(getNumCards()-1));
        return ms;
    }
}
