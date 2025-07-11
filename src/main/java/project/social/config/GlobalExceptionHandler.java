package project.social.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import project.social.exceptions.auth.InvalidRequestDataException;
import project.social.exceptions.base.AuthenticationException;
import project.social.exceptions.base.MessageSendException;
import project.social.exceptions.base.ObjectAlreadyExistsException;
import project.social.exceptions.base.ObjectNotFoundException;

import java.time.Instant;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AuthenticationException.class)
    public ResponseEntity<String> authenticationException(AuthenticationException e) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(e.getMessage());
    }

    @ExceptionHandler(InvalidRequestDataException.class)
    public ResponseEntity<StandardError> invalidRequestData(InvalidRequestDataException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Insufficient data for the request.";

        StandardError error = new StandardError(
                String.valueOf(Instant.now().toEpochMilli()),
                status.value(),
                message,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MessageSendException.class)
    public ResponseEntity<StandardError> messageSend(MessageSendException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Message send error.";

        StandardError error = new StandardError(
                String.valueOf(Instant.now().toEpochMilli()),
                status.value(),
                message,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<StandardError> objectAlreadyExists(ObjectAlreadyExistsException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        String message = "Object already exists";

        StandardError error = new StandardError(
                String.valueOf(Instant.now().toEpochMilli()),
                status.value(),
                message,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "Not found";

        StandardError error = new StandardError(
                String.valueOf(Instant.now().toEpochMilli()),
                status.value(),
                message,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }
}
