package org.example.demo;

public class Users {
    private static Users instance;
    private int id;
    private String username;
    private String email;
    private String password;
    private String priv;



    public static Users getInstance() {
        if (instance == null) {
            instance = new Users();
        }
        return instance;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPriv()
    {
        return priv;
    }

    public void setPriv(String privilege){this.priv = privilege;}

    public int getId(){return id;}

    public void setId(int id){this.id = id;}
}