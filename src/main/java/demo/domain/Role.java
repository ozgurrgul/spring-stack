package demo.domain;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    @Column(unique=true)
    public String roleName; // ADMIN | USER | ...

    protected Role() {}

    public Role(String roleName) {
        this.roleName = roleName;
    }
}