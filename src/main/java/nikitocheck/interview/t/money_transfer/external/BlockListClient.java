package nikitocheck.interview.t.money_transfer.external;

import nikitocheck.interview.t.money_transfer.domain.User;

import java.util.Set;

public interface BlockListClient {

    Set<User> getBlockedUsers();

}
