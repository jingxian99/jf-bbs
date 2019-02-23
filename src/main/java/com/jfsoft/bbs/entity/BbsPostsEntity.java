package com.jfsoft.bbs.entity;

import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * 
 * 
 * @author chenxc
 * @email 997909544@qq.com
 * @date 2019-02-23 12:51:46
 */
@TableName("bbs_posts")
public class BbsPostsEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer id;
	/**
	 * 
	 */
	private String content;
	/**
	 * 
	 */
	private Boolean good;
	/**
	 * 
	 */
	private Date initTime;
	/**
	 * 
	 */
	private Integer labelId;
	/**
	 * 
	 */
	private Integer replyCount;
	/**
	 * 
	 */
	private String title;
	/**
	 * 
	 */
	private Boolean top;
	/**
	 * 
	 */
	private Integer userId;

	/**
	 *
	 */
	private Boolean end;

	public Boolean getEnd() {
		return end;
	}

	public void setEnd(Boolean end) {
		this.end = end;
	}

	/**
	 * 设置：
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * 设置：
	 */
	public void setContent(String content) {
		this.content = content;
	}
	/**
	 * 获取：
	 */
	public String getContent() {
		return content;
	}
	/**
	 * 设置：
	 */
	public void setGood(Boolean good) {
		this.good = good;
	}
	/**
	 * 获取：
	 */
	public Boolean getGood() {
		return good;
	}
	/**
	 * 设置：
	 */
	public void setInitTime(Date initTime) {
		this.initTime = initTime;
	}
	/**
	 * 获取：
	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	public Date getInitTime() {
		return initTime;
	}
	/**
	 * 设置：
	 */
	public void setLabelId(Integer labelId) {
		this.labelId = labelId;
	}
	/**
	 * 获取：
	 */
	public Integer getLabelId() {
		return labelId;
	}
	/**
	 * 设置：
	 */
	public void setReplyCount(Integer replyCount) {
		this.replyCount = replyCount;
	}
	/**
	 * 获取：
	 */
	public Integer getReplyCount() {
		return replyCount;
	}
	/**
	 * 设置：
	 */
	public void setTitle(String title) {
		this.title = title;
	}
	/**
	 * 获取：
	 */
	public String getTitle() {
		return title;
	}
	/**
	 * 设置：
	 */
	public void setTop(Boolean top) {
		this.top = top;
	}
	/**
	 * 获取：
	 */
	public Boolean getTop() {
		return top;
	}
	/**
	 * 设置：
	 */
	public void setUserId(Integer userId) {
		this.userId = userId;
	}
	/**
	 * 获取：
	 */
	public Integer getUserId() {
		return userId;
	}
}
