package me.project.namuwikiPro.DTO.board;

import lombok.*;
import me.project.namuwikiPro.domain.Board;

@Data
@NoArgsConstructor
@Builder
public class BoardUpdateDto {

    private Long id;
    private String content;

    @Builder
    public BoardUpdateDto (Long id,String content){
        this.id=id;
        this.content=content;
    }
}
