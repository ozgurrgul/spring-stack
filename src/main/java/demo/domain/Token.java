package demo.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
public class Token extends BaseDomain {

    @Column(unique = true)
    private String tokenValue;

    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER"))
    private User user;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}