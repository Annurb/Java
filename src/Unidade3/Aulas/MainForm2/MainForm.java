package Unidade3.MainForm2;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.nio.file.Path;
import java.util.Formatter;

public class MainForm extends JFrame {

    private final Action actNew;
    private final Action actSave;
    private final Action actExit;
    private final Action actOpen;

    private final Action actCut;
    private final Action actCopy;
    private final Action actPaste;
    private Categoria categoriaRaiz;

    public MainForm() {
        super("MainForm");

        actOpen = new AbstractAction("Open") {
            @Override public void actionPerformed(ActionEvent e) { onOpen(); }
        };
        actNew = new AbstractAction("New") {
            @Override public void actionPerformed(ActionEvent e) { onNew(); }
        };

        actSave = new AbstractAction("Save") {
            @Override public void actionPerformed(ActionEvent e) { onSave(); }
        };

        actExit = new AbstractAction("Exit") {
            @Override public void actionPerformed(ActionEvent e) { onExit(); }
        };

        actCut = new AbstractAction("Cut") {
            @Override public void actionPerformed(ActionEvent e) { onCut(); }
        };

        actCopy = new AbstractAction("Copy") {
            @Override public void actionPerformed(ActionEvent e) { onCopy(); }
        };

        actPaste = new AbstractAction("Paste") {
            @Override public void actionPerformed(ActionEvent e) { onPaste(); }
        };

        setJMenuBar(buildMenuBar());
        add(buildToolBar(), BorderLayout.NORTH);
        add(new JPanel(), BorderLayout.CENTER); // placeholder

        setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override public void windowClosing(java.awt.event.WindowEvent e) { onExit(); }
        });

        setSize(900, 600);
        setLocationRelativeTo(null);

        populaCategorias();
    }

    private void populaCategorias() {
        categoriaRaiz = new Categoria("Geral", "geral");
        categoriaRaiz.addProduto(new Produto("PGeral", "pgeral", 200, categoriaRaiz ));
        Categoria vest = new Categoria("Vestidos", "vestidos");
        categoriaRaiz.addSubCategoria(vest);
        vest.addProduto(new Produto("Vestido1", "vest1", 111, vest));
    }

    private JMenuBar buildMenuBar() {
        JMenuBar bar = new JMenuBar();

        JMenu file = new JMenu("File");
        file.add(new JMenuItem(actNew));
        file.add(new JMenuItem(actOpen));
        file.add(new JMenuItem(actSave));
        file.addSeparator();
        file.add(new JMenuItem(actExit));

        JMenu edit = new JMenu("Edit");
        edit.add(new JMenuItem(actCut));
        edit.add(new JMenuItem(actCopy));
        edit.add(new JMenuItem(actPaste));

        bar.add(file);
        bar.add(edit);
        return bar;
    }

    private JToolBar buildToolBar() {
        JToolBar tb = new JToolBar();
        tb.setFloatable(false);

        tb.add(new JButton(actNew));
        tb.add(new JButton(actSave));
        tb.addSeparator();
        tb.add(new JButton(actCut));
        tb.add(new JButton(actCopy));
        tb.add(new JButton(actPaste));
        tb.addSeparator();
        tb.add(new JButton(actExit));

        return tb;
    }

    // ---- Actions (stubs) ----
    private void onOpen(){
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        int result = fileChooser.showOpenDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            categoriaRaiz =null;
            Path arquivo = fileChooser.getSelectedFile().toPath();
            Formatter output = new Formatter();

            /*to do LER OU SALVAR AERQUIVO*/
            // Carrega tudo em categoriaRaiz
            // temp.equals(categoriaRaiz)
        }
    }
    private void onNew() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setFileSelectionMode(JFileChooser.FILES_AND_DIRECTORIES);
        //fileChooser.set
        int result = fileChooser.showSaveDialog(this);
        if(result == JFileChooser.APPROVE_OPTION){
            Path arquivo = fileChooser.getSelectedFile().toPath();
            Formatter output = new Formatter();
            /*TODO LER OU SALVAR AERQUIVO*/
        }
    }
    private void onSave() { /* TODO */ }
    private void onCut()  { /* TODO */ }
    private void onCopy() { /* TODO */ }
    private void onPaste(){ /* TODO */ }

    private void onExit() {
        dispose();
        System.exit(0);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try { UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName()); }
            catch (Exception ignored) {}
            new MainForm().setVisible(true);
        });
    }
}
