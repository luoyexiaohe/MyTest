package entity;

public class History {
    private long id = -9223372036854775808L;
    private String date = null;
    private long number = -9223372036854775808L;
    private double price = 4.9E-324d;
    public String getDate(){
        return this.date;
    }
    public void setDate(String date){
        this.date=date;
    }
    public long getNumber(){
        return this.number;
    }
    public void setNumber(long number){
        this.number=number;
    }
    public double getPrice(){
        return this.price;
    }
    public void setPrice(double price){
        this.price=price;
    }
}