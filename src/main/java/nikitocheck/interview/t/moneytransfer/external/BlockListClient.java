package nikitocheck.interview.t.moneytransfer.external;

import nikitocheck.interview.t.moneytransfer.domain.User;

import java.util.List;


public interface BlockListClient {

    List<User> getBlockedUsers();

}
