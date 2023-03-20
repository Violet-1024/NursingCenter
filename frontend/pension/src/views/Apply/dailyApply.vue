<template>
  <el-container>
    <el-header>
      <Title :title="titleName" :SecTitle="SecTitleName"></Title>
    </el-header>
    <el-main :style="styleMain">
      <el-row >
        <el-col>
          <el-form :inline="true" :model="params">
            <el-form-item label="申请人" :style="styleMl_20">
              <el-input v-model="params.name" placeholder="请输入申请人姓名"></el-input>
            </el-form-item>
            <el-form-item label="处理状态" :style="styleMl_20">
              <el-select v-model="params.status" placeholder="请选择">
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

            <el-form-item :style="styleMl_20">
              <el-button type="success" @click="load"><i class="el-icon-search"></i> 筛选 </el-button>
              <el-button type="warning" @click="reset"><i class="el-icon-refresh"></i> 重置 </el-button>
            </el-form-item>

            <el-form-item :style="styleAddBtn">
              <el-button  type="success" @click="add"><i class="el-icon-plus"></i> 添加 </el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>

      <el-row>
        <el-col>
          <el-table :data='tableData'>
            <el-table-column label="申请人" prop="name" width="100%">
            </el-table-column>
            <el-table-column label="申请时间" prop="applicationtime" width="200%">
              <template slot-scope="scope">
                {{goTime(scope.row.applicationtime)}}
              </template>
            </el-table-column>
            <el-table-column label="申请原因" prop="reason" width="355%">
            </el-table-column>
            <el-table-column label="处理方案" prop="plan" width="355%">
            </el-table-column>
            <el-table-column label="处理状态" prop="status" width="100%">
            </el-table-column>
            <el-table-column label="操作" width="200%">
              <template slot-scope="scope">
                <el-button type="text" @click="dialogFormVisible = true;edit(scope.row)">编辑</el-button>
                <el-popconfirm
                    style="margin-left: 5px"
                    title="你确定删除这行数据吗？"
                    @confirm="del(scope.row.serviceid)"
                >
                  <el-button type="text" slot="reference">删除</el-button>
                </el-popconfirm>
              </template>
            </el-table-column>
          </el-table>

          <!-- 编辑弹出框 -->
          <el-dialog title="编辑数据" :visible.sync="dialogFormVisible" center>
            <el-form  :model="form">
              <el-form-item label="处理方案:">
                <br>
                <el-input v-model="form.plan" placeholder="处理方案"></el-input>
              </el-form-item>
              <el-form-item label="状态">
                <br>
                <el-radio-group v-model="form.status">
                  <el-radio label="已处理">已处理</el-radio>
                  <el-radio label="未处理">未处理</el-radio>
                </el-radio-group>
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
              <el-form-item label="申请人姓名:">
                <br>
                <el-input v-model="form.name" placeholder="请输入申请人姓名"></el-input>
              </el-form-item>
              <el-form-item label="申请原因:">
                <br>
                <el-input v-model="form.reason" placeholder="请输入申请原因"></el-input>
              </el-form-item>
              <el-form-item label="处理方案:">
                <br>
                <el-input v-model="form.plan" placeholder="处理方案"></el-input>
              </el-form-item>
              <el-form-item label="状态">
                <br>
                <el-radio-group v-model="form.status">
                  <el-radio label="已处理">已处理</el-radio>
                  <el-radio label="未处理">未处理</el-radio>
                </el-radio-group>
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
      titleName:'申请管理',
      SecTitleName:'日常申请服务',
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
          value: "已处理",
          label: "已处理",
        },
        {
          value: "未处理",
          label: "未处理",
        }
      ],
      // 编辑
      form:{},
      params: {
        pageNum: 1,
        pageSize: 5,
        name: '',
        status: ''
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
      request.get('/service/page', {
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
        name: '',
        status: ''
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
      this.form.handlingtime = new Date().getTime()
      request.post("/service/save", this.form).then(res => {
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
      this.form.applicationtime = new Date().getTime()
      if(this.form.status !=""||this.form.status !="未处理"){
        this.form.handlingtime = new Date().getTime()
      }
      request.post("/service/save", this.form).then(res => {
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
    del(serviceid) {
      request.delete("/service/delete/"+serviceid).then(res =>{
        if(res.code === '200'){
          this.$notify.success("删除成功")
          this.load()
        }else{
          this.$notify.error(res.msg)
        }
      })
    },
    //时间格式化
    goTime(val) {
      var timestamp4 = new Date(val);
      //利用拼接正则等手段转化为yyyy-MM-dd hh:mm:ss 格式
      return timestamp4.toLocaleDateString().replace(/\//g, "-") + " " + timestamp4.toTimeString().substr(0, 8);
    },
    handleCurrentChange(pageNum) {
      this.params.pageNum = pageNum
      this.load()
    }
  },

};
</script>

<style lang="less" scoped>

</style>