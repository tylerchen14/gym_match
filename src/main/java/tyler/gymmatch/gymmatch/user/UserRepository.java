package tyler.gymmatch.gymmatch.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserRepository
    extends JpaSpecificationExecutor<User>, JpaRepository<User, String> {

  boolean existsByAccount(String account);

  boolean existsByAccountAndIdNot(String account, String id);
}
;
