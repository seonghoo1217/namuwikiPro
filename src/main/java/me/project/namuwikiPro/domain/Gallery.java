package me.project.namuwikiPro.domain;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity(name = "Gallery")
@Table(name = "GALLERY")

@SequenceGenerator(
        name = "Gallery_SEQ_Generate",
        sequenceName = "Gallery_SEQ",
        initialValue = 1
)

public class Gallery extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,
            generator = "Gallery_SEQ_Generate")
    @Column(name = "gallery_id")
    private Long id;

    @Column(name = "original_file",nullable = false)
    private String originalFilename;

    @Column(name = "filename",nullable = false)
    private String filename;

    @Column(name = "filepath",nullable = false)
    private String filepath;

    @Builder
    public Gallery(Long id,String originalFilename,String filename,String filepath){
        this.id=id;
        this.originalFilename=originalFilename;
        this.filename=filename;
        this.filepath=filepath;
    }


}
