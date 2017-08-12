package demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Role extends BaseDomain {

    @Column(unique=true)
    private String roleName; // ADMIN | USER | ...

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }
}