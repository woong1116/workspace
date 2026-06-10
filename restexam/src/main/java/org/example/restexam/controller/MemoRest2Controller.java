package org.example.restexam.controller;

import org.example.restexam.domain.Memo;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
@RestController
@RequestMapping("/api2/memos")
public class MemoRest2Controller {
    private final Map<Long, Memo> memos = new HashMap<>();
    private final AtomicLong counter = new AtomicLong();


    //1. http://localhost:8080/api2/memos    -- GET   (모든 메모를 조회)  -- List<Memo>
    @GetMapping
    public List<Memo> getMemos(){
//        return new ArrayList<>(memos.values());  //가변리스트 
        return memos.values().stream().toList();  //불변리스트
    }

    //2. http://localhost:8080/api2/memos/{id}  -- GET  id에 해당하는 메모 조회   Memo
    @GetMapping("/{id}")
    public Memo getMemo(@PathVariable("id")Long id){
        return memos.getOrDefault(id,null);
    }

    //3. http://localhost:8080/api2/memos     -- POST    MEMO 생성        --  Long
    @PostMapping
    public Memo addMemo(@RequestBody Memo memo){
        long id = counter.incrementAndGet();
        memo.setId(id);
        memos.put(id, memo);
        return memo;
    }

    //4. http://localhost:8080/api2/memos/{id}  --  PUT   메모 수정
    @PutMapping("/{id}")
    public String updateMemo(@PathVariable("id")Long id, @RequestBody Memo memo){
        if(!memos.containsKey(id))
            return "Memo not found";
        memo.setId(id);
        memos.put(id,memo);
        return "memo update";
    }

    //5. http://localhost:8080/api2/memos/{id}  --   DELETE  메모 삭제
    @DeleteMapping("/{id}")
    public String deleteMemo(@PathVariable("id")Long id){
        if(memos.remove(id)==null)
            return "memo not found";
        return "memo delete";
    }
}
