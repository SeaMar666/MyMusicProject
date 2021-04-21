package src;

import javax.sound.sampled.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HipHop extends javax.swing.JFrame {

    JPanel HiphopPanel;
    private JButton addButton1;
    private JButton playButton;
    private JButton deleteButton;
    private JComboBox HiphopcomboBox;

    PreparedStatement ps;
    ResultSet rs;
    ArrayList<String> Songs;


    public HipHop() {

        try {
            String query = "select Type,Artist,Song from  project2.mymusic where Type=\"Rap\"";
            ps = DbService.connect().prepareStatement(query);
            rs = ps.executeQuery();
            Songs = new ArrayList<>();

            while (rs.next()) {
                String artist = rs.getString("Artist");
                String song = rs.getString("Song");
                String combine = artist + " - " + song;

                HiphopcomboBox.addItem(combine);
                Songs.add(song);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        }
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int index = HiphopcomboBox.getSelectedIndex();
                String value = Songs.get(index);
                try {
                    ps = DbService.connect().prepareStatement("DELETE FROM project2.mymusic  WHERE  Song= \"" + value + "\"");
                    ps.executeUpdate("DELETE FROM project2.mymusic  WHERE  Song= \"" + value + "\"");
                    HiphopcomboBox.removeItem(value);

                } catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        });
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AudioInputStream audioInput = null;
                int index = HiphopcomboBox.getSelectedIndex();
                String value = Songs.get(index);
                String query = "select Type,Artist,Song,Path from  project2.mymusic where Song= \"" + value + "\"";
                try {
                    ps = DbService.connect().prepareStatement(query);
                    rs = ps.executeQuery();

                    while (rs.next()) {
                        File file = new File(rs.getString("Path"));
                        audioInput = AudioSystem.getAudioInputStream(file);
                    }
                } catch (SQLException ex) {
                    ex.printStackTrace();
                } catch (UnsupportedAudioFileException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                Clip clip = null;
                try {
                    clip = AudioSystem.getClip();
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                }
                try {
                    clip.open(audioInput);
                } catch (LineUnavailableException ex) {
                    ex.printStackTrace();
                } catch (IOException ex) {
                    ex.printStackTrace();
                }
                clip.start();
            }
        });
        addButton1.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                LoginForm l = new LoginForm();
                int index = HiphopcomboBox.getSelectedIndex();
                String value = Songs.get(index);
                UserSongs us = new UserSongs();

                String username = l.getCurrentCustomer();
                Statement st = null;
                try {
                    st = DbService.connect().createStatement();
                    String query = "INSERT INTO users.user_songs(username,Song) VALUES (" + "'" + username + "'" + "," + "'" + value + "'" + ");";
                    st.executeUpdate(query);
                } catch (SQLException ex) {
                    ex.printStackTrace();
                }

                DefaultTableModel model = (DefaultTableModel) us.getMySongsTable().getModel();
                String[] tableColName = {"Song"};
                model.setColumnIdentifiers(tableColName);

                ResultSetMetaData rsmd;
                String musicQuery = "SELECT  Song FROM users.user_songs where username=" + "'" + username + "'" + ";";

                try {
                    ps = DbService.connect().prepareStatement(musicQuery);
                    rs = ps.executeQuery();
                    rsmd = rs.getMetaData();
                    int ColCount = rsmd.getColumnCount();
                    Object[] objects = new Object[ColCount];

                    while (rs.next()) {
                        for (int i = 1; i <= ColCount; i++) {
                            objects[i - 1] = rs.getObject(i);
                        }
                        model.addRow(objects);
                    }

                } catch (SQLException ex) {
                    ex.printStackTrace();
                    Logger.getLogger("Get connection : " + DbService.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }
}


