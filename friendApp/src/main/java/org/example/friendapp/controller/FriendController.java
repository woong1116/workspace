package org.example.friendapp.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.board.domain.Board;
import org.example.friendapp.domain.Friend;
import org.example.friendapp.service.FriendService;
import org.springframework.boot.autoconfigure.data.web.SpringDataWebProperties;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
@Slf4j
@Controller
@RequestMapping("/friend")
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;
//    친구등록 - 폼    /friend/add    -- GET
    @GetMapping("/add")
    public String addForm(){
        return "friend/addForm";
    }
//    친구등록          /friend/add    -- POST
    @PostMapping("/add")
    public String add(@ModelAttribute Friend friend){

//        이 값을 이용해서 서비스쪽에 친구 저장해줘요..
        Friend savedFriend = friendService.saveFriend(friend);

        log.info("{} Friend saved successfully",savedFriend.getName());

        return "redirect:/friend/list";
    }

//        친구목록보기   /friend/list    -- GET
    @GetMapping("/list")
    public String list(Model model, @PageableDefault(size = 3, sort = "id", direction = Sort.Direction.DESC) Pageable pageable){
//        Iterable<Friend> friends = friendService.getFriends();
        Page<Friend> friends = friendService.getFriends(pageable);


        model.addAttribute("friends", friends);

        return "friend/list";
    }

//    친구정보보기  /friend/친구id    -- GET
    @GetMapping("/{id}")
    public String view(@PathVariable("id") Long id){
        return "friend/view";
    }
//친구정보수정 - 폼   /friend/update/친구id   -- GET
    @GetMapping("/update/{id}")
    public String update(@PathVariable("id") Long id, Model model){
//        id에 해당하는 정보를 얻어서,  폼에 보여줘야 할거예요.
        Friend friend = friendService.getFriend(id);

        model.addAttribute("friend",friend);

        return "friend/updateForm";
    }

//    친구정보수정         /friend/update   -- Post
    @PostMapping("/update")
    public String update(@ModelAttribute Friend friend){

        friendService.updateFriend(friend);
        return "redirect:/friend/list";
    }
//    친구삭제   /friend/delete  -- GET
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        friendService.deleteFriend(id);
        return "redirect:/friend/list";
    }

}
