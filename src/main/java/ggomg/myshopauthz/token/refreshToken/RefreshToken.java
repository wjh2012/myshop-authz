package ggomg.myshopauthz.token.refreshToken;

import com.sun.istack.NotNull;
import javax.persistence.Entity;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;


@Getter
@Entity
@RedisHash(value = "RefreshToken")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {
    @Id
    private Long userId;
    @NotNull
    private String token;

    public static RefreshToken of(Long id, String token) {
        return new RefreshToken(id, token);
    }
}
