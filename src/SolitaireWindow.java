import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.*;

/**
 * Created by christopher on 10/26/14.
 */
public class SolitaireWindow {


    private static JFrame gameWindow;
    private static JMenuBar windowMenu;
    private static JMenu mainMenu, save;
    private static JMenuItem menuItem;
    private static JRadioButtonMenuItem buttonMenuItem;
    private static JCheckBoxMenuItem checkBoxMenuItem;
    private JMenuItem b;
    private static int[] monitorResolution = new int[2];

    private final GraphicsDevice g = GraphicsEnvironment.getLocalGraphicsEnvironment().getDefaultScreenDevice();


    private int numCardContainers = 11;


    SolitaireWindow(){
        gameWindow = new JFrame("Ultimate Solitaire for Winners");
        //Init window

        gameWindow.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        createMenu();

        //Create menu buttons
        mainMenu.add(new JMenuItem("New"));
        mainMenu.add(new JMenuItem("Save"));
        mainMenu.add(new JMenuItem("Load"));
        initExitButton();

        //Calculate window size
        findMonitorResolution();
        gameWindow.setSize((int) ((double) monitorResolution[0] * (0.9)), (int) ((double) monitorResolution[1] * (0.9)));


        //Add card containers
        initCardContainers();


        CardGrid c = new CardGrid(this);
        gameWindow.getContentPane().add(c);

        gameWindow.setBackground(Color.GREEN);

        gameWindow.setVisible(true);

    }

    //Create menubar
    private void createMenu(){
        //Menubar on top of screen
        windowMenu = new JMenuBar();
        //Game JMenu
        mainMenu = new JMenu("Game");
        mainMenu.setMnemonic(KeyEvent.VK_A);
        mainMenu.getAccessibleContext().setAccessibleDescription("ItemsAdded");
        //Add items to menu
        windowMenu.add(mainMenu);
        //Add menu to JFrame
        gameWindow.setJMenuBar(windowMenu);
    }




    //Create exit button in menu and add listener
    private void initExitButton(){
        JMenuItem exitButton = new JMenuItem("Exit");
        exitButton.addActionListener(new ActionListener(){
            public void actionPerformed(ActionEvent e){
                System.exit(0);
            }
        });
        mainMenu.add(exitButton);
    }





    private void initCardContainers(){

    }




    private void findMonitorResolution(){
        monitorResolution[0] = g.getDisplayMode().getWidth();
        monitorResolution[1] = g.getDisplayMode().getHeight();
        gameWindow.addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent componentEvent) {
                monitorResolution[0] = gameWindow.getWidth();
                monitorResolution[1] = gameWindow.getHeight();
                System.out.println("X: " + monitorResolution[0] + "  Y: " + monitorResolution[1]);
            }
        });
    }



    public int getXSize(){
        return monitorResolution[0];
    }

    public int getYSize(){
        return monitorResolution[1];
    }





    private void initNewGameButton(){}
    private void initSaveGameButton(){}
    private void initLoadGameButton(){}

}
