package src;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UserSongs extends javax.swing.JFrame{

     JPanel mySongsPanel;
     JTable mySongsTable;
     JScrollPane mySongsScrollPane;
     private JButton removeSongButton;
     DefaultTableModel model;

    public UserSongs(){

        model = (DefaultTableModel) mySongsTable.getModel();
        String[] tableColName={"Song"};
        model.setColumnIdentifiers(tableColName);

        LoginForm l = new LoginForm();
        String username = l.getCurrentCustomer();
        PreparedStatement ps;
        ResultSet rs;

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


        removeSongButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRowIndex = mySongsTable.getSelectedRow();
                model.removeRow(selectedRowIndex);
            }
        });
        mySongsScrollPane.setViewportView(mySongsTable);
    }

    public JTable getMySongsTable() {
            return mySongsTable;
    }

    public void setMySongsTable(JTable mySongsTable) {

        this.mySongsTable = mySongsTable;
    }


}
