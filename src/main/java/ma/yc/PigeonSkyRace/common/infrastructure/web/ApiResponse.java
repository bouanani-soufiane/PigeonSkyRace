package ma.yc.PigeonSkyRace.common.infrastructure.web;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class ApiResponse<ResponseDto> {

    private final ResponseDto data;
    private final String message;
    private final HttpStatus status;

    public ApiResponse(ResponseDto data, String message, HttpStatus status) {
        this.data = data;
        this.message = message;
        this.status = status;
    }

}