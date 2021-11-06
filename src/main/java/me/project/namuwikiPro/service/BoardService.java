package me.project.namuwikiPro.service;

import lombok.RequiredArgsConstructor;
import me.project.namuwikiPro.DTO.board.BoardDto;
import me.project.namuwikiPro.DTO.board.BoardUpdateDto;
import me.project.namuwikiPro.domain.Board;
import me.project.namuwikiPro.domain.Member;
import me.project.namuwikiPro.repository.BoardRepositry;
import me.project.namuwikiPro.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
@Transactional(readOnly = true)
public class BoardService {
    public BoardDto readBoard(String title){
        Board board = boardRepositry.findByTitle(title)
                .orElseThrow(() -> new IllegalAccessError("[board_id=" + title + "] 해당 게시글이 존재하지 않습니다."));
        return board.toDto();
    }

    private final BoardRepositry boardRepositry;

    private final MemberRepository memberRepository;
    @Transactional
    public void boardSave(BoardDto boardDto){
        Board board = boardDto.toEntity();
        boardRepositry.save(board);
    }

    @Transactional
    public Long boardUpdate(Long id,BoardUpdateDto boardUpdateDto){
        Board board = boardRepositry.findById(id)
                .orElseThrow(() -> new IllegalAccessError("[board_id=" + id + "] 해당 게시글이 존재하지 않습니다."));

        board.update(id,boardUpdateDto.getTitle(),boardUpdateDto.getContent());
        return id;
    }

    @Transactional
    public void delete(Long id){
        Board board = boardRepositry.findById(id)
                .orElseThrow(() -> new IllegalAccessError("[board_id=" + id + "] 해당 게시글이 존재하지 않습니다."));
        boardRepositry.delete(board);
    }
}
