package com.lab4.demo.report;

import com.lab4.demo.book.BookRepository;
import com.lab4.demo.book.model.Book;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.stream.Collectors;

import static com.lab4.demo.report.ReportType.CSV;

@RequiredArgsConstructor
@Service
public class CSVReportService implements ReportService {

    private final BookRepository bookRepository;

    @Override
    public String export() throws IOException {

        System.out.println("has entered in export!");
        try{
            List<Book> books = bookRepository.findAll().stream().filter(book -> book.getQuantity() == 0).collect(Collectors.toList());

            PrintWriter writer = new PrintWriter("myCSV.csv", "UTF-8");
            for (Book book : books) {
                writer.println(book);
            }
            writer.close();
        }
        catch ( FileNotFoundException | UnsupportedEncodingException e){
            System.out.println(e);
        }


        return "I am a CSV reporter.";
    }

    @Override
    public ReportType getType() {
        return CSV;
    }
}
