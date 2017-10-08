package com.rr;

import com.rr.model.Note;
import com.rr.model.NoteRepository;
import com.rr.model.User;
import com.rr.model.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class NotesExampleApplication {
    @Bean
    CommandLineRunner init(UserRepository userRepository, NoteRepository noteRepository) {
        return (evt) -> {
            User user = new User();
            user.setUsername("user");
            user.setPassword("123");
            userRepository.save(user);
            Note note = new Note();
            note.setUser(user);
            note.setText("this is a note0 of user");
            noteRepository.save(note);
            note = new Note();
            note.setUser(user);
            note.setText("this is a note1 of user");
            noteRepository.save(note);

            user = new User();
            user.setUsername("user0");
            user.setPassword("000");
            userRepository.save(user);
            note = new Note();
            note.setUser(user);
            note.setText("this is a note of user0");
            noteRepository.save(note);
        };
    }

    public static void main(String[] args) {
        SpringApplication.run(NotesExampleApplication.class, args);
    }
}
