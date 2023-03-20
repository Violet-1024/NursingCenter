<template>
  <el-container>
    <!-- 头部 -->
    <el-header class="bg-lightgray" height="60px">
      <PageTitle :title="'预案管理'" :SecTitle="'新建预案'"></PageTitle>
    </el-header>
    <!-- 信息输入 -->
    <el-main style="padding: 20px 0">
      <div  style="width: 85%" >
        <el-form :inline="true" :model="form" label-width="120px">
          <el-form-item label="编号">
            <el-input v-model="form.planID"  placeholder="请输入预案编号"></el-input>
          </el-form-item>
          <el-form-item label="名称">
            <el-input v-model="form.planName" placeholder="请输入预案名称"></el-input>
          </el-form-item>
          <el-form-item label="预案类型">
            <el-input v-model="form.planType" placeholder="请输入预案类型"></el-input>
          </el-form-item>
          <el-form-item label="事件类型">
            <el-input v-model="form.accentType" placeholder="请输入事件类型"></el-input>
          </el-form-item>
          <el-form-item label="新建时间">
            <el-date-picker v-model="form.newTime" type="datetime" format="yyyy-MM-dd HH:mm:ss"
                            value-formata="yyyy-MM-dd HH:mm:ss" placeholder="请选择新建时间">

            </el-date-picker>
          </el-form-item>
          <el-form-item label="状态">
            <el-radio v-model="form.state" label="启用">启用</el-radio>
            <el-radio v-model="form.state" label="停用">停用</el-radio>
          </el-form-item>
        </el-form>

        <div style="text-align: center">
          <el-button type="primary" @click="save">提交</el-button>
          <router-link to='/main/reservePlanList' style="margin-left: 20px">
            <el-button type="danger">返回</el-button>
          </router-link>
        </div>
      </div>

    </el-main>
  </el-container>
</template>


<script>
import PageTitle from "@/components/common/title";
import request from "@/utils/request";

export default {
  name: "reservePlanCreate",
  data() {
    return {
      form:{
        // planID: '',
        // planName: '',
        // planType:'',
        // accentType: '',
        // state:'',
        // newTime:''
      }
    }
  },
  components: {
    PageTitle,
  },

  methods:{
    save(){
      request.post('reservePlan/save',this.form).then(res =>{
        if(res.code ==='200'){
          this.$notify.success('新增成功')
          this.form = {}
          this.$router.push('/main/reservePlanList')
        }else{
          this.$notify.error(res.msg)
        }
      })
    }
  }
}
</script>

<style scoped>

</style>