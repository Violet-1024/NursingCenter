<template>
  <el-container>
    <el-header>
      <Title :title="titleName" :SecTitle="SecTitleName"></Title>
    </el-header>
    <el-main :style="styleMain">
      <el-row >
        <el-col>
          <el-form :inline="true" :model="params">
            <el-form-item :style="styleAddBtn">
              <el-button  type="success" @click="add"><i class="el-icon-plus"></i> 添加 </el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>

      <el-row>
        <el-col>
          <el-table :data='tableData'>
            <!--
            <el-table-column label="序号" prop="rankid" width="100%">
            </el-table-column>
-->
            <el-table-column label="姓名" prop="name" width="auto">
            </el-table-column>
            <el-table-column label="性别" prop="sex" width="auto">
            </el-table-column>
            <el-table-column label="年龄" prop="age" width="auto">
            </el-table-column>
            <el-table-column label="表现" prop="performance" width="auto">
            </el-table-column>
            <el-table-column label="心率(次/分钟)" prop="rate" width="auto">
            </el-table-column>
            <el-table-column label="血糖(mmol)" prop="sugar" width="auto">
            </el-table-column>
            <el-table-column label="血氧(mmHg)" prop="oxygen" width="auto">
            </el-table-column>


            <el-table-column label="操作" width="suto">
              <template slot-scope="scope">
                <el-button type="text" @click="dialogFormVisible = true;edit(scope.row)">编辑</el-button>
              </template>
            </el-table-column>
          </el-table>

          <!-- 编辑弹出框 -->
          <el-dialog title="编辑数据" :visible.sync="dialogFormVisible" center>
            <el-form  :model="form">
              <el-form-item label="姓名:">
                <br>
                <el-input v-model="form.name" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="性别">
                <br>
                <el-radio-group v-model="form.sex">
                  <el-radio label="男">男</el-radio>
                  <el-radio label="女">女</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="年龄:">
                <br>
                <el-input v-model="form.age" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="表现:">
                <br>
                <el-input v-model="form.performance" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="心率(次/分钟):">
                <br>
                <el-input v-model="form.rate" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="血糖(mmol):">
                <br>
                <el-input v-model="form.sugar" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="血氧(mmHg):">
                <br>
                <el-input v-model="form.oxygen" placeholder="请输入"></el-input>
              </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="dialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="saveEdit">确 定</el-button>
            </div>
          </el-dialog>

          <!-- 添加弹出框 -->
          <el-dialog title="添加数据" :visible.sync="adddialogFormVisible" center>
            <el-form  :model="form">
              <!--   <el-form-item label="序号:">
                   <br>
                   <el-input v-model="form.rankid" placeholder="请输入"></el-input>
                 </el-form-item>-->
              <el-form-item label="姓名:">
                <br>
                <el-input v-model="form.name" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="性别">
                <br>
                <el-radio-group v-model="form.sex">
                  <el-radio label="男">男</el-radio>
                  <el-radio label="女">女</el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item label="年龄:">
                <br>
                <el-input v-model="form.age" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="表现:">
                <br>
                <el-input v-model="form.performance" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="心率(次/分钟):">
                <br>
                <el-input v-model="form.rate" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="血糖(mmol):">
                <br>
                <el-input v-model="form.sugar" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="血氧(mmHg):">
                <br>
                <el-input v-model="form.oxygen" placeholder="请输入"></el-input>
              </el-form-item>

            </el-form>
            <div slot="footer" class="dialog-footer">
              <el-button @click="adddialogFormVisible = false">取 消</el-button>
              <el-button type="primary" @click="save">确 定</el-button>
            </div>
          </el-dialog>
        </el-col>
      </el-row>
    </el-main>
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
  </el-container>
</template>

<script>
import Title from "../../components/common/title.vue";
import Paging from "../../components/paging/paging.vue";
import request from "@/utils/request";
export default {
  data: function () {
    return {
      //标题
      titleName:'健康管理',
      SecTitleName:'健康数据监测',
      //主体
      styleMain:{
        padding:"20px 0px",
      },
      styleMl_20:{
        marginLeft:'20px',
      },
      styleAddBtn:{
        float:"right",
        zIndex:"3",
        marginBottom:"20px",
      },
      stateOptions: [
        {
          value: "已启用",
          label: "已启用",
        },
        {
          value: "已禁用",
          label: "已禁用",
        }
      ],
      // 编辑
      form:{},
      params: {
        pageNum: 1,
        pageSize: 5,
        customerid: '',
        name: '',
        sex: '',
        age: '',
        performance: '',
        rate: '',
        sugar: '',
        oxygen: '',
      },
      tableData: [],
      total:0,

      dialogFormVisible:false,
      adddialogFormVisible:false,
      editID:0,


    };
  },
  components: {
    Title,
    Paging,
  },
  created(){
    //页面更新
    this.load()
  },
  methods:{
    //页面更新
    load() {
      request.get('/hdatas/page', {
        params: this.params
      }).then(res => {
        if (res.code === '200') {
          this.tableData = res.data.list
          this.total = res.data.total
        }
      })
    },
    reset() {
      this.params = {
        pageNum: 1,
        pageSize: 5,
        performance: '',
        state: ''
      },
          this.load()
    },
    add(){
      this.adddialogFormVisible = true;
      this.form = {}
    },

    //编辑
    edit(row){
      this.form = JSON.parse(JSON.stringify(row))
    },
    saveEdit(){
      request.post("/hdatas/save", this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success('更新成功')
          this.dialogFormVisible = false
        } else {
          this.$notify.error('更新失败')
        }
        this.load()
      })
    },
    save(){

      request.post("/hdatas/save", this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success('保存成功')
          this.adddialogFormVisible = false
        } else {
          this.$notify.error('保存失败')
        }
        this.load()
      })
    },
    //删除
   /* del(rankid) {
      request.delete("/hdatas/delete/"+rankid).then(res =>{
        if(res.code === '200'){
          this.$notify.success("删除成功")
          this.load()
        }else{
          this.$notify.error(res.msg)
        }
      })
    },*/
    handleCurrentChange(pageNum) {
      this.params.pageNum = pageNum
      this.load()
    }
  },

};
</script>

<style lang="less" scoped>

</style>