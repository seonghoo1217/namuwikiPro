package me.project.namuwikiPro.principal;

import me.project.namuwikiPro.DTO.MemberDto;
import me.project.namuwikiPro.domain.Member;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

public class AccountContext implements UserDetails {


    private Long id;
    private String username;
    private String password;
    private String memberType;

    public AccountContext(Member member) {
        this.id=member.getId();
        this.username = member.getUsername();
        this.password = member.getPassword();
        this.memberType = member.getMemberType();
    }


    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return Collections.singletonList(new SimpleGrantedAuthority(this.memberType));
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }


    public Long getId(){return this.id;}

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    //계정비밀번호만료여부
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    //계정활성화여부
    public boolean isEnabled() {
        return true;
    }
}
