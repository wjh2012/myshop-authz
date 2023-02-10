package ggomg.myshopauthz.token.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AccessRefreshTokenDTO {
    private String accessToken;
    private String refreshToken;
}
