package me.project.namuwikiPro.repository;

import me.project.namuwikiPro.domain.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import javax.persistence.PersistenceContext;
import java.util.Optional;

@Repository
public interface MemberRepository extends JpaRepository<Member,Long> {
    Optional<Member> findByUsername (String username) throws UsernameNotFoundException;

}

