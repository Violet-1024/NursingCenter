<template>
  <el-container>
    <el-header>
      <Title :title="titleName" :SecTitle="SecTitleName"></Title>
    </el-header>
    <el-main :style="styleMain">


      <el-row>
        <el-col>
          <el-table :data='tableData'>
            <!--
            <el-table-column label="序号" prop="rankid" width="100%">
            </el-table-column>
-->
            <el-table-column label="菜品名称" prop="name" width="200%">
            </el-table-column>
            <el-table-column label="蛋白质(克)" prop="protein" width="150%">
            </el-table-column>
            <el-table-column label="碳水化合物(克)" prop="cho" width="150%">
            </el-table-column>
            <el-table-column label="热量(千卡)" prop="heat" width="150%">
            </el-table-column>
            <el-table-column label="脂肪(克)" prop="fat" width="150%">
            </el-table-column>
            <el-table-column label="价格(元)" prop="price" width="200%">
            </el-table-column>
            <el-table-column label="操作" width="200%">
              <template slot-scope="scope">
                <el-popconfirm
                    style="margin-left: 5px"
                    title="您确定要点这个吗？"
                    @confirm="ord(scope.row.price)"
                >
                  <el-button type="text" slot="reference">点餐</el-button>
                </el-popconfirm>
                <el-popconfirm
                    style="margin-left: 5px"
                    title="你确定删除这行数据吗？"
                    @confirm="del(scope.row.foodid)"
                >
                  <el-button type="text" slot="reference">删除</el-button>
                </el-popconfirm>
              </template>
            </el-table-column>
          </el-table>

          <!-- 编辑弹出框 -->
          <el-dialog title="编辑数据" :visible.sync="dialogFormVisible" center>
            <el-form  :model="form">


              <el-form-item label="菜品名称:">
                <br>
                <el-input v-model="form.name" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="蛋白质(克):">
                <br>
                <el-input v-model="form.protein" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="碳水化合物(克):">
                <br>
                <el-input v-model="form.cho" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="热量(千卡):">
                <br>
                <el-input v-model="form.heat" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="脂肪(克):">
                <br>
                <el-input v-model="form.fat" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="价格(元):">
                <br>
                <el-input v-model="form.price" placeholder="请输入"></el-input>
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
              <el-form-item label="菜品名称:">
                <br>
                <el-input v-model="form.name" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="蛋白质(克):">
                <br>
                <el-input v-model="form.protein" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="碳水化合物(克):">
                <br>
                <el-input v-model="form.cho" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="热量(千卡):">
                <br>
                <el-input v-model="form.heat" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="脂肪(克):">
                <br>
                <el-input v-model="form.fat" placeholder="请输入"></el-input>
              </el-form-item>
              <el-form-item label="价格(元):">
                <br>
                <el-input v-model="form.price" placeholder="请输入"></el-input>
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
      <el-button @click="back">
        返 回
      </el-button>
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
      titleName:'膳食管理',
      SecTitleName:'点餐',
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

      // 编辑
      form:{},
      params: {
        pageNum: 1,
        pageSize: 5,
        foodid: '',
        name: '',
        protein: '',
        cho: '',
        heat: '',
        fat: '',
        price: '',
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

    back(){
      const id = this.$route.query.id
      this.$router.push('/mainforuser/customerPage?id='+id)
    },

    //页面更新
    load() {
      request.get('/food/page', {
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
      request.post("/food/save", this.form).then(res => {
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

      request.post("/food/save", this.form).then(res => {
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
    del(rankid) {
      request.delete("/food/delete/"+rankid).then(res =>{
        if(res.code === '200'){
          this.$notify.success("删除成功")
          this.load()
        }else{
          this.$notify.error(res.msg)
        }
      })
    },



    ord(foodid){
      const getcusID = this.$route.query.id
      this.form.id = getcusID
      this.form.foodid = foodid
      request.post("/order/save/",this.form).then(res =>{
        if (res.code === '200') {
          this.$notify.success("点餐成功")
          this.load()
        } else {
          this.$notify.error(res.msg)
        }
      })
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