"""
package BankingSystem;

public class Login extends Account {

    private String name;
    private int PIN;
    private int accid;

public Login( String name, int PIN, int accid) {
    this.name = name;
    this.PIN = PIN ;
    this.accid = accid;

    }

public String name(){
    return name;
}
public int PIN(){
    return PIN;
}
    public void setPIN(int PIN){
    this.PIN = PIN;
}
public int accid(){
    return accid;
}
    public void setaccid(int accid){
    this.accid = accid;
}
    
}
"""