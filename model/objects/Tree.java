package model.objects;

public class Tree extends Content{

    //points from 3d image
    private float[] points = {0,0,0, //0 upper left
                                0, 0, 1, //1 lower left
                                1, 0, 1, //2 lower right
                                1, 0, 0, //3 upper right
                                0.5f, 2, 0.5f //4 top
    };

    //points in mesh (flat layout)
    private float[] texCoords = {0.5f,0, //0
                                    0.33f, 0.33f, //1
                                    0.66f, 0.33f, //2
                                    0, 0.5f, //3
                                    0.33f, 0.66f, //4
                                    0.66f, 0.66f, //5
                                    1, 0.5f, //6
                                    0.5f, 1}; //7

    //point, texCoord --> counter clockwise
    private int[] faces = {4,0,0,1,3,2,
                            0,1,4,3,1,4,
                            0,1,1,4,3,2,
                            1,4,2,5,3,2,
                            1,4,4,7,2,5,
                            3,2,2,5,4,6};

    public Tree(int id) {
        super(id);
    }

    public float[] getPoints() {
        return points;
    }

    public float[] getTexCoords() {
        return texCoords;
    }

    public int[] getFaces() {
        return faces;
    }
}
