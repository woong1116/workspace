package org.example.board.controller;

import lombok.RequiredArgsConstructor;
import org.example.board.domain.Board;
import org.example.board.service.BoardService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/board")
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 5, sort = "id", direction = Sort.Direction.DESC) Pageable pageable) {
        Page<Board> board = boardService.getBoard(pageable);
        model.addAttribute("board", board);

        return "board/list";
    }

    @GetMapping("/view/{id}")
    public String view(@PathVariable("id") Long id, Model model) {
        Board board = boardService.getBoard(id);
        model.addAttribute("board", board);

        return "board/view";
    }

    @GetMapping("/write")
    public String writeForm() {

        return "board/writeForm";
    }

    @PostMapping("/write")
    public String write(@ModelAttribute Board board) {
        Board savedBoard = boardService.saveBoard(board);

        return "redirect:/board/list";
    }

    @GetMapping("/updateform/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model) {
        Board board = boardService.getBoard(id);
        model.addAttribute("board", board);

        return "board/updateForm";
    }

    @PostMapping("/update")
    public String update(@ModelAttribute Board board) {
        boardService.updateBoard(board);

        return "redirect:/board/list";
    }

    @GetMapping("/deleteform/{id}")
    public String deleteForm(@PathVariable("id") Long id, Model model) {
        Board board = boardService.getBoard(id);
        model.addAttribute("board", board);

        return "board/deleteForm";
    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id) {
        boardService.deleteBoard(id);

        return "redirect:/board/list";
    }
}
