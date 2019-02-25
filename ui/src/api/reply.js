import request from '@/utils/request'

// 查询文章回复列表
export function getList(postId) {
  return request({
    url: '/reply/list/' + postId,
    method: 'GET'
  })
}

// 查询周回复榜
export function getTop(obj) {
  return request({
    url: '/reply/top?beginTime=' + obj.beginTime + '&endTime=' + obj.endTime,
    method: 'GET'
  })
}

// 添加回复
export function addReply(ReplyForm) {
  return request({
    url: '/reply/save/',
    method: 'POST',
    data: ReplyForm
  })
}

