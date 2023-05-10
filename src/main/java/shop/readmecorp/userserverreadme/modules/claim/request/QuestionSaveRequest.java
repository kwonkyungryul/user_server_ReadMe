package shop.readmecorp.userserverreadme.modules.claim.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.modules.common.custom_annotation.ValueOfEnum;
import shop.readmecorp.userserverreadme.modules.common.jpa.RoleType;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class QuestionSaveRequest {

    @ValueOfEnum(enumClass = RoleType.class, message = "권한에 이상이 있습니다.")
    private String role;

    @NotNull(message = "출판사 정보값이 없습니다.")
    private Integer publisherId;

    @NotBlank(message = "제목을 입력해주세요.")
    private String title;

    @NotBlank(message = "내용을 입력해주세요.")
    private String content;

}
