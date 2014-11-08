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

    CardStack(){
        super();
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
        //this.addMouseListener(new CardClicked());

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
     * If user is allowed to drop cards here return true, else false
     * This does not indicate whether the card added is valid according to game rules
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

    public CardTexture[] getMoveStack(){
        return new CardTexture[13];
    }

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



    public CardTexture getCardClicked(){
        return (CardTexture)cardClicked;
    }

    public int getCardClickedIndex(){
        return cardClickedIndex;
    }

    public void setCardClickedIndex(int s){
        cardClickedIndex = s;
    }

    public void setCardClicked(Component s){
        cardClicked = s;
    }

    public void setValues(MouseEvent m){
        //NOTE: POSSIBLE BUGS.
        //Keep an eye out for null pointer exceptions.
        //NOTE AGAIN:  There ARE exceptions. Possible data loss.
        //TODO: ADD TRY/CATCH
       // Point p = m.getPoint();

        //SwingUtilities.convertPointFromScreen(p, this);
        cardClicked = getComponentAt(m.getPoint());

        Container c = cardClicked.getParent();

        int j = getNumCards();
        for(int i = 0; i < j; i++){
            if(c.getComponent(i) == cardClicked)
                cardClickedIndex = i;
        }
        System.out.println("This: " + cardClicked.toString() + " and this " + cardClickedIndex);
    }

    private class CardClicked extends MouseAdapter implements Serializable{

        @Override
        public void mousePressed(MouseEvent m){

        }

    }

}
