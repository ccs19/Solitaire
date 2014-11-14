import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by christopher on 11/5/14.
 */
public class PlayStack extends CardStack implements Serializable{

    public PlayStack(){
        super();
        setLayout(null);
    }

    public String toString(){
        return "PlayStack";
    }

    public void onContainerChange(){
    }

    public CardTexture removeCard(){
        CardTexture c = removeCard(getCardClickedIndex());
        return c;
    }

    /*
     * If user is allowed to drop cards here, return true, else false
     * This does not indicate whether the card addition is valid.
     */

    public boolean allowUserDrop(){
        return true;
    }



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

    /*
     * Shows playable cards
     */
    public void showTopCard(){
        if(this.getComponentCount() == 0)
            return;
        Component[] c = this.getComponents();
        CardTexture card = (CardTexture)c[0];
        card.showCard();
    }

    @Override
    public MoveStack getMoveStack() {
        System.out.println("Adding PlayStack");
        MoveStack moveStack = new MoveStack();
        int size = this.getCardClickedIndex();
        for(int i = 0; i < size+1; i++) {
            System.out.println("Size " + size);
            moveStack.addCard(this.removeCard(size-i));
            System.out.println("Removing index " + (size) );
        }
        this.repaint();
        return moveStack;
    }
}
