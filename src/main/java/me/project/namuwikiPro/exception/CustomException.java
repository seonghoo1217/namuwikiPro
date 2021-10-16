package me.project.namuwikiPro.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CustomException extends Exception{

    private String errorMessage;

    public CustomException(String 중복_에러, Exception e) {
        
    }
}
