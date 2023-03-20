<template>
  <el-container>
    <el-header>
      <Pagetitle :title="title1" :SecTitle="title2"></Pagetitle>
    </el-header>

    <el-main style="padding: 10px 0; border-radius: 5px">
      <el-main class="search">
        <span>类型</span>
        <el-input v-model="params.title" placeholder="请输入类型" style="width: 200px">
        </el-input>
        <span>状态</span>
        <el-select v-model="params.status" placeholder="请选择状态">
          <el-option label="禁用" value="0"></el-option>
          <el-option label="启用" value="1"></el-option>
        </el-select>
        <el-button type="primary" style="margin: 0 10px; float: right" @click="searchData"
        >筛选</el-button
        >
        <el-button
            type="primary"
            style="margin: 0 10px; float: right"
            @click="dialogFormVisible = true"
        >添加</el-button
        >
      </el-main>
      <el-table :data="tableData" style="width: 100%; padding: 10px 10px">
        <el-table-column prop="title" label="类型名称" width="200">
        </el-table-column>
        <el-table-column prop="publishTime" label="发布时间"> </el-table-column>
        <el-table-column prop="status" label="状态"> </el-table-column>
        <el-table-column prop="operation" label="操作" width="350">
          <template slot-scope="scope">
            <el-button
                type="info"
                size="mini"
                plain
                @click="seeInfoBtn(scope.$index, scope.row)"
            >查看</el-button
            >
            <el-button
                type="primary"
                size="mini"
                plain
                @click="editInfoBtn(scope.$index, scope.row)"
            >编辑</el-button
            >
            <el-button
                type="danger"
                size="mini"
                plain
                @click="deleteInfo(scope.$index, scope.row)"
            >删除</el-button
            >
          </template>
        </el-table-column>
      </el-table>
      <el-dialog title="发布" :visible.sync="dialogFormVisible" center>
        <el-form :model="form">
          <el-form-item label="类型名称：" :label-width="formLabelWidth">
            <el-input v-model="form.title" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="状态：" :label-width="formLabelWidth">
            <el-radio-group v-model="form.status">
              <el-radio :label="1">启用</el-radio>
              <el-radio :label="0">禁用</el-radio>
            </el-radio-group>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
          <el-button type="primary" @click="addInfoData">确 定</el-button>
        </div>
      </el-dialog>
      <el-dialog title="查看" :visible.sync="InfoDialogFormVisible" center>
        <el-descriptions title="" :column="1" border>
          <el-descriptions-item label="类型名称">{{
              seeInfoTable.title
            }}</el-descriptions-item>
          <el-descriptions-item label="状态">
            <span v-if="seeInfoTable.status === 0">禁用</span>
            <span v-if="seeInfoTable.status === 1">启用</span>
          </el-descriptions-item>
        </el-descriptions>
        <div slot="footer" class="dialog-footer">
          <el-button @click="InfoDialogFormVisible = false">返回</el-button>
        </div>
      </el-dialog>
      <el-dialog title="修改" :visible.sync="EditDialogFormVisible" center>
        <el-descriptions title="" :column="1" border>
          <el-descriptions-item label="类型名称">
            <el-input v-model="seeInfoTable.title" autocomplete="off"></el-input>
          </el-descriptions-item>
          <el-descriptions-item label="状态">
            <el-radio-group v-model="seeInfoTable.status">
              <el-radio :label= "1">启用</el-radio>
              <el-radio :label= "0">禁用</el-radio>
            </el-radio-group>
          </el-descriptions-item>
        </el-descriptions>
        <div slot="footer" class="dialog-footer">
          <el-button @click="EditDialogFormVisible = false">返回</el-button>
          <el-button type="primary" @click="editSure">确认</el-button>
        </div>
      </el-dialog>
    </el-main>

    <el-main style="padding: 10px 0">
      <Paging
          @pagesizeChange="getPageSize"
          @currentPageChange="getCurrentPage"
          :total="this.total"
          :pagesize="this.params.pageSize"
          :currentPage="this.params.pageNum"
      ></Paging>
    </el-main>
  </el-container>
</template>
<style lang="less" scoped>
.search {
  background-color: #fff;
  margin-top: 10px;
  & > span {
    padding: 0 10px;
  }
}
</style>
<script>
import Paging from "../../components/paging/paging.vue";
import Pagetitle from "../../components/common/title.vue";
import request from "@/utils/request";

export default {
  data() {
    return {
      tableData: [],
      title1: "异常信息",
      title2: "消息类型",
      total: 0, //总共多少条，这个需要通过接口获取后赋值
      params:{
        pageNum: 1, //当前页数
        pageSize: 4, //有2，4，6，8，10可选
        title : "",
        status: "",
      },
      date: "",
      dialogFormVisible: false,
      InfoDialogFormVisible: false,
      EditDialogFormVisible: false,
      form: {
        title: "",
        status: 0,
      },
      seeInfoTable: {
        abTypeId: "",
        status: "",
        title: "",
      },
      formLabelWidth: "120px",
    };
  },
  components: {
    Pagetitle,
    Paging,
  },
  methods: {
    //子组件传入pageSize
    getPageSize(val) {
      this.params.pageSize = val;
      this.load();
    },
    //子组件传入CurrentPage
    getCurrentPage(val) {
      this.params.pageNum = val;
      this.load();
    },

    load(){
      request.get('/MessageType/page', {
        params:this.params
      }).then(res => {
        res.data.list.forEach((item) => {
          item.publishTime = this.timeTransfer(item.publishTime);
          item.status = this.statusTransfer(item.status);
        });
        this.tableData = res.data.list;
        this.total = res.data.total;
      })
    },
    //查找接口
    searchData() {
      this.load();
    },
    //编辑信息
    editInfoBtn(index, row) {
      this.EditDialogFormVisible = true;
      this.load();
      this.implicitSeeInfo(row.abTypeId);

    },
    //查看信息
    seeInfoBtn(index, row) {
      this.load();
      this.seeInfo(row.abTypeId);
    },
    //查看信息接口
    seeInfo(seeId) {
      request.get("/MessageType/"+seeId).then(res =>{
        if (res.code === '200') {
          this.seeInfoTable.title = res.data.title;
          this.seeInfoTable.status = res.data.status;
          this.InfoDialogFormVisible = true;
        }
      });
    },
    implicitSeeInfo(seeId) {
      //  this.seeInfoTable = {};
      request.get("/MessageType/"+seeId).then(res =>{
        this.seeInfoTable.title = res.data.title;
        this.seeInfoTable.status = res.data.status;
        this.seeInfoTable.abTypeId = seeId;
      });
    },
    //确认编辑信息
    editSure() {
      //调用后台
      this.editData();
    },
    //调用编辑信息接口
    editData() {
      request.put('/MessageType/edit',this.seeInfoTable).then(res => {
        if(res.code === '200'){
          this.$notify.success('更新成功')
        }else {
          this.$notify.error(res.msg)
        }
        this.load();
      })
      this.EditDialogFormVisible = false;
    },
    //添加信息
    addInfoData() {
      this.dialogFormVisible = false;
      this.addData();
    },
    //添加信息接口
    addData() {
      request.post('MessageType/Add',this.form).then(res => {
        if(res.code === '200'){
          this.$notify.success('新增成功')
        }else {
          this.$notify.error(res.msg)
        }
      });
      this.load();
    },
    //删除信息
    deleteInfo(index, row) {
      this.$confirm("此操作将删除该记录, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warning",
        center: true,
      }).then(async () => {
        this.deleteData(row.abTypeId);
        await(500);
        this.$message({
          type: "success",
          message: "删除成功!",
          duration: 700,
        });
      });
    },
    //调用删除接口
    deleteData(seeId) {
      request.get("/MessageType/cut/"+seeId).then(res =>{
        if (res.code === '200') {
          console.log("删除成功");
        }
      });
      this.load();
    },
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

    statusTransfer(item) {
      if (item === 0) {
        item = "禁用";
      } else if (item === 1) {
        item = "启用";
      }
      return item;
    },
  },
  created() {
    this.load();
  },
};
</script>