package ch.kchmiel.airpollution.service;

import ch.kchmiel.airpollution.config.AdminConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MailCreatorService {

    private AdminConfig adminConfig;

    public String buildEmail(String message) {
        return "mail";
    }
}
