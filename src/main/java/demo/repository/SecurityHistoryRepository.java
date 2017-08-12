package demo.repository;

/**
 * Created by ozgur on 7/5/17.
 */

import demo.domain.SecurityHistory;
import demo.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface SecurityHistoryRepository extends CrudRepository<SecurityHistory, String> {

    Page<SecurityHistory> findByUser(User user, Pageable pageable);

}
