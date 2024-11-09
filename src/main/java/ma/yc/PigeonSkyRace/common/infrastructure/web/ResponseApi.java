package ma.yc.PigeonSkyRace.common.infrastructure.web;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseApi<T> {

    private final T data;
    private final String message;
    private final int status;

    public ResponseApi(T data, String message, HttpStatus status) {
        this.data = data;
        this.message = message;
        this.status = status.value();
    }

    public static <T> ResponseApi<T> success(T data, String message) {
        return new ResponseApi<>(data, message, HttpStatus.OK);
    }

    public static <T> ResponseApi<T> created(T data, String message) {
        return new ResponseApi<>(data, message, HttpStatus.CREATED);
    }
}
