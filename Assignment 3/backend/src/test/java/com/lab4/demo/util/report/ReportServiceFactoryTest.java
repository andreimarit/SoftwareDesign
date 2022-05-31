//package com.lab4.demo.util.report;
//
//import org.junit.jupiter.api.Assertions;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import java.io.IOException;
//
//import static com.lab4.demo.util.report.ReportType.CSV;
//import static com.lab4.demo.util.report.ReportType.PDF;
//
//@SpringBootTest
//class ReportServiceFactoryTest {
//
//    @Autowired
//    private ReportServiceFactory reportServiceFactory;
//
//    @Test
//    void getReportService() throws IOException {
//        ReportService csvReportService = reportServiceFactory.getReportService(CSV);
//        Assertions.assertEquals("I am a CSV reporter.", csvReportService.export());
//
//        ReportService pdfReportService = reportServiceFactory.getReportService(PDF);
//        Assertions.assertEquals("I am a PDF reporter.", pdfReportService.export());
//
//    }
//}