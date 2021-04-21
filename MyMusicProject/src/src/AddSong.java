package src;

import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AddSong extends javax.swing.JFrame{
    private JTextField typeField;
    private JTextField artistField;
    private JTextField songField;
    private JButton ADDButton;
    JPanel addsongPanel;
    private JButton SELECTSONGButton;

    String songPath = null;

    public AddSong() {
        ADDButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String musictype = typeField.getText();
                String artist = artistField.getText();
                String song = songField.getText();

                PreparedStatement ps;
                ResultSet rs;
                String addSongQuery = "INSERT INTO project2.mymusic(Type,Artist,Song,MusicFile,Path) VALUES (?,?,?,?,?)";

                try {
                    ps = DbService.connect().prepareStatement(addSongQuery);

                    ps.setString(1,musictype);
                    ps.setString(2,artist);
                    ps.setString(3,song);

                    try {
                        InputStream path = new FileInputStream(new File(songPath));
                        ps.setBlob(4,path);
                        ps.setString(5,songPath);

                    } catch (FileNotFoundException ex) {
                        ex.printStackTrace();
                    }

                    if(ps.executeUpdate() != 0){
                        JOptionPane.showMessageDialog(null, "~~Song is added successfully~~");

                    }else{
                        JOptionPane.showMessageDialog(null,"Error:Please choose valid file.");
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
                System.out.println(musictype+artist+song+songPath);
            }
        });
        SELECTSONGButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String path = null;
                JFileChooser chooser = new JFileChooser();
                chooser.setCurrentDirectory(new File(System.getProperty("user.home")));

                FileNameExtensionFilter extensionFilter = new FileNameExtensionFilter("C:", ".mp3",".wav");
                chooser.addChoosableFileFilter(extensionFilter);

                int filestate= chooser.showSaveDialog(null);

                if(filestate == JFileChooser.APPROVE_OPTION){
                    File selectedPath = chooser.getSelectedFile();
                    path = selectedPath.getAbsolutePath();

                    songPath = path;
                }
            }
        });
    }
}
