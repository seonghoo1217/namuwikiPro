package me.project.namuwikiPro.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@SequenceGenerator(
        name = "Board_SEQ_Generate",
        sequenceName = "Board_SEQ",
        initialValue = 1,allocationSize = 50
)

@Entity @Getter @Setter
public class Board extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "Board_SEQ_Generate")
    @Column(name = "board_id")
    private Long id;

    private String title;
    private String content;

    @OneToMany(mappedBy = "board")
    private List<LatelyFeed> latelyFeeds=new ArrayList<>();
}
