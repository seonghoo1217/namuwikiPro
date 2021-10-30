package me.project.namuwikiPro.service;

import lombok.RequiredArgsConstructor;
import me.project.namuwikiPro.DTO.BoardDto;
import me.project.namuwikiPro.DTO.BoardUpdateDto;
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
    private final BoardRepositry boardRepositry;

    private final MemberRepository memberRepository;

    public BoardDto readBoard(Long id){
        Board board = boardRepositry.findById(id)
                .orElseThrow(() -> new IllegalAccessError("[board_id=" + id + "] 해당 게시글이 존재하지 않습니다."));
        return board.toDto();
    }
    @Transactional
    public Long boardSave(BoardDto boardDto, Long id){

        Member findMember = memberRepository.findById(id).get();

        Board board = boardDto.toEntity();

        board.setMember(findMember);

        return boardRepositry.save(board).getId();
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
