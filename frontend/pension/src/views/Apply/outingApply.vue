<template>
  <el-container>
    <el-header>
      <Title :title="titleName" :SecTitle="SecTitleName"></Title>
    </el-header>
    <el-main :style="styleMain">
      <el-row>
        <el-col>
          <el-form :inline="true" :model="params">
            <el-form-item label="申请人姓名" :style="styleMl_20">
              <el-input v-model="params.name" placeholder="请输入姓名"></el-input>
            </el-form-item>
            <el-form-item label="陪护员" :style="styleMl_20">
              <el-input v-model="params.companion" placeholder="请输入陪护员"></el-input>
            </el-form-item>
            <el-form-item label="审批状态">
              <el-select v-model="params.status" placeholder="请选择">
                <el-option
                    v-for="item in stateOptions"
                    :key="item.value"
                    :label="item.label"
                    :value="item.value"
                    :disabled="item.disabled"
                >
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item :style="styleMl_20">
              <el-button type="success" @click="load"><i class="el-icon-search"></i> 筛选</el-button>
              <el-button style="margin-left: 5px" type="warning" @click="reset"><i class="el-icon-refresh"></i> 重置
              </el-button>
            </el-form-item>
          </el-form>
        </el-col>
      </el-row>


      <el-row>
        <el-col>
          <el-table :data="tableData">
            <!--            <el-table-column label="申请单编号" prop="outingid"></el-table-column>-->
            <el-table-column label="申请时间" prop="createtime" width="200%">
              <template slot-scope="scope">
                {{ goTime(scope.row.createtime) }}
              </template>
            </el-table-column>
            <!--            <el-table-column label="申请人ID" prop="userid"></el-table-column>-->
            <el-table-column label="申请人姓名" prop="name"></el-table-column>
            <el-table-column label="预计外出时间" prop="starttime"></el-table-column>
            <el-table-column label="预计返回时间" prop="endingtime"></el-table-column>
            <el-table-column label="陪护员" prop="companion"></el-table-column>
            <el-table-column label="处理时间" prop="dealtime" width="200%">
              <template slot-scope="scope">
                {{ goTime(scope.row.dealtime) }}
              </template>
            </el-table-column>
            <el-table-column label="状态" prop="status"></el-table-column>

            <el-table-column label="操作" width="80%">
              <!-- 查看 -->
              <template  slot-scope="scope">
                <el-button type="text" @click="edit(scope.row)">处理</el-button>
              </template>
            </el-table-column>
          </el-table>


          <el-dialog title="处理" :visible.sync="dialogTableVisible" center>
            <el-form :model="form" label-width="120px">
              <el-form-item label="状态">
                <br>
                <el-radio-group v-model="form.status">
                  <el-radio label="未处理">未处理</el-radio>
                  <el-radio label="已同意">已同意</el-radio>
                  <el-radio label="已拒绝">已拒绝</el-radio>
                </el-radio-group>
              </el-form-item>
            </el-form>


            <div slot="footer" class="dialog-footer">
              <el-button @click="save">确认</el-button>
              <el-button @click="dialogTableVisible = false">返回</el-button>
            </div>
          </el-dialog>
        </el-col>
      </el-row>
    </el-main>
    <!--    分页-->
    <div style="margin-top:20px">
      <el-pagination
          background
          :current-page="params.pageNum"
          :page-size="params.pageSize"
          layout="prev, pager, next"
          @current-change="handleCurrentChange"
          :total="total">
      </el-pagination>
    </div>
  </el-container>
</template>

<script>
import Title from "../../components/common/title.vue";
import Paging from "../../components/paging/paging.vue";
import request from "@/utils/request";

export default {
  data: function () {
    return {
      //标题
      titleName: '申请管理',
      SecTitleName: '外出申请审批',
      //主体
      styleMain: {
        padding: "20px 0px",
      },
      styleMl_20: {
        marginLeft: '20px',
      },
      total: 0,
      dialogTableVisible: false,
      params: {
        pageNum: 1,
        pageSize: 5,
        name: '',
        companion: '',
        status: ''
      },
      tableData: [],
      stateOptions: [
        {
          value: "待处理",
          label: "待处理",
        },
        {
          value: "已同意",
          label: "已同意",
        },
        {
          value: "已拒绝",
          label: "已拒绝",
        },
      ],
      form: {},
    };
  },

  components: {
    Title,
    Paging,
  },
  created() {
    this.load()
  },
  methods: {
    load() {
      request.get('/outing/page', {
        params: this.params
      }).then(res => {
        if (res.code === '200') {
          this.tableData = res.data.list
          this.total = res.data.total
        }
      })
    },
    reset() {
      this.params = {
        pageNum: 1,
        pageSize: 5,
        name: '',
        companion: '',
        status: ''
      },
          this.load()
    },

    edit(row) {
      this.dialogTableVisible = true
      this.form = JSON.parse(JSON.stringify(row))
    },
    save() {
      this.form.dealtime = new Date().getTime()
      request.put("/outing/update/", this.form).then(res => {
        if (res.code === '200') {
          this.$notify.success('更新成功')
          this.dialogTableVisible = false

        } else {
          this.$notify.error('更新失败')
        }
        this.load()
      })
    },
    //时间格式化
    goTime(val) {
      if (val === null || val === '')
        return "未处理"
      // return val.toString()
      var timestamp4 = new Date(val);
      //利用拼接正则等手段转化为yyyy-MM-dd hh:mm:ss 格式
      return timestamp4.toLocaleDateString().replace(/\//g, "-") + " " + timestamp4.toTimeString().substr(0, 8);
    },
    handleCurrentChange(pageNum) {
      this.params.pageNum = pageNum
      this.load()
    }

  },
};
</script>

<style lang="less" scoped>


</style>