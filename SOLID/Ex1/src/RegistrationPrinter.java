import java.util.*;

public class RegistrationPrinter {
    public void printInput(String raw) {
        System.out.println("INPUT: " + raw);
    }

    public void printSuccess(String studentId, int totalCount) {
        System.out.println("OK: created student " + studentId);
        System.out.println("Saved. Total students: " + totalCount);
    }

    public void printConfirmation(StudentRecord record) {
        System.out.println("CONFIRMATION:");
        System.out.println(record);
    }

    public void printErrors(List<String> errors) {
        System.out.println("ERROR: cannot register");
        for (String e : errors) {
            System.out.println("- " + e);
        }
    }
}