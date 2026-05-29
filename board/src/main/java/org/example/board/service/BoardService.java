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

    //    친구 전체 목록을 가져오는 메서드
    public Iterable<Board> getBoard(){
        return boardRepository.findAll();
    }

    //    페이지에 해당하는 목록 가져오는 메서드
    @Transactional(readOnly = true)
    public Page<Board> getBoard(Pageable pageable){
        return boardRepository.findAll(pageable);
    }

    @Transactional
    public Board saveBoard(Board board){
//        우리는 친구를 등록하기 위한 조건 같은것이 있다면,  여기에서 판단할거예요.
//        조건에 모두 만족 한다면..  친구 정보를 DB 에 저장 할꺼예요.
        return boardRepository.save(board);
    }

    @Transactional(readOnly = true)
    public Board getBoard(Long id){
        return boardRepository.findById(id).orElseThrow();
    }

    @Transactional
    public Board updateBoard(Board board){
        return boardRepository.save(board);
    }

    @Transactional
    public void deleteBoard(Long id){
        boardRepository.deleteById(id);
    }

}

