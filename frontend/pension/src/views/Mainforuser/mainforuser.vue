<template>
  <div>
    <el-container>
      <el-header>
        <Top />
      </el-header>
      <el-main>
        <router-view></router-view>
      </el-main>
    </el-container>
  </div>
</template>

<script>
import Top from '../../components/top/top.vue'

export default {
  name: "Mainforuser",
  components: {
    Top,
  }
}
</script>

<style scoped>
.el-main {
  padding: 0;
}
.el-header {
  padding: 0;
}

</style>