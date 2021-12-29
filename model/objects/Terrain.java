package model.objects;

public class Terrain extends Content{
    String type;
    public Terrain(int id, String type) {
        super(id);
        this.type = type;
    }

    public String getType(){
        return this.type;
    }
}
