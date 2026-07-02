package Unidade3.ComponentesGUI.GUICalculadora1209;

import java.awt.GridLayout;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class CalculatorFrame extends JFrame implements ActionListener{
    private final JTextField textField;
    private final JButton[] buttons; //botoes
    private static final String[] names = {"7","8","9","/","4","5","6","*","1","2","3","-","0",".","=","+"};
    private final JPanel painelbotoes = new JPanel();
    public CalculatorFrame()
    {
        super("Calculator");

        // Cria-se o layout usando borderlayout
        setLayout(new BorderLayout(5,5));

        // Nova caixa de texto adicionada ao jframe
        textField = new JTextField();
        add(textField,BorderLayout.NORTH);

        // um panel com o layout de gridlayout
        painelbotoes.setLayout(new GridLayout(4,4, 5, 5));

        buttons = new JButton[names.length];

        //botoes adicionados ao panel
        for(int cont = 0; cont< names.length;cont ++){
            buttons[cont] = new JButton(names[cont]);
            buttons[cont].addActionListener(this);
            painelbotoes.add(buttons[cont]);
        }
        // adicionar o painel
        add(painelbotoes, BorderLayout.CENTER);

    }
        @Override
        public void actionPerformed(ActionEvent event)
        {
            JOptionPane.showMessageDialog(this, "Voce clicou");
        }

}
