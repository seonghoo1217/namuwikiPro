package me.project.namuwikiPro.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.FetchType.LAZY;

@Entity @Getter @Setter

@SequenceGenerator(
        name = "LatelyFeed_SEQ_Generate",
        sequenceName = "LatelyFeed_SEQ",
        initialValue = 1,allocationSize = 50
)
public class LatelyFeed extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
    generator = "LatelyFeed_SEQ_Generate")
    @Column(name = "lately_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "board_id")
    private Board board;

}
