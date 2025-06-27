package project.social.config;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import project.social.services.exceptions.*;

import java.time.Instant;

@ControllerAdvice
public class ExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(ObjectNotFoundException.class)
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

    @org.springframework.web.bind.annotation.ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<StandardError> userAlreadyExists(UserAlreadyExistsException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        String message = "User already exists";

        StandardError error = new StandardError(
                String.valueOf(Instant.now().toEpochMilli()),
                status.value(),
                message,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IncorrectPasswordException.class)
    public ResponseEntity<StandardError> incorrectPassword(IncorrectPasswordException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String message = "Incorrect password.";

        StandardError error = new StandardError(
                String.valueOf(Instant.now().toEpochMilli()),
                status.value(),
                message,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<StandardError> invalidToken(InvalidTokenException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String message = "Invalid token.";

        StandardError error = new StandardError(
                String.valueOf(Instant.now().toEpochMilli()),
                status.value(),
                message,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(IllegalFollowingArgumentException.class)
    public ResponseEntity<StandardError> illegalFollowingArgument(IllegalFollowingArgumentException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.UNAUTHORIZED;
        String message = "Invalid argument.";

        StandardError error = new StandardError(
                String.valueOf(Instant.now().toEpochMilli()),
                status.value(),
                message,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(FollowAlreadyExistsException.class)
    public ResponseEntity<StandardError> followAlreadyExists(FollowAlreadyExistsException e, HttpServletRequest request) {

        HttpStatus status = HttpStatus.CONFLICT;
        String message = "Cannot follow user twice.";

        StandardError error = new StandardError(
                String.valueOf(Instant.now().toEpochMilli()),
                status.value(),
                message,
                e.getMessage(),
                request.getRequestURI()
        );
        return ResponseEntity.status(status).body(error);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(InvalidRequestDataException.class)
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
}
