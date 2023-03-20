<template>
  <el-container>
    <!-- 头部 -->
    <el-header class="bg-lightgray" height="60px">
      <PageTitle :title="'员工管理'" :SecTitle="'员工列表'"></PageTitle>
    </el-header>
    <el-main style="padding: 20px 0">
      <!-- 导航栏 -->
      <el-row class="search">
        <el-col>
          <el-form :inline="true" :model="formInline" class="demo-form-inline">
            <el-form-item label="创建时间">
              <el-date-picker
                  type="datetime"
                  placeholder="选择日期"
                  v-model="formInline.datein"
                  value-format="timestamp"
                  style="width: 100%"
              ></el-date-picker>
            </el-form-item>

            <el-form-item label="姓名">
              <el-input
                  v-model="formInline.name"
                  placeholder="请输入姓名"
              ></el-input>
            </el-form-item>
            <el-form-item label="用户名">
              <el-input
                  v-model="formInline.username"
                  placeholder="请输入用户名"
              ></el-input>
            </el-form-item>

            <el-form-item label="职位">
              <el-input
                  v-model="formLabelAlign.escortsposition"
                  placeholder="请输入职位"
              ></el-input>
            </el-form-item>

            <el-form-item label="状态">
              <el-select v-model="formInline.region" placeholder="请选择">
                <el-option label="在职" value="在职"></el-option>
                <el-option label="离职" value="离职"></el-option>
              </el-select>
            </el-form-item>

            <el-form-item>
              <el-button type="primary" @click="search">筛选</el-button>
            </el-form-item>
            <el-form-item>
              <el-button type="primary" @click="addTeam">添加</el-button>
            </el-form-item>
          </el-form>
          <!-- 添加提示框 -->
          <el-dialog title="添加" :visible.sync="dialogVisible">
            <el-form
                :label-position="labelPosition"
                label-width="80px"
                :model="formLabelAlign"
            >
              <el-form-item label="用户名">
                <el-input
                    v-model="formLabelAlign.escortsusername"
                    placeholder="请输入用户名"
                ></el-input>
              </el-form-item>
              <el-form-item label="姓名">
                <el-input
                    v-model="formLabelAlign.escortsname"
                    placeholder="请输入姓名"
                ></el-input>
              </el-form-item>

              <el-form-item label="电话">
                <el-input
                    v-model="formLabelAlign.escortsphone"
                    placeholder="请输入电话"
                ></el-input>
              </el-form-item>
              <el-form-item label="职位">
                <el-input
                    v-model="formLabelAlign.escortsposition"
                    placeholder="请输入职位"
                ></el-input>
              </el-form-item>
              <el-form-item label="状态：">
                <el-radio-group v-model="formLabelAlign.escortsstatus">
                  <el-radio label="在职"></el-radio>
                  <el-radio label="离职"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="addtrue" class="v-btn"
                >确定</el-button
                >
                <el-button class="v-btn" @click="dialogVisible = false"
                >取消</el-button
                >
              </el-form-item>
            </el-form>
          </el-dialog>
          <!-- 编辑对话框 -->
          <el-dialog title="编辑" :visible.sync="dialogVisible1">
            <el-form
                :label-position="labelPosition"
                label-width="80px"
                :model="seeInfo"
            >
              <el-form-item label="用户名">
                <el-input
                    v-model="seeInfo.escortsusername"
                    placeholder="请输入用户名"
                ></el-input>
              </el-form-item>
              <el-form-item label="姓名">
                <el-input
                    v-model="seeInfo.escortsname"
                    placeholder="请输入姓名"
                ></el-input>
              </el-form-item>

              <el-form-item label="职位">
                <el-input
                    v-model="formLabelAlign.escortsposition"
                    placeholder="请输入职位"
                ></el-input>
              </el-form-item>

              <el-form-item label="状态：">
                <el-radio-group v-model="seeInfo.escortsUsestatus">
                  <el-radio label="在职"></el-radio>
                  <el-radio label="离职"></el-radio>
                </el-radio-group>
              </el-form-item>
              <el-form-item>
                <el-button type="primary" @click="editDatabtn" class="v-btn"
                >确定</el-button
                >
                <el-button class="v-btn" @click="dialogVisible1 = false"
                >取消</el-button
                >
              </el-form-item>
            </el-form>
          </el-dialog>
          <!-- 查看对话框 -->
          <el-dialog title="查看" :visible.sync="dialogVisible2">
            <el-descriptions title="自定义样式列表" :column="1" border>
              <el-descriptions-item
                  label="用户名"
                  label-class-name="my-label"
                  content-class-name="my-content"
              >{{ seeInfo.escortsusername }}</el-descriptions-item
              >
              <el-descriptions-item label="姓名">
                {{ seeInfo.escortsname }}</el-descriptions-item
              >

              <el-descriptions-item label="电话">
                {{ seeInfo.escortsphone }}
              </el-descriptions-item>
              <el-descriptions-item label="职位">
                {{ seeInfo.escortsposition }}
              </el-descriptions-item>
              <el-descriptions-item label="能力范围">
                222
              </el-descriptions-item>
              <el-descriptions-item label="创建时间">
                {{ seeInfo.escortcreated }}
              </el-descriptions-item>
              <el-descriptions-item label="状态">
                {{ seeInfo.escortsUsestatus }}
              </el-descriptions-item>
            </el-descriptions>
            <!-- 返回按钮 -->
            <el-button type="primary" @click="backbtn" class="v-btn"
            >返回</el-button
            >
          </el-dialog>
        </el-col>
      </el-row>
      <!-- 表单 -->
      <el-row class="table_data">
        <el-col :span="24">
          <el-table :data="tableData" style="width: 100%">
            <el-table-column fixed prop="escortsName" label="用户名">
            </el-table-column>
            <el-table-column prop="escortsUsername" label="姓名">
            </el-table-column>
            <el-table-column prop="escortsPhone" label="电话">
            </el-table-column>
            <el-table-column prop="escortsPosition" label="职位">
            </el-table-column>
            <el-table-column prop="escortsPosition" label="入职时间">
            </el-table-column>
            <el-table-column prop="escortsPosition" label="离职时间">
            </el-table-column>
            <el-table-column prop="escortsCreated" label="创建时间">
            </el-table-column>

            <el-table-column prop="escortsUsestatus" label="状态">
            </el-table-column>
            <el-table-column fixed="right" label="操作" width="220px">
              <template slot-scope="scope">
                <el-button
                    @click="handleClick(scope.$index, scope.row)"
                    size="mini"
                >查看</el-button
                >
                <el-button
                    size="mini"
                    @click="handleEdit(scope.$index, scope.row)"
                >编辑</el-button
                >
                <el-button
                    size="mini"
                    type="danger"
                    @click="handleDelete(scope.$index, scope.row)"
                >删除</el-button
                >
              </template>
            </el-table-column>
          </el-table>
        </el-col>
      </el-row>
    </el-main>
    <!-- 分页 -->
    <el-main style="padding: 20px 0">
      <Paging
          @pagesizeChange="getPagesize"
          @currentPageChange="getCurrentPage"
          :total="total"
          :pagesize="pagesize"
          :currentPage="currentPage"
      ></Paging>
    </el-main>
  </el-container>
</template>

<script>
import Paging from "../../components/paging/paging.vue";
import PageTitle from "../../components/common/title.vue";
export default {
  data() {
    return {
      id: 1,
      imageUrl: "",
      exitSelect: [],
      formInline: {
        name: "",
        username: "",
        region: "",
        datein: "",
        position: "",
      },
      currentPage: 1, //当前页数
      total: 5, //总共多少条，这个需要通过接口获取后赋值
      pagesize: 4, //有2，4，6，8，10可选
      dialogVisible: false,
      dialogVisible1: false,
      dialogVisible2: false,
      labelPosition: "top",
      tableData: [],
      //添加表单数据
      formLabelAlign: {
        escortcreated: "",
        escortsposition: "",
        escortsname: "",
        escortsphone: "",
        escortsservices: "",
        escortsstatus: "",
        escortsusername: "",
      },
      //编辑表单的数据
      exitInfo: {
        escortcreated: "",
        escortsposition: "",
        escortsname: "",
        escortsphone: "",
        escortsservices: "",
        escortsUsestatus: "",
        escortsusername: "",
      },
      //查看表单的数据
      seeInfo: {
        escortcreated: "",
        escortsposition: "",
        escortsname: "",
        escortsphone: "",
        escortsservices: "",
        escortsUsestatus: "",
        escortsusername: "",
      },
      formLabelWidth: "120px",
    };
  },
  components: {
    PageTitle,
    Paging,
  },
  created() {
    let status = this.SecstatusTransfer(this.formInline.region);
    this.getListData(
        this.currentPage,
        this.pagesize,
        this.formInline.username,
        this.formInline.name,
        status,
        this.formInline.position,
        this.formInline.datein,
    );
    this.AreaData();
  },
  methods: {
    //子组件传入pagesize
    getPagesize(val) {
      this.pagesize = val;
      let status = this.SecstatusTransfer(this.formInline.region);
      this.getListData(
          this.currentPage,
          this.pagesize,
          this.formInline.username,
          this.formInline.name,
          status,
          this.formInline.position
      );
      // console.log(this.pagesize);
    },
    //子组件传入CurrentPage
    getCurrentPage(val) {
      this.currentPage = val;
      let status = this.SecstatusTransfer(this.formInline.region);
      this.getListData(
          this.currentPage,
          this.pagesize,
          this.formInline.username,
          this.formInline.name,
          status,
          this.formInline.position
      );
    },
    //区域接口
    async AreaData() {
      const { data: res } = await this.axios({
        method: "GET",
        url: "/area/all",

      });
      console.log(res);
      if (res.code == 0) {
        this.exitSelect = res.data;
      }
    },
    //获取数据接口
    async getListData(
        page,
        pagesize,
        escortsName,
        escortsUsername,
        escortsUsestatus,
        escortsArea,
        escortsCreated
    ) {
      const { data: res } = await this.axios({
        method: "POST",
        url: "/escorts/findByWhere",
        data: {
          page: page,
          pageSize: pagesize,
          escortsName: escortsName,
          escortsUsername: escortsUsername,
          escortUsestatus: escortsUsestatus,
          escortsArea: escortsArea,
          escortsCreated:escortsCreated
        },

      });
      console.log(res);
      if (res.code == 0) {
        res.data.forEach((item) => {
          // console.log(item.escortCreated)
          let newTime = this.timeTransfer(item.escortsCreated);
          item.escortsCreated = newTime;
          if (item.escortsUsestatus == "1") {
            item.escortsUsestatus = "在职";
          } else {
            item.escortsUsestatus = "离职";
          }
        });
        this.tableData = res.data;
        this.total = res.count;
      }
    },
    //添加接口
    async addData(
        escortCreated,
        escortsPosition,
        escortsName,
        escortsPhone,
        // escortsServices,
        escortsStatus,
        escortsUsername,
    ) {
      const { data: res } = await this.axios({
        method: "POST",
        url: "/escorts/add",
        data: {
          escortCreated: escortCreated,
          escortsArea: escortsArea,
          // escortsId: 0,
          escortsName: escortsName,
          escortsPhone: escortsPhone,
          // escortsServices: escortsServices,
          escortsUsestatus: escortsStatus,
          escortsUsername: escortsUsername,
        },

      });
      console.log(res);
      if (res.code == 0) {
        // let newTime = this.timeTransfer(res.data.escortCreated);
        // res.data.escortCreated = newTime;
        this.getListData(this.currentPage, this.pagesize);
      }
    },
    //查看接口
    async searchData(seeID) {
      const { data: res } = await this.axios({
        method: "GET",
        url: "/escorts/findById/" + seeID,

      });
      // console.log(res);
      // console.log("查看", res.data.oderNumber);
      if (res.code == 0) {
        let newTime = this.timeTransfer(res.data.escortsCreated);
        res.data.escortsCreated = newTime;
        this.seeInfo.escortsusername = res.data.escortsUsername;
        this.seeInfo.escortsname = res.data.escortsName;
        this.seeInfo.escortsposition = res.data.escortsPosition;
        this.seeInfo.escortsphone = res.data.escortsPhone;
        this.seeInfo.escortcreated = res.data.escortsCreated;
        this.seeInfo.escortsUsestatus = this.statusTransfer(
            res.data.escortsUsestatus
        );
      }
    },
    //删除接口
    async deleData(deleId) {
      const { data: res } = await this.axios({
        method: "GET",
        url: "/escorts/deleteById/" + deleId,

      });

      if (res.code == 0) {
        console.log("删除", res);
        this.getListData(this.currentPage, this.pagesize);
      }
    },
    //编辑接口
    async editData(
        exitId,
        escortsPosition,
        escortsName,
        escortsPhone,
        escortsUsestatus,
        escortsUsername,
    ) {
      const { data: res } = await this.axios({
        method: "POST",
        url: "/escorts/update",
        data: {
          escortsId: exitId,
          // escortCreated: escortCreated,
          escortsPosition: escortsPosition,
          escortsName: escortsName,
          escortsPhone: escortsPhone,
          // escortsservices: escortsservices,
          escortsUsestatus: escortsUsestatus,
          escortsUsername: escortsUsername,
        },

      });
      // console.log(res);
      if (res.code == 0) {
        this.getListData(this.currentPage, this.pagesize);
      }
    },
    //搜索函数
    search() {
      let status = this.SecstatusTransfer(this.formInline.region);

      this.getListData(
          this.currentPage,
          this.pagesize,
          this.formInline.username,
          this.formInline.name,
          status,
          this.formInline.position,
          this.formInline.datein,
      );
      // console.log(this.formInline.name);
      // console.log(this.formInline.username);
      // console.log(typeof(this.formInline.region));
      // console.log(this.formInline.position);
      console.log("时间",this.formInline.datein)
    },
    //添加函数
    addTeam() {
      this.dialogVisible = true;
    },
    //确定添加按钮
    addtrue() {
      this.dialogVisible = false;
      this.formLabelAlign.escortsstatus = this.SecstatusTransfer(
          this.formLabelAlign.escortsstatus
      );
      this.addData(
          this.formLabelAlign.escortcreated,
          this.formLabelAlign.escortsposition,
          this.formLabelAlign.escortsname,
          this.formLabelAlign.escortsphone,
          // this.formLabelAlign.escortsservices,
          this.formLabelAlign.escortsstatus,
          this.formLabelAlign.escortsusername,
      );
    },
    //查看函数
    handleClick(index, row) {
      this.dialogVisible2 = true;
      this.searchData(row.escortsId);
    },
    //编辑函数
    handleEdit(index, row) {
      this.dialogVisible1 = true;
      this.id = row.escortsId;
      this.searchData(row.escortsId);
    },
    //编辑确定函数
    editDatabtn() {
      this.dialogVisible1 = false;

      this.seeInfo.escortsUsestatus = this.SecstatusTransfer(
          this.seeInfo.escortsUsestatus
      );

      this.editData(
          this.id,
          // this.exitInfo.escortcreated,
          this.seeInfo.escortsposition,
          this.seeInfo.escortsname,
          this.seeInfo.escortsphone,
          this.seeInfo.escortsUsestatus,
          this.seeInfo.escortsusername,
      );
    },
    //时间戳的转换
    timeTransfer(timestamp) {
      var date = new Date(timestamp); //时间戳为10位需*1000，时间戳为13位的话不需乘1000
      var Y = date.getFullYear() + "-";
      var M =
          (date.getMonth() + 1 < 10
              ? "0" + (date.getMonth() + 1)
              : date.getMonth() + 1) + "-";
      var D =
          (date.getDate() < 10 ? "0" + date.getDate() : date.getDate()) + " ";
      var h =
          (date.getHours() < 10 ? "0" + date.getHours() : date.getHours()) + ":";
      var m =
          (date.getMinutes() < 10 ? "0" + date.getMinutes() : date.getMinutes()) +
          ":";
      var s =
          date.getSeconds() < 10 ? "0" + date.getSeconds() : date.getSeconds();
      return Y + M + D + h + m + s;
    },
    //状态转换:文字转数字
    SecstatusTransfer(statusTemp) {
      if (statusTemp == "禁用") {
        statusTemp = "0";
      } else if (statusTemp == "启用") {
        statusTemp = "1";
      }
      return statusTemp;
    },
    //状态转换:数字转文字
    statusTransfer(statusTemp) {
      if (statusTemp == "0") {
        statusTemp = "禁用";
      } else if (statusTemp == "1") {
        statusTemp = "启用";
      }
      return statusTemp;
    },
    //返回函数
    backbtn() {
      this.dialogVisible2 = false;
    },
    handleDelete(index, row) {
      this.$confirm("此操作将永久删除该文件, 是否继续?", "提示", {
        confirmButtonText: "确定",
        cancelButtonText: "取消",
        type: "warnning",
      })
          .then(() => {
            console.log("id", row.escortsId);
            this.deleData(row.escortsId);
            this.$message({
              type: "success",
              message: "删除成功",
            });
          })
          .catch(() => {
            this.$message({
              type: "info",
              message: "已取消删除",
            });
          });
    },

    handleAvatarSuccess(res, file) {
      this.imageUrl = URL.createObjectURL(file.raw);
    },
    handleRemove(file) {
      console.log(file);
    },
    handlePictureCardPreview(file) {
      this.dialogImageUrl = file.url;
      this.dialogVisible = true;
    },
    handleDownload(file) {
      console.log(file);
    },
  },
};
</script>

<style>
.avatar-uploader .el-upload {
  border: 1px dashed #d9d9d9;
  border-radius: 6px;
  cursor: pointer;
  position: relative;
  overflow: hidden;
}
.avatar-uploader .el-upload:hover {
  border-color: #409eff;
}
.avatar-uploader-icon {
  font-size: 28px;
  color: #8c939d;
  width: 178px;
  height: 178px;
  line-height: 178px;
  text-align: center;
}
.avatar {
  width: 178px;
  height: 178px;
  display: block;
}
.v-btn {
  margin-left: 260px;
  color: black;
}
.table_data {
  border: 1px solid rgb(214, 199, 199);
  box-shadow: 0 2px 12px 0 rgba(0, 0, 0, 0.1);
}
</style>