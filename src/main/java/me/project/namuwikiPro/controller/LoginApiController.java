package me.project.namuwikiPro.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.project.namuwikiPro.domain.Member;
import me.project.namuwikiPro.repository.MemberRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
@Slf4j
public class LoginApiController {

    private final MemberRepository memberRepository;

    @GetMapping("/loginuser/{email}/exist")
    public ResponseEntity<?> checkUsername(@PathVariable("email") String email){

        log.info("email={}", email);

        Optional<Member> findMember = memberRepository.findByUsername(email);

        if(!findMember.isPresent()){
            return new ResponseEntity<>(1, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(0, HttpStatus.OK);
        }
    }
}
