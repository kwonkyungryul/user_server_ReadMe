package shop.readmecorp.userserverreadme.common.metadata;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import shop.readmecorp.userserverreadme.common.enums.MainTabType;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MainTab {
    private String mainName;

    private String mainRequestName;
}
