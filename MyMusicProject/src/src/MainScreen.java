package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainScreen extends javax.swing.JFrame{

    public JPanel MainScreenPanel;
    private JButton addANewSongButton;
    private JButton mySongsButton;
    private JButton displayAllSongsButton;
    private JButton selectMusicTypeButton;

    public MainScreen() {
        selectMusicTypeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                MusicType mt = new MusicType();
                mt.setContentPane(new MusicType().MusicTypePanel);
                mt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                mt.pack();
                mt.setVisible(true);

            }
        });
        displayAllSongsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                DisplaySongs ds = new DisplaySongs();
                ds.setContentPane(new DisplaySongs().panel1);
                ds.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ds.pack();
                ds.setVisible(true);
            }
        });
        addANewSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddSong addSong = new AddSong();
                addSong.setContentPane(new AddSong().addsongPanel);
                addSong.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                addSong.pack();
                addSong.setVisible(true);

            }
        });
        mySongsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                UserSongs ms = new UserSongs();
                ms.setContentPane(new UserSongs().mySongsPanel);
                ms.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                ms.pack();
                ms.setVisible(true);
            }
        });
    }
}
