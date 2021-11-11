package me.project.namuwikiPro.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.project.namuwikiPro.DTO.board.BoardDto;
import me.project.namuwikiPro.DTO.board.BoardUpdateDto;
import me.project.namuwikiPro.domain.Board;
import me.project.namuwikiPro.principal.AccountContext;
import me.project.namuwikiPro.repository.BoardRepositry;
import me.project.namuwikiPro.service.BoardService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
    private final BoardService boardService;
    private final BoardRepositry boardRepositry;

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
    public String boardCreate(Model model,BoardDto dto) throws UnsupportedEncodingException {

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
}
