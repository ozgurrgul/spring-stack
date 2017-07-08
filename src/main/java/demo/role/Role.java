package demo.role;

import javax.persistence.*;

@Entity
public class Role {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    public Long id;

    @Column(unique=true)
    public String roleName; // admin | jury_sectoral | jury_academic | sectoral | academician | folk

    protected Role() {}

    public Role(String roleName) {
        this.roleName = roleName;
    }
}