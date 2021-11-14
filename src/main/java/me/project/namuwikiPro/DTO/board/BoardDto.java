package me.project.namuwikiPro.DTO.board;


import lombok.*;
import me.project.namuwikiPro.domain.Board;
import me.project.namuwikiPro.domain.Category;
import me.project.namuwikiPro.domain.LatelyFeed;
import me.project.namuwikiPro.domain.Member;

@Data
@NoArgsConstructor
public class BoardDto {


    private Long id;
    private String title;
    private String writer;
    private String content;
    private Long galleryId;
    private Category category;
    private LatelyFeed latelyFeed;
    private Member member;

    @Builder
    public BoardDto(Long id,String title,String writer,String content,Long galleryId,Category category,LatelyFeed latelyFeed,Member member){
        this.id=id;
        this.title=title;
        this.writer=writer;
        this.content=content;
        this.galleryId=galleryId;
        this.category=category;
        this.latelyFeed=latelyFeed;
        this.member=member;
    }



    public Board toEntity(){
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .category(category)
                .galleryId(galleryId)
                .latelyFeed(latelyFeed)
                .member(member)
                .build();
    }


}
