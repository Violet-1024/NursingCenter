<template>
  <div>
    <!--    头部-->
    <el-header class="bg-lightgray" height="60px">
      <PageTitle :title="'床位管理'" :SecTitle="'床位列表'"></PageTitle>
    </el-header>
    <!--    搜索表单-->
    <div style="margin-top: 20px">
      <el-form :inline="true"  class="demo-form-inline">
        <el-form-item label="床位号">
          <el-input style="width: 240px;margin-left: 14px" placeholder="请输入床位号" v-model="params.bedID"></el-input>
        </el-form-item>
        <el-form-item label="房间号">
          <el-input style="width: 240px;margin-left: 8px" placeholder="请输入房间号" v-model="params.roomID"></el-input>
        </el-form-item>
        <el-form-item label="床位等级">
          <el-input style="width: 240px" placeholder="请输入床位等级" v-model="params.bedLevel"></el-input>
        </el-form-item>

        <el-form-item label="床位状态">
          <el-select  style="width: 240px" v-model="params.status" placeholder="请选择">
            <el-option
                v-for="item in stateOptions"
                :key="item.value"
                :label="item.label"
                :value="item.value"
                :disabled="item.disabled"
            >
            </el-option>
          </el-select>
        </el-form-item>
        <el-button style="margin-left: 64px" type="primary" @click="load"><i class="el-icon-search"></i>搜索</el-button>
        <el-button style="margin-left: 20px" type="warning" @click="reset"><i class="el-icon-refresh"></i>重置</el-button>
        <el-button style="margin-left: 20px" type="success" @click="add"><i class="el-icon-plus">新增</i></el-button>
      </el-form>

    </div>

    <el-table :data="tableData" stripe>
      <el-table-column prop="bedID" label="床位号" width="150px"></el-table-column>
      <el-table-column prop="roomID" label="房间号" width="150px"></el-table-column>
      <el-table-column prop="staffID" label="护理员" width="150px"></el-table-column>
      <el-table-column prop="bedLevel" label="床位等级" width="150px"></el-table-column>
      <el-table-column prop="status" label="状态" width="150px"></el-table-column>
      <el-table-column prop="createDate" label="创建时间"></el-table-column>

      <el-table-column label="操作" width="200px">
        <template v-slot="scope">
          <el-button  type="primary" size="small" @click="edit(scope.row)">
            <i class="el-icon-edit"></i>编辑</el-button>
          <el-popconfirm
              style="margin-left: 5px"
              title="你确定删除这行数据吗？"
              @confirm="del(scope.row.bedID)"
          >
            <el-button type="danger"  size="small" slot="reference">
              <i class="el-icon-delete"></i>删除</el-button>
          </el-popconfirm>
        </template>
      </el-table-column>
    </el-table>
    <!--    新增对话框-->
    <el-dialog title="新增床位" custom-class="view-box"
               width="40%" :visible.sync="dialogTableVisible" center>
      <el-form :model="form" label-width="80px">

        <el-form-item label="床位号" >
          <el-input v-model="form.bedID" placeholder="请输入床位号"></el-input>
        </el-form-item>

        <el-form-item label="房间号">
          <el-input v-model="form.roomID" placeholder="请输入房间号"></el-input>
        </el-form-item>
        <el-form-item label="护理员">
          <el-input v-model="form.staffID" placeholder="请输入护理员编号"></el-input>
        </el-form-item>
        <el-form-item label="床位等级">
          <el-select  v-model="form.bedLevel" style="width: 100%" placeholder="请选择床位等级">
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
        <el-form-item label="床位状态">
          <el-radio v-model="form.status" label="空闲">空闲</el-radio>
          <el-radio v-model="form.status" label="占用">占用</el-radio>
          <el-radio v-model="form.status" label="维修">维修</el-radio>
        </el-form-item>
      </el-form>

      <div slot="footer" class="dialog-footer">
        <el-button @click="save">新增</el-button>
        <el-button @click="dialogTableVisible = false">返回</el-button>
      </div>
    </el-dialog>

    <!--    编辑对话框-->
    <el-dialog title="编辑床位" custom-class="view-box"
               width="40%" :visible.sync="dialogFormVisible" center>
      <el-form :model="form" label-width="80px">

        <el-form-item label="床位号" >
          <el-input readonly="readonly" v-model="form.bedID" placeholder="请输入床位号"></el-input>
        </el-form-item>

        <el-form-item label="房间号">
          <el-input readonly="readonly" v-model="form.roomID" placeholder="请输入房间号"></el-input>
        </el-form-item>
        <el-form-item label="护理员">
          <el-input v-model="form.staffID" placeholder="请输入护理员编号"></el-input>
        </el-form-item>
        <el-form-item label="床位等级">
          <el-select  v-model="form.bedLevel" style="width: 100%" placeholder="请选择床位等级">
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
        <el-form-item label="床位状态">
          <el-radio v-model="form.status" label="空闲">空闲</el-radio>
          <el-radio v-model="form.status" label="占用">占用</el-radio>
          <el-radio v-model="form.status" label="维修">维修</el-radio>
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
  name: 'BedList',
  components: {
    PageTitle,
    Paging,
  },
  data() {
    return {
      dialogTableVisible : false,
      dialogFormVisible : false,
      tableData: [],
      form:{
        status: '空闲'
      },
      stateOptions: [
        {
          value: "空闲",
          label: "空闲",
        },
        {
          value: "占用",
          label: "占用",
        },
        {
          value: "维修",
          label: "维修",
        },
      ],
      levelOptions:[
        {
          value: "普通床位",
          label: "普通床位",
        },
        {
          value: "高级床位",
          label: "高级床位",
        },
        {
          value: "VIP床位",
          label: "VIP床位",
        },
      ],
      total: 0,
      params: {
        pageNum: 1,
        pageSize: 8,
        bedID: '',
        roomID: '',
        bedLevel: '',
        status: ''
      },
    }
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get('/bed/page', {
        params: this.params
      }).then(res => {
        if (res.code === '200') {
          this.tableData = res.data.list
          this.total = res.data.total
        }
      })
    },
    add(){
      this.dialogTableVisible = true

    },
    edit(row){
      this.dialogFormVisible = true
      this.form = JSON.parse(JSON.stringify(row))
    },
    save(){
      request.post('bed/save',this.form).then(res => {
        if(res.code === '200'){
          this.$notify.success('新增成功')
          this.form = {}
          this.$router.push('/main/bedList')
          this.dialogTableVisible = false
          this.load()
        }else {
          this.$notify.error(res.msg)
        }
      })
    },
    update(){
      request.put('bed/update',this.form).then(res => {
        if(res.code === '200'){
          this.$notify.success('更新成功')
          this.form = {}
          this.$router.push('/main/bedList')
          this.dialogFormVisible = false
          this.load()
        }else {
          this.$notify.error(res.msg)
        }
      })
    },
    del(bedID){
      request.delete("/bed/delete/"+bedID).then(res =>{
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