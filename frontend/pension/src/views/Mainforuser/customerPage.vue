<template>
  <div class = "customerPage-main">

    <el-header class="bg-lightgray" height="60px">
      <PageTitle :title="'Welcome'" :SecTitle="params.custName"></PageTitle>
    </el-header>

    <el-container >
      <!--    头部-->


      <el-main class="customerPage-build">
        <el-row
            type="flex"
            justify="center"
            align="middle"
            :style="{ height: windowHeight }"
        >



          <el-col class="login-box" :span="8">
            <h1 class="title-center">填写信息</h1>
            <el-form ref="form" :model="form" >
              <div class="text-center">
                <span></span>
                <br>
                <span>点击填写按钮以完善相关信息</span>
                <br>
                <span></span>
              </div>
              <el-form-item>
                <el-button
                    class="mybtn-width"
                    type="primary"
                    @click="einformation"
                >填写</el-button>
              </el-form-item>
            </el-form>
          </el-col>

          <el-col class="login-box" :span="8">
            <h1 class="title-center">客户入住</h1>
            <el-form ref="form" :model="form">

              <div class="text-center">
                <span></span>
                <br>
                <span>点击入住按钮以选择床位</span>
                <br>
                <span></span>
              </div>

              <el-form-item>
                <el-button
                    class="mybtn-width"
                    type="primary"
                    @click="checkin"
                >入住</el-button>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>


        <el-row
            type="flex"
            justify="center"
            align="middle"
            :style="{ height: windowHeight }"
        >



          <el-col class="login-box" :span="8">
            <h1 class="title-center">填写申请</h1>
            <el-form ref="form" :model="form" >

              <div class="text-center">
                <span></span>
                <br>
                <span>点击申请按钮以填写申请</span>
                <br>
                <span></span>
              </div>

              <el-form-item>
                <el-button
                    class="mybtn-width"
                    type="primary"
                    @click="eapply"
                >申请</el-button>
              </el-form-item>
            </el-form>
          </el-col>


          <el-col class="login-box" :span="8">
            <h1 class="title-center">订餐服务</h1>
            <el-form ref="form" :model="form" >

              <div class="text-center">
                <span></span>
                <br>
                <span>点击订餐按钮以选择膳食</span>
                <br>
                <span></span>
              </div>

              <el-form-item>
                <el-button
                    class="mybtn-width"
                    type="primary"
                    @click="food"
                >订餐</el-button>
              </el-form-item>
            </el-form>
          </el-col>
        </el-row>



        <el-row>

        </el-row>


      </el-main>
    </el-container>


  </div>

</template>


<script>
import Paging from "../../components/paging/paging.vue";
import PageTitle from "../../components/common/title.vue";
import request from "@/utils/request";

export default {
  name: 'CustomerPage',
  components: {
    PageTitle,
    Paging,
  },
  data() {
    return {
      tableData: [],
      form:{

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
    const id = this.$route.query.id
    request.get("/customer/"+id).then(res =>{
      this.params = res.data
      // console.log(res.data)
    })

  },
  methods: {
    einformation(){
      const id = this.$route.query.id
      request.get("/customer/"+id).then(res =>{
        this.params = res.data
        console.log(res.data)
        this.$router.push('/mainforuser/addCustomer?id='+id)
      })
  },
    checkin(){
      const id = this.$route.query.id
      request.get("/customer/"+id).then(res =>{
        this.params = res.data
        console.log(res.data)
        this.$router.push('/mainforuser/bedSelect?id='+id)
      })
    },
    food(){
      const id = this.$route.query.id
      request.get("/customer/"+id).then(res =>{
        this.params = res.data
        console.log(res.data)
        this.$router.push('/mainforuser/bespokeFood?id='+id)
      })
    },
    eapply(){
      const id = this.$route.query.id
      request.get("/customer/"+id).then(res =>{
        this.params = res.data
        console.log(res.data)
        this.$router.push('/mainforuser/addAbinfo?id='+id)
      })
    },
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
    // edit(row){
    //   this.dialogFormVisible = true
    //   this.form = JSON.parse(JSON.stringify(row))
    // },
    // update(){
    //   request.put('customer/update',this.form).then(res => {
    //     if(res.code === '200'){
    //       this.$notify.success('更新成功')
    //       this.form = {}
    //       this.$router.push('/main/customList')
    //       this.dialogFormVisible = false
    //       this.load()
    //     }else {
    //       this.$notify.error(res.msg)
    //     }
    //   })
    // },
    // del(id){
    //   request.delete("/customer/delete/"+id).then(res =>{
    //     if(res.code === '200'){
    //       this.$notify.success("删除成功")
    //       this.load()
    //     }else{
    //       this.$notify.error(res.msg)
    //     }
    //   })
    // },
    // reset() {
    //   this.params = {
    //     pageNum: 1,
    //     pageSize: 5,
    //     id: '',
    //     custIDCard: '',
    //     custName: '',
    //     phone: ''
    //   }
    //   this.load()
    // },
    // handleCurrentChange(pageNum) {
    //   this.params.pageNum = pageNum
    //   this.load()
    // }
  }
}
</script>

<style lang="less">

.customerPage-main{
  width: 100%;
  height: 100%;
}

.view-box{
  border-radius: 15px;
}

.login-box {
  float: left;
  border: 1px solid #ebeef5;
  padding: 40px;
  min-width: 500px;
  border-radius: 30px;
  background: rgba(255,255,255,0.7);
}
.title-center {
  text-align: center;
  color: #409eff;
  margin-bottom: 20px;
}
.text-center {
  text-align: center;
  color: #333333;
  margin-bottom: 20px;
}
.mybtn-width {
  width: 100%;
}

.customerPage-build{
  height: 100%;
  background-image: url("../../img/customerPage.jpg");
  background-size: 100%;
}

</style>

