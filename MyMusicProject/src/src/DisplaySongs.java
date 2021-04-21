package src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DisplaySongs extends javax.swing.JFrame{

    JTable musicTable;
    JPanel panel1;
    JScrollPane ScrollPane;
    private JButton showSongsButton;

    public DisplaySongs()
    {
        DefaultTableModel model = (DefaultTableModel) musicTable.getModel();
        String[] tableColName={"Type","Artist","Song"};
        model.setColumnIdentifiers(tableColName);

        showSongsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                ResultSet rs;
                PreparedStatement ps;
                ResultSetMetaData rsmd;
                String musicQuery = "SELECT * FROM project2.mymusic";

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
                    Logger.getLogger("Get connection : " + DbService.class.getName()).log(Level.SEVERE,null,ex);
                }
            }
        });
        ScrollPane.setViewportView(musicTable);
    }
}
