import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * Created by christopher on 10/28/14.
 */
public class DrawStack extends CardStack implements Serializable{


    DiscardStack dStack = null;

    public DrawStack(DiscardStack discardStack){
        super();
        this.setLayout(new CardLayout());
        dStack = discardStack;
        this.addMouseListener(new clickStack());
    }

    @Override
    public MoveStack getMoveStack() {
        return null;
    }

    /**
     * Draw next card and place on DiscardStack
     */
    private void nextCard(){
        CardTexture c = this.removeCard();
        c.showCard();
        dStack.addCard(c);
    }

    @Override
    public void addCard(CardTexture c){
        this.add(c, getNumCards());
    }

    public CardTexture removeCard(){
        CardTexture c = this.removeCard(0);
        return c;
    }

    public boolean allowUserDrop(){
        return false;
    }


    public void onContainerChange(){}




    private class clickStack extends MouseAdapter implements Serializable {
        @Override
        public void mouseClicked(MouseEvent m){
            if(getNumCards() > 0) {
                nextCard();
                System.out.println("num cards: " + getNumCards());
            }
            else{
                while(dStack.getNumCards() > 0){
                    CardTexture c = dStack.removeCard(0);
                    c.hideCard();
                    addCard(c);
                }
            }
        }

    }

}
