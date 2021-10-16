package me.project.namuwikiPro.DTO;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class MemberDtoTest {



    @Test
    public void 아이디_중복(){
        MemberDto dto=new MemberDto();
        MemberDto dto1 = dto;
        MemberDto dto2 = dto;
        dto1.setUsername("aaa");
        dto2.setUsername("aaa");
    }

}