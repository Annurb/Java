package Unidade3.GUIProfessor;

import javax.swing.*;
import java.awt.*;

class FramePrincipal extends JFrame {
    private JPanel panel1 = new JPanel();
    private JPanel panel2 = new JPanel();
    private JPanel panel3 = new JPanel();
    private JPanel panel4 = new JPanel();
    private JLabel label1 = new JLabel();
    private JLabel label2 = new JLabel();
    private JTextField text1 = new JTextField();
    private JTextField text2 = new JTextField();
    private JButton button1 = new JButton();
    private JButton button2 = new JButton();

    FramePrincipal() {
        super();

        label1.setText("Nome Completo");
        label2.setText("Descrição geral");
        button1.setText("Ok");
        button2.setText("Cancela");

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
