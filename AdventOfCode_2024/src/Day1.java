import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Day1 {
    public static void main(String[] args) {
        List<Integer> keys = new ArrayList<>();
        List<Integer> values = new ArrayList<>();
        File locationsFile = new File("puzzleInputFiles/day1.txt");

        try {
            Scanner scanner = new Scanner(locationsFile);
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] tokens = line.split(" {3}");
                keys.add(Integer.parseInt(tokens[0]));
                values.add(Integer.parseInt(tokens[1]));
            }
            scanner.close();
        } catch (FileNotFoundException exception) {
            System.out.println("File not found");
            System.out.println(exception.getMessage());
        }

        calculateDifference(keys, values);

        int similarityScore = 0;

        for (Integer key : keys) {
            int count = findInArray(key, values);
            similarityScore = similarityScore + (count * key);
        }

        System.out.println("Similarity score: " + similarityScore);
    }

    private static int findInArray(int value, List<Integer> array) {
        int count = 0;
        for (Integer integer : array) {
            if (integer == value) {
                count++;
            }
            if (integer > value) {
                break;
            }
        }

        return count;
    }

    private static void calculateDifference(List<Integer> list1, List<Integer> list2) {
        mergeSort(list1, 0, list1.size() - 1);
        mergeSort(list2, 0, list2.size() - 1);

        int totalDifference = 0;
        for (int i = 0; i < list1.size(); i++) {
            int difference = Math.abs(list1.get(i) - list2.get(i));
            totalDifference += difference;
        }
        System.out.println("total difference: " + totalDifference);
    }

    private static void mergeSort(List<Integer> list, int l, int r) {
        if (l < r) {
            int m = l + (r - l) / 2;

            mergeSort(list, l, m);
            mergeSort(list, m + 1, r);

            merge(list, l, r, m);
        }
    }

    private static void merge(List<Integer> list, int l, int r, int m) {
        // Sizes of sub-arrays
        int n1 = m - l + 1;
        int n2 = r - m;

        // temp arrays
        int[] lTemp = new int[n1];
        int[] rTemp = new int[n2];

        // assign data to temp arrays
        for (int i = 0; i < n1; i++) {
            lTemp[i] = list.get(l + i);
        }

        for (int i = 0; i < n2; i++) {
            rTemp[i] = list.get(m + 1 + i);
        }

        int i = 0, j = 0;
        int k = l;

        // Initial index of merged subarray
        while (i < n1 && j < n2) {
            if (lTemp[i] <= rTemp[j]) {
                list.set(k, lTemp[i]);
                i++;
            } else {
                list.set(k, rTemp[j]);
                j++;
            }
            k++;
        }

        // Copy remaining elements of lTemp
        while (i < n1) {
            list.set(k, lTemp[i]);
            i++;
            k++;
        }

        // Copy remaining elements of rTemp
        while (j < n2) {
            list.set(k, rTemp[j]);
            j++;
            k++;
        }
    }
}
