package ggomg.myshopauthz.token.userAuthority;

import ggomg.myshopauthz.token.userAuthority.role.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDTO {
    private Long id;
    private Role role;
}
