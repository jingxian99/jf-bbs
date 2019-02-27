package com.jfsoft.bbs.controller;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.jfsoft.bbs.common.utils.PageUtils;
import com.jfsoft.bbs.common.utils.R;
import com.jfsoft.bbs.entity.BbsGradeEntity;
import com.jfsoft.bbs.entity.BbsSignEntity;
import com.jfsoft.bbs.service.BbsGradeService;
import com.jfsoft.bbs.service.BbsSignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;


/**
 * 用户积分表
 *
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-25 22:11:35
 */
@RestController
@RequestMapping("/grade")
public class GradeController extends AbstractController {
    @Autowired
    private BbsGradeService bbsGradeService;


    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params) {
        PageUtils page = bbsGradeService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info")
    public R info() {
        EntityWrapper<BbsGradeEntity> wrapper = new EntityWrapper<>();
        wrapper.eq("user_id", getUserId());
        BbsGradeEntity bbsGrade = bbsGradeService.selectOne(wrapper);
        return R.ok().put("data", bbsGrade);
    }


    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody BbsGradeEntity bbsGrade) {
        bbsGradeService.insert(bbsGrade);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody BbsGradeEntity bbsGrade) {
//        ValidatorUtils.validateEntity(bbsGrade);
        bbsGradeService.updateAllColumnById(bbsGrade);//全部更新

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids) {
        bbsGradeService.deleteBatchIds(Arrays.asList(ids));

        return R.ok();
    }

}
