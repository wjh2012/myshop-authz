package ggomg.myshopauthz.authority;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class User {

    @Id
    private Long Id;

    @Column(name = "role")
    private String role;

}
