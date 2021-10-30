package me.project.namuwikiPro.repository;

import me.project.namuwikiPro.domain.Board;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardRepositry extends JpaRepository<Board,Long> {

}
