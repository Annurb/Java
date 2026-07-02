package Unidade3.ComponentesGUI.GUICalculadora1209;

import javax.swing.*;

public class PanelDemo {
    public static void main(String[] args)
    { CalculatorFrame calculadora = new CalculatorFrame();

        calculadora.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        calculadora.setSize(200, 200);
        calculadora.setVisible(true);
        calculadora.setLocationRelativeTo(null);
    }
}
