<template>
  <div>
    <!--    头部-->
    <el-header class="bg-lightgray" height="60px">
      <PageTitle :title="'员工管理'" :SecTitle="'员工列表'"></PageTitle>
    </el-header>
    <!--    搜索表单-->
    <div style="margin-top: 20px">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="姓名">
          <el-input style="width: 240px" placeholder="请输入姓名" v-model="params.name"></el-input>
        </el-form-item>
        <el-form-item label="联系方式">
          <el-input style="width: 240px;margin-left: 5px" placeholder="请输入联系方式" v-model="params.phone"></el-input>
        </el-form-item>
        <el-button style="margin-left: 5px" type="primary" @click="load"><i class="el-icon-search"></i>搜索</el-button>
        <el-button style="margin-left: 5px" type="warning" @click="reset"><i class="el-icon-refresh"></i>重置</el-button>
      </el-form>
    </div>

    <el-table :data="tableData" stripe>
      <el-table-column prop="eid" label="员工编号"></el-table-column>
      <el-table-column prop="name" label="姓名"></el-table-column>
      <el-table-column prop="age" label="年龄"></el-table-column>
      <el-table-column prop="address" label="地址"></el-table-column>
      <el-table-column prop="phone" label="电话"></el-table-column>
      <el-table-column prop="email" label="邮箱"></el-table-column>

      <el-table-column prop="gender" label="性别"></el-table-column>
      <el-table-column prop="status" label="状态"></el-table-column>

      <el-table-column label="操作" width="150px">
        <template v-slot="scope">
          <el-button type="primary" size="small" @click="$router.push('/main/employeeEdit?eid='+scope.row.eid)">编辑</el-button>
          <el-popconfirm
              style="margin-left: 5px"
              title="你确定删除这行数据吗？"
              @confirm="del(scope.row.eid)"
          >
            <el-button type="danger"  size="small" slot="reference">删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
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
  name: 'EmployeeList',
  components: {
    PageTitle,
    Paging,
  },
  data() {
    return {
      tableData: [],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 8,
        name: '',
        phone: ''
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get('/employee/page', {
        params: this.params
      }).then(res => {
        if (res.code === '200') {
          this.tableData = res.data.list
          this.total = res.data.total
        }
      })
    },
    del(eid){
      request.delete("/employee/delete/"+eid).then(res =>{
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
  }
}
</script>
