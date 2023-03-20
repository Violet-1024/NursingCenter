<template>
  <el-container>
    <el-header>
      <Pagetitle :title="this.title1" :SecTitle="this.title2"></Pagetitle>
    </el-header>

    <el-main style="padding: 10px 0; border-radius: 50px">
      <el-main class="search">
        <span>时间</span>
        <el-date-picker
            v-model="params.startDate"
            type="date"
            placeholder="选择日期"
            value-format="timestamp"
        >
        </el-date-picker>
        <span>标题</span>
        <el-input
            v-model="params.title"
            placeholder="请输入标题"
            style="width: 200px"
        ></el-input>
        <span>发布人</span>
        <el-input
            v-model="params.publisher"
            placeholder="请输入发布人"
            style="width: 200px"
        ></el-input>
        <el-button type="primary" style="margin: 0 10px ;float:right" @click="searchData"
        >查询</el-button
        >
        <el-button
            type="primary"
            style="margin: 0 10px; float: right"
        >发布</el-button
        >
      </el-main>
      <el-table :data="tableData" style="width: 100%; padding: 10px 30px">
        <el-table-column prop="title" label="消息标题" width="200">
        </el-table-column>
        <el-table-column prop= "state"  label="消息类型" width="200">
        </el-table-column>
        <el-table-column prop="startDate" style="width: 100px"   label="发布时间"> </el-table-column>
        <el-table-column prop="publisher" label="发布人"> </el-table-column>
        <el-table-column prop="audiStatus" label="审核信息"> </el-table-column>
        <el-table-column prop="operation" label="操作" width="250">
          <template slot-scope="scope">
            <el-button
                type="info"
                size="mini"
                plain
                @click="seeInfoBtn(scope.$index, scope.row)">查看</el-button>
          </template>
        </el-table-column>
      </el-table>

      <!--  发布框    -->
      <el-dialog title="发布" :visible.sync="this.dialogFormVisible" custom-class="LiangSen" center>
        <el-form :model="form">
          <el-form-item label="标题：" :label-width="formLabelWidth">
            <el-input v-model="form.title" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="类型：" :label-width="formLabelWidth">
            <el-select v-model="form.state" placeholder="请选择类型">
              <el-option
                  v-for="item in typeListData"
                  :key="item.title"
                  :label="item.title"
                  :value="item.status"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="内容：" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="form.remark"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="exitSure">取 消</el-button>
          <el-button type="primary" @click="addSure">确 定</el-button>
        </div>
      </el-dialog>


      <el-dialog title="查看" :visible.sync="InfoDialogTableVisible" center>
        <el-descriptions title="" :column="1" border>
          <el-descriptions-item label="标题">{{
              this.seeInfoTable.title
            }}</el-descriptions-item>
          <el-descriptions-item label="类型">{{
              this.seeInfoTable.state
            }}</el-descriptions-item>
          <el-descriptions-item label="内容">{{
              this.seeInfoTable.remark
            }}</el-descriptions-item>
        </el-descriptions>

        <div slot="footer" class="dialog-footer">
          <el-button type="primary" @click="auditSure">确认审核</el-button>
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

<style>
.LiangSen{

}
</style>
<script>
import Paging from "../../components/paging/paging.vue";
import Pagetitle from "../../components/common/title.vue";
import request from "@/utils/request";

export default {
  name: 'addAbInfo',
  data() {
    return {
      index: null,
      row : null,
      tableData: [],
      typeListData: [],
      listData : new Map(),
      auditInfo:[
        'Not Audited',
        'Audited'
      ],
      title1: "异常信息",
      title2: "发布消息",
      total: 0, //总共多少条，这个需要通过接口获取后赋值
      dialogFormVisible: true,
      InfoDialogTableVisible: false,
      params:{
        pageNum: 1,
        pageSize: 4,
        title: '',
        publisher: '',
        startDate: null,
      },
      typeParams:{
        pageNum: 1,
        pageSize: 10000,
        title : "",
        status: "",
      },
      //form中的用户信息希望通过统一接口获取
      form: {
        title: "",
        remark: "",
        state: "",
        publisher: "mio",
        customerId: "0000001",
      },
      seeInfoTable: {},
      formLabelWidth: "120px",
    };
  },
  components: {
    Pagetitle,
    Paging,
  },
  methods: {
    // 将 router 填写完整
    addSure() {
      this.form.customerId = this.$route.query.id;

      if (this.form.title !== '' && this.form.state !== '') {
        request.post('MessageManage/Add',this.form).then(res => {
          this.$notify.success('新增成功')

          this.$router.push("/mainforuser/customerPage?id=" + id)         //  <<==
        })
      } else {
        this.$message({
          type: "info",
          message: "请将信息填写完整",
          duration: 700,
        });
      }

      this.load();
    },
    exitSure() {
      const id = this.$route.query.id
      this.$router.push("/mainforuser/customerPage?id=" + id)             //  <<==
    },

    auditSure() {
    },
    //子组件传入pagesize
    getPageSize(val) {
      this.params.pageSize = val;
      console.log(this.params.pageSize);
      this.load();
    },
    //子组件传入CurrentPage
    getCurrentPage(val) {
      this.params.pageNum = val;
      console.log(this.params.pageNum);
      this.load();
    },
    load() {
      request.get('/MessageManage/page', {
        params:this.params
      }).then(res => {
        res.data.list.forEach((item) => {
          item.startDate = this.timeTransferForManage(item.startDate);
          item.audiStatus = this.auditInfo[item.audiStatus];
          item.state = this.listData.get(item.state);
        });
        this.tableData = res.data.list
        this.total = res.data.total
      })
    },
    getType(state){
      this.typeListData.forEach((data) => {
        if (state === data.state) {
          return data.title;
        }
      })
      return 'type not found';
    },
    //获取消息类型，暂不需要, 现在有type，所以需要了
    getData() {
      request.get('/MessageType/getType', {
        params:this.typeParams
      }).then(res => {
        this.typeListData = res.data.list;
        for (let i = 0; i < this.typeListData.length; i++) {
          this.listData.set(this.typeListData[i].state, this.typeListData[i].title);
        }
        console.log(this.typeListData.length)
        console.log(this.listData.size)
      });
    },

    seeInfoBtn(index, row) {
    },
    //查看接口
    seeInfo(seeId) {
    },

    searchData() {
    },
    timeTransferForManage(timestamp) {
    },
  },
  created() {
    this.getData();
    setTimeout(() => this.load(), 200)
  },
};
</script>

