package mk.ukim.finki.wp.lab.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND)
public class ErrorId extends RuntimeException{

    public ErrorId(Long id) {
        super(String.format("Course %d was not found", id));
    }

}
