package me.project.namuwikiPro.DTO.board;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import me.project.namuwikiPro.domain.Board;

@Getter
@NoArgsConstructor
@Builder
public class BoardUpdateDto {

    private Long id;
    private String title;
    private String content;

    @Builder
    public BoardUpdateDto (Long id,String title,String content){
        this.id=id;
        this.title=title;
        this.content=content;
    }
}
