package map;
import objects;

protected class Tile{
    int xCoordinate;
    int yCoordinate;
    boolean enterable;
    boolean visited;
    List<Content> contents;

    private Tile(int xCoordinate, int yCoordinate, List<Content> contents){
        this.xCoordinate = xCoordinate;
        this. yCoordinate = yCoordinate;
        this.contents = contents;
    }
}