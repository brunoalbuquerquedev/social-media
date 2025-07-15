package project.social.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.core.codec.CodecException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import project.social.dto.config.StandardErrorDto;
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
    public ResponseEntity<StandardErrorDto> invalidRequestData(InvalidRequestDataException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Insufficient data for the request.";

        StandardErrorDto error = new StandardErrorDto(
                String.valueOf(Instant.now().toEpochMilli()),
                status.value(),
                message,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(MessageSendException.class)
    public ResponseEntity<StandardErrorDto> messageSend(MessageSendException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.BAD_REQUEST;
        String message = "Message send error.";

        StandardErrorDto error = new StandardErrorDto(
                String.valueOf(Instant.now().toEpochMilli()),
                status.value(),
                message,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ObjectAlreadyExistsException.class)
    public ResponseEntity<StandardErrorDto> objectAlreadyExists(ObjectAlreadyExistsException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        String message = "Object already exists.";

        StandardErrorDto error = new StandardErrorDto(
                String.valueOf(Instant.now().toEpochMilli()),
                status.value(),
                message,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @ExceptionHandler(ObjectNotFoundException.class)
    public ResponseEntity<StandardErrorDto> objectNotFound(ObjectNotFoundException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.NOT_FOUND;
        String message = "Not found.";

        StandardErrorDto error = new StandardErrorDto(
                String.valueOf(Instant.now().toEpochMilli()),
                status.value(),
                message,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }
}
