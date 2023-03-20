<template>
  <el-container>
    <!-- 头部 -->
    <el-header>
      <Pagetitle :title="title1" :SecTitle="title2"></Pagetitle>
    </el-header>
    <!-- 页面主要内容 -->
    <el-main style="padding: 20px 0" class="sec-main">
      <!-- 页面的搜索功能 -->
      <el-form :inline="true" :model="params" class="demo-form-inline">
        <el-form-item label="名称">
          <el-input
            v-model="params.title"
            placeholder="请输入dishName"
            clearable
          ></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-select v-model="params.status" placeholder="请选择" clearable>
            <el-option label="好评" value="1"></el-option>
            <el-option label="差评" value="0"></el-option>
          </el-select>
        </el-form-item>
        <el-form-item>
          <el-button type="primary" @click="onSubmit">查询</el-button>
        </el-form-item>
        <!--
                <el-form-item>
          <el-button type="primary" @click="addSubmit">添加</el-button>
        </el-form-item>
        -->

      </el-form>
      <!-- 表单内容 -->
      <el-table :data="tableData" style="width: 100%" class="table-radius">
        <el-table-column
          prop="disId"
          label="评价ID"
          width="200px"
          align="center"
        >
        </el-table-column>
        <el-table-column prop="title" label="dishName" align="center">
        </el-table-column>
        <el-table-column
          prop="userName"
          label="userName"
          align="center"
        ></el-table-column>
        <el-table-column prop="servTime" label="评价时间" align="center">
        </el-table-column>
        <el-table-column prop="dishScore" label="状态" align="center">
        </el-table-column>
        <el-table-column label="操作" width="250px" align="center">
          <template slot-scope="scope">
            <el-button
              type="info"
              size="mini"
              plain
              @click="seeInfoBtn(scope.row)"
              >查看</el-button
            >
            <!--
             <el-button
              type="warning"
              size="mini"
              plain
              @click="editInfo(scope.$index, scope.row)"
              >编辑</el-button
            >
            -->
            <el-button
              type="danger"
              size="mini"
              plain
              @click="deleteMessageBox(scope.row)"
              >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>

      <!-- 添加的弹出框 -->
      <!--
            <el-dialog title="添加" :visible.sync="addDialogFormVisible">
        <el-form
          ref="addForm"
          :label-position="labelPosition"
          :label-width="formLabelWidth"
          :model="addForm"
          :rules="rules"
        >
          <el-form-item label="问题名称" prop="quesName">
            <el-input
              v-model="addForm.quesName"
              placeholder="请输入问题名称"
            ></el-input>
          </el-form-item>
          <el-form-item label="答案一" prop="ans1">
            <el-input
              v-model="addForm.ans1"
              placeholder="请输入答案"
            ></el-input>
          </el-form-item>
          <el-form-item label="答案二" prop="ans2">
            <el-input
              v-model="addForm.ans2"
              placeholder="请输入答案"
            ></el-input>
          </el-form-item>
          <el-form-item label="状态" prop="state">
            <el-radio-group v-model="addForm.state">
              <el-radio label="启用"></el-radio>
              <el-radio label="禁用"></el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="addDialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="addSure('addForm')"
            >确 定</el-button
          >
        </div>
      </el-dialog>
      -->

      <!-- 查看的弹出框 -->
      <el-dialog
        title="查看"
        :visible.sync="infoDialogTableVisible"
        :center="true"
      >
        <el-descriptions :column="1" border size="medium">
          <el-descriptions-item label="评价ID">{{
            seeInfoTable.disId
          }}</el-descriptions-item>
          <el-descriptions-item label="用户ID">{{
            seeInfoTable.userId
          }}</el-descriptions-item>
          <el-descriptions-item label="状态">{{
            seeInfoTable.dishScore
          }}</el-descriptions-item>
          <el-descriptions-item label="详情">{{
            seeInfoTable.content
          }}</el-descriptions-item>
        </el-descriptions>
        <div slot="footer" class="dialog-footer">
          <el-button @click="infoDialogTableVisible = false">返回</el-button>
        </div>
      </el-dialog>

    </el-main>
    <!-- 分页 -->
    <el-main style="padding: 20px 0">
      <Paging
        @pagesizeChange="getPagesize"
        @currentPageChange="getCurrentPage"
        :total="total"
        :pagesize="this.params.pageSize"
        :currentPage="this.params.pageNum"
      ></Paging>
    </el-main>
  </el-container>
</template>

<script>
import Pagetitle from "../../components/common/title.vue";
import Paging from "../../components/paging/paging.vue";
import request from "@/utils/request";
export default {
  data() {
    return {
      title1: "评价管理",
      title2: "菜品评价",
      total: 1, //总共多少条，这个需要通过接口获取后赋值
      params:{
        pageSize: 4,
        pageNum: 1,
        title: '',
        status: '',
      },
      tableData: [],

      //查看弹窗
      infoDialogTableVisible: false,

      labelPosition: "top", //改变文字所在位置
      //查看存储
      seeInfoTable: {
      },
      formLabelWidth: "80px",
    };
  },
  components: {
    Pagetitle,
    Paging,
  },
  methods: {
    getPagesize(val) {
      this.params.pageSize = val;
      this.load();
    },
    //子组件传入CurrentPage
    getCurrentPage(val) {
      this.params.pageNum = val;
      this.load();
    },
    //获取数据
    load() {
      request.get('/EvaDis/page', {
        params:this.params
      }).then(res => {
        this.tableData = res.data.list;
        this.tableData.forEach((item) => {
          item.servTime = this.timeTransfer(item.servTime);
          if (item.dishScore === '0') {
            item.dishScore = '差评';
          } else {
            item.dishScore = '好评';
          }
        })
        this.total = res.data.total;
      })
    },
    //查看调接口
    seeData(id) {
      request.get("/EvaDis/"+ id).then(res =>{
        this.seeInfoTable = res.data
        this.seeInfoTable.servTime = this.timeTransfer(this.seeInfoTable.servTime);
        if (this.seeInfoTable.dishScore === '0') {
          this.seeInfoTable.dishScore = '差评';
        } else {
          this.seeInfoTable.dishScore = '好评';
        }
      });
    },
    //删除调接口
    deleteData(id) {
      request.get("/EvaDis/cut/" + id).then(res =>{
        if (res.code === '200') {
          console.log("删除成功");
          this.load();
        }
      });
    },
    //搜索按钮函数
    onSubmit() {
      console.log(this.params.evaId);
      this.load();
    },
    //查看信息按钮函数
    seeInfoBtn(row) {
      this.seeData(row.disId);
      this.infoDialogTableVisible = true;
      this.load();
    },
    //删除按钮函数
    deleteMessageBox(row) {
      this.$confirm("此操作将删除该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true,
      }).then(() => {
        //在这里调用后台
        this.deleteData(row.disId);
        this.$message({
          type: "success",
          message: "删除成功!",
          duration: 700,
        });
      }).catch(() => {
        this.$message({
          type: "info",
          message: "已取消删除",
          duration: 700,
        });
      });
      this.load();
    },
    //时间戳的转换
    timeTransfer(timestamp) {
      var date = new Date(timestamp); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
      var Y = date.getFullYear() + "-";
      var M =
          (date.getMonth() + 1 < 10
              ? "0" + (date.getMonth() + 1)
              : date.getMonth() + 1) + "-";
      var D =
          (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " ";
      var h =
          (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":";
      var m =
          (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) +
          ":";
      var s =
          date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
      return Y + M + D + h + m + s;
    },
  },
  created() {
    this.load();
  },
};
</script>

<style scoped>
.table-radius {
  border-radius: 5px;
}
</style>