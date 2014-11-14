import java.awt.*;

/**
 * Created by christopher on 11/13/14.
 */
public class MoveStack extends CardStack{

    public MoveStack() {
        super();
        //this.setOpaque(true);
        this.setLayout(null);
        this.revalidate();
        this.repaint();
        this.setVisible(true);

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
        return this.removeCard(0);
    }

    //UNUSED
    public void onContainerChange(){}

    public void printCards(){
        int x = this.getNumCards();

        //debug
        for(int i = 0; i < x; i++){
          //  System.out.println("Cardx: " + this.getComponent(i).getX() + "Cardy: " + this.getComponent(i).getY());

        }
    }


}
