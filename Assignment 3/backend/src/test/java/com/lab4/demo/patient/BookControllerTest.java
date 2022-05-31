//package com.lab4.demo.patient;
//
//import com.lab4.demo.BaseControllerTest;
//import com.lab4.demo.TestCreationFactory;
//import com.lab4.demo.controller.PatientController;
//import com.lab4.demo.model.Patient;
//import com.lab4.demo.dto.PatientDTO;
//import com.lab4.demo.util.report.CSVReportService;
//import com.lab4.demo.util.report.PdfReportService;
//import com.lab4.demo.util.report.ReportServiceFactory;
//import com.lab4.demo.service.PatientService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.springframework.test.web.servlet.ResultActions;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.util.List;
//
//import static com.lab4.demo.TestCreationFactory.randomLong;
//import static com.lab4.demo.TestCreationFactory.randomString;
//import static com.lab4.demo.UrlMapping.EXPORT_REPORT;
//import static com.lab4.demo.UrlMapping.PATIENT;
//import static com.lab4.demo.util.report.ReportType.CSV;
//import static com.lab4.demo.util.report.ReportType.PDF;
//import static org.mockito.Mockito.when;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//class PatientControllerTest extends BaseControllerTest {
//
//    @InjectMocks
//    private PatientController controller;
//
//    @Mock
//    private PatientService patientService;
//
//    @Mock
//    private ReportServiceFactory reportServiceFactory;
//
//    @Mock
//    private CSVReportService csvReportService;
//
//    @Mock
//    private PdfReportService pdfReportService;
//
//    @BeforeEach
//    protected void setUp() {
//        super.setUp();
//        controller = new PatientController(patientService, reportServiceFactory);
//        mockMvc = MockMvcBuilders.standaloneSetup(controller).build();
//    }
//
//    @Test
//    void allPatients() throws Exception {
//        List<PatientDTO> patients = TestCreationFactory.listOf(Patient.class);
//        when(patientService.findAll()).thenReturn(patients);
//
//        ResultActions response = mockMvc.perform(get(PATIENT));
//
//        response.andExpect(status().isOk())
//                .andExpect(jsonContentToBe(patients));
//
//    }
//
//    @Test
//    void exportReport() throws Exception {
//        when(reportServiceFactory.getReportService(PDF)).thenReturn(pdfReportService);
//        when(reportServiceFactory.getReportService(CSV)).thenReturn(csvReportService);
//        String pdfResponse = "PDF!";
//        when(pdfReportService.export()).thenReturn(pdfResponse);
//        String csvResponse = "CSV!";
//        when(csvReportService.export()).thenReturn(csvResponse);
//
//        ResultActions pdfExport = mockMvc.perform(get(PATIENT + EXPORT_REPORT, PDF.name()));
//        ResultActions csvExport = mockMvc.perform(get(PATIENT + EXPORT_REPORT, CSV.name()));
//
//        pdfExport.andExpect(status().isOk())
//                .andExpect(content().string(pdfResponse));
//        csvExport.andExpect(status().isOk())
//                .andExpect(content().string(csvResponse));
//
//    }
//
//    @Test
//    void create() throws Exception {
//        PatientDTO reqPatient = PatientDTO.builder().name(randomString())
//                .author(randomString())
//                .genre(randomString())
//                .price(10)
//                .quantity(10)
//                .build();
//
//        when(patientService.create(reqPatient)).thenReturn(reqPatient);
//
//        ResultActions result = performPostWithRequestBody(PATIENT, reqPatient);
//        result.andExpect(status().isOk())
//                .andExpect(jsonContentToBe(reqPatient));
//    }
//
//    @Test
//    void edit() throws Exception {
//        PatientDTO reqPatient = PatientDTO.builder()
//                .id(randomLong())
//                .name(randomString())
//                .author(randomString())
//                .genre(randomString())
//                .price(10)
//                .quantity(10)
//                .build();
//
//        when(patientService.edit(reqPatient, 1L)).thenReturn(reqPatient);
//
//        ResultActions result = performPutWithRequestBody(PATIENT, reqPatient);
//        result.andExpect(status().isOk())
//                .andExpect(jsonContentToBe(reqPatient));
//    }
//}