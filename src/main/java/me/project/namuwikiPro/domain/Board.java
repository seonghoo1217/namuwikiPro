package me.project.namuwikiPro.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.project.namuwikiPro.DTO.board.BoardDto;

import javax.persistence.*;

import static javax.persistence.FetchType.*;

@SequenceGenerator(
        name = "Board_SEQ_Generate",
        sequenceName = "Board_SEQ",
        initialValue = 1
)

@Entity @Getter @Setter
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "Board_SEQ_Generate")
    @Column(name = "board_id")
    private Long id;

    @Lob
    @Column(name = "board_title")
    private String title;

    @Lob
    @Column(name = "Board_content")
    private String content;

    @JoinColumn(name = "category_id")
    @ManyToOne(fetch = LAZY)
    private Category category;

    @JoinColumn(name = "lately_id")
    @ManyToOne(fetch = LAZY)
    private LatelyFeed latelyFeed;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    private Member member;


    @Builder
    public Board(Long id,String title,String content,Category category,LatelyFeed latelyFeed) {
        this.id=id;
        this.title=title;
        this.content=content;
        this.category=category;
        this.latelyFeed=latelyFeed;
    }

    public Board() {

    }

    public void update(Long id,String title,String content){
        this.id=id;
        this.title=title;
        this.content=content;
    }

    public BoardDto toDto(){
        return BoardDto.builder()
                .id(id)
                .title(title)
                .content(content)
                .category(category)
                .latelyFeed(latelyFeed)
                .build();
    }

}

