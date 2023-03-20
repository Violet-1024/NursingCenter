<template>
  <div>

    <!--    编辑对话框-->
    <el-dialog title="编辑个人信息" custom-class="view-box"
               width="40%" :visible.sync="this.dialogFormVisible" center>
      <el-form :model="form" label-width="80px">

        <el-form-item label="身份证号">
          <el-input  v-model="form.custIDCard" placeholder="请输入身份证号"></el-input>
        </el-form-item>
        <el-form-item label="客户姓名">
          <el-input v-model="form.custName" placeholder="请输入客户姓名"></el-input>
        </el-form-item>
        <el-form-item label="用户名">
          <el-input v-model="form.username" placeholder="请输入用户名"></el-input>
        </el-form-item>
        <el-form-item label="手机号">
          <el-input v-model="form.phone" placeholder="请输入手机号"></el-input>
        </el-form-item>
        <el-form-item label="邮箱">
          <el-input v-model="form.custEmail" placeholder="请输入邮箱"></el-input>
        </el-form-item>
        <el-form-item label="备注">
          <el-input v-model="form.remark" placeholder="备注"></el-input>
        </el-form-item>
      </el-form>
      <div slot="footer" class="dialog-footer">
        <el-button @click="update">确认</el-button>
        <el-button @click="cancel">返回</el-button>
      </div>

    </el-dialog>

  </div>

</template>


<script>
import customerPage from "@/views/Mainforuser/customerPage";
import request from "@/utils/request";

export default {
  name: 'CustomerList',
  components: {
    customerPage,
  },
  data() {
    return {
      tableData: [],
      form:{
        id: '',
        custIDCard:'',
        custName:'',
        username:'',
        phone:'',
        custEmail:'',
        remark:''
      },

      dialogFormVisible : true,
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
      const id = this.$route.query.id
      this.form.id = id
      request.get('/customer/'+id).then(res =>{
        if(res.code==='200'){
          console.log(res.data)
          this.form = res.data

        }else{
          this.$notify.error(res.msg)
        }
      })

    },
    edit(row){
      this.form = JSON.parse(JSON.stringify(row))
    },

    //   router push

    cancel(){
      const id = this.$route.query.id;
      this.$router.push('/mainforuser/customerPage?id='+id)              //  <<==
    },
    update(){
      const id = this.$route.query.id;
      request.put('customer/update',this.form).then(res => {
        this.$notify.success('更新成功')

        this.$router.push('/mainforuser/customerPage?id='+id)          //  <<==


      })
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



