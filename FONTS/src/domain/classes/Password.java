package domain.classes;

public class Password {

    private String id;
    private String info;

    public Password(String id, String info){
        this.id = id;
        this.info = info;
    }

    public Password(String id) {
        this.id = id;
        this.info = "";
    }

    public String getId() { return this.id; }

    public String getInfo() { return this.info; }

    public void setId(String newId) { this.id = newId; }

    public void setInfo(String newInfo) { this.info = newInfo; }


}
