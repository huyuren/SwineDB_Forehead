package com.example.swinedatebaseproject.controller;

import com.example.swinedatebaseproject.domain.Notice;
import com.example.swinedatebaseproject.response.ResponseResult;
import com.example.swinedatebaseproject.service.NoticeService;
import com.example.swinedatebaseproject.util.ResponseResultUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @Author dd
 * @Date 2022/11/14
 */
@RequestMapping(value = "/notice", method = {RequestMethod.GET, RequestMethod.POST, RequestMethod.DELETE})
@RestController
public class NoticeController {

    @Autowired
    NoticeService noticeService;


    @PostMapping("/add")
    public void addNotice(@RequestBody Notice notice) {
        if (Objects.isNull(notice.getTitle()) || "".equals(notice.getTitle())){
            notice.setTitle("公告");
        }
        if (Objects.isNull(notice.getContent()) || "".equals(notice.getContent())){
            notice.setContent("无内容");
        }
        if (Objects.isNull(notice.getStartTime())){
            notice.setStartTime(LocalDateTime.now());
        }
        if (Objects.isNull(notice.getEndTime())) {
            notice.setEndTime(LocalDateTime.now().plusDays(7));
        }
        noticeService.save(notice);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteNotice(@PathVariable("id") Integer id) {
        noticeService.removeById(id);
    }

    @GetMapping("/get")
    public ResponseResult getNotices() {
        List<Notice> list = noticeService.list();
        List<Notice> notices = new ArrayList<>();
        for (Notice notice : list) {
            LocalDateTime endTime = notice.getEndTime();
//            公告不需要考虑是否超期！
//            if (endTime.isBefore(LocalDateTime.now())) {
//                continue;
//            }
            notices.add(notice);
        }

        notices = notices.stream().sorted((o1, o2) -> {
            if (o1.getEndTime().isBefore(o2.getEndTime())) {
                return -1;
            } else if (o1.getEndTime().isAfter(o2.getEndTime())) {
                return 1;
            } else {
                return 0;
            }
        }).toList();
//        System.out.println(notices);
        return ResponseResultUtils.getSuccessResponseResult("notices", notices);
    }
}
