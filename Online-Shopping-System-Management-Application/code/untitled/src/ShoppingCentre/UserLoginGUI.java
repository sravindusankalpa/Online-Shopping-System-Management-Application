package ShoppingCentre;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.Scanner;

public class UserLoginGUI extends JFrame {

    private JPanel signInPanel;
    private JPanel signUpPanel;
    private JTextField signInUsernameField;
    private JPasswordField signInPasswordField;
    private JTextField signUpUsernameField;
    private JPasswordField signUpPasswordField;

    private ShoppingCart shoppingCart;

    public UserLoginGUI() {
        // Initialize frame
        setTitle("Westminster Shopping Centre");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the frame on the screen

        // Create sign-in panel
        signInPanel = new JPanel();
        signInPanel.setLayout(new BoxLayout(signInPanel, BoxLayout.Y_AXIS));
        signInPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));

        // Create components for sign-in panel
        JLabel signInLabel = new JLabel("Sign In");
        signInLabel.setFont(new Font("Arial", Font.BOLD, 18));


        signInUsernameField = new JTextField(20);
        signInPasswordField = new JPasswordField(20);

        JButton signInButton = new JButton("Sign In");
        JButton goToSignUpButton = new JButton("Don't have an account? Sign Up");

        // Add components to the sign-in panel
        signInPanel.add(signInLabel);
        signInPanel.add(new JLabel("Username:"));
        signInPanel.add(signInUsernameField);
        signInPanel.add(new JLabel("Password:"));
        signInPanel.add(signInPasswordField);

        JPanel signInButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        signInButtonPanel.add(signInButton);
        signInPanel.add(signInButtonPanel);

        JPanel goToSignUpButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        goToSignUpButtonPanel.add(goToSignUpButton);
        signInPanel.add(goToSignUpButtonPanel);

        // Create sign-up panel
        signUpPanel = new JPanel();
        signUpPanel.setLayout(new BoxLayout(signUpPanel, BoxLayout.Y_AXIS));
        signUpPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        signUpPanel.setVisible(false);

        // Create components for sign-up panel
        JLabel signUpLabel = new JLabel("Sign Up");
        signUpLabel.setFont(new Font("Arial", Font.BOLD, 18));

        signUpUsernameField = new JTextField(20);
        signUpPasswordField = new JPasswordField(20);

        JButton signUpButton = new JButton("Sign Up");
        JButton goToSignInButton = new JButton("Already have an account? Sign In");

        // Add components to the sign-up panel
        signUpPanel.add(signUpLabel);
        signUpPanel.add(new JLabel("Username:"));
        signUpPanel.add(signUpUsernameField);
        signUpPanel.add(new JLabel("Password:"));
        signUpPanel.add(signUpPasswordField);

        JPanel signUpButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        signUpButtonPanel.add(signUpButton);
        signUpPanel.add(signUpButtonPanel);

        JPanel goToSignInButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        goToSignInButtonPanel.add(goToSignInButton);
        signUpPanel.add(goToSignInButtonPanel);

        // Set layout for the main frame
        setLayout(new CardLayout());

        // Add panels to the frame
        add(signInPanel, "SignInPanel");
        add(signUpPanel, "SignUpPanel");

        // Add action listeners
        signInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signIn();
            }
        });

        signUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                signUp();
            }
        });

        goToSignUpButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
                cardLayout.show(getContentPane(), "SignUpPanel");
            }
        });

        goToSignInButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cardLayout = (CardLayout) getContentPane().getLayout();
                cardLayout.show(getContentPane(), "SignInPanel");
            }
        });
    }

    private void signIn() {
        String username = signInUsernameField.getText();
        String password = new String(signInPasswordField.getPassword());

        // Read user information from the text file
        if (checkCredentials(username, password)) {
            JOptionPane.showMessageDialog(this, "Sign In Successful!");
            openShoppingGUI();
        } else {
            JOptionPane.showMessageDialog(this, "Invalid username or password", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private boolean checkCredentials(String username, String password) {
        // Check if the given credentials match any user in the text file
        try (Scanner scanner = new Scanner(new File("userDatabase.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(username) && parts[1].equals(password)) {
                    return true; // Match found
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false; // No match found
    }

    private void signUp() {
        String username = signUpUsernameField.getText();
        String password = new String(signUpPasswordField.getPassword());

        // Check if the username or password is blank
        if (username.trim().isEmpty() || password.trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and password cannot be blank.",
                    "Error", JOptionPane.ERROR_MESSAGE);
            return; // Exit the method if input is blank
        }

        // Check if the username already exists
        if (userExists(username)) {
            JOptionPane.showMessageDialog(this, "Username already exists. Please choose a different username.",
                    "Error", JOptionPane.ERROR_MESSAGE);
        } else {
            // Set the sign-up status to true
            boolean isUserSignedUp = true;
            // Create a new User object
            User newUser = new User(username, password);

            // Save user information to a text file
            saveUserToFile(newUser);

            // Display success message or perform other actions
            JOptionPane.showMessageDialog(this, "User signed up successfully!");

            // Create a new ShoppingCart with the signup status
            shoppingCart = new ShoppingCart(true);

            openShoppingGUI();
        }
    }

    private boolean userExists(String username) {
        // Check if the given username already exists in the text file
        try (Scanner scanner = new Scanner(new File("userDatabase.txt"))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(",");
                if (parts.length == 2 && parts[0].equals(username)) {
                    return true; // Username already exists
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        return false; // Username not found
    }

    private void saveUserToFile(User user) {
        // Save user information to a text file
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("userDatabase.txt", true))) {
            writer.write(user.getUsername() + "," + user.getPassword());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void openShoppingGUI() {
        // Create an instance of the Shopping GUI
        ShoppingGUI shoppingGUI = new ShoppingGUI();


        // Hide the login GUI
        setVisible(false);

        // Make the Shopping GUI visible
        shoppingGUI.setVisible(true);
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new UserLoginGUI().setVisible(true);
            }
        });
    }
}

