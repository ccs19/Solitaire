import javax.swing.*;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.io.Serializable;

/**
 * Created by christopher on 10/29/14.
 */
public abstract class CardStack extends JPanel implements Serializable{


    CardStack(){
        super();

        this.addContainerListener(new ContainerListener() {
            @Override
            public void componentAdded(ContainerEvent containerEvent) {
                redraw();
            }

            @Override
            public void componentRemoved(ContainerEvent containerEvent) {
                redraw();
            }
        });

    }

    private void redraw(){
        onContainerChange();
        revalidate();
        repaint();
    }


    /**
     * Additional actions to perform when cardstack changes.
     */
    public abstract void onContainerChange();

    /*
     * If user is allowed to drop cards here, return true, else false
     * This does not indicate whether the card added is valid
     */
    public abstract boolean allowUserDrop();

    /**
     * Returns the number of cards currently on the stack
     * @return number of cards
     */
    public int getNumCards(){
        return this.getComponentCount();
    }

    /**
     * Add a card to the stack
     * @param c card to add to stack
     */
    public void addCard(CardTexture c){
        this.add(c);
    }

    /**
     * Remove and return a card from the stack.
     * @return Card removed
     */
    public abstract CardTexture removeCard();


    /**
     * Removes and returns card by index
     * If no cards, return null
     * @param n Index of card
     * @return CardTexture removed from stack
     */
    protected CardTexture removeCard(int n){
        if(getNumCards()>0) {
            CardTexture c = (CardTexture) this.getComponent(n);
            this.remove(n);
            return c;
        }
        else
            return null;
    }


}
