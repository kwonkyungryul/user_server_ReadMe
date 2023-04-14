package shop.readmecorp.userserverreadme.modules.membership.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import shop.readmecorp.userserverreadme.modules.membership.entity.Membership;

public interface MembershipRepository extends JpaRepository<Membership, Integer> {
}
