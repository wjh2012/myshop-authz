package ggomg.myshopauthz.tokenProvider.refreshToken;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RefreshTokenRepository extends CrudRepository<UserIdRefreshToken, Long> {
}
