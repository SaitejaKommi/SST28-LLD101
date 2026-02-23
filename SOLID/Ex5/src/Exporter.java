
public abstract class Exporter {

    public final ExportResult export(ExportRequest req) {
        if (req == null) {
            return ExportResult.error("ExportRequest must not be null");
        }
        return doExport(req);
    }

    protected abstract ExportResult doExport(ExportRequest req);
}