import java.io.*;
import java.util.Arrays;
import java.util.List;

public class Main {
    static int iter = 0;
    public static void main(String[] args) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter("data_Floyd.csv"));
        for (int k=1; k<=50; k++) {
            try {
                BufferedReader reader = new BufferedReader(new FileReader("data_set_" + k + ".txt"));
                int vertexes = Integer.parseInt(reader.readLine());
                int[][] matrix = new int[vertexes][vertexes];
                String line;
                int i = 0;
                while ((line = reader.readLine()) != null) {
                    matrix[i] = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                    i++;
                }

                long startTime = System.nanoTime();
                List<int[][]> arr = algorithmFloyd(vertexes, matrix);
                long endTime = System.nanoTime();
                writer.write(vertexes + ", " + (endTime-startTime) + ", " + iter +"\n");
            } catch (IOException e) {
                System.out.println("An error occurred while reading the file.");
                e.printStackTrace();
            }
        }
        writer.close();
    }

    public static List<int[][]> algorithmFloyd(int vertexes, int[][] graph){
        iter = 0;
        int infinity = 2000;

        int[][] matrix = new int[vertexes][vertexes];
        for (int i=0; i<vertexes; i++){
            for (int j=0; j<vertexes; j++){
                iter++;
                if (i==j){
                    matrix[i][i] = 0;
                }
                else if (graph[i][j]==0){
                    matrix[i][j] = infinity;
                }
                else{
                    matrix[i][j] = graph[i][j];
                }
            }
        }

        int[][] next = new int[vertexes][vertexes];
        for(int i=0; i<vertexes; i++) {
            for (int j = 0; j < vertexes; j++) {
                iter++;
                if (matrix[i][j] != 0) {
                    next[i][j] = j + 1;
                } else {
                    next[i][j] = 0;
                };
            }
        }

        for (int v = 0; v < vertexes; v++){
            for (int i = 0; i < vertexes; i++){
                for (int j = 0; j < vertexes; j++){
                    iter++;
                    if (matrix[i][v]<infinity && matrix[v][j]<infinity){
                        matrix[i][j] = matrix[v][j]+matrix[i][v];
                        next[i][j] = next[i][v];
                    }
                }
            }
        }
        return List.of(matrix, next);
    }
}