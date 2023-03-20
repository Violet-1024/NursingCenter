<template>
  <el-container>
    <el-header>
      <Title :title='title1' :SecTitle='title2'></Title>
    </el-header>
    <el-main id="time" class="textcolor">
      <span class="textcolor">时间</span>
      <el-date-picker v-model="find1.startTime" type="date" placeholder="选择日期" id="select_time" value-format="timestamp"></el-date-picker>
      <span class="textcolor mar-left">姓名</span>
      <el-input v-model="find1.name" placeholder="请输入编号" maxlength='100'></el-input>
      <span class="textcolor mar-left">用户</span>
      <el-input v-model="find1.username" placeholder="请输入姓名" maxlength='100'></el-input>
      <div id="business_box" class="mar-left">
        <span class="textcolor">企业</span>
        <el-select v-model="find1.companyId" placeholder="请选择" clearable>
          <el-option v-for="item in business_option" :key="item.id" :label="item.name" :value="item.value">
          </el-option>
        </el-select>
      </div>
      <div id="statu_box" align="center">
        <span class="textcolor mar-top">状态</span>
        <el-select v-model="find1.useStatus" placeholder="请选择" class="" clearable>
          <el-option v-for="item in stutas_options" :key="item.value" :label="item.label" :value="item.value">
          </el-option>
        </el-select>
        <el-button type="primary" @click="findOrder1">筛选</el-button>
        <el-button type="success" @click="dialogFormVisible2 = true">添加</el-button>
        <el-dialog title="添加" :visible.sync="dialogFormVisible2" style="width:auto;">
          <el-form :model="newForm" :rules="rules" ref="newForm">
            <el-form-item label="用户名:" :label-width="formLabelWidth" prop="username">
              <el-input v-model="newForm.username" autocomplete="off" class="input_width" placeholder="请输入用户名"></el-input>
            </el-form-item>
            <el-form-item label="姓名:" :label-width="formLabelWidth" prop="name">
              <el-input v-model="newForm.name" autocomplete="off" class="input_width" placeholder="请输入姓名"></el-input>
            </el-form-item>
            <el-form-item label="电话:" :label-width="formLabelWidth" prop="phone">
              <el-input v-model="newForm.phone" autocomplete="off" class="input_width" placeholder="请输入电话"></el-input>
            </el-form-item>
            <el-form-item label="企业:" :label-width="formLabelWidth" prop="companyId">
              <el-select v-model="newForm.companyId" placeholder="请选择" clearable class="input_width">
                <el-option v-for="item in business_option" :key="item.id" :label="item.name" :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="部门:" :label-width="formLabelWidth" prop="departmentId">
              <el-select v-model="newForm.departmentId" placeholder="请选择" clearable class="input_width">
                <el-option v-for="item in department_option" :key="item.id" :label="item.name" :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="区域:" :label-width="formLabelWidth" prop="areaId">
              <el-select v-model="newForm.areaId" placeholder="请选择" clearable class="input_width">
                <el-option v-for="item in area_option" :key="item.id" :label="item.name" :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item label="角色:" :label-width="formLabelWidth" prop="roleId">
              <el-select v-model="newForm.roleId" placeholder="请选择" clearable class="input_width">
                <el-option v-for="item in role_option" :key="item.id" :label="item.name" :value="item.id">
                </el-option>
              </el-select>
            </el-form-item>
            <el-form-item v-model="newForm.useStatus" label="状态:" prop="useStatus" :label-width="formLabelWidth" style="text-align:left;">
              <el-radio v-model="newForm.useStatus" label="1">启用</el-radio>
              <el-radio v-model="newForm.useStatus" label="2">禁用</el-radio>
            </el-form-item>
          </el-form>
          <div slot="footer" class="dialog-footer">
            <el-button @click="dialogFormVisible2 = resetForm('newForm')">取 消</el-button>
            <el-button type="primary" @click="dialogFormVisible2 = add('newForm')">确定</el-button>
          </div>
        </el-dialog>
      </div>
    </el-main>
    <el-main id="content_box">
      <el-table :data="tableData" style="width: 100%">
        <el-table-column prop="id" label="序号" width="73" header-align="center" align="center">
        </el-table-column>
        <el-table-column prop="username" label="用户名" width="135" header-align="center" align="center">
        </el-table-column>
        <el-table-column prop="name" label="姓名" width="80" header-align="center" align="center">
        </el-table-column>
        <el-table-column prop="roleName" label="角色" width="127" header-align="center" align="center">
        </el-table-column>
        <el-table-column prop="companyName" label="企业" width="100" header-align="center" align="center">
        </el-table-column>
        <el-table-column prop="departmentName" label="部门" width="86" header-align="center" align="center">
        </el-table-column>
        <el-table-column prop="areaName" label="区域" width="86" header-align="center" align="center">
        </el-table-column>
        <el-table-column prop="created" label="创建时间" width="156" header-align="center" align="center">
        </el-table-column>
        <el-table-column prop="useStatus" label="状态" width="72" header-align="center" align="center">
        </el-table-column>
        <el-table-column label="操作" align="center" width="160">
          <template slot-scope="scope">
            <el-button size="mini" type="success" @click="dialogFormVisible = handleEditing(scope.$index, scope.row)">编辑</el-button>
            <el-dialog title="编辑" :visible.sync="dialogFormVisible">
              <el-form :model="changeForm" :rules="rules" ref="changeForm">
                <el-form-item label="用户名:" :label-width="formLabelWidth" prop="username">
                  <el-input v-model="changeForm.username" autocomplete="off" class="input_width" placeholder="请输入用户名"></el-input>
                </el-form-item>
                <el-form-item label="姓名:" :label-width="formLabelWidth" prop="name">
                  <el-input v-model="changeForm.name" autocomplete="off" class="input_width" placeholder="请输入姓名"></el-input>
                </el-form-item>
                <el-form-item label="电话:" :label-width="formLabelWidth" prop="phone">
                  <el-input v-model="changeForm.phone" autocomplete="off" class="input_width" placeholder="请输入电话"></el-input>
                </el-form-item>
                <el-form-item label="企业:" :label-width="formLabelWidth" prop="companyId">
                  <el-select v-model="changeForm.companyId" placeholder="请选择" clearable class="input_width">
                    <el-option v-for="item in business_option" :key="item.id" :label="item.name" :value="item.id">
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="部门:" :label-width="formLabelWidth" prop="departmentId">
                  <el-select v-model="changeForm.departmentId" placeholder="请选择" clearable class="input_width">
                    <el-option v-for="item in department_option" :key="item.id" :label="item.name" :value="item.id">
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="区域:" :label-width="formLabelWidth" prop="areaId">
                  <el-select v-model="changeForm.areaId" placeholder="请选择" clearable class="input_width">
                    <el-option v-for="item in area_option" :key="item.id" :label="item.name" :value="item.id">
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item label="角色:" :label-width="formLabelWidth" prop="roleId">
                  <el-select v-model="changeForm.roleId" placeholder="请选择" clearable class="input_width">
                    <el-option v-for="item in role_option" :key="item.id" :label="item.name" :value="item.id">
                    </el-option>
                  </el-select>
                </el-form-item>
                <el-form-item v-model="changeForm.useStatus" label="状态:" prop="useStatus" :label-width="formLabelWidth" style="text-align:left;">
                  <el-radio v-model="changeForm.useStatus" label="1">启用</el-radio>
                  <el-radio v-model="changeForm.useStatus" label="2">禁用</el-radio>
                </el-form-item>
              </el-form>
              <div slot="footer" class="dialog-footer">
                <el-button @click="dialogFormVisible = resetForm('changeForm')">取 消</el-button>
                <el-button type="primary" @click="dialogFormVisible = submit('changeForm')">确定</el-button>
              </div>
            </el-dialog>
            <el-button size="mini" type="danger" @click="handleDelete(scope.$index, scope.row)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
      <Page
        @pagesizeChange="getPagesize"
        @currentPageChange="getCurrentPage"
        :total="total"
        :pagesize="find1.pageSize"
        :currentPage="find1.page"
      ></Page>
    </el-main>
  </el-container>
</template>

<script>
import Title from '@/components/common/title.vue'
import Page from '@/components/paging/paging.vue'
export default {
  data() {
    return {
      total: 1,
      title1: '膳食管理',
      title2: '制定膳食+暂未完成',
      find1: {
        startTime: '',
        username: '',
        name: '',
        companyId: '',
        useStatus: '',
        pageSize: 4,
        page: 1
      },
      stutas_options: [
        {
          value: '1',
          label: '启用'
        },
        {
          value: '0',
          label: '禁用'
        }
      ],
      tableData: [],
      dialogFormVisible: false,
      dialogFormVisible2: false,
      newForm: {
        username: '',
        name: '',
        phone: '',
        departmentId: '',
        departmentName: '',
        companyName: '',
        companyId: '',
        areaName: '',
        areaId: '',
        roleName: '',
        roleId: '',
        useStatus: ''
      },
      changeForm: {
        username: '',
        name: '',
        phone: '',
        departmentId: '',
        departmentName: '',
        companyName: '',
        companyId: '',
        areaName: '',
        areaId: '',
        roleName: '',
        roleId: '',
        useStatus: '',
        id: ''
      },
      business_option: [],
      department_option: [],
      area_option: [],
      role_option: [{ id: 1, name: 'qqq' }],
      formLabelWidth: '120px',
      rules: {
        username: [
          { required: true, message: '请输入用户名称', trigger: 'blur' },
          { min: 3, max: 10, message: '长度在 3 到 10 个字符', trigger: 'blur' }
        ],
        name: [
          { required: true, message: '请选择活动区域', trigger: 'blur' }
        ],
        phone: [
          { required: true, message: '请填入电话号码', trigger: 'blur' }
        ],
        companyId: [
          { required: true, message: '请选择企业', trigger: 'change' }
        ],
        departmentId: [
          { required: true, message: '请选择部门', trigger: 'change' }
        ],
        areaId: [
          { required: true, message: '请选择区域', trigger: 'change' }
        ],
        roleId: [
          { required: true, message: '请选择角色', trigger: 'change' }
        ],
        useStatus: [
          { required: true, message: '请选择', trigger: 'blur' }
        ]
      }
    }
  },
  components: {
    Title,
    Page
  },
  methods: {
    async getData() {
      const { data: res } = await this.axios({
        method: "POST",
        url: "/admin/list",
        data: {
          startTime: this.find1.startTime,
          username: this.find1.username,
          name: this.find1.name,
          companyId: this.find1.companyId,
          useStatus: this.find1.useStatus,
          pageSize: this.find1.pageSize,
          page: this.find1.page
        },
        
      })
      if (res.code == 0) {
        this.total = res.count
        this.tableData = res.data;
        this.tableData.forEach(element => {
          element.created = this.timestampToTime(element.created)
          if (element.useStatus == 0) {
            element.useStatus = '禁用'
          } else {
            element.useStatus = '启用'
          }
        })
      }
      console.log(res)
    },
    findOrder1() {
      console.log("成功")
      this.getData();
    },
    handleDelete(index, row) {
      var that = this;
      this.$confirm('此操作将删除该项, 是否继续?', '提示', {
        confirmButtonText: '确定',
        cancelButtonText: '取消',
        type: 'warning',
        center: true
      }).then(() => {
        this.deleteUser(row.id);
        that.tableData.some((item) => {
          if (item.id == row.id) {
            that.tableData.splice(index, 1)
            return true
          }
        })
        this.$message({
          type: 'success',
          message: '删除成功!',
          duration: 700
        });
      }).catch(() => {
        this.$message({
          type: 'info',
          message: '已取消删除',
          duration: 700
        });
      })
    },
    handleEditing(index, row) {
      this.changeForm.username = row.username;
      this.changeForm.name = row.name;
      this.changeForm.id = row.id;
      return true
    },
    submit(formName) {
      let stuta;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          /* 这里发起请求 */
          stuta = false;
          this.re_idname(this.changeForm.companyId, this.business_option, 'companyName','changeForm');
          this.re_idname(this.changeForm.areaId, this.area_option, 'areaName','changeForm');
          this.re_idname(this.changeForm.departmentId, this.department_option, 'departmentName','changeForm');
          this.re_idname(this.changeForm.roleId, this.role_option, 'roleName','changeForm');
          this.changeUser();
          //清空表单
          this.$refs[formName].resetFields();
        } else {
          stuta = true;
          console.log('失败');
          //清空表单
          //   this.$refs[formName].resetFields();
        }
      });
      return stuta;
    },
    resetForm(formName) {
      this.$refs[formName].resetFields();
      return false
    },
    add(formName) {
      let stuta;
      this.$refs[formName].validate((valid) => {
        if (valid) {
          /* 这里发起请求 */
          stuta = false;
          this.re_idname(this.newForm.companyId, this.business_option, 'companyName','newForm');
          this.re_idname(this.newForm.areaId, this.area_option, 'areaName','newForm');
          this.re_idname(this.newForm.departmentId, this.department_option, 'departmentName','newForm');
          this.re_idname(this.newForm.roleId, this.role_option, 'roleName','newForm');
          console.log(this.newForm)
          this.addUser();
          //清空表单
          this.$refs[formName].resetFields();
        } else {
          stuta = true;
          console.log('失败');
        }
      });
      return stuta;
    },
    //分页
    getPagesize(val) {
      this.find1.pageSize = val;
      this.getData();
    },
    //子组件传入CurrentPage
    getCurrentPage(val) {
      this.find1.page = val;
      this.getData();
    },
    timestampToTime(time) {
      // 时间戳为10位需*1000，时间戳为13位的话不需乘1000
      var date = new Date(time)
      let y = date.getFullYear()
      let MM = date.getMonth() + 1
      MM = MM < 10 ? ('0' + MM) : MM
      let d = date.getDate()
      d = d < 10 ? ('0' + d) : d
      let h = date.getHours()
      h = h < 10 ? ('0' + h) : h
      let m = date.getMinutes()
      m = m < 10 ? ('0' + m) : m
      let s = date.getSeconds()
      s = s < 10 ? ('0' + s) : s
      return y + '年' + MM + '月' + d + '日'
    },
    async getDepartment() {
      const { data: res } = await this.axios({
        method: 'get',
        url: '/department/all',
        
      });
      if (res.code == 0) {
        res.data.forEach(index => {
          index['departmentName'] = index['name'];
          index['departmentId'] = index['id'];
          delete index['departmentId'];
          delete index['departmentName'];
        })
        this.department_option = res.data
      }
      this.getData();
    },
    async getCompany() {
      const { data: res } = await this.axios({
        method: 'get',
        url: '/company/all',
        
      });
      if (res.code == 0) {
        res.data.forEach(index => {
          index['companyName'] = index['name'];
          index['companyId'] = index['id'];
          delete index['companyId'];
          delete index['companyName'];
        })
        this.business_option = res.data;
      }this.getData();
    },
    async getArea() {
      const { data: res } = await this.axios({
        method: 'get',
        url: '/area/all',
        
      });
      if (res.code == 0) {
        res.data.forEach(index => {
          index['areaName'] = index['name'];
          index['areaId'] = index['id'];
          delete index['areaId'];
          delete index['areaName'];
        })
        this.area_option = res.data
      }
    },
    async getRole() {
      const { data: res } = await this.axios({
        method: 'get',
        url: '/role/find',
        
      });
      if (res.code == 0) {
        res.data.forEach(index => {
          index['roleName'] = index['name'];
          index['roleId'] = index['id'];
          delete index['roleId'];
          delete index['roleName'];
        })
        this.role_option = res.data
      };
    },
    async addUser() {
      const { data: res } = await this.axios({
        method: "POST",
        url: "/admin/add",
        data: {
          username: this.newForm.username,
          name: this.newForm.name,
          phone: this.newForm.phone,
          departmentId: this.newForm.departmentId,
          departmentName: this.newForm.departmentName,
          companyName: this.newForm.companyName,
          companyId: this.newForm.companyId,
          areaName: this.newForm.areaName,
          areaId: this.newForm.areaId,
          roleName: this.newForm.roleName,
          roleId: this.newForm.roleId,
          useStatus: this.newForm.useStatus
        },
        
      })
      this.getData();
    },
    re_idname(Id, arr, key_1,form) {
      arr.forEach((item) => {
        for (let key in item) {
          if (item[key] == Id) {
            this[form][key_1] = item['name'];
          }
        }
      });
    },
    async deleteUser(id) {
      const { data: res } = await this.axios({
        method: "get",
        url: "/admin/delete/" + id,
       
      })
      this.getData();
    },
    async changeUser() {
      const { data: res } = await this.axios({
        method: "POST",
        url: "/admin/update",
        data: {
          username: this.changeForm.username,
          name: this.changeForm.name,
          phone: this.changeForm.phone,
          departmentId: this.changeForm.departmentId,
          departmentName: this.changeForm.departmentName,
          companyName: this.changeForm.companyName,
          companyId: this.changeForm.companyId,
          areaName: this.changeForm.areaName,
          areaId: this.changeForm.areaId,
          roleName: this.changeForm.roleName,
          roleId: this.changeForm.roleId,
          useStatus: this.changeForm.useStatus,
          id: this.changeForm.id
        },
        
      })
      this.getData();
    }
  },
  created() {
    this.getData();
    this.getDepartment();
    this.getCompany();
    this.getArea();
    this.getRole();
  }
}
</script>

<style lang="less" scoped>
#time {
  padding: 20px 10px;
  background-color: white;
  margin-top: 30px;
  border-radius: 5px;
  span {
    margin-right: 10px;
  }
  & > div {
    width: 200px;
    display: inline-block;
  }
  & > #statu_box {
    margin-top: 20px;
    width: 412px;
    & .input_width {
      width: 552.5px;
    }
    & > div {
      width: 200px;
      margin-right: 20px;
    }
  }
  & > #business_box {
    margin-top: 20px;
    width: 262px;
    & > div {
      width: 200px;
      margin-right: 20px;
    }
  }
}
.mar-left {
  margin-left: 20px;
}
#content_box {
  height: 409px;
  background-color: rgb(255, 255, 255);
  margin-top: 30px;
  border-radius: 5px;
  overflow: hidden;
  .input_width {
    width: 552.5px;
  }
}
#content_box:hover {
  overflow: auto;
}
.el-table::before {
  display: none !important;
}
</style>