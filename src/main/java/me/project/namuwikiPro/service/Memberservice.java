package me.project.namuwikiPro.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import me.project.namuwikiPro.DTO.MemberDto;
import me.project.namuwikiPro.domain.Member;
import me.project.namuwikiPro.principal.AccountContext;
import me.project.namuwikiPro.repository.MemberRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.management.relation.Role;
import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Log4j2
public class Memberservice implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Transactional
    public Long createUser  (MemberDto dto) {
         BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
         dto.setPassword(passwordEncoder.encode(dto.getPassword()));

        if(dto.getUsername().equals("seonghoo1217@naver.com")){
             dto.setMemberType("ADMIN");
            }else {
             dto.setMemberType("USER");
            }

             return memberRepository.save(dto.toEntity()).getId();
    }

    public boolean checkEmailDuplicate(String username){
        return memberRepository.existsByUsername(username);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Member member= memberRepository.findByUsername(username).get();

        return new AccountContext(member);
    }
}

