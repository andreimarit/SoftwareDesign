package com.lab4.demo.book;

import com.lab4.demo.book.model.Book;
import com.lab4.demo.book.model.dto.BookDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final BookMapper bookMapper;

    private Book findById(Long id) {
        return bookRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Book not found: " + id));
    }

    public List<BookDTO> findAll() {
        return bookRepository.findAll().stream()
                .map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public BookDTO create(BookDTO book) {
        return bookMapper.toDto(bookRepository.save(
                bookMapper.fromDto(book)
        ));
    }

    public BookDTO edit(BookDTO book, Long id) {
        Book actBook = findById(id);
        actBook.setId(book.getId());
        actBook.setName(book.getName());
        actBook.setAuthor(book.getAuthor());
        actBook.setGenre(book.getGenre());
        actBook.setPrice(book.getPrice());
        actBook.setQuantity(book.getQuantity());
        return bookMapper.toDto(
                bookRepository.save(actBook)
        );
    }

    public void delete(Long id){
     bookRepository.deleteById(id);
    }

    public List<BookDTO> findByNAG(String name,String author, String genre){
        return bookRepository.findByNameContainingAndAuthorContainingAndGenreContainingIgnoreCase(name,author,genre)
                .stream().map(bookMapper::toDto)
                .collect(Collectors.toList());
    }

    public boolean sold(Long id, int quantity){
        Book book = findById(id);
        if(quantity > book.getQuantity()){
            book.setQuantity(book.getQuantity()-quantity);
            bookRepository.save(book);
            return true;
        }
        else return false;
    }
}
