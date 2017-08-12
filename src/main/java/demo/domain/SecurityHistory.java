package demo.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

/**
 * Created by ozgur on 7/23/17.
 */
@Data
@Entity
@NoArgsConstructor
public class SecurityHistory extends BaseDomain {

    public enum Type {
        REGISTER,
        LOGIN,
        PASSWORD_CHANGE
    }

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(foreignKey = @ForeignKey(name = "FK_USER"))
    private User user;

    private String message;

    @Enumerated(EnumType.STRING)
    private Type type;

    @Override
    public boolean equals(Object o) {
        return super.equals(o);
    }

}
