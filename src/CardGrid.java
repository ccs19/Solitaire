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
    private static final int COLUMNS = 6;
    private static final int SPACE = 5;
    private static final Dimension CARDGRIDSIZE = new Dimension(WIDTH,HEIGHT);
    private static final Dimension LABEL_SIZE = new Dimension(128,100);
    private GridLayout gridLayout = new GridLayout(ROWS, COLUMNS, SPACE, SPACE);
    private JPanel backPanel = new JPanel(gridLayout);
    private JPanel[][] cardGrid = new JPanel[ROWS][COLUMNS];
    private CardTexture c;


    public CardGrid(SolitaireWindow s) {
        backPanel.setSize(CARDGRIDSIZE);
        backPanel.setLocation(2 * SPACE, 2 * SPACE);
        backPanel.setBackground(Color.WHITE);

        for(int r = 0; r < ROWS; r++){
            for(int c = 0; c < COLUMNS; c++){
                cardGrid[r][c] = new JPanel(new GridBagLayout());
                backPanel.add(cardGrid[r][c]);
            }
        }

        c = new CardTexture(1, s);
        cardGrid[1][3].add(c);

        backPanel.setBorder(BorderFactory.createEmptyBorder(SPACE, SPACE, SPACE, SPACE));
        setPreferredSize(CARDGRIDSIZE);
        add(backPanel, JLayeredPane.DEFAULT_LAYER);

        CardDrag mouseAdapter = new CardDrag();
        addMouseListener(mouseAdapter);
        addMouseMotionListener(mouseAdapter);

    }







    //Mouse drag class
    private class CardDrag extends MouseAdapter {

        private JLabel dragCard = null;
        private int wDiv;
        private int hDiv;
        private JPanel clickedCardPanel = null;


        @Override
        public void mousePressed(MouseEvent m) {

            clickedCardPanel = (JPanel)backPanel.getComponentAt(m.getPoint());
            Component[] c = clickedCardPanel.getComponents();
            if(c.length == 0 )
                return;

            if(c[0] instanceof JLabel){
                dragCard = (JLabel)c[0];
                clickedCardPanel.remove(dragCard);
                clickedCardPanel.revalidate();
                clickedCardPanel.repaint();

                wDiv = dragCard.getWidth() / 2;
                hDiv = dragCard.getHeight() / 2;


                int x = m.getPoint().x - wDiv;
                int y = m.getPoint().y - hDiv;
                dragCard.setLocation(x, y);
                add(dragCard, JLayeredPane.DRAG_LAYER);
                repaint();
            }
        }


        @Override
        public void mouseReleased(MouseEvent m){
            if(dragCard == null) {
                return;
            }


            remove(dragCard);

            //Check valid drop location
            JPanel dropLocation = (JPanel) backPanel.getComponentAt(m.getPoint());
            if(dropLocation == null){

                clickedCardPanel.add(dragCard);
                clickedCardPanel.revalidate();
            }
            else{
                int r, c, x;
                r = c = x = -1;


                search:    for(int i = 0; i < cardGrid.length; i++){

                        for(int j = 0; j < cardGrid[i].length; j++){
                            if(cardGrid[i][j] == dropLocation){
                                r = i;
                                c = j;
                                break search;
                            }
                        }

                    }

                if( r == -1 || c == -1){
                    clickedCardPanel.add(dragCard);
                    clickedCardPanel.revalidate();
                }
            }

        repaint();
        dragCard = null;




        }


    }

}

