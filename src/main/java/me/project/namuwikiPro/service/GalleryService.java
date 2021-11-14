package me.project.namuwikiPro.service;

import lombok.RequiredArgsConstructor;
import me.project.namuwikiPro.DTO.GalleryDto;
import me.project.namuwikiPro.domain.Gallery;
import me.project.namuwikiPro.repository.GalleryRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GalleryService {
    private final GalleryRepository galleryRepository;

    @Transactional
    public Long saveFile(GalleryDto galleryDto){
        return galleryRepository.save(galleryDto.toEntity()).getId();
    }

    @Transactional
    public GalleryDto getFile(Long id){
        Gallery gallery = galleryRepository.findById(id).get();

        GalleryDto galleryDto=GalleryDto.builder()
                .id(id)
                .originalFilename(gallery.getOriginalFilename())
                .filename(gallery.getOriginalFilename())
                .filePath(gallery.getFilepath())
                .build();
        return galleryDto;
    }
}
