package model.objects;

public class Enemy extends Content{
    float[] attributes;

    public Enemy(int id, float[] attributes) {
        super(id);
        this.attributes = attributes;
    }
}
