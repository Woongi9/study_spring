package hello.exception.exceptionhandler;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * packageName :  hello.exception.exceptionhandler
 * fileName : ErrorResult
 * author :  eomjin-ung
 * date : 2023/03/12
 * description :
 * ===========================================================
 * DATE                 AUTHOR              NOTE
 * -----------------------------------------------------------
 * 2023/03/12           eomjin-ung          init
 */

@Data
@AllArgsConstructor
public class ErrorResult {

    private String code;
    private String message;
}
