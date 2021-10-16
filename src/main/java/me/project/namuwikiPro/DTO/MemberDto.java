package me.project.namuwikiPro.DTO;

import lombok.*;
import me.project.namuwikiPro.domain.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;
@Getter @Setter
@Data
@NoArgsConstructor
public class MemberDto {


    private Long id;


    private String realname;

    @NotBlank(message = "이메일을 입력해주세요")
    @Email(message = "올바른 이메일형태로 입력을 해주세요")
    private String username;

    @NotBlank(message = "비밀번호를 입력해주세요")
    private String password;

    private String age;

    private String street;

    private String memberType;


    @Builder
    public MemberDto(Long id,String realname, String username, String password,String street,String age,String memberType) {
        this.id=id;
        this.realname = realname;
        this.username = username;
        this.password = password;
        this.street=street;
        this.age=age;
        this.memberType=memberType;
    }

    public Member toEntity(){
        return Member.builder()
                .id(id)
                .username(username)
                .password(password)
                .realname(realname)
                .age(age)
                .street(street)
                .memberType(memberType)
                .build();
    }


}
