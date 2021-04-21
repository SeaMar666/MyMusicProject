package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MusicType extends javax.swing.JFrame{
    private JButton JAZZButton;
    private JButton POPButton;
    private JButton ROCKButton;
    private JButton HIPHOPButton;
    public JPanel MusicTypePanel;

    public MusicType() {
        HIPHOPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                HipHop hp = new HipHop();
                hp.setContentPane(new HipHop().HiphopPanel);
                hp.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                hp.pack();
                hp.setVisible(true);
            }
        });
        ROCKButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Rock rock = new Rock();
                rock.setContentPane(new Rock().rockPanel);
                rock.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                rock.pack();
                rock.setVisible(true);
            }
        });
        POPButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Pop pop = new Pop();
                pop.setContentPane(new Pop().popPanel);
                pop.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                pop.pack();
                pop.setVisible(true);
            }
        });
        JAZZButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                Jazz jazz = new Jazz();
                jazz.setContentPane(new Jazz().jazzPanel);
                jazz.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                jazz.pack();
                jazz.setVisible(true);
            }
        });
    }
    public static void main(String[] args) {
        JFrame frame=new JFrame();
        frame.setContentPane(new MusicType().MusicTypePanel);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }
}
