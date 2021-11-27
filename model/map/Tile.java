package model.map;
import model.objects.*;
import java.util.*;

public class Tile{
    int xCoordinate;
    int yCoordinate;
    boolean enterable;
    boolean visited;
    ArrayList<Content> contents = new ArrayList<Content>();

    private Tile(int xCoordinate, int yCoordinate, ArrayList<Content> contents){
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.contents = contents;
    }
}