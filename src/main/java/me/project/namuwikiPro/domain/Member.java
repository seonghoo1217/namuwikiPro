package me.project.namuwikiPro.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Column(name = "member_unique")
    private int unique;

    @Column(name = "board_member")
    @OneToMany(mappedBy = "member")
    private List<Board> boards=new ArrayList<>();




    @Builder
    public Member(Long id,String realname, String username, String password,String street,String age,String memberType,int unique) {
        this.id=id;
        this.realname = realname;
        this.username = username;
        this.password = password;
        this.street=street;
        this.age=age;
        this.unique=unique;
        this.memberType=memberType;
    }
}

