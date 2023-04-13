package ggomg.myshopauthz.token.userAuthority;

import ggomg.myshopauthz.token.userAuthority.role.Role;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {

    @NotNull(message = "id can't be null")
    private Long id;
    @NotNull(message = "role can't be null")
    private Role role;
}
