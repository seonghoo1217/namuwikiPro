package me.project.namuwikiPro.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.project.namuwikiPro.DTO.GalleryDto;
import me.project.namuwikiPro.DTO.board.BoardDto;
import me.project.namuwikiPro.DTO.board.BoardUpdateDto;
import me.project.namuwikiPro.domain.Board;
import me.project.namuwikiPro.domain.Gallery;
import me.project.namuwikiPro.principal.AccountContext;
import me.project.namuwikiPro.repository.BoardRepositry;
import me.project.namuwikiPro.service.BoardService;
import me.project.namuwikiPro.service.GalleryService;
import me.project.namuwikiPro.util.MD5Generator;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.NoSuchAlgorithmException;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
    private final BoardService boardService;
    private final BoardRepositry boardRepositry;
    private final GalleryService galleryService;

    @GetMapping("/boardCreate")
    public String boardCreateForm(Model model, BoardDto dto,
                                  @AuthenticationPrincipal AccountContext accountContext){

        log.info("boardCreate 호출");
        model.addAttribute("dto",dto);
        model.addAttribute("accountContext",accountContext);
        log.info("accountContext={}", accountContext);

        return "/board/boardCreate";
    }
    @PostMapping("/boardCreate")
    public String boardCreate(Model model,BoardDto dto,@AuthenticationPrincipal AccountContext accountContext) throws UnsupportedEncodingException {


        log.info("boardCreate");
        boardService.boardSave(dto);
        String title = URLEncoder.encode(dto.getTitle(), "UTF-8");

        return "redirect:read/"+title;
    }



    @GetMapping("/read/{title}")
    public String readBoard(@PathVariable("title")String title,
                            @AuthenticationPrincipal AccountContext accountContext,
                            Model model) {
        BoardDto boardDto = boardService.readBoard(title);
        model.addAttribute("boardDto",boardDto);
        model.addAttribute("accountContext",accountContext);
        return "board/boardRead";
    }

    @PostMapping("/read/{title}")
    public String readBoardUpdate(@PathVariable("title")String title,
                                  @AuthenticationPrincipal AccountContext accountContext,
                                  Model model,BoardDto dto) throws UnsupportedEncodingException {

        log.info("boardupdat용 readpost");

        title = URLEncoder.encode(dto.getTitle(), "UTF-8");

        return "redirect:/board/"+title+"/boardUpdate";
    }



    @GetMapping("/{title}/boardUpdate")
    public String updateBoard(@PathVariable("title")String title,
                              @AuthenticationPrincipal AccountContext accountContext,
                              Model model,BoardDto dto) {
        log.info("update호출");
        model.addAttribute("dto",dto);
        model.addAttribute("accountContext",accountContext);

        return "board/boardUpdate";
    }
    @PostMapping("/{title}/boardUpdate")
    public String update(@PathVariable("title")String title,
                         Board board) throws UnsupportedEncodingException {

        log.info("board--title={}", title);
        log.info("board--Board={}", board.getId());

        boardService.boardUpdate(title, board);
        title = URLEncoder.encode(board.getTitle(), "UTF-8");
        return "redirect:/board/read/"+title;
    }


    @GetMapping("/{title}/delete")
    public String deleteBoard(BoardDto dto){
        boardService.delete(dto.getId());
        return "/delete";
    }

    @PostMapping("/post")
    public String imageWrite(@RequestParam("gallery")MultipartFile gallery,BoardDto dto) throws IOException, NoSuchAlgorithmException {
        try {
            String originalFilename=gallery.getOriginalFilename();
            String filename=new MD5Generator(originalFilename).toString();
            //실행되는위치에 files 폴더에 저장
            String savePath=System.getProperty("user.dir")+"\\files";
            //파일저장되는곳에 폴더없을시에 폴더생성
            if(!new File(savePath).exists()){
                try {
                    new File(savePath).mkdir();
                }catch (Exception e){
                    e.printStackTrace();
                }
            }
            String filePath=savePath+"\\"+filename;
            gallery.transferTo(new File(filePath));

            GalleryDto galleryDto = new GalleryDto();
            galleryDto.setOriginalFilename(originalFilename);
            galleryDto.setFilename(filename);
            galleryDto.setFilePath(filePath);

            Long fileId=galleryService.saveFile(galleryDto);
            dto.setGalleryId(fileId);
            boardService.boardSave(dto);
        }catch (Exception e){
            e.printStackTrace();
        }
        return "redirect:/";
    }


}
