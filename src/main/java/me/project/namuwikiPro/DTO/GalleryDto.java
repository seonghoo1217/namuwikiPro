package me.project.namuwikiPro.DTO;


import lombok.*;
import me.project.namuwikiPro.domain.Gallery;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class GalleryDto {
    private Long id;
    private String originalFilename;
    private String filename;
    private String filePath;

    public Gallery toEntity(){
        Gallery build=Gallery.builder()
                .id(id)
                .originalFilename(originalFilename)
                .filename(filename)
                .filepath(filePath)
                .build();
        return build;
    }

    @Builder
    public GalleryDto(Long id,String originalFilename,String filename, String filePath){
        this.id=id;
        this.originalFilename=originalFilename;
        this.filename=filename;
        this.filePath=filePath;
    }
}
