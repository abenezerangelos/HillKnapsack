import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Random;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Knapsack Optimizer with Hill Climbing - Created by: Armin\n");
        Random rnd = new Random();
        int NUM_OF_ITEMS = 200;

        File dataFile = new File("data.csv");
        if(!dataFile.exists()) {
            try {
                PrintWriter pw = new PrintWriter(dataFile);
                // initialize item list
                for (int i = 0; i < NUM_OF_ITEMS; i++) {
                    int v = rnd.nextInt(19) + 1;
                    int w = rnd.nextInt(49) + 1;
                    pw.println(v + "," + w);
                }
                pw.flush();
                pw.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
                System.err.println("fatal: error writing file.");
                return;
            }
        }

        int[] values = new int[NUM_OF_ITEMS];
        int[] weights = new int[NUM_OF_ITEMS];

        try {
            Scanner fscn = new Scanner(dataFile);
            //load items
            for (int i = 0; i < NUM_OF_ITEMS; i++) {
                String[] parts = fscn.nextLine().split(",");
                values[i] = Integer.parseInt(parts[0]);
                weights[i] = Integer.parseInt(parts[1]);
                System.out.println(i + ":(" + weights[i] + "kg) (" + values[i] + "$)");
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.err.println("fatal: error reading file.");
            return;
        }

        Scanner stdscn = new Scanner(System.in);
        System.out.println("please specify max weight of the knapsack:");
        int MAX_WEIGHT = stdscn.nextInt();

        Evaluator evaluator = new Evaluator(weights,values,MAX_WEIGHT);
        Neighbours neighbours = new Neighbours();

        // initialize random starting state
        boolean[] initialPickups = new boolean[NUM_OF_ITEMS];
        for (int i = 0; i < NUM_OF_ITEMS; i++) {
            initialPickups[i] = rnd.nextBoolean();
        }
        State initialState = new State(initialPickups);

        HillClimbing hc = new HillClimbing(neighbours,evaluator,initialState);
        State optimumState = hc.climb();

        System.out.println("Answer:");
        System.out.println(optimumState.toString());

        int totalWeight = 0;
        int totalValue = 0;
        for (int i = 0; i < optimumState.getPickups().length; i++) {
            if(optimumState.getPickups()[i]) {
                totalWeight += weights[i];
                totalValue += values[i];
            }
        }

        System.out.println("Picked up Weight:" + totalWeight);
        System.out.println("Picked up Value:" + totalValue);
    }

}
