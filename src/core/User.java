package core;


public abstract class User implements Blockable, Runnable {
    protected String username;
    protected String password;


    public User() {
    }

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean checkPassword() {
        return false;
    }

    @Override
    public void run(){

    }

}