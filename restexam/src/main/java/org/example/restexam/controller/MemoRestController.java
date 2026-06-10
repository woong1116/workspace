package org.example.restexam.controller;

import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@RestController
@RequestMapping("/api/memos")
public class MemoRestController {
    private final Map<Long, String> memos = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();

    @GetMapping
    public Map<Long, String> getMemos(){
        return memos;
    }

    @GetMapping("/{id}")
    public String getMemo(@PathVariable("id")Long id){
//        return memos.get(id);
        return memos.getOrDefault(id,id+"에 해당하는 메모를 찾을 수 없어요.");
    }

    @PostMapping
    public Long createMemo(@RequestBody String memo){
        long id = counter.incrementAndGet();
        memos.put(id, memo);
        return  id;
    }

    @PutMapping("/{id}")
    public String updateMemo(@PathVariable("id") Long id, @RequestBody String memo){
        if(!memos.containsKey(id))
            return id + "에 해당하는 메모는 없어요.";
        memos.put(id,memo);
        return "메모수정성공!!";
    }

    @DeleteMapping("/{id}")
    public String deleteMemo(@PathVariable("id")Long id){
//        if(!memos.containsKey(id))
//            return "메모를 찾을 수 없어요.";
        if(memos.remove(id) == null)
            return "메모를 찾을 수 없어요.";

        return "메모삭제성공!!";
    }
}
