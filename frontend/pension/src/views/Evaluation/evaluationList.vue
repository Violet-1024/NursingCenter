<template>
  <el-container>
    <el-header>
      <Pagetitle :title="title1" :SecTitle="title2"></Pagetitle>
    </el-header>
    <el-main class="pagination-padding">
      <!-- 上边的搜索栏 -->
      <el-row>
        <el-col>
          <el-form :inline="true" :model="params" class="demo-form-inline">
            <el-form-item label="活动时间">
              <el-date-picker
                  v-model="params.servTime"
                  value-format="yyyy-MM-dd"
                  format="yyyy-MM-dd"
                  type="date"
                  placeholder="选择活动日期">
              </el-date-picker>
            </el-form-item>
            <el-form-item label="编号">
              <el-input
                v-model="params.evaId"
                placeholder="编号"
                clearable
              ></el-input>
            </el-form-item>
            <el-form-item label="用户">
              <el-input
                v-model="params.userName"
                placeholder="用户"
                clearable
              ></el-input>
            </el-form-item>
            <el-form-item label="陪护员">
              <el-input
                v-model="params.nurse"
                placeholder="陪护员"
                clearable
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="onSubmit">筛选</el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>

      <!-- 下边的列表 -->
      <el-table :data="tableData" style="width: 100%" class="table-radius">
        <el-table-column
          prop="evaId"
          label="订单编号"
          width="200"
          align="center"
        >
        </el-table-column>
        <el-table-column prop="userId" label="ID" width="250" align="center">
        </el-table-column>
        <el-table-column prop="userName" label="姓名" width="250" align="center">
        </el-table-column>
        <!--
                <el-table-column
          prop="serviceType"
          label="服务类型"
          width="130"
          header-align="center"
          align="center"
        >
        </el-table-column>
        -->

        <el-table-column
          prop="nurse"
          label="陪护员"
          width="100"
          align="center"
        >
        </el-table-column>
        <el-table-column label="评分" width="250" align="center">
          <template slot-scope="scope">
            <el-rate
              :value="scope.row.score"
              disabled
              show-score
              text-color="#ff9900"
              score-template="{value}"
            >
            </el-rate>
          </template>
        </el-table-column>
        <el-table-column
          prop="createTime"
          label="评价时间"
          width="400"
          align="center"
        >
        </el-table-column>
        <el-table-column label="操作" width="160" align="center">
          <template slot-scope="scope">
            <el-button type="info" size="mini" plain @click="seeInfo(scope.row)"
              >查看</el-button
            >
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

      <!-- 点击查看的弹出的消息框 -->
      <el-dialog title="查看" :visible.sync="dialogTableVisible" :center="true">
        <el-descriptions class="margin-top" :column="1" size="medium" border>
          <el-descriptions-item>
            <template slot="label"> 订单编号 </template>
            {{ infoData.evaId }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label"> ID </template>
            {{ infoData.userId }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label"> 姓名 </template>
            {{ infoData.userName }}
          </el-descriptions-item>
          <!--
                  <el-descriptions-item>
            <template slot="label"> 服务类型 </template>
            {{ infoData.serviceType }}
          </el-descriptions-item>
          -->
          <el-descriptions-item>
            <template slot="label"> 陪护员 </template>
            {{ infoData.nurse }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label"> 评分 </template>
            {{ infoData.serviceScore }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label"> 服务时长 </template>
            {{ infoData.createTime }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label"> 服务态度 </template>
            {{ infoData.servTime }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label"> 专业程度 </template>
            {{ infoData.professional }}
          </el-descriptions-item>
          <el-descriptions-item>
            <template slot="label"> 内容 </template>
            {{ infoData.content }}
          </el-descriptions-item>
        </el-descriptions>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogTableVisible = false">返回</el-button>
        </div>
      </el-dialog>
    </el-main>

    <!-- 分页 -->
    <el-main class="pagination-padding">
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
      title2: "评价列表",
      total: 5, //总共多少条，这个需要通过接口获取后赋值
      params: {
        pageSize: 4,
        pageNum: 1,
        servTime: null,
        evaId: '',
        userName: '',
        nurse: '',
      },
      dialogTableVisible: false,
      //通过回台获取赋值给tableData
      tableData: [],
      //点击查看后通过后台查询的数据
      infoData: {
        evaId: "",
        userId: "",
        userName: "",
        nurse: "",
        serviceScore: "",
        createTime: '',
        servTime: '',
        professional: "",
        content: "",
      },
    };
  },
  components: {
    Pagetitle,
    Paging,
  },
  methods: {
    //子组件传入pagesize
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
      request.get('/Eva/page', {
        params:this.params
      }).then(res => {
        this.tableData = res.data.list;
        this.tableData.forEach((item) => {
          item.servTime = this.timeTransfer(item.servTime);
          item.createTime = this.timeTransfer(item.createTime);
          item.score = parseInt(item.serviceScore);
        })
        this.total = res.data.total;
      })
    },
    //查看调接口
    seeData(id) {
      request.get("/Eva/"+ id).then(res =>{
        this.infoData = res.data
        this.infoData.servTime = this.timeTransfer(this.infoData.servTime);
        this.infoData.createTime = this.timeTransfer(this.infoData.createTime);
      });
    },
    //删除调接口
    deleteData(id) {
      request.get("/Eva/cut/" + id).then(res =>{
        if (res.code === '200') {
          console.log("删除成功");
        }
      });
      this.load();
    },
    //搜索按钮函数
    onSubmit() {
      console.log(this.params.evaId);
      this.load();
    },
    //查看信息按钮函数
    seeInfo(row) {
      this.seeData(row.evaId);
      this.dialogTableVisible = true;
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
          this.deleteData(row.evaId);
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

<style lang="less" scoped>
.pagination-padding {
  padding: 20px 0;
}
.table-radius {
  border-radius: 5px;
}
</style>