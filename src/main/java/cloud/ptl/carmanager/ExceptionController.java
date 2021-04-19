package cloud.ptl.carmanager;

import lombok.Data;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@ControllerAdvice
public class ExceptionController {
    @Data
    private static class ExceptionWrapper{
        private String message;
        private String errorCode;
    }

    @ExceptionHandler(ProcessingException.class)
    public ExceptionWrapper handle(ProcessingException ex){
        ExceptionWrapper ew = new ExceptionWrapper();
        ew.setErrorCode("241");
        ew.setMessage(
                ex.getMessage()
        );
        return ew;
    }
}
