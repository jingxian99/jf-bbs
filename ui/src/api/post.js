import request from '@/utils/request'
// 查询文章列表
export function getList(obj) {
  return request({
    url: '/posts/list?currPage=' + obj.currPage + '&pageSize=' + obj.pageSize + '&sortType=' + obj.sortType + '&postType=' +
      obj.postType + '&labelId=' + obj.labelId,
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

// 查询文章详情
export function getDetail(postId) {
  return request({
    url: '/posts/detail/' + postId,
    method: 'GET'
  })
}