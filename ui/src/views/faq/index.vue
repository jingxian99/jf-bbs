<template>
  <div class="layui-container">
    <!--<el-row :gutter="10">-->
    <!--<el-col :lg="18" :xs="24">-->
    <!--<el-card shadow="never">-->
    <!--<div slot="header">-->
    <!--<el-input placeholder="请输入产品或问题" autofocus>-->
    <!--<el-button slot="append" type="primary" icon="el-icon-search"></el-button>-->
    <!--</el-input>-->
    <!--</div>-->
    <!--<div class="faq_cont_list">-->
    <!--<dl>-->
    <!--<dd v-for="i in 5">-->
    <!--<a href="">选中一个单元格输入，输入括号（）以后，光标会飘到前面一个单元格，怎么办？</a>-->
    <!--</dd>-->
    <!--</dl>-->
    <!--</div>-->
    <!--</el-card>-->
    <!--</el-col>-->
    <!--<el-col :lg="6" :xs="24">-->
    <!--<el-card shadow="never">-->
    <!--<div slot="header">-->
    <!--<span style="font-size: 18px">热门问题</span>-->
    <!--</div>-->
    <!--</el-card>-->
    <!--</el-col>-->
    <!--</el-row>-->
    <el-row :gutter="10">
      <el-col :lg="6" :xs="24" class="faq-tree">
        <el-tabs type="border-card" class="md8">
          <el-tab-pane label="FAQ分类">
            <el-tree
              default-expand-all
              :data="labelList"
              :props="defaultProps"
              highlight-current
              @node-click="handleNodeClick">
            </el-tree>
          </el-tab-pane>
        </el-tabs>
      </el-col>
      <el-col :lg="18" :xs="24" v-show="listBox" class="faq-tabs">
        <el-button size="mini" class="extra" type="primary" v-if="isAdd" @click="addFAQ">新增</el-button>
        <el-input placeholder="请输入关键字" size="mini" v-model="filterText" @input="filterFAQ"></el-input>
        <el-tabs type="border-card">
          <el-tab-pane label="FAQ列表"></el-tab-pane>
          <!-- <el-tab-pane label="热门问题"></el-tab-pane>-->
          <div class="faq-list" v-if="!filterText">
            <el-card shadow="never" v-for="(item, index) in findList" :key="index">
              <el-link type="primary" @click="faqDetail(item)" :title="item.question">
                <span>{{index + 1}}</span>. {{item.question | subString(18)}}
              </el-link>
              <el-row type="flex" justify="center" class="icon-head">
                <span><svg-icon icon-class="head"></svg-icon>{{item.useful}}</span>
                <span><svg-icon icon-class="head-1"></svg-icon>{{item.useless}}</span>
              </el-row>
              <div v-if="item.userId == $store.state.user.id" class="faq-list-tools">
                <span @click="editFAQ(item)"><i class="el-icon-edit"></i></span>
                <span @click="deleteFAQ(item)"><i class="el-icon-delete"></i></span>
              </div>
            </el-card>
          </div>
          <!-- 过滤时列表 -->
          <div class="faq-list" v-else>
            <el-card shadow="never" v-for="(item, index) in filterFAQList" :key="index">
              <el-link type="primary" @click="faqDetail(item)">
                <span>{{index + 1}}</span>. {{item.question | subString(18)}}
              </el-link>
              <el-row type="flex" justify="center" class="icon-head">
                <span><svg-icon icon-class="head"></svg-icon>{{item.useful}}</span>
                <span><svg-icon icon-class="head-1"></svg-icon>{{item.useless}}</span>
              </el-row>
              <div v-if="item.userId == $store.state.user.id" class="faq-list-tools">
                <span @click="editFAQ(item)"><i class="el-icon-edit"></i></span>
                <span @click="deleteFAQ(item)"><i class="el-icon-delete"></i></span>
              </div>
            </el-card>
          </div>
        </el-tabs>
      </el-col>
      <el-col :lg="18" :xs="24" v-show="detailBox">
        <el-card shadow="never">
          <div slot="header">
            <el-page-header @back="goBack" :content="toFaq.question"></el-page-header>
          </div>
          <p v-html="toFaq.answer" class="photos" id="detail-body"></p>
          <el-divider></el-divider>
          <el-row type="flex" justify="center" class="icon-head">
            <span :class="{active: isUseful == '1'}" @click="setUseful('1')">
              <svg-icon icon-class="head"></svg-icon>有用({{toFaq.useful || 0}})
            </span>
            <span :class="{active: isUseful == '0'}" @click="setUseful('0')">
              <svg-icon icon-class="head-1"></svg-icon>没用({{toFaq.useless || 0}})
            </span>
          </el-row>
        </el-card>
      </el-col>
      <el-col :lg="18" :xs="24" v-show="addBox">
        <el-card shadow="never">
          <div slot="header">
            <el-page-header @back="closeFAQ" content="新增FAQ"></el-page-header>
          </div>
          <el-form v-model="faqForm" label-width="60px">
            <el-form-item label="Q:">
              <el-input v-model="faqForm.question" size="small"></el-input>
            </el-form-item>
            <el-form-item label="A:">
              <!-- 富文本 -->
              <div v-model="faqForm.answer" ref="editor" class="editor"></div>
            </el-form-item>
          </el-form>
          <el-row type="flex" justify="end">
            <el-button size="small" @click="closeFAQ">取消</el-button>
            <el-button size="small" type="primary" @click="saveFAQ">保存</el-button>
          </el-row>
        </el-card>
      </el-col>
    </el-row>
    <!-- 新增 -->
  </div>
</template>

<script>
  import E from 'wangeditor'
  import * as faq from '@/api/faq';
  import {isPC} from '@/utils/common';
  import * as face from '@/assets/face.json';


  export default {
    name: "index",
    data() {
      return {
        isAdd: false, // 新增
        filterText: '', // 过滤
        listBox: true,
        detailBox: false,
        addBox: false,
        labelList: [],
        faqList: [],
        findList: [],
        filterFAQList: [], // 过滤后的结果
        faq: '',
        toFaq: '',
        isUseful: '', // 评价是否有用
        defaultProps: {
          id: 'id',
          children: 'children',
          label: 'name',
          parentId: 'parentId'
        },
        faqForm: {
          id: '',
          question: '',
          answer: '',
          typeId: ''
        },
        editor: null,
        layer: null,
      }
    },
    created() {
      this.getFaq();
      this.getFaqList("");
    },
    mounted() {
      this.initEditor();
    },
    methods: {
      layui() {
        let _this = this;
        layui.use(['layer'], function () {
          _this.layer = layui.layer;
          _this.layer.photos({
            photos: '#detail-body',
            anim: 5 //0-6的选择，指定弹出图片动画类型，默认随机（请注意，3.0之前的版本用shift参数）
          });
        });
      },
      initEditor() {
        let _this = this;
        this.editor = new E(this.$refs.editor)
        this.editor.customConfig.debug = true

        this.editor.customConfig.onchange = (html) => {
          _this.faqForm.answer = html;
        };
        this.editor.customConfig.uploadImgServer = window.localStorage.baseUrl + '/upload/file2';
        this.editor.customConfig.uploadFileName = 'file';
        // 移动端
        this.editor.customConfig.menus = [
          'head',
          'bold',
          'italic',
          'underline',
          'image',
          'emoticon'
        ];

        this.editor.customConfig.emotions = [
          {
            // tab 的标题
            title: '默认',
            // type -> 'emoji' / 'image'
            type: 'image',
            // content -> 数组
            content: face
          },
          {
            // tab 的标题
            title: 'emoji',
            // type -> 'emoji' / 'image'
            type: 'emoji',
            // content -> 数组
            content: [
              '😀', '😁', '😂', '🤣', '😃', '😅', '😆', '😉', '😊', '😋', '😎', '😍', '😘'
            ]
          }
        ];
        this.editor.customConfig.zIndex = 1;
        this.editor.create();
      },
      getFaq() {
        faq.getFaq().then(res => {
          this.labelList = res.data;
        })
      },
      getFaqList(typeId) {
        return new Promise(resolve => {
          let id = typeId === '' ? '' : Number(typeId);
          faq.getFaqList(id).then(res => {
            this.faqList = res.data;
            this.findList = this.faqList;
            resolve()
          })
        })
      },
      findFaqList(typeId) {
        // 查询结果
        let arr = [];
        this.faqList.forEach(e => {
          if (e.typeId == typeId) {
            arr.push(e)
          }
        });
        this.findList = arr;
      },
      handleNodeClick(data) {
        if (data.parentId !== 0) {
          if (this.detailBox || this.addBox) {
            this.detailBox = false;
            this.closeFAQ();
          } else {
            this.isAdd = true;
            this.faqForm.typeId = data.id;
            this.faq = data;
            this.getFaqList(this.faq.id);
          }
        }
      },
      faqDetail(item) {
        this.listBox = false;
        this.detailBox = true;
        this.toFaq = item;
        this.isUseful = item.thought === null ? '' : item.thought === true ? '1' : '0';
        /* 包含图片时可大图查看 */
        this.$nextTick(() => {
          this.layui();
        })
      },
      goBack() {
        this.listBox = true;
        this.detailBox = false;
      },
      /* 评价FAQ是否有用 */
      setUseful(item) {
        if (this.isUseful !== '') {
          return this.$message({type: 'success', message: '您已经评价过了！', duration: 1000})
        }
        this.isUseful = item;
        let faqId = this.toFaq.id;
        let isGood = this.isUseful == '1' ? true : false;
        if (item == '0') {
          this.toFaq.useless += 1;
        } else {
          this.toFaq.useful += 1;
        }
        faq.good(faqId, isGood).then(res => {
          this.getFaqList(this.faqForm.typeId)
          this.$message({type: 'success', message: res.msg, duration: 1000})
        })
      },
      /* 新增FAQ */
      addFAQ() {
        this.addBox = true;
        this.listBox = false;
        this.faqForm.question = '';
        this.faqForm.answer = '';
        this.faqForm.id = '';
      },
      /* 保存FAQ */
      saveFAQ() {
        let data = {
          question: this.faqForm.question,
          answer: this.faqForm.answer,
          typeId: +this.faqForm.typeId,
          id: this.faqForm.id
        }
        faq.update(data).then(res => {
          this.closeFAQ();
          this.$message({type: 'success', message: res.msg, duration: 1000})
        })
      },
      /* 取消FAQ */
      closeFAQ() {
        this.addBox = false;
        this.listBox = true;
        this.getFaqList(this.faqForm.typeId)
      },
      /* 编辑FAQ */
      editFAQ(item) {
        this.addBox = true;
        this.listBox = false;
        this.faqForm.id = item.id;
        this.faqForm.question = item.question;
        this.faqForm.typeId = item.typeId;
        this.editor.txt.html(item.answer);
      },
      /* 删除FAQ */
      deleteFAQ(item) {
        this.$confirm(`即将删除此FAQ，是否继续?`, '提示', {
          confirmButtonText: '确定',
          cancelButtonText: '取消',
          type: 'warning'
        }).then(() => {
          faq.del(item.id).then(res => {
            this.$message({type: 'success', message: res.msg, duration: 1000});
            this.getFaqList(this.faqForm.typeId);
          })
        }).catch(() => {
        });
      },
      /* 过滤 */
      filterFAQ(text) {
        let arr = this.findList.filter(item => {
          return item.question.indexOf(text) != -1;
        })
        this.filterFAQList = arr;
      }
    }
  }
</script>

<style scoped lang="scss">
  .layui-container {

  }

  /deep/ .el-tabs--border-card {
    box-shadow: none;
  }

  .md8 {
    margin-bottom: 8px;
  }

  .faq-tree {
    position: relative;

  }

  .faq-tabs {
    position: relative;
    .extra {
      position: absolute;
      top: 6px;
      right: 180px;
      z-index: 1;
    }
    & > .el-input {
      position: absolute;
      right: 12px;
      top: 6px;
      width: 160px;
      z-index: 1;
      /deep/ input {
        border-radius: 0;
      }
    }
    .faq-list {
      .el-card {
        position: relative;
        margin-bottom: 10px;
        /deep/ .el-card__body {
          padding: 10px;
          overflow: hidden;
        }
      }
      .el-link {
        margin: 4px 0;
        span {
          text-align: center;
          display: inline-block;
          width: 24px;
        }
      }
      &-tools {
        float: right;
        margin-top: 8px;
        span {
          cursor: pointer;
          margin: 0px 4px;
        }
      }
      .icon-head {
        float: right;
        margin-top: 8px;
      }
    }
  }

  .icon-head {
    span {
      cursor: pointer;
      margin: 0 10px;
      color: #666;
      .svg-icon {
        margin-right: 4px;
      }
      &.active {
        color: #009688;
      }
    }
  }

  .editor {
    text-align: left;
    /deep/ .w-e-toolbar {
      background-color: #fff !important;
      border: 1px solid #DCDFE6 !important;
    }

    /deep/ .w-e-text {
      overflow-y: auto;

      p {
        margin: 10px 0;
      }
    }

    /deep/ .w-e-text-container {
      height: 500px !important;
      border: 1px solid #DCDFE6 !important;
    }

    /deep/ .w-e-toolbar .w-e-menu {
      line-height: 24px;
    }
  }
</style>
