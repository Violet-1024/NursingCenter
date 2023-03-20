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
export default {
  data() {
    return {
      newTime: ""
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