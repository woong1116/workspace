package org.example.board.service;

import lombok.RequiredArgsConstructor;
import org.example.board.domain.Board;
import org.example.board.repository.BoardRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

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
        board.setCreatedAt(LocalDateTime.now());

        return boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Board getBoard(Long id) {
        return boardRepository.findById(id).orElseThrow();
    }

    @Transactional
    public boolean updateBoard(Board board) {
        Board updateBoard = boardRepository.findById(board.getId()).orElseThrow();
        if (!updateBoard.getPassword().equals(board.getPassword())) {
            return false;
        }

        updateBoard.setName(board.getName());
        updateBoard.setTitle(board.getTitle());
        updateBoard.setContent(board.getContent());
        updateBoard.setUpdatedAt(LocalDateTime.now());

        boardRepository.save(updateBoard);

        return true;
    }

    @Transactional
    public boolean deleteBoard(Long id, String password) {
        Board board = boardRepository.findById(id).orElseThrow();

        if (!board.getPassword().equals(password)) {
            return false;
        }

        boardRepository.delete(board);

        return true;
    }
}

