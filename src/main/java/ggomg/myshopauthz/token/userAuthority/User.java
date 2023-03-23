package ggomg.myshopauthz.token.userAuthority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id
    private Long Id;

    @Column(name = "role")
    @Enumerated
    private Role role;

    public static User of(Long id, Role role) {
        return new User(id, role);
    }
}
