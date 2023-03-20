<template>
  <el-row class="header-margin">
    <el-col>
      <div class="pg-title">{{ title }}/{{ SecTitle }}</div>
    </el-col>
  </el-row>
</template>

<script>
export default {
  props: {
    title: String,
    SecTitle: String,
  },
};
</script>

<style>
.pg-title {
  padding-left: 10px;
  height: 60px;
  line-height: 60px;
  font-size: 16px;
  font-weight: bold;
  color: #6c6d6d;
  background-color: #fff;
  border-radius: 5px;
}
.header-margin {
  margin-left: -20px;
  margin-right: -20px;
}
</style>