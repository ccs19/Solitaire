import javax.swing.*;
import java.awt.*;
import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * Created by christopher on 10/29/14.
 */
public abstract class CardStack extends JPanel implements Serializable{

    private Component cardClicked= null;
    private int cardClickedIndex = -1;
    private int cardClickedY = 0;

    CardStack(){
        super();
        this.setBackground(Color.BLACK);
        Dimension d = new Dimension();
        d.setSize(CardConstants.getNewCardSizeX(), CardConstants.getNewCardSizeY());
        this.setPreferredSize(d);
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

    /**
     * Repaint/revalidate when stacks change
     */
    private void redraw(){
        onContainerChange();
        revalidate();
        repaint();
    }


    /**
     * Additional actions to perform when cardstack changes.
     */
    public abstract void onContainerChange();

    /**
     * Returns true if user is allowed to drop cards, else false.
     * Not based on game rules
     * @return Allowed user drop
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
     * Add a card to top of stack (Index 0)
     * @param c card to add to stack
     */
    public void addCard(CardTexture c){
        this.add(c);
        redraw();
        c.setVisible(true);
    }


    /**
     * Add card to specified index
     * @param c Card to be added
     * @param n Index to add to
     */
    public void addCard(CardTexture c, int n){
        this.add(c,n);
        c.setVisible(true);
        redraw();

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
    public CardTexture removeCard(int n){
        if(getNumCards()>0) {
            CardTexture c = (CardTexture) this.getComponent(n);
            this.remove(n);
            return c;
        }
        else
            return null;
    }


    /**
     * Returns card by index without removing from stack
     * If empty, returns null
     * @return card by index
     */
    public CardTexture getCard(int n){
        if(getNumCards() > 0){
            CardTexture c = (CardTexture) this.getComponent(n);
            return c;
        }
        else
            return null;
    }


    /**
     * Return index of card clicked in stack
     * @return index of card clicked
     */
    public int getCardClickedIndex(){
        return cardClickedIndex;
    }

    /**
     * Return Y axis value of card clicked in reference to stack it's in
     * @return Y value of card
     */
    public int getCardClickedY(){
        return cardClickedY;
    }

    /**
     * Gets stack based on cards clicked
     * @return Card stack clicked
     */
    public abstract MoveStack getMoveStack();


    /**
     * Adds moved stack to this stack
     * @param m stack to be added
     */
    public void addCardStack(MoveStack m){
        int size = m.getNumCards();
        for(int i = 0; i < size; i++)
            this.addCard(m.removeCard());
        this.repaint();

    }

    /**
     * Find card clicked and set card index
     * @param m Mouse event on clicked card stack
     */
    public void setValues(MouseEvent m){
        cardClicked = getComponentAt(m.getPoint());
        Container c = cardClicked.getParent();

        int j = getNumCards();
        for(int i = 0; i < j; i++){
            if(c.getComponent(i) == cardClicked)
                cardClickedIndex = i;
        }
        cardClickedY = cardClicked.getY();
        cardClicked.setVisible(true);
    }



    /**
     * Make top card visible
     */
    public void showTopCard(){
        if(this.getComponentCount() == 0)
            return;
        Component[] c = this.getComponents();
        CardTexture card = (CardTexture)c[0];
        card.showCard();
    }

}
