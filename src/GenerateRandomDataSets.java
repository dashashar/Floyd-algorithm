import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;

public class GenerateRandomDataSets {
        private static final int NUM_DATA_SETS = 50; // Количество наборов данных
        private static final int MIN_SIZE = 10; // Минимальный размер набора
        private static final int MAX_SIZE = 100; // Максимальный размер набора

        public static void main(String[] args) {
            for (int k = 1; k <= NUM_DATA_SETS; k++) {
                int dataSize = generateRandomNumberInRange(MIN_SIZE, MAX_SIZE);
                int[][] data = generateRandomData(dataSize);

                try {
                    BufferedWriter writer = new BufferedWriter(new FileWriter("data_set_" + k + ".txt"));
                    writer.write(dataSize + "\n");
                    for (int i=0; i<dataSize; i++) {
                        for (int j=0; j<dataSize; j++){
                            writer.write(data[i][j] + " ");
                        }
                        writer.newLine();
                    }
                    writer.close();
                } catch (IOException e) {
                    System.out.println("An error occurred while writing to the file.");
                    e.printStackTrace();
                }
            }
            System.out.println("Random data sets have been generated and written to files.");
        }

        private static int generateRandomNumberInRange(int min, int max) {
            Random random = new Random();
            return random.nextInt(max - min + 1) + min;
        }

        private static int[][] generateRandomData(int size) {
            int[][] data = new int[size][size];
            Random random = new Random();
            for (int i = 0; i < size; i++) {
                for (int j=0; j<size; j++){
                    data[i][j] = random.nextInt(100); // Генерация случайного числа от 0 до 999
                }
            }
            return data;
        }
    }