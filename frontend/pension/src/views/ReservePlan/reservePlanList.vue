<template>
  <div>
    <!--    头部-->
    <el-header class="bg-lightgray" height="60px">
      <PageTitle :title="'预案管理'" :SecTitle="'预案列表'"></PageTitle>
    </el-header>
    <!--    搜索表单-->
    <div style="margin-top: 20px">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="预案编号">
          <el-input style="width: 240px" placeholder="请输入预案编号" v-model="params.planID"></el-input>
        </el-form-item>
        <el-form-item label="预案名称">
          <el-input style="width: 240px;margin-left: 5px" placeholder="请输入预案名称" v-model="params.planName"></el-input>
        </el-form-item>
        <el-form-item label="事件类型">
          <el-input style="width: 240px;margin-left: 5px" placeholder="请输入事件类型" v-model="params.accentType"></el-input>
        </el-form-item>
        <el-button style="margin-left: 5px" type="primary" @click="load"><i class="el-icon-search"></i>搜索</el-button>
        <el-button style="margin-left: 5px" type="warning" @click="reset"><i class="el-icon-refresh"></i>重置</el-button>
        <el-button style="float: right"   type="danger"   @click="$router.push('/main/reservePlanCreate')">新增</el-button>
      </el-form>
    </div>

    <el-table :data="tableData" stripe>
      <el-table-column prop="planID" label="编号"></el-table-column>
      <el-table-column prop="planName" label="名称"></el-table-column>
      <el-table-column prop="planType" label="预案类型"></el-table-column>
      <el-table-column prop="state" label="状态"></el-table-column>
      <el-table-column prop="accentType" label="事件类型"></el-table-column>
      <el-table-column prop="newTime" label="创建时间"></el-table-column>

      <el-table-column label="操作" width="150px">
        <template v-slot="scope">
          <el-button type="primary" size="small" @click="$router.push('/main/reservePlanEdit?planID='+scope.row.planID)">编辑</el-button>
          <el-popconfirm
              style="margin-left: 5px"
              title="你确定删除这行数据吗？"
              @confirm="del(scope.row.planID)"
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
  name: 'reservePlanList',
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
        accentType: '',
        planID: '',
        planName:'',
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get('/reservePlan/page', {
        params: this.params
      }).then(res => {
        if (res.code === '200') {
          this.tableData = res.data.list
          this.total = res.data.total
        }
      })
    },
    del(planID){
      request.delete("/reservePlan/delete/"+planID).then(res =>{
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
