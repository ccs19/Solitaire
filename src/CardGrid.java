import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Created by christopher on 10/27/14.
 */
public class CardGrid extends JLayeredPane {


    private static int WIDTH = 1280;
    private static int HEIGHT = 1024;


    private static final int ROWS = 2;
    private static final int COLUMNS = 7;
    private static final int SPACE = 1;
    private static final Dimension CARDGRIDSIZE = new Dimension(WIDTH,HEIGHT);
    private static final Dimension LABEL_SIZE = new Dimension(128,100);
    private GridLayout gridLayout = new GridLayout(ROWS, COLUMNS, SPACE, SPACE);
    private JPanel backPanel = new JPanel(gridLayout);
    private JPanel[][] cardGrid = new JPanel[ROWS][COLUMNS];
    private CardTexture c;
    private GridBagConstraints gridBagConstraints = new GridBagConstraints();


    public CardGrid(SolitaireWindow s) {
        backPanel.setSize(CARDGRIDSIZE);
        backPanel.setLocation(2 * SPACE, 2 * SPACE);
        backPanel.setBackground(Color.WHITE);
        gridBagConstraints.fill = GridBagConstraints.VERTICAL;
        for(int r = 0; r < ROWS; r++){
            for(int c = 0; c < COLUMNS; c++){

                cardGrid[r][c] = new JPanel(new GridBagLayout());
                backPanel.add(cardGrid[r][c]);
            }
        }



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

        while(i < 52)
        {

            if(done == 0)
            for(int j = 0; j < COLUMNS; j++){
                for(int k = 0; k != p; k++) {
                    cardGrid[1][j].add(new CardTexture(i, s));
                    i++;
                }
                p++;
            }
            done = 1;
            cardGrid[0][0].add(new CardTexture(i, s));
            i++;
        }
    }


    //Mouse drag class
    private class CardDrag extends MouseAdapter {

        private CardTexture dragCard = null;
        private int wDiv;
        private int hDiv;
        private JPanel clickedCardPanel = null;



        @Override
        public void mousePressed(MouseEvent m) {

            clickedCardPanel = (JPanel)backPanel.getComponentAt(m.getPoint());
            Component[] c = clickedCardPanel.getComponents();
            if(c.length == 0 )
                return;

            if(c[0] instanceof CardTexture){
                dragCard = (CardTexture)c[0];
                System.out.println("Card:" + dragCard.toString());
                clickedCardPanel.remove(dragCard);
                clickedCardPanel.revalidate();
                clickedCardPanel.repaint();

                add(dragCard, JLayeredPane.DRAG_LAYER);
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

            //If no valid click, do nothing, return
            if(dragCard == null) {
                return;
            }

            //Remove CardTexture from JPanel
            remove(dragCard);

            //Check valid drop location
            JPanel dropLocation = (JPanel) backPanel.getComponentAt(m.getPoint());
            if(dropLocation == null){
                clickedCardPanel.add(dragCard);
                clickedCardPanel.revalidate();

            }

            //Executes if valid drop location
            else{
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
                if(invalidDrop(r, c)){
                    clickedCardPanel.add(dragCard);
                    clickedCardPanel.revalidate();
                }
                //else add to new destination
                else{
                    dropLocation.add(dragCard);
                    dropLocation.revalidate();
                }
            }
        repaint();
        dragCard = null;
        }


        //Allows fluid movement of card
        private void MoveCard(CardTexture c, int x, int y){
            c.setLocation(x, y);
            c.repaint();
        }




        private boolean invalidDrop(int r, int c){
            if(r == -1 || c == -1){
                return true;
            }
            else if(r == 0 && c == 2 || r == 0 && c == 0 || r == 0 && c == 1)
                return true;
            else
                return false;
        }


    }

}

