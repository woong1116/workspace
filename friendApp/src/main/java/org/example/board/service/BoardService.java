package org.example.board.service;

import lombok.RequiredArgsConstructor;
import org.example.board.domain.Board;
import org.example.board.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardRepository;

    @Transactional(readOnly = true)
    public Iterable<Board> getBoard() {
        return boardRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Page<Board> getBoard(Pageable pageable) {
        return boardRepository.findAll(pageable);
    }

    @Transactional
    public Board saveBoard(Board board) {
        return boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Board getBoard(Long id) {
        return boardRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Board updateBoard(Board board) {
        return boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long id) {
        boardRepository.deleteById(id);
    }

}

