package me.project.namuwikiPro.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@SequenceGenerator(
        name = "Describe_SEQ_Generate",
        sequenceName = "Describe_SEQ",
        initialValue = 1,allocationSize = 50
)

@Entity @Getter @Setter
public class Describe extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "Describe_SEQ_Generate")
    @Column(name = "describe_id")
    private Long id;

    private String describeArea;

    @OneToMany(mappedBy = "describe")
    private List<DescribeList> describeLists=new ArrayList<>();

}
