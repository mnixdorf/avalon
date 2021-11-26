package map;

protected class Grid{
    private int rowsCount;
    private int colcount;
    List<List<Tile>> content;

    private Grid(int cols, int rows){
        content = new ArrayList<ArrayList<Tile>>();
        for (int i = 0; i < rows; i++){
            for(int j = 0; j < cols; j++){

            }
        }
    }

    private Grid(Grid grid){
    }
}