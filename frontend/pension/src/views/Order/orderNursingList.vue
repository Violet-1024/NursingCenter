



<template>
  <div>
    <!--    头部-->
    <el-header>
      <Title :title='title1' :SecTitle='title2'></Title>
    </el-header>
    <!--    搜索表单-->
    <div style="margin-top: 20px">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="护理记录">
          <el-input style="width: 240px" placeholder="请输入护理记录编号" v-model="params.recordID"></el-input>
        </el-form-item>
        <el-form-item label="用户">
          <el-input style="width: 240px;margin-left: 5px" placeholder="请输入用户ID" v-model="params.id"></el-input>
        </el-form-item>
        <el-form-item label="时间">
          <el-date-picker style="width: 240px;margin-left: 5px" placeholder="请选择时间" v-model="params.nursingTime"></el-date-picker>
        </el-form-item>
        <el-button style="margin-left: 5px" type="primary" @click="load"><i class="el-icon-search"></i>搜索</el-button>
        <el-button style="margin-left: 5px" type="warning" @click="reset"><i class="el-icon-refresh"></i>重置</el-button>
      </el-form>
    </div>

    <el-table :data="tableData" stripe>

      <el-table-column prop="nursingTime" label="创建时间"></el-table-column>
      <el-table-column prop="recordID" label="编号"></el-table-column>
      <el-table-column prop="id" label="user"></el-table-column>
      <el-table-column prop="eid" label="员工"></el-table-column>
      <el-table-column prop="nursingName" label="护理"></el-table-column>
      <el-table-column prop="status" label="状态"></el-table-column>


      <el-table-column label="操作" width="150px">
        <template v-slot="scope">
          <el-button type="primary" size="small" @click="edit(scope.row)">编辑</el-button>
          <el-popconfirm
              style="margin-left: 5px"
              title="你确定删除这行数据吗？"
              @confirm="del(scope.row.recordID)"
          >
            <el-button type="danger"  size="small" slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <el-dialog title="处理" :visible.sync="dialogTableVisible" center>
      <el-form :model="form" label-width="120px">
        <el-form-item label="状态">
          <br>
          <el-radio-group v-model="form.status">
            <el-radio label="已付款">已付款</el-radio>
            <el-radio label="待付款">待付款</el-radio>
          </el-radio-group>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="save">确认</el-button>
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
import Title from '@/components/common/title.vue'
import Page from '@/components/paging/paging.vue'
import request from "@/utils/request";
export default {
  data() {
    return {
      total: 0, //总共多少条，这个需要通过接口获取后赋值
      title1: '订单管理',
      title2: '护理记录列表',
      dialogTableVisible: false,
      params: {
        nursingTime: '',
        recordID: '',
        eid: '',
        id:'',
        status: '',
        nursingName:'',
        pageNum: 1,
        pageSize: 8
      },
      options: [
        {
          value: 0,
          label: '待付款'
        },
        {
          value: 1,
          label: '已付款'
        },
      ],
      tableData: [
      ],
      form:{},
    };
  },
  components: {
    Title,
    Page
  },
  methods: {
    edit(row) {
      this.dialogTableVisible = true
      // this.form = JSON.parse(JSON.stringify(row))
    },
    load() {
      request.get('/nursingrecord/page', {
        params: this.params
      }).then(res => {
        if (res.code === '200') {
          this.tableData = res.data.list
          this.total = res.data.total
        }
      })
    },

    save() {
      request.post("/nursingrecord/update/", this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success('更新成功')
          this.dialogTableVisible = false

        } else {
          this.$notify.error('更新失败')
        }
        this.load()
      })
    },
    del(recordID){
      request.delete("/nursingrecord/delete/"+recordID).then(res =>{
        if(res.code === '200'){
          this.$notify.success("删除成功")
          this.load()
        }else{
          this.$notify.error(res.msg)
        }
      })
    },
    reset() {
      this.params = {
        pageNum: 1,
        pageSize: 8,
        name: '',
        phone: ''
      }
      this.load()
    },
    handleCurrentChange(pageNum) {
      this.params.pageNum = pageNum
      this.load()
    }

  },
  created() {
    this.load();
  }

};
</script>



