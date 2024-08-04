package nikitocheck.interview.t.moneytransfer.service;

import nikitocheck.interview.t.moneytransfer.domain.MoneyTransfer;
import nikitocheck.interview.t.moneytransfer.domain.User;
import nikitocheck.interview.t.moneytransfer.external.AuditClient;
import nikitocheck.interview.t.moneytransfer.external.BlockListClient;
import nikitocheck.interview.t.moneytransfer.repository.UsersRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

@Service
public final class MoneyTransferService {

    public BlockListClient blockListClient;

    public UsersRepository usersRepository;
    public AuditClient auditClient;

    public static final Logger logger = LoggerFactory.getLogger(MoneyTransfer.class);


    public void transferMoney(MoneyTransfer moneyTransfer) {

        // для ускорения поиска
        var blockedUsers = new HashSet<>(blockListClient.getBlockedUsers());

        // проверяем что пользователи не заблокированы
        if (blockedUsers.contains(moneyTransfer.from()) || blockedUsers.contains(moneyTransfer.to())){
            logger.error("Money transfer is forbidden", new RuntimeException(String.format("money transfer is forbidden %s", moneyTransfer)));
        }

        // ищем пользователей в бд
        var from = getUser(moneyTransfer.from().getId());
        var to = getUser(moneyTransfer.to().getId());
        logger.info("users found");

        // обновляем счёт отправителя
        from.setMoney(from.getMoney() - moneyTransfer.money());
        saveUser(from);
        logger.info("from updated");

        // обновляем счёт получателя
        to.setMoney(to.getMoney() - moneyTransfer.money());
        saveUser(to);
        logger.info("to updated");

        // отправляем событие в аудит
        auditClient.reportAuditEvent(moneyTransfer, LocalDateTime.now());
        logger.info("audit sent");
    }

    @Transactional
    private User getUser(UUID id) {
       return   usersRepository.findAll().stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Transactional
    private void saveUser(User user) {
        usersRepository.save(user);
    }
}
