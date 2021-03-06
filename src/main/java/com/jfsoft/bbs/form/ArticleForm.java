package com.jfsoft.bbs.form;

import java.util.Date;

/**
 * @author chenxc
 * @email 997909544@qq.com
 * @Date 2019/4/10  16:33
 */
public class ArticleForm {

    private String id;

    private String code;

    private String title;

    private String source;

    private String author;

    private Date pubdate;

    private String description;

    private String cover;

    private String content;

    private String pubdateStr;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPubdateStr() {
        return pubdateStr;
    }

    public void setPubdateStr(String pubdateStr) {
        this.pubdateStr = pubdateStr;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
