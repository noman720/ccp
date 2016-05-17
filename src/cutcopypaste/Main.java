/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package cutcopypaste;

import com.csbl.ui.MainForm;
import com.csbl.util.MyEventQueue;
import java.awt.Component;
import java.awt.Container;
import java.awt.KeyboardFocusManager;
import java.awt.TextField;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.text.DefaultEditorKit;
import javax.swing.text.JTextComponent;

/**
 *
 * @author NOMAN
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here

//        KeyboardFocusManager.getCurrentKeyboardFocusManager()
//                .addPropertyChangeListener("permanentFocusOwner", new PropertyChangeListener() {
//            @Override
//            public void propertyChange(final PropertyChangeEvent e) {
//                if (e.getNewValue() instanceof JTextField) {
//                    //  invokeLater needed for JFormattedTextField
//                    SwingUtilities.invokeLater(new Runnable() {
//                        @Override
//                        public void run() {
//                            JTextField textField = (JTextField) e.getNewValue();
//                            textField.selectAll();
//                            System.out.println("textField Found");
//                            
//                            final Container container = textField.getParent();
//                            
//                            textField.addActionListener(new ActionListener() {
//                                @Override
//                                public void actionPerformed(ActionEvent e) {
//                                    installContextMenu(container);
//                                }
//                            });
//                        }
//                    });
//                }
//            }
//        });
        
        Toolkit.getDefaultToolkit().getSystemEventQueue().push(new MyEventQueue());
        new MainForm(null, true).setVisible(true);
    }
    
    
    
    private static void installContextMenu(Container comp) {
    for (Component c : comp.getComponents()) {
        if (c instanceof JTextComponent) {
            c.addMouseListener(new MouseAdapter() {
                public void mouseReleased(final MouseEvent e) {
                    if (e.isPopupTrigger()) {
                        final JTextComponent component = (JTextComponent)e.getComponent();
                        final JPopupMenu menu = new JPopupMenu();
                        JMenuItem item;
                        item = new JMenuItem(new DefaultEditorKit.CopyAction());
                        item.setText("Copy");
                        item.setEnabled(component.getSelectionStart() != component.getSelectionEnd());
                        menu.add(item);
                        item = new JMenuItem(new DefaultEditorKit.CutAction());
                        item.setText("Cut");
                        item.setEnabled(component.isEditable() && component.getSelectionStart() != component.getSelectionEnd());
                        menu.add(item);
                        item = new JMenuItem(new DefaultEditorKit.PasteAction());
                        item.setText("Paste");
                        item.setEnabled(component.isEditable());
                        menu.add(item);
                        menu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            });
        } else if (c instanceof Container)
            installContextMenu((Container) c);
    }
}
}
