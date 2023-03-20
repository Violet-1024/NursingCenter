<template>
  <el-row class="main-menu" :style="{ height: windowHeight }">
    <el-col>
      <el-menu
          :default-active="menuSelectIndex"
          class="el-menu-vertical-demo"
          @select="handleSelect"
          background-color="#363743"
          text-color="#fff"
          active-text-color="#ffd04b"
          unique-opened
          router
      >
        <el-submenu index="1">
          <template slot="title">
            <i class="el-icon-reading"></i>
            <span>客户管理</span>
          </template>

          <el-menu-item index="1-1" :route="{ path: '/main/customList' }">客户列表</el-menu-item>
        </el-submenu>
        <el-submenu index="2">
          <template slot="title">
            <i class="el-icon-s-flag"></i>
            <span>床位管理</span>
          </template>
          <el-menu-item index="2-1" :route="{ path: '/main/bedList' }">床位列表</el-menu-item>
          <el-menu-item index="2-2" :route="{ path: '/main/bedRecord' }">床位记录</el-menu-item>
        </el-submenu>
        <el-submenu index="3">
          <template slot="title">
            <i class="el-icon-s-custom"></i>
            <span>护理管理</span>
          </template>

          <el-menu-item index="3-1" :route="{ path: '/main/nursingList' }">护理列表</el-menu-item>
          
        </el-submenu>
        <el-submenu index="4">
          <template slot="title">
            <i class="el-icon-pie-chart"></i>
            <span>健康管理</span>
          </template>

          <el-menu-item index="4-1" :route="{path: '/main/healthDatas' }">健康数据监测</el-menu-item>
          <el-menu-item index="4-2" :route="{path: '/main/healthRank'}">健康评级</el-menu-item>
        </el-submenu>

        <el-submenu index="5">
          <template slot="title">
            <i class="el-icon-chat-round"></i>
            <span>膳食管理</span>
          </template>

          <el-menu-item index="5-1" :route="{path:'/main/foodList'}">膳食列表</el-menu-item>
<!--          <el-menu-item index="5-2" :route="{path:'/main/bespokeFood'}">制定膳食</el-menu-item>-->
        </el-submenu>

        <el-submenu index="6">
          <template slot="title">
            <i class="el-icon-monitor"></i>
            <span>异常状态管理</span>
          </template>
          <el-menu-item index="6-1" :route="{path:'/main/applyManagement'}">申请管理</el-menu-item>
          <el-menu-item index="6-2" :route="{path:'/main/approvalManagement'}">审批管理</el-menu-item>
        </el-submenu>

        <el-submenu index="7">
          <template slot="title">
            <i class="el-icon-s-order"></i>
            <span>员工管理</span>
          </template>

          <el-menu-item index="7-1" :route="{path:'/main/employeeList'}">员工列表</el-menu-item>
          <el-menu-item index="7-2" :route="{path:'/main/employeeEnrolling'}">员工入职</el-menu-item>
<!--          <el-menu-item index="7-3" :route="{path:'/main/employeeEdit'}">信息修改</el-menu-item>-->
        </el-submenu>

        <el-submenu index="8">
          <template slot="title">
            <i class="el-icon-s-release"></i>
            <span>订单管理</span>
          </template>

          <el-menu-item index="8-1" :route="{path:'/main/orderFoodList'}">订单列表</el-menu-item>
          <el-menu-item index="8-2" :route="{path:'/main/orderNursingList'}">护理记录</el-menu-item>
        </el-submenu>

        <el-submenu index="9">
          <template slot="title">
            <i class="el-icon-s-finance"></i>
            <span>申请管理</span>
          </template>

          <el-menu-item index="9-1" :route="{path:'/main/outingApply'}">外出申请审批</el-menu-item>
          <el-menu-item index="9-2" :route="{path:'/main/dailyApply'}">日常申请服务</el-menu-item>
        </el-submenu>

        <el-submenu index="10">
          <template slot="title">
            <i class="el-icon-chat-dot-square"></i>
            <span>评价管理</span>
          </template>

          <el-menu-item index="10-1" :route="{path:'/main/evaluationList'}">评价列表</el-menu-item>
          <el-menu-item index="10-2" :route="{path:'/main/evaluationConfig'}">评价配置</el-menu-item>
        </el-submenu>

        <el-submenu index="11">
          <template slot="title">
            <i class="el-icon-bell"></i>
            <span>预案管理</span>
          </template>

          <el-menu-item index="11-1" :route="{path:'/main/reservePlanList'}">预案列表</el-menu-item>
<!--          <el-menu-item index="11-2" :route="{path:'/main/reservePlanCreate'}">新增预案</el-menu-item>-->
<!--          <el-menu-item index="11-3" :route="{path:'/main/reservePlanType'}">预案类型</el-menu-item>-->
<!--          <el-menu-item index="11-4" :route="{path:'/main/accidentType'}">事故类型</el-menu-item>-->
        </el-submenu>

      </el-menu>
    </el-col>
  </el-row>
</template>

<script>
export default {
  data() {
    return {
      windowHeight: "",
      menuSelectIndex: sessionStorage.getItem("index")?sessionStorage.getItem("index"):"1-1",
    };
  },
  created() {
    this.windowHeight = window.innerHeight - 63 + "px";
    window.onresize = function () {
      this.windowHeight = window.innerHeight - 63 + "px";
    };
  },

  methods: {
    handleSelect(index, indexPath) {
      console.log(index); //被点击的菜单的索引
      // console.log(indexPath)
      this.menuSelectIndex = index;
      sessionStorage.setItem("index",index)
    },
  },
};
</script>

<style lang="less" scoped>
.main-menu {
  background-color: #363743;
  overflow: auto;
  position: fixed;
}
.el-row {
  width: 250px;
}
.el-menu {
  border-right-width: 0px !important;
}
</style>