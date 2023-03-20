<template>
  <el-container>
    <el-header>
      <Pagetitle :title="this.title1" :SecTitle="this.title2"></Pagetitle>
    </el-header>

    <el-main style="padding: 10px 0; border-radius: 5px">
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
            @click="addbtn"
        >发布</el-button
        >
      </el-main>
      <el-table :data="tableData" style="width: 100%; padding: 10px 10px">
        <el-table-column prop="title" label="消息标题" width="200">
        </el-table-column>
        <el-table-column prop= "state"  label="消息类型" width="200">
        </el-table-column>
        <el-table-column prop="startDate" style="width: 100px"   label="发布时间"> </el-table-column>

        <!--
                <el-table-column prop="publisher" label="发布人"> </el-table-column>
        -->

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
      <el-dialog title="发布" :visible.sync="dialogFormVisible" center>
        <el-form :model="form">
          <el-form-item label="标题：" :label-width="formLabelWidth">
            <el-input v-model="form.title" autocomplete="off"></el-input>
          </el-form-item>
          <el-form-item label="类型：" :label-width="formLabelWidth">
            <el-select v-model="form.state" placeholder="请选择类型">
              <el-option
                  v-for="item in typeListData"
                  :key="item"
                  :label="item.title"
                  :value="item.state"
              ></el-option>
            </el-select>
          </el-form-item>
          <el-form-item label="内容：" :label-width="formLabelWidth">
            <el-input type="textarea" v-model="form.remark"></el-input>
          </el-form-item>
        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="dialogFormVisible = false">取 消</el-button>
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
<script>
import Paging from "../../components/paging/paging.vue";
import Pagetitle from "../../components/common/title.vue";
import request from "@/utils/request";

export default {
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
      title2: "消息管理",
      total: 0, //总共多少条，这个需要通过接口获取后赋值
      dialogFormVisible: false,
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
        publisher: "",
        customerId: "0000001",
      },
      seeInfoTable: {},
      formLabelWidth: "120px",
      publishMan: '',
    };
  },
  components: {
    Pagetitle,
    Paging,
  },
  methods: {
    auditSure() {
      request.get("/MessageManage/audit/"+this.row.abId).then(res =>{});
      this.InfoDialogTableVisible = false;
      this.load();
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
          if (this.listData.has(item.state)) {
            item.state = this.listData.get(item.state);
          } else {
            item.state = 'type not found';
          }

        });
        this.tableData = res.data.list
        this.total = res.data.total
      })
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
        console.log(this.typeListData)
        console.log(this.listData.size)
        console.log(this.listData)
      });
    },

    seeInfoBtn(index, row) {
      this.index = index;
      this.row = row;
      this.seeInfo(row.abId);
    },
    //查看接口
    seeInfo(seeId) {
      request.get("/MessageManage/"+seeId).then(res =>{
        this.seeInfoTable = res.data
        this.seeInfoTable.state = this.listData.get(this.seeInfoTable.state);
      });
      this.InfoDialogTableVisible = true;
    },
    //点击添加按钮
    addbtn() {
      this.dialogFormVisible = true;
    },
    addSure() {
      this.form.publisher = this.publishMan;

      request.post('MessageManage/Add',this.form).then(res => {
        if(res.code === '200'){
          this.$notify.success('新增成功')
          this.form = {
            title: "",
            remark: "",
            state: "",
            publisher: "mio",
            customerId: "0000001",}
          this.load();
        }else {
          this.$notify.error(res.msg)
        }
      })
      this.dialogFormVisible = false;
    },

    getNameById() {
      const id = this.$route.query.id;
      request.get('/customer/'+id).then(res =>{
        if(res.code==='200'){
          //  console.log(res.data)
          this.publishMan = res.data.custName;
          //  this.$notify.success("选择成功")
        }else{
          this.$notify.error(res.msg)
        }
      })
    },
    searchData() {
      this.load();
    },
    timeTransferForManage(timestamp) {
      let date = new Date(timestamp); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
      let Y = date.getFullYear() + "-";
      let M =
          (date.getMonth() + 1 < 10
              ? "0" + (date.getMonth() + 1)
              : date.getMonth() + 1) + "-";
      let D =
          (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " ";
      let h =
          (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":";
      let m =
          (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) +
          ":";
      let s =
          date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
      return Y + M + D + h + m + s;
    },
  },
  created() {
    this.getData();
    setTimeout(() => this.load(), 200);
    this.getNameById();
  },
};
</script>