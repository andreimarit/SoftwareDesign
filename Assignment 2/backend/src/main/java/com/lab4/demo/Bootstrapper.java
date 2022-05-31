package com.lab4.demo;

import com.lab4.demo.book.BookRepository;
import com.lab4.demo.book.BookService;
import com.lab4.demo.book.model.Book;
import com.lab4.demo.book.model.dto.BookDTO;
import com.lab4.demo.security.AuthService;
import com.lab4.demo.security.dto.SignupRequest;
import com.lab4.demo.user.RoleRepository;
import com.lab4.demo.user.UserRepository;
import com.lab4.demo.user.model.ERole;
import com.lab4.demo.user.model.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
@RequiredArgsConstructor
public class Bootstrapper implements ApplicationListener<ApplicationReadyEvent> {

    private final RoleRepository roleRepository;

    private final UserRepository userRepository;

    private final AuthService authService;

    private final BookRepository bookRepository;

    private final BookService bookService;

    @Value("${app.bootstrap}")
    private Boolean bootstrap;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        if (bootstrap) {
            bookRepository.deleteAll();
            userRepository.deleteAll();
            roleRepository.deleteAll();
            for (ERole value : ERole.values()) {
                roleRepository.save(
                        Role.builder()
                                .name(value)
                                .build()
                );
            }
            authService.register(SignupRequest.builder()
                    .email("alex@email.com")
                    .username("alex")
                    .password("WooHoo1!")
                    .roles(Set.of("ADMIN"))
                    .build());
            authService.register(SignupRequest.builder()
                    .email("alex1@email.com")
                    .username("alex1")
                    .password("WooHoo1!")
                    .roles(Set.of("REGULAR"))
                    .build());
            bookRepository.save(Book.builder()
                    .name("pungutaCu2bani")
                    .author("ion")
                    .genre("poveste")
                    .price(10)
                    .quantity(5)
                    .build());

            bookRepository.save(Book.builder()
                    .name("ursuPacalitDeVulpe")
                    .author("ion")
                    .genre("poveste2")
                    .price(10)
                    .quantity(0)
                    .build());

            bookRepository.save(Book.builder()
                    .name("GatamFacultatea")
                    .author("student")
                    .genre("SF")
                    .price(10)
                    .quantity(0)
                    .build());

        }
    }
}
