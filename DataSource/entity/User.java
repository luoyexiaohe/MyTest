package entity;

public class User {
    private long id = -9223372036854775808L;
    private String name = null;
    private String sex = null;
    private String address = null;
    private String tel = null;
    private long number = -9223372036854775808L;
    private double money = 4.9E-324d;
    private String addtime = null;
    private String username = null;
    private String password = null;
    public String getName(){
        return this.name;
    }
    public void setName(String name){
        this.name=name;
    }
    public String getSex(){
        return this.sex;
    }
    public void setSex(String sex){
        this.sex=sex;
    }
    public String getAddress(){
        return this.address;
    }
    public void setAddress(String address){
        this.address=address;
    }
    public String getTel(){
        return this.tel;
    }
    public void setTel(String tel){
        this.tel=tel;
    }
    public long getNumber(){
        return this.number;
    }
    public void setNumber(long number){
        this.number=number;
    }
    public double getMoney(){
        return this.money;
    }
    public void setMoney(double money){
        this.money=money;
    }
    public String getAddtime(){
        return this.addtime;
    }
    public void setAddtime(String addtime){
        this.addtime=addtime;
    }
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