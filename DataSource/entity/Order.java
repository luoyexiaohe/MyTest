package entity;

public class Order {
    private long id = -9223372036854775808L;
    private String type = null;
    private long uid = -9223372036854775808L;
    private double price = 4.9E-324d;
    private String addtime = null;
    private String state = null;
    private String username = null;
    private String tel = null;
    private long appealmark = -9223372036854775808L;
    private String appealtext = null;
    private String dealtext = null;
    public String getType(){
        return this.type;
    }
    public void setType(String type){
        this.type=type;
    }
    public long getUid(){
        return this.uid;
    }
    public void setUid(long uid){
        this.uid=uid;
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
    public String getState(){
        return this.state;
    }
    public void setState(String state){
        this.state=state;
    }
    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username=username;
    }
    public String getTel(){
        return this.tel;
    }
    public void setTel(String tel){
        this.tel=tel;
    }
    public long getAppealmark(){
        return this.appealmark;
    }
    public void setAppealmark(long appealmark){
        this.appealmark=appealmark;
    }
    public String getAppealtext(){
        return this.appealtext;
    }
    public void setAppealtext(String appealtext){
        this.appealtext=appealtext;
    }
    public String getDealtext(){
        return this.dealtext;
    }
    public void setDealtext(String dealtext){
        this.dealtext=dealtext;
    }
}