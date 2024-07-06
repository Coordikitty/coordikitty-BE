package Coordinate.coordikittyBE.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
@AllArgsConstructor
public class CoordikittyException extends RuntimeException{
    private final ErrorType errorType;

    public CoordikittyException(final ErrorType errorType, final Throwable cause){
        super(errorType.name(), cause);
        this.errorType = errorType;
    }

    public HttpStatusCode getStatusCode(){
        return HttpStatus.valueOf(errorType.getStatusCode());
    }

    public String getErrorCode(){
        return errorType.getErrorCode();
    }

    @Override
    public String getMessage(){
        return errorType.getMessage();
    }

}
