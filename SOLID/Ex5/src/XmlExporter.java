import java.nio.charset.StandardCharsets;

public class XmlExporter extends Exporter {
    @Override
    protected ExportResult doExport(ExportRequest req) {
        String xml = "<export>"
                + "<title>" + escapeXml(req.title) + "</title>"
                + "<body>" + escapeXml(req.body) + "</body>"
                + "</export>";
        return ExportResult.ok("application/xml", xml.getBytes(StandardCharsets.UTF_8));
    }

    private String escapeXml(String s) {
        if (s == null)
            return "";
        return s.replace("&", "&amp;").replace("<", "&lt;").replace(">", "&gt;");
    }
}