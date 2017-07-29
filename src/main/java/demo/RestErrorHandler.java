package demo;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class RestErrorHandler {
 
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public FieldErrorListDTO processValidationError(MethodArgumentNotValidException ex) {

        BindingResult errors = ex.getBindingResult();
        FieldErrorListDTO fieldErrorListDTO = new FieldErrorListDTO();

        for (FieldError fieldError : errors.getFieldErrors()) {
            fieldErrorListDTO.addError(fieldError.getField(), fieldError.getDefaultMessage());
        }

        return fieldErrorListDTO;
    }

    class FieldErrorListDTO {

        public List<FieldErrorResponseDTO> fields = new ArrayList<>();
        public String error_code = "FORM_VALIDATION_ERROR";

        public void addError(String field, String message) {
            fields.add(new FieldErrorResponseDTO(field, message));
        }
    }

    class FieldErrorResponseDTO {

        public String field;
        public String message;

        public FieldErrorResponseDTO(String field, String message) {
            this.field = field;
            this.message = message;
        }

    }
}