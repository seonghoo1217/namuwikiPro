package me.project.namuwikiPro.domain;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@SequenceGenerator(
        name = "File_SEQ_Generate",
        sequenceName = "File_SEQ",
        initialValue = 1
)
public class Files extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "file_id")
    private Long id;

    @Column(name = "filename")
    private String filename;

    @Column(name = "file_Oriname")
    private String fileOriName;

    @Column(name = "fileUrl")
    private String fileUrl;
}
