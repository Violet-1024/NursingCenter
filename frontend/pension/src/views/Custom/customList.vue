<template>
  <div>
    <!--    头部-->
    <el-header class="bg-lightgray" height="60px">
      <PageTitle :title="'客户管理'" :SecTitle="'客户列表'"></PageTitle>
    </el-header>
    <!--    搜索表单-->
    <div style="margin-top: 20px">
      <el-form :inline="true" :model="formInline" class="demo-form-inline">
        <el-form-item label="客户编号">
          <el-input style="width: 240px" placeholder="请输入客户编号" v-model="params.id"></el-input>
        </el-form-item>
        <el-form-item label="身份证号">
          <el-input style="width: 240px" placeholder="请输入身份证号" v-model="params.custIDCard"></el-input>
        </el-form-item>
        <el-form-item label="客户姓名">
          <el-input style="width: 240px" placeholder="请输入客户姓名" v-model="params.custName"></el-input>
        </el-form-item>

        <el-form-item label="手机号码">
          <el-input style="width: 240px" placeholder="请输入手机号码" v-model="params.phone"></el-input>
        </el-form-item>
        <el-button style="margin-left: 64px" type="primary" @click="load"><i class="el-icon-search"></i>搜索</el-button>
        <el-button style="margin-left: 20px" type="warning" @click="reset"><i class="el-icon-refresh"></i>重置</el-button>
      </el-form>
    </div>

    <el-table :data="tableData" stripe>
      <el-table-column prop="id" label="客户编号" width="100px"></el-table-column>
      <el-table-column prop="custIDCard" label="身份证号" width="200px"></el-table-column>
      <el-table-column prop="custName" label="客户姓名" ></el-table-column>
      <el-table-column prop="custAge" label="年龄" ></el-table-column>
      <el-table-column prop="phone" label="电话号码" width="150px"></el-table-column>
      <el-table-column prop="status" label="状态"></el-table-column>
      <el-table-column prop="custLevel" label="客户级别"></el-table-column>
      <el-table-column prop="nursingLevel" label="护理级别"></el-table-column>

      <el-table-column label="操作" width="175px">
        <template v-slot="scope">
          <el-button type="primary" size="small" @click="edit(scope.row)">
            <i class="el-icon-edit"></i>编辑</el-button>

          <el-popconfirm
              style="margin-left: 5px"
              title="你确定删除这行数据吗？"
              @confirm="del(scope.row.id)"
          >
            <el-button type="danger"  size="small" slot="reference">
              <i class="el-icon-delete"></i>删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!--    编辑对话框-->
    <el-dialog title="编辑客户信息" custom-class="view-box"
               width="40%" :visible.sync="dialogFormVisible" center>
      <el-form :model="form" label-width="80px">

        <el-form-item label="客户编号" >
          <el-input readonly="readonly" v-model="form.id" placeholder="请输入客户编号号"></el-input>
        </el-form-item>

        <el-form-item label="身份证号">
          <el-input readonly="readonly" v-model="form.custIDCard" placeholder="请输入身份证号"></el-input>
        </el-form-item>
        <el-form-item label="客户姓名">
          <el-input readonly="readonly" v-model="form.custName" placeholder="请输入客户姓名"></el-input>
        </el-form-item>
        <el-form-item label="客户级别">
          <el-select  v-model="form.custLevel" style="width: 100%" placeholder="请选择客户等级">
            <el-option
                v-for="item in levelOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-form-item label="护理级别">
          <el-input v-model="form.nursingLevel" placeholder="请输入护理级别"></el-input>
        </el-form-item>
        <el-form-item label="客户状态">
          <el-radio v-model="form.status" label="在住">在住</el-radio>
          <el-radio v-model="form.status" label="暂离">暂离</el-radio>
          <el-radio v-model="form.status" label="离开">离开</el-radio>
        </el-form-item>

      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="update">更新</el-button>
        <el-button @click="dialogFormVisible = false">返回</el-button>
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
  name: 'CustomerList',
  components: {
    PageTitle,
    Paging,
  },
  data() {
    return {
      tableData: [],
      form:{
        custLevel:''
      },
      stateOptions: [
        {
          value: "在住",
          label: "在住",
        },
        {
          value: "暂离",
          label: "暂离",
        },
        {
          value: "离开",
          label: "离开",
        },
      ],
      levelOptions: [
        {
          value: "普通用户",
          label: "普通用户",
        },
        {
          value: "普通会员",
          label: "普通会员",
        },
        {
          value: "高级会员",
          label: "高级会员",
        },
      ],
      dialogFormVisible : false,
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 7,
        id: '',
        custIDCard: '',
        custName: '',
        phone: ''
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get('/customer/page', {
        params: this.params
      }).then(res => {
        if (res.code === '200') {
          this.tableData = res.data.list
          this.total = res.data.total
        }
      })
    },
    edit(row){
      this.dialogFormVisible = true
      this.form = JSON.parse(JSON.stringify(row))
    },
    update(){
      request.put('customer/update',this.form).then(res => {
        if(res.code === '200'){
          this.$notify.success('更新成功')
          this.form = {}
          this.$router.push('/main/customList')
          this.dialogFormVisible = false
          this.load()
        }else {
          this.$notify.error(res.msg)
        }
      })
    },
    del(id){
      request.delete("/customer/delete/"+id).then(res =>{
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
        pageSize: 5,
        id: '',
        custIDCard: '',
        custName: '',
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

<style lang="less">
.view-box{
  border-radius: 15px;
}
</style>

