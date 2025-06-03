import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SafeBytes {
    public static void main(String[] args) {
        JFrame f = new JFrame();
        ImageIcon logo = new ImageIcon("src/res/icons.png");

        // Create components
        JLabel nameOfSite = new JLabel("Site Name:");
        nameOfSite.setFont(new Font("Copperplate Gothic Bold", Font.PLAIN, 15));
        JTextField name = new JTextField();
        name.setFont(new Font("Copperplate Gothic Bold",Font.PLAIN,15));

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

                try{
                    new JdbcConnector();
                } catch (Exception ex) {
                    throw new RuntimeException(ex);
                }
            }
        });
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
    }
}
