package GUINomeEDescricao;

import javax.swing.*;
import java.awt.*;

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

    }
}
