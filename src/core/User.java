package core;

/**
 * This class contains details of the user on the system
 */
public abstract class User implements Blockable, Runnable {
    /**
     * Protected Fields
     */
    private String username;
    private String password;

    /**
     * An empty constructor
     */
    User() {
    }

    /**
     * This constructor will set the username and password of the user
     *
     * @param username the username for the user
     * @param password the password for the user
     */
    User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * This method will return the username of the user
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * This method will set the username of the user
     *
     * @param username the username to be set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * This method will will return the password for the user
     *
     * @return the password as a string
     */
    private String getPassword() {
        return password;
    }

    /**
     * This method will set the password for the user
     *
     * @param password the password for the user as a string
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This method will check that the password entered is valid
     *
     * @return true if the password matches
     */
    public boolean checkPassword(String password) {

        return password.equals(getPassword());
    }

    @Override
    public void run() {

    }

}