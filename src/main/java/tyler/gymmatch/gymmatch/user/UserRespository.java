package tyler.gymmatch.gymmatch.user;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public class UserRespository extends JpaSpecificationExecutor<User>, JpaRepository<User, String>  {}
