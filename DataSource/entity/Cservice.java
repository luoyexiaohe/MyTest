package entity;

public class Cservice {
    private long id = -9223372036854775808L;
    private String username = null;
    private String password = null;
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password=password;
    }
}