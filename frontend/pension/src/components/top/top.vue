
<template>
  <div class="position">
    <el-row
        class="main-header"
        type="flex"
        justify="center"
        align="middle"
        style="background-color: lightslategray "
    >
      <el-col :span="8" class="line-height">
        <span class="header-title">东软颐养中心</span>
      </el-col>
      <el-col :span="6" class="datetime" offset="8">
        <span class = "date" style="color: #20b9ae">{{ newTime }}</span>
      </el-col>


      <el-col :span="3" class ="admin">
        <el-avatar icon="el-icon-user-solid"></el-avatar>
      </el-col>


      <el-col :span="2" >
        <el-link type="button" class="ml" @click.prevent="exit" >
          <i class="el-icon-switch-button">退出登录</i></el-link>
      </el-col>
    </el-row>
  </div>
</template>

<script>
import login from "../../views/login.vue"

export default {
  data() {
    return {
      newTime: "",
      username:""
    }
  },

  methods: {
    exit() {
      sessionStorage.removeItem("token");
      sessionStorage.removeItem("index");
      this.$router.push("/login");
    },

    getNowTime() {
      var date = new Date();
      let Month = String(date.getMonth() + 1).length > 1?(date.getMonth() + 1):'0'+(date.getMonth() + 1);
      let Datedata = String(date.getDate()).length > 1?date.getDate():"0"+(date.getDate())
      let Hours = String(date.getHours()).length > 1?date.getHours():"0"+(date.getHours())
      let Minutes = String(date.getMinutes()).length > 1?date.getMinutes():"0"+(date.getMinutes())
      let Seconds = String(date.getSeconds()).length > 1?date.getSeconds():"0"+date.getSeconds()
      var time = date.getFullYear() + '-' + Month + '-' + Datedata + ' ' + Hours + ':' + Minutes + ':' + Seconds;
      this.newTime = time;
    },

    getusername(){
      this.username = login.data().form.username
    },
    addZero(s) {
      return s < 10 ? ('0' + s) : s;
    },

  },

  mounted(){
    this.getNowTime();
    clearInterval(myTimeDisplay);
    var myTimeDisplay = setInterval(() => {this.getNowTime();}, 1000);
  },

}

</script>

<style lang="less" scoped>
.main-header {
  height: 60px;
  background-color: rgb(252, 248, 248);
  margin-left: -20px;
}
.position {
  position: fixed;
  z-index: 1;
  width: 100%;
}
.header-title {
  margin-left: 30px;
  color: #20b9ae;
  font-size: 22px;
  font-weight: bolder;
}
.ml {
  margin-left: 6px;
  font-size: 16px;

  vertical-align:middle
}
.admin {
  font-size: 18px;
  text-align: center;
}
.datetime {
  font-style: normal;
  font-size: 16px;
  text-align: right;
}
.el-avatar {
  margin-left: 120px;
}
</style>











<!--<template>-->
<!--  <div>-->
<!--    <el-menu :default-active="1" class="el-menu-demo" mode="horizontal" @select="handleSelect"-->
<!--             background-color="#32896C" text-color="#ffffff">-->
<!--      <el-menu-item index="1">首页</el-menu-item>-->
<!--      <el-submenu index="2">-->
<!--        <template slot="title">客户管理</template>-->
<!--        <el-menu-item index="2-1">客户列表</el-menu-item>-->
<!--        <el-menu-item index="2-2">客户入住</el-menu-item>-->
<!--        <el-menu-item index="2-3">客户退住</el-menu-item>-->
<!--      </el-submenu>-->
<!--      <el-menu-item index="3">床位管理</el-menu-item>-->
<!--      <el-submenu index="4">-->
<!--        <template slot="title">护理管理</template>-->
<!--        <el-menu-item index="4-1">护理设置</el-menu-item>-->
<!--        <el-menu-item index="4-2">定制护理</el-menu-item>-->
<!--      </el-submenu>-->
<!--      <el-submenu index="5">-->
<!--        <template slot="title">膳食管理</template>-->
<!--        <el-menu-item index="5-1">膳食列表</el-menu-item>-->
<!--        <el-menu-item index="5-2">制定膳食</el-menu-item>-->
<!--      </el-submenu>-->
<!--      <el-submenu index="6">-->
<!--        <template slot="title">员工管理</template>-->
<!--        <el-menu-item index="6-1">客户列表</el-menu-item>-->
<!--        <el-menu-item index="6-2">员工入职</el-menu-item>-->
<!--        <el-menu-item index="6-3">员工离职</el-menu-item>-->
<!--      </el-submenu>-->
<!--      <el-submenu index="7">-->
<!--        <template slot="title">异常状态</template>-->
<!--        <el-menu-item index="7-1">申请信息</el-menu-item>-->
<!--        <el-menu-item index="7-2">审核信息</el-menu-item>-->
<!--      </el-submenu>-->
<!--      <el-submenu index="8">-->
<!--        <template slot="title">健康管理</template>-->
<!--        <el-menu-item index="8-1">健康监测</el-menu-item>-->
<!--        <el-menu-item index="8-2">养护记录</el-menu-item>-->
<!--        <el-menu-item index="8-3">健康日历</el-menu-item>-->
<!--      </el-submenu>-->
<!--    </el-menu>-->
<!--  </div>-->
<!--</template>-->

<!--<script>-->
<!--export default {-->
<!--  name: "Top",-->
<!--  data() {-->
<!--    return {}-->
<!--  },-->
<!--  methods: {-->
<!--    handleSelect(id) {-->
<!--      switch (id) {-->
<!--        case '1':-->
<!--          this.$router.push('/main')-->
<!--          break;-->
<!--        case '2':-->
<!--          this.$router.push('/mainforuser/customerCheckin')-->
<!--          break;-->
<!--        case '2-1':-->
<!--          this.$router.push('/mainforuser/customerCheckin')-->
<!--          break;-->
<!--        case '2-2':-->
<!--          this.$router.push('/mainforuser/customerCheckin')-->
<!--          break;-->
<!--        case '2-3':-->
<!--          this.$router.push('/main')-->
<!--          break;-->
<!--        case '3':-->
<!--          this.$router.push('/main')-->
<!--          break;-->
<!--        case '4-1':-->
<!--          this.$router.push('/main')-->
<!--          break;-->
<!--        case '4-2':-->
<!--          this.$router.push('/main')-->
<!--          break;-->
<!--        case '5-1':-->
<!--          this.$router.push('/main')-->
<!--          break;-->
<!--        case '5-2':-->
<!--          this.$router.push('/main')-->
<!--          break;-->
<!--        case '6-1':-->
<!--          this.$router.push('/main')-->
<!--          break;-->
<!--        case '6-2':-->
<!--          this.$router.push('/main')-->
<!--          break;-->
<!--        case '6-3':-->
<!--          this.$router.push('/main')-->
<!--          break;-->
<!--        case '7-1':-->
<!--          this.$router.push('/main')-->
<!--          break;-->
<!--        case '7-2':-->
<!--          this.$router.push('/main')-->
<!--          break;-->
<!--        case '8-1':-->
<!--          this.$router.push('/main')-->
<!--          break;-->
<!--        case '8-2':-->
<!--          this.$router.push('/main')-->
<!--          break;-->
<!--        case '8-3':-->
<!--          this.$router.push('/main')-->
<!--          break;-->
<!--      }-->
<!--    }-->
<!--  }-->
<!--}-->
<!--</script>-->

<!--<style scoped>-->
<!--.el-menu-item,el-submenu  {-->
<!--  width: 100px;-->
<!--  text-align: center;-->
<!--  font-weight: bold;-->
<!--}-->
<!--li.el-submenu {-->
<!--  font-weight: bold;-->
<!--  width: 100px;-->
<!--}-->
<!--</style>-->