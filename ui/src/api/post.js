import axios from 'axios'
import request from '@/utils/request'

// 查询文章列表
export function getList(obj) {
  return request({
    url: '/posts/list?currPage=' + obj.currPage + '&pageSize=' + obj.pageSize + '&sortType=' + obj.sortType + '&postType=' +
    obj.postType + '&labelId=' + obj.labelId + '&tagId=' + obj.tagId,
    method: 'GET'
  })
}

// 查询置顶文章
export function getTop() {
  return request({
    url: '/posts/top',
    method: 'GET'
  })
}

// 获取一言
export function hitokoto() {
  return request({
    url: '/posts/hitokoto',
    method: 'GET'
  })
}

// 查询文章详情
export function getDetail(postId) {
  return request({
    url: '/posts/detail/' + postId,
    method: 'GET'
  })
}

//搜索引擎查询
export function search(keyword) {
  return request({
    url: '/posts/search/' + keyword,
    method: 'GET'
  })
}

// 查询帖子列表
export function getPersonList(id, type, order) {
  return request({
    url: '/posts/personList?userId=' + id + '&type=' + type + '&order=' + order,
    method: 'GET'
  })
}

// 发布文章
export function publish_old(bbsPosts) {
  return request({
    url: '/posts/save',
    method: 'POST',
    data: bbsPosts
  })
}

export function publish(bbsPosts) {
  return request({
    url: '/posts/publish',
    method: 'POST',
    data: bbsPosts
  })
}

/**
 * 上传文件
 * @param bbsPostsFiles
 */
export function updateFile(postsId, bbsPostsFiles) {
  return request({
    url: '/posts/updateFile?postsId=' + postsId,
    method: 'POST',
    data: bbsPostsFiles
  })
}


export function getFileList(postsId) {
  return request({
    url: `/posts/getFileList/${postsId}`,
    method: 'GET'
  })
}

// 查询个人收藏文章列表
export function getCollecList() {
  return request({
    url: '/posts/collection',
    method: 'GET'
  })
}

// 更新置顶
export function updatTop(id) {
  return request({
    url: '/posts/top/' + id,
    method: 'GET'
  })
}

// 更新加精
export function updatGood(id) {
  return request({
    url: '/posts/good/' + id,
    method: 'GET'
  })
}

// 删除文章
export function del(id) {
  return request({
    url: '/posts/delete/' + id,
    method: 'GET'
  })
}


// 查询首页文章数量
export function getUpdateCount() {
  return request({
    url: '/posts/getUpdateCount',
    method: 'GET'
  })
}

// 查询首页最新文章列表
export function getNewPosts(data) {
  return request({
    url: `/posts/getNewPosts?currPage=${data.currPage}&limit=${data.limit}`,
    method: 'GET'
  })
}

/**
 * 新本查询文章
 */
export function getPostsList(form) {
  return request({
    url: '/posts/getPostsList?pageIndex=' + form.pageIndex + '&pageSize=' + form.pageSize + '&sortType=' + form.sortType +
    '&tagId=' + form.tagId + '&labelId=' + form.labelId,
    method: 'GET'
  })
}

/**
 * 阅读量增加
 * @param id
 */
export function addRead(id) {
  return request({
    url: '/posts/addRead/' + id,
    method: 'GET'
  })
}

/**
 * 下载附件
 * @param fileId
 */
export function downloadFile(fileId) {
  return axios({
    url: window.localStorage.baseUrl + '/download/' + fileId,
    method: 'GET',
    headers: {
      'J-Token': window.localStorage['J-Token']
    },
    responseType: 'blob'
  })
}

/**
 * 删除附件
 * @param fileId 文件ID
 */
export function deleteFile(fileId) {
  return request({
    url: '/posts/deleteFile/' + fileId,
    method: 'GET'
  })
}
