package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsPostsEntity;
import com.jfsoft.bbs.service.BbsPostsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 12:51:46
 */
@RestController
@RequestMapping("/posts")
public class PostsController {

    @Autowired
    private BbsPostsService bbsPostsService;

    /**
     * 首页列表查询
     *
     * @param currPage
     * @param pageSize
     * @param sort          排序：0 时间， 1 热度
     * @param searchType    查询：0 所有， 1 未结， 2 已结， 3 精华
     * @return
     */
    @RequestMapping("/list")
    public R list(Integer currPage, Integer pageSize, Integer sort, Integer searchType) {
        Map<String, Object> map = new HashMap<>();
        EntityWrapper<BbsPostsEntity> wrapper = new EntityWrapper<>();
        map.put("currPage", currPage);
        map.put("pageSize", pageSize);
        if (sort == 0) {
            map.put("sidx", "init_time");
            map.put("order", "desc");
        } else {
            map.put("sidx", "reply_count");
            map.put("order", "desc");
        }
        if (searchType == 3) {
            wrapper.eq("good = {0}", 1);
        }
        //TODO 已完结和未完结未写

        PageUtils page = bbsPostsService.queryPage(map,wrapper);
        return R.ok().put("data", page);
    }


    /**
     * 查询置顶
     * @return
     */
    @RequestMapping("/top")
    public R top() {
        Map<String, Object> map = new HashMap<>();
        EntityWrapper<BbsPostsEntity> wrapper = new EntityWrapper<>();
        map.put("currPage", 1);
        map.put("pageSize", 4);
        wrapper.eq("top = {0}", true);
        wrapper.orderBy("reply_count desc");
        PageUtils page = bbsPostsService.queryPage(map, wrapper);
        return R.ok().put("data", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id) {
        BbsPostsEntity bbsPosts = bbsPostsService.selectById(id);

        return R.ok().put("bbsPosts", bbsPosts);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")

    public R save(@RequestBody BbsPostsEntity bbsPosts) {
        bbsPostsService.insert(bbsPosts);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")

    public R update(@RequestBody BbsPostsEntity bbsPosts) {
//        ValidatorUtils.validateEntity(bbsPosts);
//        bbsPostsService.updateAllColumnById(bbsPosts);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        bbsPostsService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
