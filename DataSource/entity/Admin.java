package entity;

public class Admin {
    private String username = null;
    private String password = null;
    private long id = -9223372036854775808L;
    public String getPassword(){
        return this.password;
    }
    public void setPassword(String password){
        this.password=password;
    }
    public long getId(){
        return this.id;
    }
    public void setId(long id){
        this.id=id;
    }
}