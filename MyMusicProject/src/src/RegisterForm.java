package src;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RegisterForm extends javax.swing.JFrame{
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField usernameField;
    private JButton cancelButton;
    private JButton createButton;
    public JPanel RegisterPanel;
    private JPasswordField passwordField1;
    private JPasswordField passwordField2;

    public RegisterForm() {

        createButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                String firstName = firstNameField.getText();
                String lastName = lastNameField.getText();
                String userName = usernameField.getText();
                String pass1 = String.valueOf(passwordField1.getPassword());
                String pass2 = String.valueOf(passwordField2.getPassword());

                if(verifyFields())
                {
                    if(!checkUsername(userName))
                    {
                        PreparedStatement ps;
                        ResultSet rs;
                        String registerUserQuery = "INSERT INTO users.new_table(first_name,last_name,username,password,confirm_password) VALUES (?,?,?,?,?)";

                        try {
                            ps = DbService.connect().prepareStatement(registerUserQuery);

                            ps.setString(1,firstName);
                            ps.setString(2,lastName);
                            ps.setString(3,userName);
                            ps.setString(4,pass1);
                            ps.setString(5,pass2);

                            if(ps.executeUpdate() != 0){
                                JOptionPane.showMessageDialog(null, "Your Account Has Been Created");

                            }else{
                                JOptionPane.showMessageDialog(null,"Error:Check Your Information");
                            }
                        } catch (SQLException ex) {
                            ex.printStackTrace();
                        }
                    }
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        this.dispose();
    }

    public boolean verifyFields() {

        String fName = firstNameField.getText();
        String lName = lastNameField.getText();
        String uName = usernameField.getText();
        String password = String.valueOf(passwordField1.getPassword());
        String confirmPass = String.valueOf(passwordField2.getPassword());

        if (fName.trim().equals("") || lName.trim().equals("") || uName.trim().equals("") || password.trim().equals("") || confirmPass.trim().equals(""))
        {
            JOptionPane.showMessageDialog(null, " One Or More Fields Are Empty", "Empty Fields ", 2);
            return false;
        } else if (passwordField1.equals(passwordField2)) {
            JOptionPane.showMessageDialog(null, "Password Doesn't Match ", "Please Confirm Password ", 2);
            return false;
        } else {
            return true;
        }
    }

    public boolean checkUsername(String username)
    {
        PreparedStatement st;
        ResultSet rs;
        boolean username_exist = false;

        String query = "SELECT * FROM users.new_table WHERE username = ?";
        try {
            st = DbService.connect().prepareStatement(query);
            st.setString(1,username);
            rs=st.executeQuery();

            if(rs.next())
            {
                username_exist = true;
                JOptionPane.showMessageDialog(null,"This Username Already Taken Choose Another One "
                ,"Username Failed",2);
            }

        } catch (SQLException e) {
            e.printStackTrace();
            Logger.getLogger("Get connection : " + DbService.class.getName()).log(Level.SEVERE,null,e);
        }

        return username_exist;

    }

}
