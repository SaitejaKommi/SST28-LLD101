
public class CreditsRule implements EligibilityRule {
    private final int minCredits;

    public CreditsRule(int minCredits) {
        this.minCredits = minCredits;
    }

    @Override
    public String check(StudentProfile s) {
        return s.earnedCredits < minCredits ? "credits below " + minCredits : null;
    }
}