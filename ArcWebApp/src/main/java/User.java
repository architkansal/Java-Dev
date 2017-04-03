package ArcWebApp;
 
public class User  {
 
    private int id;
    private String name;
    private String addr;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setAddr(String addr) {
        this.addr = name;
    }
    public String getAddr() {
        return addr;
    }
    public String toString(){
        return "id: "+id+" Name: "+name + " Addr: "+addr;
    }
 
}