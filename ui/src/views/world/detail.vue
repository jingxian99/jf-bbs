<template>
  <div class="layui-container">
    <div class="layui-row layui-col-space15">
      <div class="layui-col-md8 content detail">
        <div class="fly-panel detail-box">
          <h1>{{articleInfo.title}}</h1>
          <!--<div class="fly-detail-info">-->
          <!--&lt;!&ndash; <span class="layui-badge">审核中</span> &ndash;&gt;-->
          <!--<span class="layui-badge layui-bg-green fly-detail-column">动态</span>-->

          <!--<span class="layui-badge" style="background-color: #999;">未结</span>-->
          <!--&lt;!&ndash; <span class="layui-badge" style="background-color: #5FB878;">已结</span> &ndash;&gt;-->

          <!--<span class="layui-badge layui-bg-black">置顶</span>-->
          <!--<span class="layui-badge layui-bg-red">精帖</span>-->

          <!--<div class="fly-admin-box" data-id="123">-->
          <!--<span class="layui-btn layui-btn-xs jie-admin" type="del">删除</span>-->

          <!--<span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="1">置顶</span>-->
          <!--&lt;!&ndash; <span class="layui-btn layui-btn-xs jie-admin" type="set" field="stick" rank="0" style="background-color:#ccc;">取消置顶</span> &ndash;&gt;-->

          <!--<span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="1">加精</span>-->
          <!--&lt;!&ndash; <span class="layui-btn layui-btn-xs jie-admin" type="set" field="status" rank="0" style="background-color:#ccc;">取消加精</span> &ndash;&gt;-->
          <!--</div>-->
          <!--<span class="fly-list-nums">-->
          <!--<a href="#comment"><i class="iconfont" title="回答">&#xe60c;</i> 66</a>-->
          <!--<i class="iconfont" title="人气">&#xe60b;</i> 99999-->
          <!--</span>-->
          <!--</div>-->
          <!--<div class="detail-about">-->
          <!--<a class="fly-avatar" href="../user/home.html">-->
          <!--<img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt="贤心">-->
          <!--</a>-->
          <!--<div class="fly-detail-user">-->
          <!--<a href="../user/home.html" class="fly-link">-->
          <!--<cite>贤心</cite>-->
          <!--<i class="iconfont icon-renzheng" title="认证信息："></i>-->
          <!--<i class="layui-badge fly-badge-vip">VIP3</i>-->
          <!--</a>-->
          <!--<span>2017-11-30</span>-->
          <!--</div>-->
          <!--<div class="detail-hits" id="LAY_jieAdmin" data-id="123">-->
          <!--<span style="padding-right: 10px; color: #FF7200">悬赏：60飞吻</span>-->
          <!--<span class="layui-btn layui-btn-xs jie-admin" type="edit"><a href="add.html">编辑此贴</a></span>-->
          <!--</div>-->
          <!--</div>-->
          <div class="detail-body photos" v-html="articleInfo.content" v-loading="loading">
          </div>
        </div>

        <!--<div class="fly-panel detail-box" id="flyReply">-->
        <!--<fieldset class="layui-elem-field layui-field-title" style="text-align: center;">-->
        <!--<legend>回帖</legend>-->
        <!--</fieldset>-->

        <!--<ul class="jieda" id="jieda">-->
        <!--<li data-id="111" class="jieda-daan">-->
        <!--<a name="item-1111111111"></a>-->
        <!--<div class="detail-about detail-about-reply">-->
        <!--<a class="fly-avatar" href="">-->
        <!--<img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt=" ">-->
        <!--</a>-->
        <!--<div class="fly-detail-user">-->
        <!--<a href="" class="fly-link">-->
        <!--<cite>贤心</cite>-->
        <!--<i class="iconfont icon-renzheng" title="认证信息：XXX"></i>-->
        <!--<i class="layui-badge fly-badge-vip">VIP3</i>-->
        <!--</a>-->

        <!--<span>(楼主)</span>-->
        <!--&lt;!&ndash;-->
        <!--<span style="color:#5FB878">(管理员)</span>-->
        <!--<span style="color:#FF9E3F">（社区之光）</span>-->
        <!--<span style="color:#999">（该号已被封）</span>-->
        <!--&ndash;&gt;-->
        <!--</div>-->

        <!--<div class="detail-hits">-->
        <!--<span>2017-11-30</span>-->
        <!--</div>-->

        <!--<i class="iconfont icon-caina" title="最佳答案"></i>-->
        <!--</div>-->
        <!--<div class="detail-body jieda-body photos">-->
        <!--<p>香菇那个蓝瘦，这是一条被采纳的回帖</p>-->
        <!--</div>-->
        <!--<div class="jieda-reply">-->
        <!--<span class="jieda-zan zanok" type="zan">-->
        <!--<i class="iconfont icon-zan"></i>-->
        <!--<em>66</em>-->
        <!--</span>-->
        <!--<span type="reply">-->
        <!--<i class="iconfont icon-svgmoban53"></i>-->
        <!--回复-->
        <!--</span>-->
        <!--<div class="jieda-admin">-->
        <!--<span type="edit">编辑</span>-->
        <!--<span type="del">删除</span>-->
        <!--&lt;!&ndash; <span class="jieda-accept" type="accept">采纳</span> &ndash;&gt;-->
        <!--</div>-->
        <!--</div>-->
        <!--</li>-->

        <!--<li data-id="111">-->
        <!--<a name="item-1111111111"></a>-->
        <!--<div class="detail-about detail-about-reply">-->
        <!--<a class="fly-avatar" href="">-->
        <!--<img src="https://tva1.sinaimg.cn/crop.0.0.118.118.180/5db11ff4gw1e77d3nqrv8j203b03cweg.jpg" alt=" ">-->
        <!--</a>-->
        <!--<div class="fly-detail-user">-->
        <!--<a href="" class="fly-link">-->
        <!--<cite>贤心</cite>-->
        <!--</a>-->
        <!--</div>-->
        <!--<div class="detail-hits">-->
        <!--<span>2017-11-30</span>-->
        <!--</div>-->
        <!--</div>-->
        <!--<div class="detail-body jieda-body photos">-->
        <!--<p>蓝瘦那个香菇，这是一条没被采纳的回帖</p>-->
        <!--</div>-->
        <!--<div class="jieda-reply">-->
        <!--<span class="jieda-zan" type="zan">-->
        <!--<i class="iconfont icon-zan"></i>-->
        <!--<em>0</em>-->
        <!--</span>-->
        <!--<span type="reply">-->
        <!--<i class="iconfont icon-svgmoban53"></i>-->
        <!--回复-->
        <!--</span>-->
        <!--<div class="jieda-admin">-->
        <!--<span type="edit">编辑</span>-->
        <!--<span type="del">删除</span>-->
        <!--<span class="jieda-accept" type="accept">采纳</span>-->
        <!--</div>-->
        <!--</div>-->
        <!--</li>-->

        <!--&lt;!&ndash; 无数据时 &ndash;&gt;-->
        <!--&lt;!&ndash; <li class="fly-none">消灭零回复</li> &ndash;&gt;-->
        <!--</ul>-->

        <!--<div class="layui-form layui-form-pane">-->
        <!--<form action="/jie/reply/" method="post">-->
        <!--<div class="layui-form-item layui-form-text">-->
        <!--<a name="comment"></a>-->
        <!--<div class="layui-input-block">-->
        <!--<textarea id="L_content" name="content" required lay-verify="required" placeholder="请输入内容"  class="layui-textarea fly-editor" style="height: 150px;"></textarea>-->
        <!--</div>-->
        <!--</div>-->
        <!--<div class="layui-form-item">-->
        <!--<input type="hidden" name="jid" value="123">-->
        <!--<button class="layui-btn" lay-filter="*" lay-submit>提交回复</button>-->
        <!--</div>-->
        <!--</form>-->
        <!--</div>-->
        <!--</div>-->
      </div>
      <!--<div class="layui-col-md4">-->
        <!--<dl class="fly-panel fly-list-one">-->
          <!--<dt class="fly-panel-title">本周热议</dt>-->
          <!--<dd>-->
            <!--<a href="">基于 layui 的极简社区页面模版</a>-->
            <!--<span><i class="iconfont icon-pinglun1"></i> 16</span>-->
          <!--</dd>-->
          <!--<dd>-->
            <!--<a href="">基于 layui 的极简社区页面模版</a>-->
            <!--<span><i class="iconfont icon-pinglun1"></i> 16</span>-->
          <!--</dd>-->
          <!--<dd>-->
            <!--<a href="">基于 layui 的极简社区页面模版</a>-->
            <!--<span><i class="iconfont icon-pinglun1"></i> 16</span>-->
          <!--</dd>-->
          <!--<dd>-->
            <!--<a href="">基于 layui 的极简社区页面模版</a>-->
            <!--<span><i class="iconfont icon-pinglun1"></i> 16</span>-->
          <!--</dd>-->
          <!--<dd>-->
            <!--<a href="">基于 layui 的极简社区页面模版</a>-->
            <!--<span><i class="iconfont icon-pinglun1"></i> 16</span>-->
          <!--</dd>-->
          <!--<dd>-->
            <!--<a href="">基于 layui 的极简社区页面模版</a>-->
            <!--<span><i class="iconfont icon-pinglun1"></i> 16</span>-->
          <!--</dd>-->
          <!--<dd>-->
            <!--<a href="">基于 layui 的极简社区页面模版</a>-->
            <!--<span><i class="iconfont icon-pinglun1"></i> 16</span>-->
          <!--</dd>-->
          <!--<dd>-->
            <!--<a href="">基于 layui 的极简社区页面模版</a>-->
            <!--<span><i class="iconfont icon-pinglun1"></i> 16</span>-->
          <!--</dd>-->
          <!--<dd>-->
            <!--<a href="">基于 layui 的极简社区页面模版</a>-->
            <!--<span><i class="iconfont icon-pinglun1"></i> 16</span>-->
          <!--</dd>-->
          <!--<dd>-->
            <!--<a href="">基于 layui 的极简社区页面模版</a>-->
            <!--<span><i class="iconfont icon-pinglun1"></i> 16</span>-->
          <!--</dd>-->

          <!--&lt;!&ndash; 无数据时 &ndash;&gt;-->
          <!--&lt;!&ndash;-->
          <!--<div class="fly-none">没有相关数据</div>-->
          <!--&ndash;&gt;-->
        <!--</dl>-->

        <!--<div class="fly-panel">-->
          <!--<div class="fly-panel-title">-->
            <!--这里可作为广告区域-->
          <!--</div>-->
          <!--<div class="fly-panel-main">-->
            <!--<a href="http://layim.layui.com/?from=fly" target="_blank" class="fly-zanzhu"-->
               <!--time-limit="2017.09.25-2099.01.01" style="background-color: #5FB878;">LayIM 3.0 - layui 旗舰之作</a>-->
          <!--</div>-->
        <!--</div>-->

        <!--<div class="fly-panel" style="padding: 20px 0; text-align: center;">-->
          <!--&lt;!&ndash;<img src="../../res/images/weixin.jpg" style="max-width: 100%;" alt="layui">&ndash;&gt;-->
          <!--<p style="position: relative; color: #666;">微信扫码关注 layui 公众号</p>-->
        <!--</div>-->

      <!--</div>-->
    </div>
  </div>
</template>

<script>
  import * as world from '@/api/world'

  export default {
    name: "detail",
    data() {
      return {
        articleId: '',
        articleInfo: '',
        loading: false
      }
    },
    created() {
      this.articleId = this.$route.query.articleId;
      this.labelId = this.$route.query.labelId;
      this.getArticleVo(this.labelId);
    },
    methods: {
      getArticleVo(labelId) {
        let type;
        switch (labelId) {
          case '0' : type = 'health'; break;
          case '1' : type = 'kr'; break;
          case '2' : type = 'zhihu'; break;
          case '3' : type = 'infoQ'; break;
          case '4' : type = 'pm'; break;
        }
        this.loading = true;
        world.getInfo(type, this.articleId).then(res => {
          this.loading = false;
          this.articleInfo = res.data;
        }).catch(() => {
          this.loading = false;
        })
      }
    }
  }
</script>

<style scoped>

</style>
