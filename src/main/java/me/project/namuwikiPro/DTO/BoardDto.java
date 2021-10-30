package me.project.namuwikiPro.DTO;


import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import me.project.namuwikiPro.domain.Board;
import me.project.namuwikiPro.domain.Category;
import me.project.namuwikiPro.domain.LatelyFeed;

@Getter
@NoArgsConstructor
public class BoardDto {


    private Long id;
    private String title;
    private String writer;
    private String content;
    private Category category;
    private LatelyFeed latelyFeed;

    @Builder
    public BoardDto(Long id,String title,String writer,String content,Category category,LatelyFeed latelyFeed){
        this.id=id;
        this.title=title;
        this.writer=writer;
        this.content=content;
        this.category=category;
        this.latelyFeed=latelyFeed;
    }



    public Board toEntity(){
        return Board.builder()
                .id(id)
                .title(title)
                .writer(writer)
                .content(content)
                .category(category)
                .latelyFeed(latelyFeed)
                .build();
    }


}
