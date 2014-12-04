import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Christopher Schneider on 11/5/14.
 */
public class PlayStack extends CardStack implements Serializable{

    /**
     * Initializes cascaded play stacks
     */
    public PlayStack(){
        super();
        setLayout(null);
    }

    /**
     * Returns card stack type name
     * @return string "PlayStack"
     */
    public String toString(){
        return "PlayStack";
    }

    /**
     * Unused in this class
     */
    public void onContainerChange(){
    }

    /**
     * Remove card based on index
     * @return Card clicked
     */
    public CardTexture removeCard(){
        CardTexture c = removeCard(getCardClickedIndex());
        return c;
    }


    public boolean allowUserDrop(){
        return true;
    }


    /**
     * Add card and place it according to number of cards in stack.
     * @param c card to add to stack
     */
    @Override
    public void addCard(CardTexture c){
        c.setBounds(0, 20 * getNumCards(), c.getWidth(), c.getHeight());
        this.add(c);
        this.setComponentZOrder(c, 0);
        c.setVisible(true);
    }

    /**
     * Add card and cascade it
     * @param c Card to be added
     * @param n Index to add to
     */
    @Override
    public void addCard(CardTexture c, int n){
        c.setBounds(0, this.getCardClickedY(), c.getWidth(), c.getHeight());
        this.add(c, getCardClickedIndex());
    }


    /**
     * Return movestack based on card clicked
     * @return stack clicked
     */
    @Override
    public MoveStack getMoveStack() {
        MoveStack moveStack = new MoveStack();
        int size = this.getCardClickedIndex();

        CardTexture c = (CardTexture)this.getComponent(size);

       if(!c.isCardFaceUp())
           return null;

        for(int i = 0; i < size+1; i++) {
            moveStack.addCard(this.removeCard(size-i));
        }

        return moveStack;
    }

    /**
     * Attempts to add a dragged stack to the PlayStack
     * Follows standard Solitaire rules
     * @return False if invalid drop
     */
    public boolean addCardStackFinish(MoveStack m){
        CardTexture dest = null, source = null;

        if(this.getNumCards() == 0){
            source = m.getCard(m.getNumCards()-1);
            int x = source.getVal();
            if(source.getVal() == 12) {
                this.addCardStack(m);
                return true;
            }
            else return false;
        }


        dest = this.getCard(0);
        source = m.getCard(m.getNumCards() - 1);

        if(dest.getColor().equals(source.getColor()))
            return false;
        else if(source.getVal() != dest.getVal() - 1)
            return false;
        else
            this.addCardStack(m);
        return true;
    }

    @Override
    public void addCardStack(MoveStack m){
        int size = m.getNumCards();
        for(int i = 0; i < size; i++)
            this.addCard(m.removeCard());
        this.repaint();
    }

}
