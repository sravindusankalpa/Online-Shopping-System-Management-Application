package ShoppingCentre;

// Class User
public class User {
    // Private fields to store user information
    private String username;
    private String password;

    // Constructor for the User class, allowing initialization of username and password
    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    // Getter and setter for username
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    // Getter and setter for password
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

