<template>
  <div>
    <!--    头部-->
    <el-header class="bg-lightgray" height="60px">
      <PageTitle :title="'护理管理'" :SecTitle="'护理列表'"></PageTitle>
    </el-header>
    <!--    搜索表单-->
    <div style="margin-top: 20px">
      <el-form :inline="true"  class="demo-form-inline">
        <el-form-item label="护理编号">
          <el-input style="width: 200px" placeholder="请输入护理编号" v-model="params.nursingID"></el-input>
        </el-form-item>
        <el-form-item label="护理级别">
          <el-input style="width: 200px" placeholder="请输入护理级别" v-model="params.nursingLevel"></el-input>
        </el-form-item>

        <el-form-item label="护理状态">
          <el-select  style="width: 160px" v-model="params.status" placeholder="请选择护理状态">
            <el-option v-for="item in stateOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled"
            >
            </el-option>
          </el-select>
        </el-form-item>

        <el-button style="margin-left: 20px" type="primary" @click="load"><i class="el-icon-search">搜索</i></el-button>
        <el-button style="margin-left: 20px" type="warning" @click="reset"><i class="el-icon-refresh">重置</i></el-button>
        <el-button style="margin-left: 20px" type="success" @click="add"><i class="el-icon-plus">新增</i></el-button>
      </el-form>
    </div>

    <el-table :data="tableData" stripe>
      <el-table-column prop="nursingID" label="护理编号" width="100px"></el-table-column>
      <el-table-column prop="nursingLevel" label="护理级别" width="100px"></el-table-column>
      <el-table-column prop="nursingName" label="护理名称" width="150px"></el-table-column>
      <el-table-column prop="price" label="价格" width="100px"></el-table-column>
      <el-table-column prop="introduce" label="描述" ></el-table-column>
      <el-table-column prop="status" label="状态" width="100px"></el-table-column>

      <el-table-column label="操作" width="200px">
        <template v-slot="scope">
          <el-button type="primary" size="small" @click="edit(scope.row)">
            <i class="el-icon-edit">编辑</i></el-button>

          <el-popconfirm
              style="margin-left: 5px"
              title="你确定删除这行数据吗？"
              @confirm="del(scope.row.nursingID)"
          >
            <el-button type="danger"  size="small" slot="reference">
              <i class="el-icon-delete">删除</i></el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>

    <!--    新增对话框-->
    <el-dialog title="新增护理内容" custom-class="view-box"
               width="40%" :visible.sync="dialogTableVisible" center>
      <el-form :model="form" label-width="80px">

        <el-form-item label="护理级别">
          <el-input v-model="form.nursingLevel" placeholder="请输入护理级别"></el-input>
        </el-form-item>
        <el-form-item label="护理名称">
          <el-input v-model="form.nursingName" placeholder="请输入护理名称"></el-input>
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="form.price" placeholder="请输入价格"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="form.introduce" placeholder="请输入描述"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-radio v-model="form.status" label="未启用">未启用</el-radio>
          <el-radio v-model="form.status" label="已启用">已启用</el-radio>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="save">新增</el-button>
        <el-button @click="dialogTableVisible = false">返回</el-button>
      </div>
    </el-dialog>

    <!--    编辑对话框-->
    <el-dialog title="编辑护理内容" custom-class="view-box"
               width="40%" :visible.sync="dialogFormVisible" center>
      <el-form :model="form" label-width="80px">

        <el-form-item label="护理级别">
          <el-input v-model="form.nursingLevel" placeholder="请输入护理级别"></el-input>
        </el-form-item>
        <el-form-item label="护理名称">
          <el-input v-model="form.nursingName" placeholder="请输入护理名称"></el-input>
        </el-form-item>
        <el-form-item label="价格">
          <el-input v-model="form.price" placeholder="请输入价格"></el-input>
        </el-form-item>
        <el-form-item label="描述">
          <el-input v-model="form.introduce" placeholder="请输入描述"></el-input>
        </el-form-item>
        <el-form-item label="状态">
          <el-radio v-model="form.status" label="未启用">未启用</el-radio>
          <el-radio v-model="form.status" label="已启用">已启用</el-radio>
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
  name: 'NursingList',
  components: {
    PageTitle,
    Paging,
  },
  data() {
    return {
      form:{

        status: '未启用'
      },
      dialogTableVisible : false,
      dialogFormVisible : false,
      tableData: [],
      stateOptions: [
        {
          value: "已启用",
          label: "已启用",
        },
        {
          value: "未启用",
          label: "未启用",
        },
      ],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 7,
        nursingID: '',
        nursingLevel: '',
        nursingName: '',
        status: ''
      }
    }
  },
  created() {
    this.load()
  },
  methods: {
    add(){
      this.dialogTableVisible = true

    },
    edit(row){
      this.dialogFormVisible = true
      this.form = JSON.parse(JSON.stringify(row))
    },
    load() {
      request.get('/nursing/page', {
        params: this.params
      }).then(res => {
        if (res.code === '200') {
          this.tableData = res.data.list
          this.total = res.data.total
        }
      })
    },
    save(){
      request.post('nursing/save',this.form).then(res => {
        if(res.code === '200'){
          this.$notify.success('新增成功')
          this.form = {}
          this.$router.push('/main/nursingList')
          this.dialogTableVisible = false
          this.load()
        }else {
          this.$notify.error(res.msg)
        }
      })
    },
    update(){
      request.put('nursing/update',this.form).then(res => {
        if(res.code === '200'){
          this.$notify.success('更新成功')
          this.form = {}
          this.$router.push('/main/nursingList')
          this.dialogFormVisible = false
          this.load()
        }else {
          this.$notify.error(res.msg)
        }
      })
    },
    del(nursingID){
      request.delete("/nursing/delete/"+nursingID).then(res =>{
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
        nursingID: '',
        nursingLevel: '',
        nursingName: '',
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