package nikitocheck.interview.t.moneytransfer.external;

import nikitocheck.interview.t.moneytransfer.domain.MoneyTransfer;

import java.time.LocalDateTime;

public interface AuditClient {
    void reportAuditEvent(MoneyTransfer moneyTransfer, LocalDateTime timestampUtc) throws ServiceNotAvailableException;
}
