package com.lab4.demo.report;

import com.lab4.demo.book.BookRepository;
import com.lab4.demo.book.model.Book;
import lombok.RequiredArgsConstructor;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.export.SimplePdfReportConfiguration;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static com.lab4.demo.report.ReportType.PDF2;

@RequiredArgsConstructor
@Service
public class JasperPDFReportService implements ReportService {


    private final BookRepository bookRepository;

    @Override
    public String export() {

        try {
            List<Book> books = bookRepository.findAll().stream().filter(book -> book.getQuantity() == 0).collect(Collectors.toList());

            JRBeanCollectionDataSource dataSource = new JRBeanCollectionDataSource(books);

            Map<String, Object> parameters = new HashMap<>();
            parameters.put("createdBy","Andrei");

            JasperPrint jasperPrint = JasperFillManager.fillReport("JapserPDF.txt", parameters, dataSource);

            //probably not needed until next comm...
            JRPdfExporter exporter = new JRPdfExporter();

            exporter.setExporterInput(new SimpleExporterInput(jasperPrint));
            exporter.setExporterOutput(
              new SimpleOutputStreamExporterOutput("bookJasperReport.pdf"));

            SimplePdfReportConfiguration reportConfig
              = new SimplePdfReportConfiguration();
            reportConfig.setSizePageToContent(true);
            reportConfig.setForceLineBreakPolicy(false);

            SimplePdfExporterConfiguration exportConfig
              = new SimplePdfExporterConfiguration();
            exportConfig.setMetadataAuthor("Andrei");
            exportConfig.setEncrypted(true);
            exportConfig.setAllowedPermissionsHint("PRINTING");

            exporter.setConfiguration(reportConfig);
            exporter.setConfiguration(exportConfig);

            exporter.exportReport();


           // JasperExportManager.exportReportToPdfFile(jasperPrint, "jasperPDF.pdf");
        }
        catch (JRException e){
            System.out.println(e);
        }


        return "I am Jasper PDF reporter!";
    }

    @Override
    public ReportType getType() {
        return PDF2;
    }
}
