package ggomg.myshopauthz.user;

import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class UserDTO {

    @NotNull(message = "id can't be null")
    private Long id;
    @NotNull(message = "role can't be null")
    private Role role;
}
