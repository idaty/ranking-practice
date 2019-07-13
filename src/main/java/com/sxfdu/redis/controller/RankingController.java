package com.sxfdu.redis.controller;

import com.sxfdu.redis.service.RankingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.ZSetOperations;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author sunxu93@163.com
 * @date 19/7/7/007 17:23
 */
@Controller
public class RankingController {


    @Autowired
    private RankingService rankingService;

    @ResponseBody
    @RequestMapping("/addScore")
    public String addRank(String uid, Integer score) {
        rankingService.rankAdd(uid, score);
        return "success";
    }



    @ResponseBody
    @RequestMapping("/increScore")
    public String increScore(String uid, Integer score) {
        rankingService.increScore(uid, score);
        return "success";
    }

    @ResponseBody
    @RequestMapping("/rank")
    public Map<String, Long> rank(String uid) {
        Map<String, Long> map = new HashMap<>();
        map.put(uid, rankingService.rankNum(uid));
        return map;
    }

    @ResponseBody
    @RequestMapping("/score")
    public Long rankNum(String uid) {
        return rankingService.getScore(uid);
    }

    @ResponseBody
    @RequestMapping("/scoreByRange")
    public Set<ZSetOperations.TypedTuple<Object>> scoreByRange(Integer start, Integer end) {
        return rankingService.rankWithScore(start,end);
    }



    @ResponseBody
    @RequestMapping("/sale/increScore")
    public String increSaleScore(String uid, Integer score) {
        rankingService.increSaleScore(uid, score);
        return "success";
    }


    @ResponseBody
    @RequestMapping("/sale/userScore")
    public Map<String,Object> userScore(String uid,String name) {
        return rankingService.userRank(uid,name);
    }

    @ResponseBody
    @RequestMapping("/sale/top")
    public List<Map<String,Object>> reverseZRankWithRank(long start, long end) {
        return rankingService.reverseZRankWithRank(start,end);
    }


    @ResponseBody
    @RequestMapping("/sale/scoreByRange")
    public List<Map<String,Object>> saleScoreByRange(Integer start, Integer end) {
        return rankingService.saleRankWithScore(start,end);
    }

}
