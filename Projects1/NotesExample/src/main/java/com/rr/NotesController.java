package com.rr;

import com.rr.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.Optional;

/**
 * @author roman.rudenko on 25-May-16.
 */
@RestController
@RequestMapping("{username}/notes")
public class NotesController {

    @Autowired
    UserRepository userRepository;
    @Autowired
    NoteRepository noteRepository;

    @RequestMapping
    public Collection<Note> readNotes(@PathVariable String username) {
        return noteRepository.findByUserUsername(username);
    }

    @RequestMapping(method = RequestMethod.POST)
    public void addNote(@PathVariable String username, @RequestBody Note note){
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UserNotFoundException(username));
        note.setUser(user);
        noteRepository.save(note);
    }
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class UserNotFoundException extends RuntimeException{
    public UserNotFoundException(String username) {
        super("could not find user '" + username + "'.");
    }
}
