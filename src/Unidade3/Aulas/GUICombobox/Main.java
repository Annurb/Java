package Unidade3.GUICombobox;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

class FramePrincipal extends JFrame {
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    private JLabel label1 = new JLabel();
    private JLabel label2 = new JLabel();
    private JLabel label3 = new JLabel();
    private JTextField text1 = new JTextField();
    private JTextField text2 = new JTextField();
    private final String[] estados = {"Santa Catatina", "Paraná", "Rio Grande do Sul"};
    JComboBox<String> comboBox = new JComboBox<>(estados);
    private JButton button1 = new JButton();
    private JButton button2 = new JButton();

    //getSelectedItem pra pegar o item selecionado
    FramePrincipal() {
        super();

        comboBox.setMaximumRowCount(3);

        label1.setText("Nome Completo");
        label2.setText("Descrição geral");
        button1.setText("Ok");
        button1.addActionListener((event)->{onOkClick(event);});
        button2.setText("Cancela");
        button2.addActionListener((event)->{onCancelClick(event);});

        panel1.setLayout(new BorderLayout(5, 0)); // o layout BorderLayout permite redimensionar componentes
        panel1.setBorder(BorderFactory.createEtchedBorder()); // criei uma borda visível para vocês enxergaremo JPanel
        panel1.add(label1, BorderLayout.WEST); // o label fica alinhado à esquerna no JPanel
        panel1.add(text1, BorderLayout.CENTER); // o text ocupa a posição "Central" do JPanel, o que o permite ocupar todo o espaço disponível
        panel1.setMaximumSize(new Dimension(Integer.MAX_VALUE, panel1.getPreferredSize().height)); // apenas DEPOIS de termos adicionado (add) todos os componentes, podemos ajustar o tamanho desejado/mximo para o JPanel

        panel2.setLayout(new BorderLayout(5, 0));
        panel2.setBorder(BorderFactory.createEtchedBorder());
        panel2.add(label2, BorderLayout.WEST);
        panel2.add(text2, BorderLayout.CENTER);
        panel2.setMaximumSize(new Dimension(Integer.MAX_VALUE, panel2.getPreferredSize().height));

        panel3.setLayout(new BorderLayout());
        panel3.setBorder(BorderFactory.createEtchedBorder());
        panel3.setPreferredSize(new Dimension(200, 100));
        panel3.setMinimumSize(new Dimension(200, 100));
        panel3.setMaximumSize(new Dimension(Integer.MAX_VALUE, Integer.MAX_VALUE));
        add(comboBox);
        panel4.setLayout(new FlowLayout(FlowLayout.RIGHT));
        panel4.setBorder(BorderFactory.createEtchedBorder());
        panel4.add(button1);
        panel4.add(button2);
        panel4.setMaximumSize(new Dimension(Integer.MAX_VALUE, panel4.getPreferredSize().height));

        getContentPane().setLayout(new BoxLayout(getContentPane(), BoxLayout.Y_AXIS)); // ajuste do layout do Frame. Esse é o layout Vertical (Y_AXIS)
        add(panel1); // acrescentas os JPanel no Frame na ordem em que devem aparecer
        add(panel2);
        add(panel3);
        add(panel4);
    }

    void onOkClick(ActionEvent event) {
        if (text1.getText().equals("") || text2.getText().equals("")) {
            JOptionPane.showMessageDialog(this, "Preencha todos os campos");
        } else {
            dispose();
        }
    }

    void onCancelClick(ActionEvent event) {
        if (!text1.getText().equals("") || !text2.getText().equals("")) {
            int resp = JOptionPane.showConfirmDialog(this,"Os dados foram alterados. Deseja descartar as alteraçẽos?","", JOptionPane.YES_NO_OPTION);
            if (resp == JOptionPane.NO_OPTION) {
                return;
            }
        }
        dispose();
    }
}

class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            FramePrincipal app = new FramePrincipal();
            app.pack();
            app.setSize(500, 300);
            app.setLocationRelativeTo(null);
            app.setVisible(true);
        });
    }
}
