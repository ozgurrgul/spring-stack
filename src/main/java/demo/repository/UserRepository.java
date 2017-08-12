package demo.repository;

/**
 * Created by ozgur on 7/5/17.
 */

import demo.domain.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {

    User getByEmailAndPassword(String email, String password);
    User getByEmail(String email);

}
