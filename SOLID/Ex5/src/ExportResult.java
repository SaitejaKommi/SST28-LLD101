public class ExportResult {
    public final String contentType;
    public final byte[] bytes;
    public final boolean isOk;
    public final String errorMessage;

    private ExportResult(String contentType, byte[] bytes) {
        this.contentType = contentType;
        this.bytes = bytes;
        this.isOk = true;
        this.errorMessage = null;
    }

    private ExportResult(String errorMessage) {
        this.contentType = null;
        this.bytes = new byte[0];
        this.isOk = false;
        this.errorMessage = errorMessage;
    }

    public static ExportResult ok(String contentType, byte[] bytes) {
        return new ExportResult(contentType, bytes);
    }

    public static ExportResult error(String message) {
        return new ExportResult(message);
    }
}