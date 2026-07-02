import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

public class MainForm extends JFrame {

    private final Action actNew;
    private final Action actSave;
    private final Action actExit;

    private final Action actCut;
    private final Action actCopy;
    private final Action actPaste;

    private final Action actCliente;

    //--------
    Cliente clienteDominio;
    {
        clienteDominio = new Cliente("Astrogild", "111.222.333-44");
    }
    public MainForm() {
        super("MainForm");

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

        actCliente = new AbstractAction("Cliente") {
            @Override public void actionPerformed(ActionEvent e) { onCliente(); }
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
    }

    private JMenuBar buildMenuBar() {
        JMenuBar bar = new JMenuBar();

        JMenu file = new JMenu("File");
        file.add(new JMenuItem(actNew));
        file.add(new JMenuItem(actSave));
        file.addSeparator();
        file.add(new JMenuItem(actExit));
        file.add(new JMenuItem(actCliente));

        JMenu edit = new JMenu("Edit");
        edit.add(new JMenuItem(actCut));
        edit.add(new JMenuItem(actCopy));
        edit.add(new JMenuItem(actPaste));
        edit.add(new JMenuItem(actCliente));

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
        tb.add(new JButton(actCliente));
        tb.addSeparator();
        tb.add(new JButton(actExit));

        return tb;
    }

    // ---- Actions (stubs) ----
    private void onNew() { /* TODO */ }
    private void onSave() { /* TODO */ }
    private void onCut()  { /* TODO */ }
    private void onCopy() { /* TODO */ }
    private void onPaste(){ /* TODO */ }
    private void onCliente(){
        ClienteView dialog = new ClienteView(clienteDominio);
        dialog.pack();
        dialog.setVisible(true);
    }

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
