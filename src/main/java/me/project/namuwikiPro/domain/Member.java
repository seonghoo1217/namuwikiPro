package me.project.namuwikiPro.domain;

import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import static javax.persistence.FetchType.*;

@Entity @Getter @Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@ToString
@SequenceGenerator(
        name = "Member_SEQ_Generate",
        sequenceName = "Member_SEQ",
        initialValue = 1,allocationSize = 50
)
public class Member {
    @Id @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "Member_SEQ_Generate")
    @Column(name = "member_id")
    private Long id;

    @Column(name = "email",unique = true)
    private String username;

    @Column(name = "username")
    private String realname;

    @Column(name = "password")
    private String password;

    @Column(name = "street")
    private String street;

    @Column(name = "age")
    private String age;

    @Column(name = "member_type")
    private String memberType;



    @Builder
    public Member(Long id,String realname, String username, String password,String street,String age,String memberType) {
        this.id=id;
        this.realname = realname;
        this.username = username;
        this.password = password;
        this.street=street;
        this.age=age;
        this.memberType=memberType;
    }
}
