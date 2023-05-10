package shop.readmecorp.userserverreadme.modules.membership.service;

import org.springframework.stereotype.Service;
import shop.readmecorp.userserverreadme.modules.membership.entity.Membership;
import shop.readmecorp.userserverreadme.modules.membership.repository.MembershipRepository;

import java.util.Optional;

@Service
public class MembershipService {

    private final MembershipRepository membershipRepository;

    public MembershipService(MembershipRepository membershipRepository) {
        this.membershipRepository = membershipRepository;
    }

    public Optional<Membership> getMembership(Integer id) {
        return membershipRepository.findById(id);
    }
}
