import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.UUID;

public class SafeBytes {
    static final String URL = "jdbc:mysql://localhost:3306/safebytes";
    static final String USER = "root";
    static final String PASSWORD = "Umang@8933";
    public static void main(String[] args) {
        //Making connection
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection co = DriverManager.getConnection(URL,USER,PASSWORD);
            //Making Jfram
            JFrame mainFrame = new JFrame();
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

            //button
            JButton submit = new JButton("Submit");
            submit.setFont(new Font("Algerian",Font.PLAIN,12));

            //seeAll
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
            mainFrame.add(nameOfSite);
            mainFrame.add(name);
            mainFrame.add(passwordOSite);
            mainFrame.add(password);
            mainFrame.add(submit);
            mainFrame.add(seeAll);

            // Adding
            submit.addActionListener(new ActionListener() {
            @Override
                public void actionPerformed(ActionEvent e) {
                    String insertQuery = "INSERT INTO save_password(id, name, password) VALUES (?, ?, ?)";
                    try{
                        PreparedStatement pstm = co.prepareStatement(insertQuery);
                        pstm.setString(1, UUID.randomUUID().toString());
                        pstm.setString(2, name.getText());
                        pstm.setString(3, password.getText());
                        pstm.executeUpdate();
                    } catch (Exception ex) {
                        throw new RuntimeException(ex);
                    }
                }
            });
            //Seeing all passwords
            seeAll.addActionListener(new ActionListener()  {
                @Override
                public void actionPerformed(ActionEvent e) {
                    try {
                        JFrame seeAllFrame = new JFrame();
                        // Create table model
                        DefaultTableModel model = new DefaultTableModel();
                        model.addColumn("ID");
                        model.addColumn("Site Name");
                        model.addColumn("Password");
                        JTable table = new JTable(model);
                        // Fetch data from database 
                        String seeAllQuery = "SELECT * FROM save_password";
                        Statement st = co.createStatement();
                        ResultSet rs = st.executeQuery(seeAllQuery);
                        while (rs.next()) {
                            int id = rs.getInt(1);
                            String siteName = rs.getString(2);
                            String pass = rs.getString(3);
                            model.addRow(new Object[]{id, siteName, pass});
                        }
                        seeAllFrame.add(new JScrollPane(table));
                        seeAllFrame.setSize(650, 550);
                        seeAllFrame.setLocationRelativeTo(null);
                        seeAllFrame.setVisible(true);
                    }catch (Exception e1){
                        System.out.println(e1);
                    }
                }
            });
            // Frame properties
            mainFrame.setLocationRelativeTo(null);
            mainFrame.setLayout(null);
            mainFrame.setIconImage(logo.getImage());
            mainFrame.setTitle("SafeBytes");
            mainFrame.setSize(350, 200);
            mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            mainFrame.setVisible(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
