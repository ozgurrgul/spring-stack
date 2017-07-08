package demo.token;

/**
 * Created by ozgur on 7/5/17.
 */

import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, Long> {

    Token findTokenByTokenValue(String tokenValue);

}
