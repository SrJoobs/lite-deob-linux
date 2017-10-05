package gq.netin.frame;

import java.awt.event.WindowEvent;
import java.awt.event.WindowAdapter;
import javax.swing.ImageIcon;
import javax.swing.JProgressBar;
import javax.swing.JPanel;
import javax.swing.JFrame;

public class Loading
{
    private JFrame frame;
    private JPanel panel;
    private JProgressBar loadingBar;
    
    public Loading(final String title, final ImageIcon icon) {
        this.frame = new JFrame(title);
        this.panel = new JPanel();
        this.frame.setIconImage(icon.getImage());
        this.frame.setSize(243, 57);
        this.frame.setResizable(false);
        this.frame.setLocationRelativeTo(null);
        this.frame.setDefaultCloseOperation(3);
        (this.loadingBar = new JProgressBar()).setBounds(50, -50, 840, 260);
        this.loadingBar.setIndeterminate(true);
        this.panel.add(this.loadingBar);
        this.frame.setContentPane(this.panel);
        this.frame.setVisible(true);
        this.frame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(final WindowEvent e) {
                System.exit(0);
            }
        });
    }
    
    public void setTitle(final String string) {
        this.frame.setTitle(string);
    }
    
    public void dispose() {
        this.frame.dispose();
    }
}
