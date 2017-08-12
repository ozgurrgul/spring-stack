package demo.service;

import demo.domain.SecurityHistory;
import demo.domain.User;
import demo.repository.SecurityHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;

/**
 * Created by ozgur on 8/8/17.
 */
@Service
public class SecurityHistoryService {

    @Autowired
    SecurityHistoryRepository securityHistoryRepository;

    @Transactional
    public void saveLoginHistory(User user, String ip, String userAgent) {
        saveAuthHistory(user, ip, userAgent, SecurityHistory.Type.LOGIN);
    }

    @Transactional
    public void saveRegisterHistory(User user, String ip, String userAgent) {
        saveAuthHistory(user, ip, userAgent, SecurityHistory.Type.REGISTER);
    }

    @Transactional
    private void saveAuthHistory(User user, String ip, String userAgent, SecurityHistory.Type type) {

        SecurityHistory securityHistory = new SecurityHistory();
        securityHistory.setUser(user);
        securityHistory.setType(type);
        securityHistory.setMessage(ip + " : " + userAgent);

        securityHistoryRepository.save(securityHistory);
    }

    @Transactional
    public void savePasswordChangeHistory(User user, String ip, String userAgent) {

        SecurityHistory securityHistory = new SecurityHistory();
        securityHistory.setUser(user);
        securityHistory.setType(SecurityHistory.Type.PASSWORD_CHANGE);
        securityHistory.setMessage("Password changed");
        securityHistory.setMessage(ip + " : " + userAgent);

        securityHistoryRepository.save(securityHistory);
    }


    @Transactional
    public Page<SecurityHistory> findByUser(User user, Pageable pageable) {
        return securityHistoryRepository.findByUser(user, pageable);
    }
}
