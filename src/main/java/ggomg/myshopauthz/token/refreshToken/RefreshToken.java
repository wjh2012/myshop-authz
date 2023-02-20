package ggomg.myshopauthz.token.refreshToken;

import com.sun.istack.NotNull;
import java.util.UUID;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.redis.core.RedisHash;


@Getter
@Entity
@RedisHash
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class RefreshToken {
    @Id
    @GeneratedValue
    private String redisKey;
    @NotNull
    private String token;

    public static RefreshToken of(Long userId, String token) {
        String redisKey = userId.toString() + UUID.randomUUID();
        return new RefreshToken(redisKey, token);
    }

}
