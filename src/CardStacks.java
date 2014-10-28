import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created by christopher on 10/26/14.
 */
public class CardStacks extends JPanel{

    private JLabel label;
    private JTextField textField;
    private JPanel panel;
    private JButton exitButton;
    private JButton colorButton;

    public CardStacks()
    {
        super();
        label = new JLabel("hello world label");
        //textField = new JTextField("this is a textbox");
       // exitButton = new JButton("Exit");
        //exitButton.addActionListener(new ExitButtonListener());
       // colorButton = new JButton("Change Color");

        // This is an anonymous class for the listener
        //colorButton.addActionListener(new ActionListener()
       // {   public void actionPerformed(ActionEvent e)
       //     {  colorButtonActionPerformed(e);}});

        // This uses a named listener class
        exitButton.addActionListener(new ExitButtonListener());

        //add(label);
        //add(textField);
        //add(colorButton);
        add(exitButton);
        setBackground(new Color(0, 0, 255));
    }

    private void colorButtonActionPerformed(ActionEvent evt)
    {
        setBackground(new Color(255, 0, 255));
    }

    private void exitButtonActionPerformed(ActionEvent evt)
    {
        System.exit(0);
    }

    public class ExitButtonListener implements ActionListener
    {
        public void actionPerformed(ActionEvent evt)
        {
            exitButtonActionPerformed(evt);
        }
    }

}
