package com.jfsoft.bbs.dao;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.jfsoft.bbs.entity.BbsPostsEntity;
import org.apache.ibatis.annotations.Mapper;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 12:51:46
 */
@Mapper
public interface BbsPostsDao extends BaseMapper<BbsPostsEntity> {

    List<BbsPostsEntity> getList(Map<String, Object> params);


    List<BbsPostsEntity> getTopList(Map<String, Object> params);

    BbsPostsEntity getPostByID(Map<String, Object> params);

    List<BbsPostsEntity> getPostByCollection(Integer userId);

    List<BbsPostsEntity> findAll();

    List<BbsPostsEntity> getPostsList(Map<String, Object> params);

    BbsPostsEntity getPostByReplyId(Integer replyId);

    int getTodayCount();

    int getYesterdayCount();

    List<BbsPostsEntity> getListByReplyTime(Map<String, Object> page);

    Integer getPostCount(Integer id);

    /**
     * 查询 label 下最新回帖时间
     */
    Date getLastReplyDate(Map<String, Object> params);
}
