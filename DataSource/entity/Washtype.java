package entity;

public class Washtype {
    private long id = -9223372036854775808L;
    private String washtype = null;
    private String clothestype = null;
    private double price = 4.9E-324d;
    private String addtime = null;
    public String getWashtype(){
        return this.washtype;
    }
    public void setWashtype(String washtype){
        this.washtype=washtype;
    }
    public String getClothestype(){
        return this.clothestype;
    }
    public void setClothestype(String clothestype){
        this.clothestype=clothestype;
    }
    public double getPrice(){
        return this.price;
    }
    public void setPrice(double price){
        this.price=price;
    }
    public String getAddtime(){
        return this.addtime;
    }
    public void setAddtime(String addtime){
        this.addtime=addtime;
    }
}