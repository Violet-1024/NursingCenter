<template>
  <div>
    <!--    头部-->
    <el-header class="bg-lightgray" height="60px">
      <PageTitle :title="'床位管理'" :SecTitle="'床位记录'"></PageTitle>
    </el-header>
    <!--    搜索表单-->
    <div style="margin-top: 20px">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="床位号">
          <el-input style="width: 240px;margin-left: 14px" placeholder="请输入床位号" v-model="params.bedID"></el-input>
        </el-form-item>
        <el-form-item label="房间号">
          <el-input style="width: 240px;margin-left: 8px" placeholder="请输入房间号" v-model="params.roomID"></el-input>
        </el-form-item>
        <el-form-item label="客户姓名">
          <el-input style="width: 240px" placeholder="请输入客户姓名" v-model="params.custName"></el-input>
        </el-form-item>
        <el-form-item label="员工编号">
          <el-input style="width: 240px" placeholder="请输入客户姓名" v-model="params.staffID"></el-input>
        </el-form-item>

        <el-button style="margin-left: 64px" type="primary" @click="load">
          <i class="el-icon-search">搜索</i>
        </el-button>
        <el-button style="margin-left: 20px" type="warning" @click="reset">
          <i class="el-icon-refresh">重置</i>
        </el-button>
      </el-form>

    </div>

    <el-table :data="tableData" stripe>
      <el-table-column prop="recordID" label="编号" ></el-table-column>
      <el-table-column prop="bedID" label="床位号" ></el-table-column>
      <el-table-column prop="roomID" label="房间号" ></el-table-column>
      <el-table-column prop="staffID" label="护理员" ></el-table-column>
      <el-table-column prop="custName" label="客户姓名"  ></el-table-column>
      <el-table-column prop="entryDate" label="入住时间" width="200px"></el-table-column>
      <el-table-column prop="leaveDate" label="离开时间" width="200px"></el-table-column>
      <el-table-column label="操作" width="200px">
        <template v-slot="scope">
          <el-button  type="primary" size="small" @click="view(scope.row)">
            <i class="el-icon-view"></i>查看</el-button>
          <el-popconfirm
              style="margin-left: 5px"
              title="你确定删除这行数据吗？"
              @confirm="del(scope.row.recordID)"
          >
            <el-button type="danger"  size="small" slot="reference">
              <i class="el-icon-delete"></i>删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!-- 查看对话框   -->
    <el-dialog title="床位记录查看" custom-class="view-box"
               width="40%" :visible.sync="dialogTableVisible" center>
      <el-form :model="form" label-width="80px">

        <el-form-item label="床位号" >
          <el-input readonly="readonly" v-model="form.bedID"></el-input>
        </el-form-item>

        <el-form-item label="房间号">
          <el-input readonly="readonly" v-model="form.roomID"></el-input>
        </el-form-item>
        <el-form-item label="护理员">
          <el-input readonly="readonly" v-model="form.staffID"></el-input>
        </el-form-item>
        <el-form-item label="客户姓名">
          <el-input readonly="readonly" v-model="form.custName"></el-input>
        </el-form-item>
        <el-form-item label="入住时间">
          <el-input readonly="readonly" v-model="form.entryDate"></el-input>
        </el-form-item>
        <el-form-item label="离开时间">
          <el-input readonly="readonly" v-model="form.leaveDate"></el-input>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="dialogTableVisible = false">返回</el-button>
      </div>
    </el-dialog>

    <!--    分页-->
    <div style="margin-top:20px">
      <el-pagination
          background
          :current-page="params.pageNum"
          :page-size="params.pageSize"
          layout="prev, pager, next"
          @current-change="handleCurrentChange"
          :total="total">
      </el-pagination>
    </div>
  </div>

</template>


<script>
import Paging from "../../components/paging/paging.vue";
import PageTitle from "../../components/common/title.vue";
import request from "@/utils/request";

export default {
  name: 'BedRecord',
  components: {
    PageTitle,
    Paging,
  },
  data() {
    return {
      tableData: [],
      total: 0,
      dialogTableVisible: false,
      form:{},
      params: {
        pageNum: 1,
        pageSize: 8,
        recordID:'',
        bedID: '',
        roomID: '',
        custName: '',
        staffID: ''
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get('/bedrecord/page', {
        params: this.params
      }).then(res => {
        if (res.code === '200') {
          this.tableData = res.data.list
          this.total = res.data.total
        }
      })
    },
    del(recordID){
      request.delete("/bedrecord/delete/"+recordID).then(res =>{
        if(res.code === '200'){
          this.$notify.success("删除成功")
          this.load()
        }else{
          this.$notify.error(res.msg)
        }
      })
    },
    view(row){
      this.dialogTableVisible = true
      this.form = JSON.parse(JSON.stringify(row))
    },
    reset() {
      this.params = {
        pageNum: 1,
        pageSize: 8,
        bedID: '',
        roomID: '',
        name: '',
        status: ''
      }
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.params.pageNum = pageNum
      this.load()
    }
  }
}
</script>

<style lang="less">
  .view-box{
    border-radius: 15px;
  }


</style>
