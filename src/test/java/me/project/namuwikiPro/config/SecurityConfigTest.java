package me.project.namuwikiPro.config;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootTest
class SecurityConfigTest {
    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    public void passwordTest(){
        String passwd="1111";

        String encodepw=passwordEncoder.encode(passwd);

        System.out.println("encodepw = " + encodepw);

        boolean matchResult=passwordEncoder.matches(passwd,encodepw);
        System.out.println("matchResult = " + matchResult);
    }
}