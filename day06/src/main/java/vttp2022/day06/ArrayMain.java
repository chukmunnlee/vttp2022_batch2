package vttp2022.day06;

public class ArrayMain {

    public static void main(String[] args) {

        String[] lines = new String[10];

        // 3 x 3 matrix of float
        float[][] mat2 = new float[3][3];

        float[][] mat = new float[3][];
        for (int i = 0; i < mat.length; i++) {
            mat[i] = new float[3];
            mat[i][0] = i;
            mat[i][1] = i;
            mat[i][2] = i;
        }

        float[][] mat3 = new float[3][];
        for (int i = 0; i < mat3.length; i++) {
            mat3[i] = new float[i + 1];
        }

    }
    
}
