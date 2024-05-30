package org.kevin.dto.response;
import lombok.*;

@Builder
@Getter @Setter
@AllArgsConstructor @NoArgsConstructor
public class WebResponse<T> {
    private int status;
    private String message;
    private PagingResponse paging;
    private T data;
}
