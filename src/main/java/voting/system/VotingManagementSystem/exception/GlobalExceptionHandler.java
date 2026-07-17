package voting.system.VotingManagementSystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFoundException(ResourceNotFoundException ex) {
        ErrorResponse errorPayload = new ErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                "Not Found",
                ex.getMessage(),
                ex.getClassName()
        );
        return new ResponseEntity<>(errorPayload, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(IllegalEnumException.class)
    public ResponseEntity<ErrorResponse> handleEnumNotFoundException(IllegalEnumException ex) {
        ErrorResponse errorPayload = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Argument",
                ex.getMessage(),
                ex.getClassName()
        );
        return new ResponseEntity<>(errorPayload, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ErrorResponse> handleTypeMismatch(MethodArgumentTypeMismatchException ex) {
        String message = "Invalid request parameter.";

        // Check if the mismatched parameter is an Enum
        if (ex.getRequiredType() != null && ex.getRequiredType().isEnum()) {
            String enumName = ex.getRequiredType().getSimpleName();
            String allowedValues = java.util.Arrays.toString(ex.getRequiredType().getEnumConstants());

            message = String.format("Invalid value '%s' for %s. Allowed values are: %s",
                    ex.getValue(), enumName, allowedValues);
        }

        ErrorResponse errorPayload = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Request",
                message,
                ex.getRequiredType().getSimpleName()
        );

        return new ResponseEntity<>(errorPayload, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });

        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException ex) {
        ErrorResponse errorPayload = new ErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Bad Argument",
                ex.getMessage()

        );

        return new ResponseEntity<>(errorPayload, HttpStatus.BAD_REQUEST);
    }

}
