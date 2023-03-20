<template>
  <el-container class="login-build">
    <el-main>
      <el-row
        type="flex"
        justify="center"
        align="middle"
        :style="{ height: windowHeight }"
      >



        <el-col class="login-box" :span="8">
          <h1 class="title-center">东软颐养中心</h1>
          <el-form ref="form" :model="form" :rules="rules" label-width="55px">
            <el-form-item label="账号:" prop="username">
              <el-input v-model="form.username" placeholder="请输入账号"></el-input>
            </el-form-item>
            <el-form-item label="密码:" prop="password">
              <el-input
                v-model="form.password"
                type="password"
                placeholder="请输入密码"
                show-password
              ></el-input>
            </el-form-item>
            <el-form-item>
              <el-radio v-model="form.position" label="管理员">管理员</el-radio>
              <el-radio v-model="form.position" label="用户">用户</el-radio>
            </el-form-item>
            <el-form-item>
              <el-button
                class="mybtn-width"
                type="primary"
                @click="login"
                >登录</el-button>
            </el-form-item>

            <el-form-item>
              <el-button
                  class="mybtn-width"
                  type="primary"
                  @click="register"
              >注册</el-button>
            </el-form-item>

          </el-form>
        </el-col>
      </el-row>

      <!--    编辑对话框-->
      <el-dialog title="注册" custom-class="view-box"
                 width="40%" :visible.sync="this.dialogFormVisible" center>
        <el-form :model="form" label-width="80px">

          <el-form-item label="用户名" >
            <el-input  v-model="form.username" placeholder="请输入用户名"></el-input>
          </el-form-item>

          <el-form-item label="密码">
            <el-input type="password" v-model="form.password" placeholder="请输入密码"></el-input>
          </el-form-item>
          <el-form-item label="确认密码">
            <el-input type="password" v-model="form.rpassword" placeholder="请输确认密码"></el-input>
          </el-form-item>

        </el-form>
        <div slot="footer" class="dialog-footer">
          <el-button @click="save">确认</el-button>
          <el-button @click="cancel">返回</el-button>
        </div>

      </el-dialog>

    </el-main>
  </el-container>
</template>

<script>
import request from "@/utils/request";

export default {
  name:'login',
  data() {
    return {
      windowHeight: 0,
      dialogFormVisible:false,
      form: {
        username: "",
        id:"",
        password: "",
        phone:"",
        position: "用户",
      },
      //验证规则的设置
      rules: {
        username: [
          { required: true, message: "必须输入账号", trigger: "blur" },
          {
            min: 3,
            max: 20,
            message: "账号的长度在3 - 20 位字符",
            trigger: "blur",
          },
        ],
        password: [
          { required: true, message: "必须输入密码", trigger: "blur" },
          {
            min: 6,
            max: 12,
            message: "密码的长度是 6 - 12 位字符",
            trigger: "blur",
          },
        ],
      },
    };
  },
  methods: {
    register(){
      this.dialogFormVisible=true
    },

    login(){
      if(this.form.position === '管理员'){
        request.post('/admin/login',this.form).then(res =>{
          if(res.code==='200'){
            this.$notify.success("登录成功")
            this.$router.push('/main')
          }else{
            this.$notify.error(res.msg)
          }
        })
      }else{
        request.post('/customer/login',this.form).then(res =>{
          if(res.code==='200'){
            this.$notify.success("用户登录成功")
            const id = res.data.id;
            this.$router.push('/mainforuser/customerPage?id='+id)

          }else{
            this.$notify.error(res.msg)
          }
        })
      }

    },
    cancel(){
      this.dialogFormVisible = false//  <<==
    },
    save(){
      // form.id = this.$route.query.id;
      request.post('customer/save',this.form).then(res => {
        this.$notify.success('注册成功')

        this.dialogFormVisible = false          //  <<==


      })
    },

  },
  created() {
    this.windowHeight = window.innerHeight - 41 + "px";
    window.onresize = function () {
      this.windowHeight = window.innerHeight - 41 + "px";
    };
  },
};
</script>

<style lang="less" scoped>
.login-box {
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
.mybtn-width {
  width: 100%;
}

.login-build{
  background-image: url("../img/home.jpg");
  background-size: 100%;
}
</style>