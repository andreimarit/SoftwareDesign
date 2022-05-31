//package com.lab4.demo.util.report;
//
//import com.lab4.demo.repo.PatientRepository;
//import com.lab4.demo.model.Patient;
//import lombok.RequiredArgsConstructor;
//import org.apache.pdfbox.pdmodel.PDDocument;
//import org.apache.pdfbox.pdmodel.PDPage;
//import org.apache.pdfbox.pdmodel.PDPageContentStream;
//import org.apache.pdfbox.pdmodel.font.PDType1Font;
//import org.springframework.stereotype.Service;
//
//import java.io.IOException;
//import java.util.List;
//import java.util.stream.Collectors;
//
//import static com.lab4.demo.util.report.ReportType.PDF;
//
//@RequiredArgsConstructor
//@Service
//public class PdfReportService implements ReportService {
//
//
//    private final PatientRepository patientRepository;
//
//    @Override
//    public String export() throws IOException {
//
//        try {
//
//            List<Patient> patients = patientRepository.findAll().stream().filter(patient -> patient.getQuantity() == 0).collect(Collectors.toList());
//
//            PDDocument doc = new PDDocument();
//            PDPage page = new PDPage();
//            doc.addPage(page);
//            PDPageContentStream contentStream = new PDPageContentStream(doc, page);
//            contentStream.setFont(PDType1Font.TIMES_ROMAN, 12);
//            contentStream.beginText();
//            contentStream.setLeading(14.5f);
//            contentStream.newLineAtOffset(25, 700);
//            for (Patient patient : patients) {
//                contentStream.showText(patient.toString());
//                contentStream.newLine();
//            }
//            contentStream.endText();
//            contentStream.close();
//            doc.save("myPDF.pdf");
//            doc.close();
//        }
//        catch (IOException e){
//            System.out.println(e);
//        }
//        return "I am a PDF reporter.";
//    }
//
//
//    @Override
//    public ReportType getType() {
//        return PDF;
//    }
//
//
//}
