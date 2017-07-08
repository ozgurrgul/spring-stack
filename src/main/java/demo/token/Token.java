package demo.token;

import demo.user.User;

import javax.persistence.*;

@Entity
public class Token {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;
    public String tokenValue;

    @ManyToOne(cascade = {CascadeType.ALL})
    private User user;

    protected Token() {
    }

    public Token(String tokenValue) {
        this.tokenValue = tokenValue;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}