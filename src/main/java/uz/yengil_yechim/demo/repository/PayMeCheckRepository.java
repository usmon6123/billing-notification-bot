package uz.yengil_yechim.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.yengil_yechim.demo.entity.PayMeCheck;

public interface PayMeCheckRepository extends JpaRepository<PayMeCheck,Long> {
}
