import java.util.Scanner;

public class CGPAGPACalculator implements Runnable {

    private static final int NUMBER_OF_SUBJECTS = 5;
    private static final int TOTAL_CREDITS = 30;
    
    private static double[] grades = new double[NUMBER_OF_SUBJECTS];
    private static double[] credits = new double[NUMBER_OF_SUBJECTS];
    
     private static double gpa = 0.0;
    private static double cgpa = 0.0;
    
    private int subjectIndex;
    
    public CGPAGPACalculator(int subjectIndex) {
        this.subjectIndex = subjectIndex;
    }
    
    public void run() {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the grade for subject " + (subjectIndex + 1) + ": ");
        grades[subjectIndex] = scanner.nextDouble();
        
        System.out.print("Enter the credit for subject " + (subjectIndex + 1) + ": ");
        credits[subjectIndex] = scanner.nextDouble();
        
        scanner.close();
    }
    
    public static void main(String[] args) throws InterruptedException {
        Thread[] threads = new Thread[NUMBER_OF_SUBJECTS];
        
        for (int i = 0; i < NUMBER_OF_SUBJECTS; i++) {
            threads[i] = new Thread(new CGPAGPACalculator(i));
            threads[i].start();
        }
        
        for (int i = 0; i < NUMBER_OF_SUBJECTS; i++) {
            threads[i].join();
        }
        
        for (int i = 0; i < NUMBER_OF_SUBJECTS; i++) {
            gpa += grades[i] * credits[i];
        }
        
        gpa /= TOTAL_CREDITS;
        
        System.out.println("Your GPA is " + gpa);
        
        // Calculate CGPA
        // Here, you can use the previous semester's CGPA and GPA to calculate the current CGPA
        // For simplicity, we are assuming that the previous semester's CGPA is 3.0
        cgpa = (3.0 * 15 + gpa * 15) / 30;
        
        System.out.println("Your CGPA is " + cgpa);
    }

}
