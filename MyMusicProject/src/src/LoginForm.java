package src;

import javax.swing.*;
import java.awt.event.*;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class LoginForm extends javax.swing.JFrame {

    private JTextField usernameField;
    private JPanel panel;
    private JButton loginButton;
    private JButton cancelButton;
    private JButton register_Button;
    private JPasswordField passwordField;
    static String CurrentCustomer="";

    public LoginForm() {

        register_Button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                  RegisterForm rgf = new RegisterForm();
                  rgf.setContentPane(new RegisterForm().RegisterPanel);
                  rgf.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                  rgf.pack();
                  rgf.setVisible(true);
            }
        });

        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                PreparedStatement st;
                ResultSet rs;
                String username = usernameField.getText();
                String password = String.valueOf(passwordField.getPassword());
                String query = "SELECT * FROM users.new_table WHERE username = ? AND password = ?" ;
                setCurrentCustomer(username);

                if(username.trim().equals("username"))
                {
                    JOptionPane.showMessageDialog(null,"Enter Your Username ", "Empty Username" ,2);
                }
                else if(password.equals("password"))
                {
                    JOptionPane.showMessageDialog(null,"Enter Your Password ", "Empty Password" ,2);
                }
                else
                {
                    try{
                        st = DbService.connect().prepareStatement(query);

                        st.setString(1,username);
                        st.setString(2,password);
                        rs = st.executeQuery();

                        if(rs.next()){
                            MainScreen mt = new MainScreen();
                            mt.setContentPane(new MainScreen().MainScreenPanel);
                            mt.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            mt.pack();
                            mt.setVisible(true);

                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Invalid Username / Password ", "Login Error",2);

                        }
                    }catch (SQLException ex) {
                        ex.printStackTrace();
                        Logger.getLogger("Get connection : " + DbService.class.getName()).log(Level.SEVERE,null,ex);
                    }
                }
            }
        });
    }
    public JTextField getUsernameField() {
        return usernameField;
    }
    public void setCurrentCustomer(String currentCustomer) {
        CurrentCustomer = currentCustomer;
    }
    public String getCurrentCustomer() {
        return CurrentCustomer;
    }
    public static void main(String[] args) {
        JFrame frame = new JFrame("ARE YOU READY TO EXPLORE A NEW WORLD ? ");
        frame.setContentPane(new LoginForm().panel);
        //frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

}
