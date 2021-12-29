package model.map;
import model.objects.*;
import java.util.*;

public class Tile{
    private int xCoordinate;
    private int yCoordinate;
    private boolean enterable;
    private boolean visited;
    private ArrayList<Content> contents = new ArrayList<Content>();

    public int getxCoordinate(){
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    public boolean isEnterable() {
        return enterable;
    }

    public boolean isVisited() {
        return visited;
    }

    public ArrayList<Content> getContents() {
        return contents;
    }

    public Tile(int xCoordinate, int yCoordinate, ArrayList<Content> contents){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.contents = contents;
        //TODO enterable and walkable
    }
}