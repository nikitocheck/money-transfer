package nikitocheck.interview.t.moneytransfer.repository;

import nikitocheck.interview.t.moneytransfer.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UsersRepository extends JpaRepository<User, UUID> {

}
