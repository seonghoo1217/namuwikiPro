package me.project.namuwikiPro.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import me.project.namuwikiPro.DTO.board.BoardDto;
import org.apache.tomcat.jni.Local;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;

import javax.persistence.*;

import java.time.LocalDateTime;

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
    @Column(name = "board_title",unique = true)
    private String title;

    @Lob
    @Column(name = "board_content")
    private String content;

    @Column(name = "board_writer")
    private String writer;

    @Column(name = "galleryId")
    private Long galleryId;

    @JoinColumn(name = "category_id")
    @ManyToOne(fetch = LAZY)
    private Category category;

    @JoinColumn(name = "lately_id")
    @ManyToOne(fetch = LAZY)
    private LatelyFeed latelyFeed;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="member_id")
    private Member member;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDate;

    @LastModifiedBy
    private LocalDateTime modifiedDate;



    @Builder
    public Board(Long id,String title,String content,String writer,Long galleryId,LocalDateTime createDate, LocalDateTime modifiedDate,Category category,LatelyFeed latelyFeed,Member member) {
        this.id=id;
        this.title=title;
        this.content=content;
        this.writer=writer;
        this.galleryId=galleryId;
        this.createDate = createDate;
        this.modifiedDate = modifiedDate;
        this.category=category;
        this.latelyFeed=latelyFeed;
        this.member=member;
    }

    public Board() {

    }

    public void update(String title,String content){
        this.title=title;
        this.content=content;
    }

    public BoardDto toDto(){
        return BoardDto.builder()
                .id(id)
                .title(title)
                .content(content)
                .category(category)
                .galleryId(galleryId)
                .latelyFeed(latelyFeed)
                .writer(writer)
                .member(member)
                .build();
    }

}

