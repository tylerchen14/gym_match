package tyler.gymmatch.gymmatch.gym;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface GymRepository extends JpaRepository<Gym, String>, JpaSpecificationExecutor<Gym> {}
