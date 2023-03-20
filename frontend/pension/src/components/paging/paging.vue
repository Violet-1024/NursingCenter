<template>
  <el-row class="pagination-bgc">
    <el-col :span="12" :offset="6">
      <div class="block">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-sizes="[4, 6, 8, 10, 12]"
          :page-size="pagesize"
          layout="total, sizes, prev, pager, next"
          :total="total"
          class="pagination"
        >
        </el-pagination>
      </div>
    </el-col>
  </el-row>
</template>

<script>
export default {
  data() {
    return {
      //pagesize: 4,
      //currentPage: 1,
    };
  },
  props: {
    total: Number, //一共多少条
    pagesize:Number,
    currentPage:Number
  },
  methods: {
    // 对应下拉列表中的每页条数的改变
    handleSizeChange(v) {
 
      this.pagesize = v;
      this.$emit("pagesizeChange", this.pagesize);
    },
    handleCurrentChange(val) {
      
      this.currentPage = val;
      this.$emit("currentPageChange", this.currentPage);
    },
  },
};
</script>

<style lang="less" scoped>
.pagination {
  text-align: center;
}
.pagination-bgc {
  background-color: #fff;
  border-radius: 5px;
  height: 40px;
  padding-top: 3px;
}
</style>
