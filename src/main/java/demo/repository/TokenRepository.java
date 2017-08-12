package demo.repository;

/**
 * Created by ozgur on 7/5/17.
 */

import demo.domain.Token;
import org.springframework.data.repository.CrudRepository;

public interface TokenRepository extends CrudRepository<Token, String> {
    Token findTokenByTokenValue(String tokenValue);
}
