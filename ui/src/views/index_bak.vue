<template>
  <div>
    <!-- <v-header @change="changeLabel"></v-header>-->
    <div class="layui-container">
      <div class="layui-row layui-col-space15">
        <div class="layui-col-md8">
          <!--<div class="fly-panel" v-if="labelId == 0">-->
            <!--<div class="layui-row fly-panel-main" style="padding: 8px;">-->
              <!--<div class="fly-topline" id="FLY_topline" lay-anim="fade"-->
                   <!--style="width: 100%;">-->
                <!--<div carousel-item="">-->
                  <!--<div class="layui-this">-->
                    <!--<router-link to="/post/detail?postId=135&labelId=6">-->
                      <!--<img src="../../static/images/music.jpg" alt="金风音乐会"-->
                           <!--style="max-width: 100%;">-->
                    <!--</router-link>-->
                  <!--</div>-->
                <!--</div>-->
              <!--</div>-->
            <!--</div>-->
          <!--</div>-->
          <!-- 标签区 -->
          <div class="fly-panel" v-if="false">
            <div class="fly-panel-title fly-filter">
              <a>标签</a>
              <a class="layui-hide-sm layui-show-xs-block fly-right"
                 style="color: #FF5722;" @click="toSignin">去签到</a>
            </div>
            <div class="layui-row fly-panel-main" style="padding: 15px;">
              <div class="layui-clear fly-list-quick">
                <div class="layui-col-xs2">
                  <div><a href="/extend/" target="_blank"> 组件平台 </a></div>
                </div>
                <div class="layui-col-xs2">
                  <div><a href="/case/2019/" target="_blank"> 年度案例 </a></div>
                </div>
                <!--<div class="layui-col-xs6">-->
                <!--<div><a href="http://fly.layui.com/jie/30227/" target="_blank"> 精贴集锦 </a></div>-->
                <!--</div>-->
                <!--<div class="layui-col-xs6">-->
                <!--<div><a href="http://fly.layui.com/jie/4281/" target="_blank"> Git 仓库 </a></div>-->
                <!--</div>-->
                <!--<div class="layui-col-xs6">-->
                <!--<div><a href="/store/" target="_blank"> layui 模板 </a></div>-->
                <!--</div>-->
                <!--<div class="layui-col-xs6">-->
                <!--<div><a href="http://fly.layui.com/jie/13435/" target="_blank"> 关于飞吻 </a></div>-->
                <!--</div>-->
                <a name="signin"></a></div>
            </div>
          </div>
          <div class="fly-panel layui-hide-xs" v-if="false">
            <div style="padding: 0; height: 50px">
              <!--<div class="layui-container">-->
              <!--<ul class="layui-clear">-->
              <!--&lt;!&ndash; 用户登入后显示 &ndash;&gt;-->
              <!--<li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block">-->
              <!--<router-link to="/set/index">我发表的贴</router-link>-->
              <!--</li>-->
              <!--<li class="layui-hide-xs layui-hide-sm layui-show-md-inline-block">-->
              <!--<router-link to="/set/index#collection">我收藏的贴</router-link>-->
              <!--</li>-->
              <!--</ul>-->

              <!--<div class="fly-column-right layui-hide-xs">-->
              <!--<span class="fly-search"><i class="layui-icon"></i></span>-->
              <!--<a @click="toAdd" class="layui-btn">发表新帖</a>-->
              <!--</div>-->
              <!--<div class="layui-hide-sm layui-show-xs-block"-->
              <!--style="margin-top: -10px; padding-bottom: 10px; text-align: center;">-->
              <!--<a @click="toAdd" class="layui-btn">发表新帖</a>-->
              <!--</div>-->
              <!--</div>-->
              <div class="layui-row">
                <div class="layui-col-md9">
                  <div class="layui-row">
                    <div class="layui-col-md1">
                      <img :src="defaultAvatar" style="height: 50px">
                    </div>

                    <div class="layui-col-md11 fly-column">
                      <ul class="layui-clear layui-row">
                        <li class="layui-hide-xs">
                          <a style="cursor: pointer; padding: 0 0 0 20px;" @click="selectPostType">
                            <i class="layui-icon layui-icon-edit"
                               style="font-size: 20px; top: 2px; position: relative;"></i>
                            写文章
                          </a>
                        </li>
                        <!--<li class="layui-hide-xs">-->
                        <!--<a style="cursor: pointer; padding: 0 0 0 20px">-->
                        <!--<i class="layui-icon layui-icon-link" style="font-size: 12px;"></i>-->
                        <!--分享链接-->
                        <!--</a>-->
                        <!--</li>-->
                      </ul>
                    </div>
                  </div>
                </div>
                <!--<div class="layui-col-md3 fly-column" style="display: flex;justify-content: space-around;">-->
                <!--&lt;!&ndash;<a style="cursor: pointer"></a>&ndash;&gt;-->
                <!--&lt;!&ndash;<a style="cursor: pointer">收藏</a>&ndash;&gt;-->
                <!--<router-link to="/set/index#collection">我的收藏</router-link>-->
                <!--<router-link to="/set/index">我的帖子</router-link>-->
                <!--&lt;!&ndash;<a style="cursor: pointer">我的收藏</a>&ndash;&gt;-->
                <!--</div>-->
              </div>
            </div>
          </div>
          <!-- 标签 -->
          <div class="layui-card" v-if="labelId != 0">
            <div class="layui-card-header">
              模块
            </div>
            <div class="layui-card-body">
              <div class="layuiadmin-card-link fly-filter">
                <a href="javascript:;" :class='{"layui-this":activeTag == -1}' @click="tagIdChanged('', '-1')">全部</a>
                <a href="javascript:;" v-for="(tag, index) in tagList" @click="tagIdChanged(tag.id, index)"
                   :class='{"layui-this":activeTag==index}'>{{tag.name}}</a>
              </div>
            </div>
          </div>
          <!-- 置顶区 -->
          <div class="fly-panel" v-if="labelId == 0" v-loading="loadTop">
            <div class="fly-panel-title fly-filter">
              <a>置顶</a>
              <a class="layui-hide-sm layui-show-xs-block fly-right" id="LAY_goSignin"
                 style="color: #FF5722;" @click="toSignin">去签到</a>
            </div>
            <ul class="fly-list">
              <li v-for="top in topPostList">
                <router-link :to="'/user/index?userId='+ top.userId" class="fly-avatar">
                  <img :src="top.icon == null ? defaultAvatar : top.icon"
                       :alt="top.author">
                </router-link>
                <h2>
                  <a v-if="top.tagName != null" class="layui-badge">{{top.tagName}}</a>
                  <router-link :to="'/post/detail?postId=' + top.id + '&labelId=' + top.labelId">{{top.title}}
                  </router-link>
                </h2>
                <div class="fly-list-info">
                  <a href="" link>
                    <cite>{{top.author}}</cite>
                    <!--<i class="iconfont icon-renzheng" title="认证信息：XXX"></i>-->
                    <!--<i class="layui-badge fly-badge-vip">VIP3</i>-->
                  </a>
                  <span>{{top.initTime | getDate}}</span>
                  <span class="fly-list-kiss layui-hide-xs" title="悬赏钻石" v-show="top.rewardGrade != 0">
                    <i class="layui-icon layui-icon-diamond"></i>
                    {{top.rewardGrade}}
                  </span>
                  <span v-if="top.end" class="layui-badge fly-badge-accept layui-hide-xs">已结</span>
                  <span class="fly-list-nums">
                <i class="iconfont icon-pinglun1" title="回答"></i> {{top.replyCount}}
              </span>
                </div>
                <div class="fly-list-badge">

                  <span v-if="top.top" class="layui-badge layui-bg-black">置顶</span>
                  <span v-if="top.good" class="layui-badge layui-bg-red">精帖</span>

                </div>
              </li>
            </ul>
          </div>
          <!-- 文章区 -->
          <div class="fly-panel" style="margin-bottom: 0;" v-loading="loading">
            <!-- TAB -->
            <div class="fly-panel-title fly-filter">
              <a style="cursor: pointer" :class="{'layui-this': postTypeActive == 0}" @click="setPostType(0)">综合</a>
              <span class="fly-mid"></span>
              <a style="cursor: pointer" :class="{'layui-this': postTypeActive == 1}" @click="setPostType(1)">未结</a>
              <span class="fly-mid"></span>
              <a style="cursor: pointer" :class="{'layui-this': postTypeActive == 2}" @click="setPostType(2)">已结</a>
              <span class="fly-mid"></span>
              <a style="cursor: pointer" :class="{'layui-this': postTypeActive == 3}" @click="setPostType(3)">精华</a>
              <!-- sort -->
              <span class="fly-filter-right layui-hide-xs">
                <a style="cursor: pointer" :class="{'layui-this': sortTypeActive == 0}" @click="setSortType(0)">按最新</a>
                <span class="fly-mid"></span>
                <a style="cursor: pointer" :class="{'layui-this': sortTypeActive == 1}" @click="setSortType(1)">按热议</a>
              </span>
            </div>
            <!-- 无数据时 -->
            <div v-if="postList.length == 0" class="fly-none">
              就等你发帖的呢
              <router-link to="/add/index">👉点我发帖</router-link>
            </div>
            <!-- LIST -->
            <ul class="fly-list">
              <li v-for="post in postList">
                <!--<router-link :to="'/user/index?userId='+ post.userId" class="fly-avatar">-->
                <!--<img :src="post.icon == null? defaultAvatar : post.icon" :alt="post.author">-->
                <!--</router-link>-->
                <a class="fly-avatar">
                  <img :src="post.icon == null? defaultAvatar : post.icon" :alt="post.author">
                </a>
                <h2>
                  <a v-if="post.tagName != null" class="layui-badge">{{post.tagName}}</a>
                  <router-link :to="'/post/detail?postId=' + post.id + '&labelId=' + post.labelId">{{post.title}}
                  </router-link>
                </h2>
                <div class="fly-list-info">
                  <!--<router-link :to="'/user/index?userId='+ post.userId">-->
                  <!--<cite>{{post.author}}</cite>-->
                  <!--&lt;!&ndash;-->
                  <!--<i class="iconfont icon-renzheng" title="认证信息：XXX"></i>-->
                  <!--<i class="layui-badge fly-badge-vip">VIP3</i>-->
                  <!--&ndash;&gt;-->
                  <!--</router-link>-->
                  <a>
                    <cite>{{post.author}}</cite>
                  </a>
                  <span>{{post.replytime | getDate}}</span>
                  <span class="fly-list-kiss layui-hide-xs" title="悬赏钻石" v-show="post.rewardGrade != 0">
                    <i class="layui-icon layui-icon-diamond"></i> {{post.rewardGrade}}</span>
                  <span v-if="post.end" class="layui-badge fly-badge-accept layui-hide-xs">已结</span>
                  <span class="fly-list-nums">
                    <i class="iconfont icon-pinglun1" title="回答"></i>{{post.replyCount}}
                  </span>
                </div>
                <div class="fly-list-badge">
                  <span v-if="post.good" class="layui-badge layui-bg-red">精帖</span>
                </div>
              </li>
            </ul>
            <div style="text-align: center" v-if="postList.length > 0">
              <div class="laypage-main">
                <a style="cursor: pointer" @click="nextPage" class="laypage-next">更多求解</a>
              </div>
            </div>
          </div>
        </div>
        <!-- 右侧卡片 -->
        <div class="layui-col-md4">
          <!-- 签到 -->
          <div class="fly-panel fly-signin" v-if="labelId == 0" id="signin">
            <div class="fly-panel-title">
              签到
              <i class="fly-mid"></i>
              <a style="cursor: pointer" @click="signDoc" class="fly-link" id="LAY_signinHelp">说明</a>
              <i class="fly-mid"></i>
              <a style="cursor: pointer" class="fly-link" id="LAY_signinTop" @click="signTop">活跃榜</a>
              <span class="fly-signin-days">已连续签到<cite><span v-text="signCount"></span></cite>天</span>
            </div>
            <div class="fly-panel-main fly-signin-main">
              <button v-if="isSign" class="layui-btn layui-btn-disabled">今日已签到</button>
              <button v-else class="layui-btn layui-btn-danger" @click="saveSign">今日签到</button>
              <span>
                可获得<cite v-text="currGrade"></cite>钻石
                <a @click="openLog" style="cursor: pointer" class="fly-link">记录</a>
              </span>


              <!-- 已签到状态 -->
              <!--
              <button class="layui-btn layui-btn-disabled">今日已签到</button>
              <span>获得了<cite>20</cite>飞吻</span>
              -->
            </div>
          </div>
          <!-- 赞助商 -->
          <div class="fly-panel">
            <div class="fly-panel-title">
              钻石赞助商
              <i class="fly-mid"></i>
              <a href="http://oa.bjjfsoft.com/bbs/#/post/detail?postId=166&labelId=6" class="fly-link">我要加入</a>
            </div>
            <div class="fly-panel-main">
              <a @click="getAD" class="fly-zanzhu" style="background-color: #5FB878; cursor: pointer">期待您的加入</a>
            </div>
          </div>
          <!-- 温馨通道 -->
          <div class="fly-panel" v-if="false" style="height: 171px;">
            <h3 class="fly-panel-title">公告</h3>
            <div class="layui-card-body">
              热烈庆祝金风社区成立！！！<br>
              特别感谢胡俊杰、闵江沛、秦志颖、江茂华等童鞋为金风社区做出的巨大贡献！！！
              <img src="../../static/layui/images/face/63.gif"/>
              <img src="../../static/layui/images/face/63.gif"/>
              <img src="../../static/layui/images/face/63.gif"/>
              <img src="../../static/layui/images/face/63.gif"/>
            </div>
            <!--<ul class="fly-panel-main fly-list-static">-->
            <!--<li>-->
            <!--<a href="http://fly.layui.com/jie/4281/" target="_blank">layui 的 GitHub 及 Gitee (码云) 仓库，欢迎Star</a>-->
            <!--</li>-->
            <!--<li>-->
            <!--<a href="http://fly.layui.com/jie/5366/" target="_blank">-->
            <!--layui 常见问题的处理和实用干货集锦-->
            <!--</a>-->
            <!--</li>-->
            <!--<li>-->
            <!--<a href="http://fly.layui.com/jie/4281/" target="_blank">layui 的 GitHub 及 Gitee (码云) 仓库，欢迎Star</a>-->
            <!--</li>-->

            <!--</ul>-->
          </div>
          <div class="fly-panel" v-if="labelId == 0">
            <h3 class="fly-panel-title">今日热点</h3>
            <ul class="fly-panel-main fly-list-static">
              <li v-for="item in todayHotList">
                <!--<a href="http://fly.layui.com/jie/4281/" target="_blank">{{item.title}}</a>-->
                <router-link :to="'/post/detail?postId=' + item.id + '&labelId=' + item.labelId">{{item.title}}
                </router-link>
              </li>
            </ul>
          </div>
          <!-- 回帖周榜 -->
          <div class="fly-panel fly-rank fly-rank-reply" id="LAY_replyRank" v-if="labelId == 0">
            <h3 class="fly-panel-title">回贴周榜</h3>
            <dl>
              <!--<i class="layui-icon fly-loading">&#xe63d;</i>-->
              <dd v-for="replyTop in replyTopList">
                <router-link :to="'/user/index?userId='+replyTop.userId">
                  <img :src="replyTop.icon == null? defaultAvatar : replyTop.icon">
                  <cite>{{replyTop.author}}</cite><i>{{replyTop.replyCount}}<span>次回答</span></i>
                </router-link>
              </dd>
            </dl>
          </div>
          <!-- 本周热议 -->
          <dl class="fly-panel fly-list-one">
            <dt class="fly-panel-title">本周热议</dt>
            <dd v-for="hot in hotList">
              <router-link :to="'/post/detail?postId=' + hot.id + '&labelId=' + hot.labelId">{{hot.title}}</router-link>
              <span><i class="iconfont icon-pinglun1"></i>{{hot.replyCount}}</span>
            </dd>
            <!-- 无数据时 -->
            <!--
            <div class="fly-none">没有相关数据</div>
            -->
          </dl>
          <!-- 广告区域 -->
          <!--<div class="fly-panel">-->
          <!--<div class="fly-panel-title">-->
          <!--广告区域-->
          <!--</div>-->
          <!--<div class="fly-panel-main">-->
          <!--<a @click="getAD" class="fly-zanzhu" style="background-color: #5FB878; cursor: pointer">敬请期待</a>-->
          <!--</div>-->
          <!--</div>-->
          <div class="fly-panel" style="padding: 20px 0; text-align: center;">
            <img src="../../static/images/weixin.jpg" style="max-width: 100%;" alt="layui">
            <p style="position: relative; color: #666;">微信扫码关注 金风推特 公众号</p>
          </div>
          <!-- 友情链接 -->
          <div class="fly-panel fly-link">
            <h3 class="fly-panel-title">友情链接</h3>
            <dl class="fly-panel-main">
              <dd><a href="http://www.bjjfsoft.com/" target="_blank">金风易通</a></dd>
              <!--<dd><a href="http://layim.layui.com/" target="_blank">WebIM</a></dd>-->
              <!--<dd><a href="http://layer.layui.com/" target="_blank">layer</a></dd>-->
              <!--<dd><a href="http://www.layui.com/laydate/" target="_blank">layDate</a></dd>-->
              <!--<dd><a-->
              <!--href="mailto:xianxin@layui-inc.com?subject=%E7%94%B3%E8%AF%B7Fly%E7%A4%BE%E5%8C%BA%E5%8F%8B%E9%93%BE"-->
              <!--class="fly-link">申请友链</a></dd>-->
            </dl>
          </div>
        </div>
      </div>
    </div>

    <!-- 签到规则 -->
    <div class="layui-text" style="padding: 20px; display: none" id="signDoc">
      <blockquote class="layui-elem-quote">“签到”可获得社区钻石，规则如下</blockquote>
      <table class="layui-table">
        <thead>
        <tr>
          <th>连续签到天数</th>
          <th>每天可获钻石</th>
        </tr>
        </thead>
        <tbody>
        <tr>
          <td>＜5</td>
          <td>5</td>
        </tr>
        <tr>
          <td>≥5</td>
          <td>10</td>
        </tr>
        <tr>
          <td>≥15</td>
          <td>15</td>
        </tr>
        <tr>
          <td>≥30</td>
          <td>20</td>
        </tr>
        </tbody>
      </table>
      <ul>
        <li>中间若有间隔，则连续天数重新计算</li>
        <li style="color: #FF5722;">不可利用程序自动签到，否则钻石清零</li>
      </ul>
    </div>

    <!-- 签到榜 -->
    <div class="layui-tab layui-tab-brief" style="margin: 5px 0 0; display: none" id="signTop">
      <ul class="layui-tab-title">
        <li class="layui-this" @click="getListSign(1)">最新签到</li>
        <li @click="getListSign(2)">今日最快</li>
        <!--<li @click="getListSign(3)">总签到榜</li>-->
      </ul>
      <div class="layui-tab-content fly-signin-list" id="LAY_signin_list">
        <ul class="layui-tab-item layui-show">
          <li v-for="sign in noticeList">
            <a style="cursor: pointer" @click="toUserHome(sign.userId)">
              <img :src="sign.icon == null ? defaultAvatar : sign.icon">
              <cite class="fly-link" :title="sign.username">{{sign.username | subString(6)}}</cite>
            </a>
            <!--<a href="" target="_blank">-->
            <!--<img :src="sign.icon == null ? defaultAvatar : sign.icon">-->
            <!--<cite class="fly-link">{{sign.username}}</cite>-->
            <!--</a>-->
            <span class="fly-grey">签到于 {{sign.initTime}}</span>
          </li>
        </ul>
        <ul class="layui-tab-item">
          <li v-for="sign in noticeList">
            <a style="cursor: pointer" @click="toUserHome(sign.userId)">
              <img :src="sign.icon == null ? defaultAvatar : sign.icon">
              <cite class="fly-link" :title="sign.username">{{sign.username | subString(6)}}</cite>
            </a>
            <span class="fly-grey">签到于 {{sign.initTime}}</span>
          </li>
        </ul>
        <!--<ul class="layui-tab-item">-->
        <!--<li v-for="sign in noticeList">-->
        <!--<a style="cursor: pointer" @click="toUserHome(sign.userId)">-->
        <!--<img :src="sign.icon == null ? defaultAvatar : sign.icon">-->
        <!--<cite class="fly-link" :title="sign.username">{{sign.username | subString(6)}}</cite>-->
        <!--</a>-->
        <!--<span class="fly-grey">已连续签到 <i>{{sign.signCount}} </i>天</span>-->
        <!--</li>-->
        <!--</ul>-->
      </div>
    </div>
    <!-- 文章类型 -->
    <div id="postType">
      <div class="fly-panel" v-for="(label, index) in labelList" style="width: 60px; height: 40px">
        <div class="fly-panel-main">{{label.name}}</div>
      </div>
    </div>
    <!-- 钻石记录 -->
    <el-dialog title="钻石记录" :visible.sync="logBox">
      <el-table :data="logList" size="mini" height="60vh">
        <el-table-column property="initTime" label="时间"></el-table-column>
        <el-table-column property="remarks" label="记录"></el-table-column>
      </el-table>
    </el-dialog>
  </div>
</template>
<script>
  /*import Header from '@/components/Header';*/
  import * as post from '@/api/post';
  import * as reply from '@/api/reply';
  import * as time from '@/utils/time';
  import * as sign from '@/api/sign';
  import * as grade from '@/api/grade';
  import * as label from '@/api/label';
  import * as tag from '@/api/tag';
  import * as log from '@/api/log';

  export default {
    name: "index",
    /* components: {
       'v-header': Header
     },*/
    data() {
      return {
        postList: [],
        topPostList: [],
        replyTopList: [],
        hotList: [],
        noticeList: [],
        currPage: 1,
        pageSize: 15,
        sortType: 0,         // 排序类型：0 时间， 1 热度
        postType: 0,         // 文章类型：0 所有， 1 未结， 2 已结， 3 精华
        labelId: '',
        tagId: 0,
        sortTypeActive: 0,
        postTypeActive: 0,
        signCount: 0, //连续签到次数
        isSign: false,
        currGrade: 0, //今天签到应得的分数
        layer: null,
        $: null,
        todayHotList: [],
        defaultAvatar: require('../../static/images/avatar/4.jpg'),
        labelList: [],
        tagList: [],
        activeTag: -1,
        loading: false,
        loadTop: false,
        logList: [],
        logBox: false
      }
    },
    created() {
      // layui.cache.user = {
      //   username: '游客'
      //   ,uid: -1
      //   ,avatar: '../../../static/images/avatar/00.jpg'
      //   ,experience: 83
      //   ,sex: '男'
      // };
      // layui.config({
      //   version: "3.0.0"
      //   ,base: '../../../static/mods/'
      // }).extend({
      //   fly: 'index'
      // }).use('fly');
      let id = this.$route.query.id ? this.$route.query.id : '';
      this.changeLabel(id);
      this.getTopPostList();
      this.getReplyTop();
      this.getWeekHot();
      // this.init_layui();
      // this.getCount();
      this.getBool();
      this.getCurrGrade();
      this.getTodayHot();
    },
    mounted() {
      let _this = this;
      layui.use(['layer'], function () {
        _this.layer = layui.layer;
        _this.$ = layui.jquery
      });
    },
    watch: {
      '$route.query.id'(val) {
        this.changeLabel(val);
        this.activeTag = '-1';
        this.getTagByLabelId();
      }
    },
    methods: {
      // 跳转到签到
      toSignin() {
        document.querySelector("#signin").scrollIntoView(true);
      },
      toUserHome(userId) {
        this.layer.closeAll();
        this.$router.push('/user/index?userId=' + userId);
      },
      //获取该用户今日签到与否
      getBool() {
        sign.getBool().then(res => {
          // console.log(res.data);
          this.isSign = res.data.isSign;
          this.signCount = res.data.count;
        })
      },
      saveSign() {
        sign.saveSign().then(res => {
          this.getBool();
          this.layer.msg('签到成功，钻石+' + this.currGrade);
        })
      },
      signDoc() {
        this.layer.open({
          type: 1
          , title: '签到说明'
          , area: '300px'
          , shade: 0.8
          , shadeClose: true
          , content: this.$('#signDoc')
        });
      },
      signTop() {
        this.layer.open({
          type: 1
          , title: '签到活跃榜 TOP-20'
          , area: '300px'
          , shade: 0.8
          , shadeClose: true
          , content: this.$('#signTop')
        });
        this.getListSign(1);
      },
      openLog() {
        this.logBox = true;
        let type = 1;  // 钻石记录
        log.getList(type).then(res => {
          // console.log(res.data)
          this.logList = res.data;
        })
      },
      //TODO userId
      // init_layui() {
      //   layui.cache.user = {
      //     username: '游客'
      //     , uid: -1
      //     , avatar: '../../../static/images/avatar/00.jpg'
      //     , experience: 83
      //     , sex: '男'
      //   };
      //   layui.config({
      //     version: "3.0.0"
      //     , base: '../../../static/mods/'
      //   }).extend({
      //     a: 'index'
      //   }).use('a');
      // },
      changeLabel(e) {
        this.labelId = e;
        this.getPostList();
      },
      // 查询文章列表
      getPostList() {
        let obj = {
          currPage: this.currPage,
          pageSize: this.pageSize,
          sortType: this.sortType,
          postType: this.postType,
          labelId: this.labelId,
          tagId: this.tagId,
          beginTime: '',
          endTime: ''
        };
        // console.log("obj.tagId" + obj.tagId);
        this.loading = true;
        post.getList(obj).then(res => {
          this.loading = false;
          this.postList = res.data;
        })
      },
      // 查询置顶文章
      getTopPostList() {
        this.loadTop = true;
        post.getTop().then(res => {
          // console.log(res.data)
          this.loadTop = false;
          this.topPostList = res.data;
        })
      },
      // 设置排序类型
      setSortType(type) {
        this.sortTypeActive = type;
        this.sortType = type;
        this.getPostList();
      },
      setPostType(type) {
        this.currPage = 1;
        this.postTypeActive = type;
        this.postType = type;
        this.getPostList();
      },
      // 查询回复周榜
      getReplyTop() {
        let obj = {
          beginTime: time.getWeekStartDate(),
          endTime: time.getWeekEndDate()
        }
        reply.getTop(obj).then(res => {
          this.replyTopList = res.data;
        })
      },

      // 查询今日热榜
      getTodayHot() {
        // console.log(this.labelId)
        let obj = {
          currPage: 1,
          pageSize: 10,
          sortType: 0,
          postType: 0,
          labelId: this.labelId,
          tagId: 0,
          beginTime: time.formatDate(new Date()),
          endTime: time.formatDate(new Date()),
        };
        // console.log(obj)
        // this.layer.load(0, {shade: false});
        post.getList(obj).then(res => {
          // this.layer.closeAll();
          this.todayHotList = res.data;
        })
      },
      // 获取本周热议
      getWeekHot() {
        // console.log(this.labelId)
        let obj = {
          currPage: 1,
          pageSize: 10,
          sortType: 0,
          postType: 0,
          labelId: this.labelId,
          tagId: 0,
          beginTime: time.getWeekStartDate(),
          endTime: time.getWeekEndDate(),
        };
        // console.log(obj)
        // this.layer.load(0, {shade: false});
        post.getList(obj).then(res => {
          // this.layer.closeAll();
          this.hotList = res.data;
        })
      },
      nextPage() {
        this.currPage += 1;
        let obj = {
          currPage: this.currPage,
          pageSize: this.pageSize,
          sortType: this.sortType,
          postType: this.postType,
          labelId: this.labelId,
          tagId: this.tagId,
          beginTime: '',
          endTime: ''
        };
        this.loading = true;
        post.getList(obj).then(res => {
          res.data.map(item => {
            this.loading = false;
            this.postList.push(item);
          })
        })
      },
      getCurrGrade() {
        grade.getCurr().then(res => {
          this.currGrade = res.data;
        })
      },
      getListSign(listType) {
        sign.listSign(listType).then(res => {
          this.noticeList = res.data;
        })
      },
      getAD() {
        this.layer.msg('期待您的加入');
      },
      selectPostType() {
        this.$router.push('/add/index');
        // this.getAllLabel();
        // this.layer.open({
        //   type: 1
        //   , title: '请选择文章类型'
        //   , area: ['600px', '400px']
        //   , shade: 0.8
        //   , shadeClose: true
        //   , content: this.$('#postType')
        // });
      },
      // 查询所有 Label
      getAllLabel() {
        label.getList().then(res => {
          this.labelList = res.data.list;
        })
      },
      getTagByLabelId() {
        tag.getList(this.labelId).then(res => {
          this.tagList = res.data;
        })
      },
      tagIdChanged(tagId, index) {
        this.activeTag = index;
        this.tagId = tagId;
        this.getPostList();
        this.tagId = 0
      }
    },
    filters: {
      getDate(dateTimeStamp) {
        var minute = 1000 * 60;
        var hour = minute * 60;
        var day = hour * 24;
        var halfamonth = day * 15;
        var month = day * 30;

        if (dateTimeStamp == undefined) {
          return false;
        } else {
          dateTimeStamp = dateTimeStamp.replace(/\-/g, "/");

          var sTime = new Date(dateTimeStamp).getTime();//把时间pretime的值转为时间戳

          var now = new Date().getTime();//获取当前时间的时间戳

          var diffValue = now - sTime;

          if (diffValue < 0) {
            console.log("结束日期不能小于开始日期！");
          }

          var monthC = diffValue / month;
          var weekC = diffValue / (7 * day);
          var dayC = diffValue / day;
          var hourC = diffValue / hour;
          var minC = diffValue / minute;

          if (monthC >= 1) {
            return parseInt(monthC) + "个月前";
          } else if (weekC >= 1) {
            return parseInt(weekC) + "周前";
          } else if (dayC >= 1) {
            return parseInt(dayC) + "天前";
          } else if (hourC >= 1) {
            return parseInt(hourC) + "个小时前";
          } else if (minC >= 1) {
            return parseInt(minC) + "分钟前"
          } else {
            return "刚刚";
          }
        }
      },
      formatDate(date) {
        const da = new Date(date);
        let hours = da.getHours() < 10 ? '0' + da.getHours() : da.getHours();
        let min = da.getMinutes() < 10 ? '0' + da.getMinutes() : da.getMinutes();
        let sec = da.getSeconds() < 10 ? '0' + da.getSeconds() : da.getSeconds();
        return hours + ':' + min + ':' + sec;
      },
      subString(str, n) {
        if (!str) {
          return '';
        }
        let sign = str.length > n ? '...' : '';
        return str.substring(0, n) + sign;
      }
    }
  }
</script>
<style scoped lang="scss">
  .fly-list-one dd span {
    float: right;
  }

  .icon-pinglun1 {
    right: 5px;
  }

  .layuiadmin-card-link a {
    margin-right: 20px;
  }


  @media screen and (max-width: 750px) {
    /deep/ .el-dialog {
      width: 90%;
    }
    /deep/ .el-dialog__body {
      padding: 0 20px;
    }
  }


  @media only screen and (min-width: 750px) {
    /deep/ .el-dialog {
      width: 40%;
    }
    /deep/ .el-dialog__body {
      padding: 0 20px;
    }
  }

</style>
