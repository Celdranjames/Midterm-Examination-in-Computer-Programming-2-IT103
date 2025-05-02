import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class Main {

    public static double calculateAverageGrade(String filename) {
        double totalGrade = 0;
        int numberOfStudents = 0;

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                if (lineScanner.hasNext()) {
                    lineScanner.next();
                    if (lineScanner.hasNextDouble()) {
                        totalGrade += lineScanner.nextDouble();
                        numberOfStudents++;
                    }
                }
                lineScanner.close();
            }
            scanner.close();

            if (numberOfStudents > 0) {
                return totalGrade / numberOfStudents;
            } else {
                return 0;
            }

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found!");
            return 0;
        }
    }

    public static void printStudentGrades(String filename) {
        System.out.println("Student Grades:");

        try {
            File file = new File(filename);
            Scanner scanner = new Scanner(file);

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                Scanner lineScanner = new Scanner(line);
                if (lineScanner.hasNext()) {
                    String name = lineScanner.next();
                    if (lineScanner.hasNextDouble()) {
                        double grade = lineScanner.nextDouble();
                        String gradeString = String.valueOf(grade);
                        if (gradeString.endsWith(".0")) {
                            gradeString = gradeString.substring(0, gradeString.length() - 2);
                        }
                        System.out.println(name + ": " + gradeString);
                    }
                }
                lineScanner.close();
            }
            scanner.close();

        } catch (FileNotFoundException e) {
            System.out.println("Error: File not found!");
        }
    }

    public static void main(String[] args) {
        String filename = "grades.txt";

        java.io.PrintWriter writer = null;
        try {
            writer = new java.io.PrintWriter(filename);
            writer.println("Alice 85");
            writer.println("Bob 90");
            writer.println("Charlie 75");
        } catch (java.io.FileNotFoundException e) {
            System.out.println("Error creating sample file.");
        } finally {
            if (writer != null) {
                writer.close();
            }
        }

        printStudentGrades(filename);
        double averageGrade = calculateAverageGrade(filename);
        System.out.println("\nAverage Grade : " + String.format("%.2f", averageGrade));
    }
}