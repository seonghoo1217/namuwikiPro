package me.project.namuwikiPro.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter
@SequenceGenerator(
        name = "Board_SEQ_Generate",
        sequenceName = "Board_SEQ",
        initialValue = 1,allocationSize = 50
)
public class CreateBoard extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator ="Board_SEQ_Generate" )
    @Column(name = "ctboard_id")
    private Long id;

    private String title;
    private String content;
}
