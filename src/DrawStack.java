import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by christopher on 10/28/14.
 */
public class DrawStack extends DiscardStack {


    DiscardStack dStack = null;

    public DrawStack(CardGrid cardGrid, DiscardStack discardStack){
        super();
        dStack = discardStack;
        this.addMouseListener(new clickStack());
    }

    private void nextCard(){
        dStack.addCard(this.removeCard());
        repaint();
        revalidate();

    }

    @Override
    public void addCard(CardTexture c){
        this.add(c, getNumCards());
        repaint();
        revalidate();
    }

    @Override
    public CardTexture removeCard(){
        if(getNumCards() == 0)
            return null;
        CardTexture c = (CardTexture)this.getComponent(0);
        repaint();
        revalidate();
        return c;
    }





    private class clickStack extends MouseAdapter {
        @Override
        public void mouseClicked(MouseEvent m){
            if(getNumCards() > 0) {
                nextCard();
                System.out.println("num cards: " + getNumCards());
            }
            else{
                while(dStack.getNumCards() > 0){
                    addCard(dStack.removeCard());
                }
            }


        }

    }

}
