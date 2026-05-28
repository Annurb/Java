package GUICalculadora1209;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelFrame extends JFrame {
    private final JTextField textField;
    private final JButton plainJButton; //botâo
    private final JPanel painel1;
    public PanelFrame()
    {
        super("Calculator");
        setLayout(new FlowLayout());
        textField = new JTextField(10);
        add(textField, BorderLayout.PAGE_START);
        plainJButton = new JButton("Plain Button");
        painel1 = new JPanel();
        painel1.add(plainJButton);
        add(painel1);

    }
    private class textFieldHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event)
        {
            String string = "";
            if(event.getSource() == textField)
                string = String.format("textField: $s", event.getActionCommand());
            JOptionPane.showMessageDialog(null, string);
        }
    }
}
