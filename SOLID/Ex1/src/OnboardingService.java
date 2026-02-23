import java.util.*;

public class OnboardingService {
    private final InputParser parser;
    private final StudentValidator validator;
    private final RegistrationPrinter printer;
    private final StudentInterface studentInterface;

    public OnboardingService( StudentInterface studentInterface) {
        this.parser = new InputParser();
        this.validator = new StudentValidator();
        this.printer = new RegistrationPrinter();
        this.studentInterface = studentInterface;
    }

    // Intentionally violates SRP: parses + validates + creates ID + saves + prints.
    public void registerFromRawInput(String raw) {
        printer.printInput(raw);


        Map<String, String> kv = parser.parse(raw);

    
            List<String> errors = validator.validate(kv);
            if (!errors.isEmpty()) {
                printer.printErrors(errors);
                return;
            }

        String id = IdUtil.nextStudentId(studentInterface.count());
        StudentRecord rec = createRecord(id, kv);
        studentInterface.save(rec);

        printer.printSuccess(id, studentInterface.count());
        printer.printConfirmation(rec);
    
    }

private final StudentRecord createRecord(String id, Map<String, String> kv) {
    String name = kv.get("name");
    String email = kv.get("email");
    String phone = kv.get("phone");
    String program = kv.get("program");
    return new StudentRecord(id, name, email, phone, program);

}
}