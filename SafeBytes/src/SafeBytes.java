import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.UUID;

public class SafeBytes {
    static final String URL = "jdbc:mysql://localhost:3306/safebytes";
    static final String USER = "root";
    static final String PASSWORD = "Umang@8933";
    public static void main(String[] args) {
        UUID uniqueID = UUID.randomUUID();//for unique id's
        //Making connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection co = DriverManager.getConnection(URL,USER,PASSWORD);
            //Making Jfram
            JFrame f = new JFrame();
            ImageIcon logo = new ImageIcon("src/res/icons.png");
            // Create components
            //   name
            JLabel nameOfSite = new JLabel("Site Name:");
            nameOfSite.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 15));
            JTextField name = new JTextField();
            name.setFont(new Font("Copperplate Gothic Bold",Font.PLAIN,15));

            // password
            JLabel passwordOSite = new JLabel("Password:");
            passwordOSite.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 15));
            JTextField password = new JTextField();
            password.setFont(new Font("Copperplate Gothic Bold",Font.PLAIN,15));

            JButton submit = new JButton("Submit");
            submit.setFont(new Font("Algerian",Font.PLAIN,12));
            JButton seeAll = new JButton("Saved Passwords");
            seeAll.setFont(new Font("Algerian",Font.PLAIN,12));

            // Set bounds for alignment
            nameOfSite.setBounds(20, 20, 100, 30);
            name.setBounds(130, 20, 180, 30);
            passwordOSite.setBounds(20, 70, 100, 30);
            password.setBounds(130, 70, 180, 30);
            submit.setBounds(20, 120, 140, 30);
            seeAll.setBounds(170, 120, 140, 30);

            // Add components to the frame
            f.add(nameOfSite);
            f.add(name);
            f.add(passwordOSite);
            f.add(password);
            f.add(submit);
            f.add(seeAll);

            // Adding
            submit.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    String insertQuery = "INSERT INTO SafeBytes(id, name, password) VALUES (?, ?, ?)";
                        String idString = uniqueID.toString();
                    try{
                        PreparedStatement pstm = co.prepareStatement(insertQuery);
                        pstm.setString(1,uniqueID.toString());
                        pstm.setString(2, name.getText());
                        pstm.setString(3, password.getText());
                        pstm.executeUpdate();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            //Seeing all passwords
            seeAll.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {

                }
            });

            // Frame properties
            f.setLocationRelativeTo(null);
            f.setLayout(null);
            f.setIconImage(logo.getImage());
            f.setTitle("SafeBytes");
            f.setSize(350, 200);
            f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            f.setVisible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
