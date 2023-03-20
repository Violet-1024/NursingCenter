<template>
  <el-container>
    <el-header>
      <!-- 顶部 -->
      <Header></Header>
    </el-header>
    <el-container>
      <el-aside width="250px">
        <!-- 左侧 -->
        <Aside></Aside>
      </el-aside>
      <el-main class="main-bgc" :style="{ height: windowHeight }">
        <!-- 右侧 -->
        <router-view></router-view>
      </el-main>
    </el-container>
  </el-container>
</template>

<script>
import Header from "../components/header/header.vue";
import Aside from "../components/aside/aside.vue";

export default {
  data() {
    return {
      windowHeight: "",
    };
  },
  components: {
    Header,
    Aside,
  },
  created() {
    this.windowHeight = window.innerHeight - 63 + "px";
    window.onresize = function () {
      this.windowHeight = window.innerHeight - 63 + "px";
    };
  },
};
</script>

<style lang="less">
.main-bgc {
  background-color: #e8ebf0;
}
</style>