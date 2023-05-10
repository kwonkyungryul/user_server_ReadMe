package shop.readmecorp.userserverreadme.modules.common.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class ResponseDTO<T> {
    private Integer code;

    private String msg;

    private T data;
}
