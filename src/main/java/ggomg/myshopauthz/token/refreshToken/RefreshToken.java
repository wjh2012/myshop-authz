package ggomg.myshopauthz.token.refreshToken;

import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;


@Getter
@RedisHash("memberId")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {
    @Id
    private Long id;
    private String refreshToken;

    public static RefreshToken of(Long userId, String refreshToken) {
        return new RefreshToken(userId, refreshToken);
    }

}
