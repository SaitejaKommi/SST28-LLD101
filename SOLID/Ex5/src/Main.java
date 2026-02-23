public class Main {
    public static void main(String[] args) {
        System.out.println("=== Export Demo ===");

        ExportRequest req = new ExportRequest("Weekly Report", SampleData.longBody());
        Exporter pdf = new PdfExporter();
        Exporter csv = new CsvExporter();
        Exporter json = new JsonExporter();

        System.out.println("PDF: " + describe(pdf.export(req)));
        System.out.println("CSV: " + describe(csv.export(req)));
        System.out.println("JSON: " + describe(json.export(req)));

        Exporter xml = new XmlExporter();
        System.out.println("XML: " + describe(xml.export(req)));
    }


    private static String describe(ExportResult result) {
        return result.isOk
                ? "OK bytes=" + result.bytes.length
                : "ERROR: " + result.errorMessage;
    }
}