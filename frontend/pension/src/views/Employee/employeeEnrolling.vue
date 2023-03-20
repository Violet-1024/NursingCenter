<template>
  <el-container>
    <!-- 头部 -->
    <el-header class="bg-lightgray" height="60px">
      <PageTitle :title="'员工管理'" :SecTitle="'员工入职'"></PageTitle>
    </el-header>
    <!-- 信息输入 -->
    <el-main style="padding: 20px 0">
      <div  style="width: 85%" >
        <el-form :inline="true" :model="form" label-width="120px">
          <el-form-item label="姓名">
            <el-input v-model="form.name" placeholder="请输入姓名"></el-input>
          </el-form-item>
          <el-form-item label="性别">
            <el-radio v-model="form.gender" label="男">男</el-radio>
            <el-radio v-model="form.gender" label="女">女</el-radio>
          </el-form-item>
          <el-form-item label="职位">
            <el-input v-model="form.position" placeholder="请输入职位"></el-input>
          </el-form-item>
          <el-form-item label="年龄">
            <el-input v-model="form.age" placeholder="请输入年龄"></el-input>
          </el-form-item>
          <el-form-item label="手机号">
            <el-input v-model="form.phone" placeholder="请输入手机号"></el-input>
          </el-form-item>
          <el-form-item label="身份证号">
            <el-input v-model="form.custIDCard" placeholder="请输入身份证号"></el-input>
          </el-form-item>
          <el-form-item label="地址">
            <el-input v-model="form.address" placeholder="请输入地址"></el-input>
          </el-form-item>
          <el-form-item label="邮箱">
            <el-input v-model="form.email" placeholder="请输入地址"></el-input>
          </el-form-item>
        </el-form>

        <div style="text-align: center">
          <el-button type="primary" @click="save">提交</el-button>
          <router-link to='/main/EmployeeList' style="margin-left: 20px">
            <el-button type="danger">返回</el-button>
          </router-link>
        </div>
      </div>

    </el-main>
  </el-container>
</template>

<script>
import request from "@/utils/request";
import PageTitle from "../../components/common/title.vue";
export default {
  name: 'EmployeeList',
  data() {
    return {
      form:{
      }
    }
  },
  components: {
    PageTitle,
  },
  methods:{
    save(){
      request.post('employee/save',this.form).then(res => {
        if(res.code === '200'){
          this.$notify.success('新增成功')
          this.form = {}
        }else {
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>

<style>

</style>