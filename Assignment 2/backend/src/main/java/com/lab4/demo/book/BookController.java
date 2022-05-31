package com.lab4.demo.book;

import com.lab4.demo.book.model.dto.BookDTO;
import com.lab4.demo.report.ReportServiceFactory;
import com.lab4.demo.report.ReportType;
import com.lab4.demo.security.dto.MessageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

import static com.lab4.demo.UrlMapping.*;

@RestController
@RequestMapping(FRONT_OFFICE)
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final ReportServiceFactory reportServiceFactory;

    @GetMapping
    public List<BookDTO> allBooks() {
        return bookService.findAll();
    }

    @PostMapping
    public BookDTO create(@RequestBody BookDTO book) {
        return bookService.create(book);
    }

    @PatchMapping(ID)
    public BookDTO edit(@RequestBody BookDTO book, @PathVariable Long id) {
        return bookService.edit(book, id);
    }

    @GetMapping(EXPORT_REPORT)
    public String exportReport(@PathVariable ReportType type) throws IOException {
        return reportServiceFactory.getReportService(type).export();
    }

    @DeleteMapping(ID)
    public void delete(@PathVariable Long id){
        bookService.delete(id);
    }

    @GetMapping(TAG)
    public List<BookDTO>  findNAG(@RequestBody String name, @RequestBody String author, @RequestBody String genre){
        return bookService.findByNAG(name, author, genre);
    }

    @PostMapping(ID + SELL)
    public ResponseEntity<?> sell(@PathVariable Long id, @PathVariable int quantity){
        if(bookService.sold(id, quantity)) return ResponseEntity.ok(new MessageResponse("The selling was made!"));
        else return ResponseEntity.badRequest().body(new MessageResponse("Something wrong with the selling: Error!"));
    }


}
