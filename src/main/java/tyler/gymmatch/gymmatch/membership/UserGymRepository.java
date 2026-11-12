package tyler.gymmatch.gymmatch.membership;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface UserGymRepository
    extends JpaSpecificationExecutor<UserGym>, JpaRepository<UserGym, String> {

        long countByGym_Id(String gymId);
    }
