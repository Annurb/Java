package VisualInterface;
import javax.swing.*;

public class main {
    public static void main(String[] args){
        JOptionPane.showMessageDialog(null, "Ola e bem vindo!");
        String nome = JOptionPane.showInputDialog(null, "Qual é o seu nome?");
        JOptionPane.showMessageDialog(null, "Olá " + nome);
    }
}
