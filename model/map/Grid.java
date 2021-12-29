package model.map;
import model.objects.Content;
import model.objects.Terrain;

import java.sql.Array;
import java.util.*;

//x * y grid of the game area, each tile of the grid represents a possibly walkable area with objects or interactions
public class Grid{
    private int rowsCount;
    private int colcount;
    private ArrayList<ArrayList<Tile>> content = new ArrayList<ArrayList<Tile>>();

    public int getColcount() {
        return colcount;
    }

    public int getRowsCount() {
        return rowsCount;
    }

    public ArrayList<ArrayList<Tile>> getContent(){
        return content;
    }

    //TODO add contents
    //initialization for new games
    public Grid(int cols, int rows){
        this.rowsCount = rows;
        this.colcount = cols;
        for (int i = 0; i < rows; i++){
            ArrayList<Tile> tileList = new ArrayList<Tile>();
            for(int j = 0; j < cols; j++){
                ArrayList<Content> contents = new ArrayList<>();
                contents.add(generateTerrain());
                tileList.add(new Tile(i, j, contents));
            }
            //System.out.println("GridCreate:" + tileList);
            this.content.add(tileList);
            //System.out.println("GridCreate:" + content);
            generateTerrain();
        }
    }

    //creator function for loading purposes
    public Grid(Grid grid){
        this.rowsCount = grid.getRowsCount();
        this.colcount = grid.getColcount();
        this.content = grid.getContent();
    }
    
    //TODO create patches of land, forest and rocks + bodies of water and lakes
    private Terrain generateTerrain(){
        Random random = new Random();
        int rng = random.nextInt(4);
        String terrain;
        switch(rng){
            case 0:
                terrain = "grass";
                break;
            case 1:
                terrain = "rock";
                break;
            case 2:
                terrain = "water";
                break;
            case 3:
                terrain = "forest";
                break;
            default:
                terrain = "grass";
        }
        Terrain type = new Terrain(rng, terrain);
        return type;
    }
}