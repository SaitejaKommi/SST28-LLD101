
public class DisciplinaryFlagRule implements EligibilityRule {
    @Override
    public String check(StudentProfile s) {
        return s.disciplinaryFlag != LegacyFlags.NONE ? "disciplinary flag present" : null;
    }
}