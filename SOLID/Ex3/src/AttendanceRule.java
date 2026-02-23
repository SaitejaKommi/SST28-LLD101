
public class AttendanceRule implements EligibilityRule {
    private final int minAttendance;

    public AttendanceRule(int minAttendance) {
        this.minAttendance = minAttendance;
    }

    @Override
    public String check(StudentProfile s) {
        return s.attendancePct < minAttendance ? "attendance below " + minAttendance : null;
    }
}