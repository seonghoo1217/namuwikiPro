package me.project.namuwikiPro.controller;

import lombok.RequiredArgsConstructor;
import me.project.namuwikiPro.DTO.BoardDto;
import me.project.namuwikiPro.principal.AccountContext;
import me.project.namuwikiPro.service.BoardService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/{id}/readBoard")
    public String readBoard(@PathVariable("id") Long id){

        return "board/readBoard";
    }
    @GetMapping("/boardCreate")
    public String boardCreateForm(Model model, BoardDto dto,AccountContext accountContext){

        model.addAttribute("dto",dto);
        model.addAttribute("accountContext",accountContext);

        return "boardCreate";
    }
    @PostMapping("/boardCreate")
    public String boardCreate(BoardDto dto, @AuthenticationPrincipal AccountContext accountContext){



        boardService.boardSave(dto, accountContext.getId());


        return "/{id}/readBoard";
    }

}
