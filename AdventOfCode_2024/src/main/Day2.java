package main;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

public class Day2 {
    public static void main(String[] args) {
        System.out.println("Day 2:");
        int safeCount = 0;
        try {
            File file = new File("puzzleInputFiles/day2.txt");
            Scanner scanner = new Scanner(file);
            int currentLevel = 0;

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                int[] levels = Arrays.stream(line.split(" ")).mapToInt(Integer::parseInt).toArray();
                boolean faultDetected = false;
                boolean safe = true;
                Boolean increasing = null;
                boolean increasingReassigned = false;

                currentLevel++;
                System.out.println("line: " + line);
                for (int i = 0; i < levels.length; i++) {
                    if (i < levels.length - 1) {
                        if (increasing == null) {
                            increasing = levels[i] < levels[i + 1];
                        }

                        if (!(levels[i] - levels[i + 1] >= 3) && levels[i] < levels[i + 1] && Boolean.TRUE.equals(increasing) && levels[i] != levels[i + 1]) {
                            continue;
                        } else if (!(levels[i] - levels[i + 1] <= -3) && levels[i] > levels[i + 1] && Boolean.FALSE.equals(increasing) && levels[i] != levels[i + 1]) {
                            continue;
                        } else {
                            System.out.println("else");
                            increasing = null;
                            int[] newArray1 = new int[levels.length - 1];
                            int currentIndex = 0;

                            for (int j = 0; j < newArray1.length; j++) {
                                if (i == j) {
                                    currentIndex++;
                                }

                                newArray1[j] = levels[currentIndex++];
                            }

                            for (int j = 0; j < newArray1.length; j++) {
                                if (!faultDetected && !increasingReassigned) {
                                    increasing = levels[i] < levels[i + 1];
                                    increasingReassigned = true;
                                }

                                if (j < newArray1.length - 1) {
                                    if (increasing != null) {
                                        if (!(newArray1[j] - newArray1[j + 1] >= 3) && newArray1[j] < levels[j + 1] && Boolean.TRUE.equals(increasing) && !faultDetected) {
                                            faultDetected = true;
                                            continue;
                                        } else if (!(newArray1[j] - newArray1[j + 1] <= -3) && newArray1[j] > newArray1[j + 1] && Boolean.FALSE.equals(increasing) && !faultDetected) {
                                            faultDetected = true;
                                            continue;
                                        } else {
                                            if (faultDetected) {
                                                safe = false;
                                                break;
                                            }
                                        }
                                    } else {
                                        safe = false;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }

                if (safe) safeCount++;
                String formatted = String.format("Level: %d, Safe: %s \n", currentLevel, safe);
                System.out.println(formatted);
            }

            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("File not found");
            System.out.println(e.getMessage());
            System.exit(-1);
        }

        System.out.println("Safe count: " + safeCount);
    }

//                        else {
//                            if (i < levels.length - 2) {
//                                if ((levels[i + 2] - levels[i] <= 3) && levels[i] < levels[i + 2] && Boolean.TRUE.equals(increasing) && !faultDetected) {
//                                    faultDetected = true;
//                                    i++;
//                                    continue;
//                                } else if ((levels[i + 2] - levels[i] >= -3) && levels[i] > levels[i + 2] && Boolean.FALSE.equals(increasing) && !faultDetected) {
//                                    faultDetected = true;
//                                    i++;
//                                    continue;
//                                } else {
//                                    if (!faultDetected && i == levels.length - 2) {
//                                        break;
//                                    }
//                                }
//
//                                safe = false;
//                                break;
//                            }
//                        }
//                    } else if (faultDetected && levels[i] > levels[i - 1] && Boolean.FALSE.equals(increasing)) {
//                        safe = false;
//                    } else if (faultDetected && levels[i] < levels[i - 1] && Boolean.TRUE.equals(increasing)) {
//                        safe = false;
//                    } else if (faultDetected && levels[i] == levels[i - 1]) {
//                        safe = false;

    // +6

    // level 50, 45, 75, 100, 135, 149
}
