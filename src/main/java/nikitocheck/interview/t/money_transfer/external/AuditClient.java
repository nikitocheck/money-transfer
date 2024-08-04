package nikitocheck.interview.t.money_transfer.external;

import nikitocheck.interview.t.money_transfer.domain.MoneyTransfer;

import java.time.LocalDateTime;

public interface AuditClient {
    void reportAuditEvent(MoneyTransfer moneyTransfer, LocalDateTime timestampUtc) throws ServiceNotAvailableException;
}
