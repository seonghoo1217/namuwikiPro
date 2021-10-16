package me.project.namuwikiPro.domain;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.FetchType.LAZY;

@Entity @Getter @Setter

@SequenceGenerator(
        name = "Category_SEQ_Generate",
        sequenceName = "Category_SEQ",
        initialValue = 1,allocationSize = 50
)

public class Category extends BaseEntity{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "Category_SEQ_Generate")
    @Column(name = "category_id")
    private Long id;

    @ManyToOne(fetch = LAZY)
    @JoinColumn(name = "parent_cat")
    private Category parentCategory;

    @OneToMany(mappedBy = "parentCategory")
    @Column(name = "child_cat")
    private List<Category> childCategories=new ArrayList<>();
}
