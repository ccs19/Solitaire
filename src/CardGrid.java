import javax.swing.*;
import java.awt.*;
import java.awt.CardLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.Serializable;

/**
 * Created by christopher on 10/27/14.
 */
public class CardGrid extends JLayeredPane implements Serializable {


    private static int WIDTH = 1300;
    private static int HEIGHT = 1000;


    private static final int ROWS = 2;
    private static final int COLUMNS = 7;
    private static final int SPACE = 10;
    private static final Dimension CARDGRIDSIZE = new Dimension(WIDTH,HEIGHT);
    private static final Dimension LABEL_SIZE = new Dimension(128,100);
    private static GridLayout gridLayout = new GridLayout(ROWS, COLUMNS, SPACE, SPACE);
    private static JPanel backPanel = new JPanel(gridLayout);
    private static JPanel[][] cardGrid = new JPanel[ROWS][COLUMNS];

    private CardGrid thisPane = this;


    //Need to create deck of cards?
    private static int NUMSUITS = 4;




    public CardGrid(SolitaireWindow s) {
        backPanel.setSize(CARDGRIDSIZE);
        backPanel.setLocation(2 * SPACE, 2 * SPACE);
        backPanel.setBackground(Color.GREEN);
       // gridBagConstraints.fill = GridBagConstraints.VERTICAL;
       // gridBagConstraints.gridheight = 13;



        //Create special card stacks
        cardGrid[0][1] = new DiscardStack();
        cardGrid[0][2] = new DiscardStack();
        cardGrid[0][0] = new DrawStack((DiscardStack)cardGrid[0][1]);

        //Create finish stacks (Where we drop cards to win)
        for(int i = 0; i < NUMSUITS; i++)
            cardGrid[0][i+3] = new FinishStack();

        //Create regular card stacks
        for(int c = 0; c < COLUMNS; c++)
            cardGrid[1][c] = new PlayStack();

        //Add all stacks to JPanel
        for(int r = 0; r < ROWS; r++)
            for(int c = 0; c < COLUMNS; c++) {
                    backPanel.add(cardGrid[r][c]);
            }

        //Fill stacks with cards
        addAllCards(s);


        backPanel.setBorder(BorderFactory.createEmptyBorder(SPACE, SPACE, SPACE, SPACE));
        setPreferredSize(CARDGRIDSIZE);
        add(backPanel, JLayeredPane.DEFAULT_LAYER);
        CardDrag mouseAdapter = new CardDrag();
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);
    }


    private void addAllCards(SolitaireWindow s){
        int p = 1;
        int i = 0;
        int done = 0;

        DrawStack d = (DrawStack)cardGrid[0][0];

        while(i < 52)
        {
            PlayStack ps;
            if(done == 0)
            for(int j = 0; j < COLUMNS; j++){
                ps = (PlayStack)cardGrid[1][j];
                for(int k = 0; k != p; k++) {
                    CardTexture c = new CardTexture(i, s);
                    ps.addCard( c );
                    i++;
                }
                ps.showTopCard();
                p++;
            }
            done = 1;
            d.addCard(new CardTexture(i, s));
            i++;
        }
    }

    public JPanel getBackPanel(){
        return backPanel;
    }


    //Mouse drag class
    private class CardDrag extends MouseAdapter implements Serializable{

        private CardTexture dragCard = null;
        private int wDiv;
        private int hDiv;
        private CardStack clickedCardPanel = null;



        @Override
        public void mousePressed(MouseEvent m) {

            clickedCardPanel = (CardStack)backPanel.getComponentAt(m.getPoint());

            Component[] c = clickedCardPanel.getComponents();
            if(c.length == 0 )
                return;
            if(c[0] instanceof CardTexture) {
               clickedCardPanel.setValues(SwingUtilities.convertMouseEvent(thisPane, m, clickedCardPanel));
               dragCard = clickedCardPanel.removeCard();
               add(dragCard, JLayeredPane.DRAG_LAYER);
               dragCard.repaint();
               mouseDragged(m);
            }
        }



        //Animation for moving card.
        @Override
        public void mouseDragged(MouseEvent m){
            //Click offset so mouse pointer is in middle of card
            wDiv = dragCard.getWidth() / 2;
            hDiv = dragCard.getHeight() / 2;

            MoveCard(dragCard, m.getPoint().x - wDiv, m.getPoint().y - hDiv);

            //Repaint on drag to avoid card clipping
            repaint();
        }
        @Override
        public void mouseReleased(MouseEvent m){
            CardStack dropLocation;
            //If no valid click, do nothing, return
            if(dragCard == null) {
                return;
            }

            //Remove CardTexture from JPanel layer
            remove(dragCard);


            //If invalid drop location, put card back
            try {
                dropLocation = (CardStack) backPanel.getComponentAt(m.getPoint());
            }
            catch(Exception E){
                putCardBack();
                return;
            }


            int r, c, x;
            r = c = x = -1;

                //Check each card grid location to see if we're putting it
                //in a valid JPanel
                search:for(int row = 0; row < cardGrid.length; row++){
                        for(int col = 0; col < cardGrid[row].length; col++){
                            if(cardGrid[row][col] == dropLocation){
                                r = row;
                                c = col;
                                break search;
                            }
                        }
                    }
                //If invalid card panel, put back in original panel
                if(dropLocation.allowUserDrop() == false)
                    putCardBack();
                //else add to new destination
                else
                    dropLocation.addCard(dragCard);


        repaint();
        dragCard = null;
        }


        //Allows fluid movement of card
        private void MoveCard(CardTexture c, int x, int y){
            c.setLocation(x, y);
            c.repaint();
            dragCard.repaint();
        }


        private void putCardBack(){
            clickedCardPanel.addCard(dragCard);
        }

        /**Figures out what card we're grabbing.
         * Determines if we grab from a regular or special stack
         * @param c Components in JLabel object; must be CardTexture objects
         */
        private void setDragCard(Component[] c, MouseEvent m){
            //if (clickedCardPanel.toString().equals("DiscardStack") || clickedCardPanel.toString().equals("FinishStack")) {
            //    dragCard = (CardTexture)c[c.length-1];
            //    clickedCardPanel.remove(dragCard);
            //}

            //else {

              //  PlayStack p = (PlayStack)clickedCardPanel;
             //   dragCard = p.removeCard();
             //   System.out.println("Card:" + dragCard.toString());
           // }

        dragCard.setVisible(true);
        }
    }

}

