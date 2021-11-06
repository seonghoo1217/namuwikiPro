package me.project.namuwikiPro.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.project.namuwikiPro.DTO.board.BoardDto;
import me.project.namuwikiPro.domain.Board;
import me.project.namuwikiPro.principal.AccountContext;
import me.project.namuwikiPro.service.BoardService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
@Slf4j
public class BoardController {
    private final BoardService boardService;

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
    public String boardCreate(Model model,BoardDto dto){

        log.info("boardCreate");
        boardService.boardSave(dto);

        log.info("title={}", dto.getTitle());
        return "redirect:read/"+dto.getTitle();
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


}
