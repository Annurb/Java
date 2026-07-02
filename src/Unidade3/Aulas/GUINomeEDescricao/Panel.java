package GUINomeEDescricao;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Panel extends JFrame {
    private final JLabel label1;
    private final JTextField textField;
    private final JLabel label2;
    private final JTextField textField2;

    private final JButton ok;
    private final JButton cancel;

    private final JPanel painel1;
    private final JPanel painel2;
    private final JPanel painel3;
    public Panel(){
        super("Nome completo e descrição");

        painel1 = new JPanel();
        painel2 = new JPanel();
        painel3 = new JPanel();

        textField = new JTextField(10);

        label1 = new JLabel("Nome completo: ");
        label2 = new JLabel("Descrição geral:");
        textField2 = new JTextField(10);

        ok = new JButton("Ok");
        cancel = new JButton("Cancel");

        painel1.add(label1);
        painel1.add(textField);
        add(painel1, BorderLayout.PAGE_START);

        painel2.add(label2);
        painel2.add(textField2);
        add(painel2, BorderLayout.CENTER);

        painel3.add(ok);
        painel3.add(cancel);
        add(painel3, BorderLayout.PAGE_END);

        ButtonHandler handler = new ButtonHandler();
        ButtonHandler1 handler1 = new ButtonHandler1();
        ok.addActionListener(handler);

        cancel.addActionListener(handler1);


    }
    private class ButtonHandler implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event){
            if(textField.getText().trim().isEmpty() || textField2.getText().trim().isEmpty() ) {
                JOptionPane.showMessageDialog(Panel.this, String.format("Você se esqueceu de preencher"));
            }
        }
    }
    private class ButtonHandler1 implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent event) {
            if (!textField.getText().trim().isEmpty()|| !textField2.getText().trim().isEmpty()) {
                JOptionPane.showMessageDialog(Panel.this, "Você preencheu um dos campos, tem certeza que deseja cancelar?");
            }
        }}
}
