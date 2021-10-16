package me.project.namuwikiPro.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity @Getter @Setter

@SequenceGenerator(
        name = "DescribeList_SEQ_Generate",
        sequenceName = "DescribeList_SEQ",
        initialValue = 1,allocationSize = 50
)
public class DescribeList extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator ="DescribeList_SEQ_Generate" )
    @Column(name = "dblist_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "describe_id")
    private Describe describe;
}
