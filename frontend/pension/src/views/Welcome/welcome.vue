<template xmlns="http://www.w3.org/1999/html">
  <div style="background-color: #EEEEEE">
    <div class="title">
      <div class="title-left">
        <img src="../../img/logo.jpg" style="height: 100%">
      </div>
      <div class="title-menu">
        <el-menu :default-active="1" class="el-menu--horizontal-demo" mode="horizontal" @select="handleSelect"
                 background-color="#242424" text-color="#ffffff" active-text-color="#FFD700"
                 :unique-opened="true"
        >
          <el-menu-item index="1">首页</el-menu-item>
          <el-submenu index="2">
            <template slot="title">企业介绍</template>
            <el-menu-item index="2-1">企业风采</el-menu-item>
            <el-menu-item index="2-2">员工精神</el-menu-item>
            <el-menu-item index="2-3">爱老志愿</el-menu-item>
          </el-submenu>
          <el-submenu index="3">
            <template slot="title">医疗公寓</template>
            <el-menu-item index="3-1">公寓介绍</el-menu-item>
            <el-menu-item index="3-2">环境展示</el-menu-item>
          </el-submenu>
          <el-submenu index="4">
            <template slot="title">主要业务</template>
            <el-menu-item index="4-1">健康城镇</el-menu-item>
            <el-menu-item index="4-2">定制护理</el-menu-item>
            <el-menu-item index="4-3">老年大学</el-menu-item>
          </el-submenu>
          <el-submenu index="5">
            <template slot="title">招商引资</template>
            <el-menu-item index="5-1">咨询招商</el-menu-item>
            <el-menu-item index="5-2">欢迎合作</el-menu-item>
            <el-menu-item index="5-2">养老联盟</el-menu-item>
          </el-submenu>
          <el-submenu index="6">
            <template slot="title">集团新闻</template>
            <el-menu-item index="6-1">最新动态</el-menu-item>
            <el-menu-item index="6-2">优惠活动</el-menu-item>
            <el-menu-item index="6-3">志愿活动</el-menu-item>
          </el-submenu>
          <el-submenu index="7">
            <template slot="title">联系我们</template>
            <el-menu-item index="7-1">联系我们</el-menu-item>
            <el-menu-item index="7-2">诚聘英才</el-menu-item>
          </el-submenu>
        </el-menu>
      </div>
      <div class="title-right">
        <router-link to="/login" style="text-align: center">
          <el-button type="text" style="margin-top: 9%;font-size: 16px">立即登录</el-button>
        </router-link>

      </div>
    </div>


    <div>
      <!--这是图片轮换器-->
      <div id="slider">
        <div class="window" @mouseover="stop" @mouseleave="play">
          <ul class="container" :style="containerStyle">
            <li>
              <img :style="{width:imgWidth+'px'}" :src="sliders[sliders.length - 1].img" alt="">
            </li>
            <li  v-for="(item, index) in sliders" :key="index">
              <img :style="{width:imgWidth+'px'}" :src="item.img" alt="">
            </li>
            <li>
              <img :style="{width:imgWidth+'px'}" :src="sliders[0].img" alt="">
            </li>
          </ul>
          <ul class="direction">
            <li class="left" @click="move(1500, 1, speed)">
              <svg class="icon" width="30px" height="30.00px" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"><path fill="#ffffff" d="M481.233 904c8.189 0 16.379-3.124 22.628-9.372 12.496-12.497 12.496-32.759 0-45.256L166.488 512l337.373-337.373c12.496-12.497 12.496-32.758 0-45.255-12.498-12.497-32.758-12.497-45.256 0l-360 360c-12.496 12.497-12.496 32.758 0 45.255l360 360c6.249 6.249 14.439 9.373 22.628 9.373z"  /></svg>
            </li>
            <li class="right" @click="move(1500, -1, speed)">
              <svg class="icon" width="30px" height="30.00px" viewBox="0 0 1024 1024" version="1.1" xmlns="http://www.w3.org/2000/svg"><path fill="#ffffff" d="M557.179 904c-8.189 0-16.379-3.124-22.628-9.372-12.496-12.497-12.496-32.759 0-45.256L871.924 512 534.551 174.627c-12.496-12.497-12.496-32.758 0-45.255 12.498-12.497 32.758-12.497 45.256 0l360 360c12.496 12.497 12.496 32.758 0 45.255l-360 360c-6.249 6.249-14.439 9.373-22.628 9.373z"  /></svg>
            </li>
          </ul>
          <ul class="dots">
            <li v-for="(dot, i) in sliders" :key="i"
                :class="{dotted: i === (currentIndex-1)}"
                @click = jump(i+1)
            >
            </li>
          </ul>
        </div>
      </div>
      <!--   这是界面   -->
      <div class="i-a">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tbody><tr>
            <td class="i-title">Main Business
              <a href="">主营业态</a></td>
          </tr>
          <tr>
            <td><table width="90%" border="0" cellspacing="0" cellpadding="0" >
              <tbody><tr>
                <td class="cp2"><a href=""><img src="http://www.syjintaiyang.com/images/a1.jpg">
                  <p>老年大学</p></a>
                  <i>
                    <span id="Label7">开设有书法、绘画、声乐、舞蹈、乐器、养生等6大类30多项课程</span></i>

                </td>
                <td class="cp2"><a href=""><img src="http://www.syjintaiyang.com/images/a2.jpg">
                  <p>候鸟旅居</p></a>
                  <i>
                    <span id="Label8">精选三亚、大理、丽江、西双版纳、马来西亚海岛等适宜养生养老的地区</span></i>
                </td>
                <td class="cp2"><a href=""><img src="http://www.syjintaiyang.com/images/a3.jpg">
                  <p>翼护到家</p></a>
                  <i>
                    <span id="Label9">结合智能养老模式并改造升级，保证将五星级护理服务送到客户家里</span></i>
                </td>
                <td class="cp2"><a href=""><img src="http://www.syjintaiyang.com/images/a4.jpg">
                  <p>健康城镇</p></a>
                  <i>
                    <span id="Label10">温泉主题度假区与绿花湖农业观光养老庄园，打造东北亚农业观光养老第一品牌</span></i>
                </td>
              </tr>
              </tbody></table>
            </td>
          </tr>
          </tbody></table>
      </div>
      <div class="i-gy">
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tbody><tr>
            <td class="i-title">Medical Apartment
              <a href="">医疗公寓</a></td>
          </tr>
          <tr>
            <td class="pic">
              <table id="DataList5" cellspacing="0" cellpadding="0" border="0" style="width:100%;border-collapse:collapse;">
                <tbody><tr>
                  <td>
                    <div class="pic1"><a id="DataList5__ctl0_HyperLink1" href="" target="_blank"><img src="http://www.syjintaiyang.com/UpImg/small/2017122115494632540.jpg" border="0" width="235" height="160"></a></div>
                  </td><td>
                  <div class="pic1">  <a id="DataList5__ctl1_HyperLink1" href="" target="_blank"><img src="http://www.syjintaiyang.com/UpImg/small/2017122114471476591.jpg" border="0" width="235" height="160"></a></div>
                </td><td>
                  <div class="pic1">  <a id="DataList5__ctl2_HyperLink1" href="" target="_blank"><img src="http://www.syjintaiyang.com/UpImg/small/2017122114461954439.jpg" border="0" width="235" height="160"></a></div>
                </td><td>
                  <div class="pic1">  <a id="DataList5__ctl3_HyperLink1" href="" target="_blank"><img src="http://www.syjintaiyang.com/UpImg/small/2017111516114734934.jpg" border="0" width="235" height="160"></a></div>
                </td>
                </tr><tr>
                  <td>
                    <div class="pic1"><a id="DataList5__ctl4_HyperLink1" href="" target="_blank"><img src="http://www.syjintaiyang.com/UpImg/small/2017111516105643305.jpg" border="0" width="235" height="160"></a></div>
                  </td><td>
                  <div class="pic1">  <a id="DataList5__ctl5_HyperLink1" href="" target="_blank"><img src="http://www.syjintaiyang.com/UpImg/small/2017111516103815017.jpg" border="0" width="235" height="160"></a></div>
                </td><td>
                  <div class="pic1">  <a id="DataList5__ctl6_HyperLink1" href="" target="_blank"><img src="http://www.syjintaiyang.com/UpImg/small/2017111516095452063.jpg" border="0" width="235" height="160"></a>
                  </div>
                </td><td>
                  <div class="pic1">  <a id="DataList5__ctl7_HyperLink1" href="" target="_blank"><img src="http://www.syjintaiyang.com/UpImg/small/201772015220489039.jpg" border="0" width="235" height="160"></a></div>
                </td>
                </tr>
                </tbody></table>
            </td>
          </tr>
          <tr>
            <td height="80"><div class="most"><a href="">查看更多</a></div></td>
          </tr>
          </tbody></table>
      </div>


      <div class id="bottom">

        <br>大连东软颐养中心<br/>
        <br>联系电话：+86 19818969698<br/>
        <br>地址：大连市金州区图强街321号<br/>
        <br>Copyright © 2022dljjsyl.com service by tirunidea 辽ICP备17021889号-1<br/>
      </div>
    </div>
  </div>


</template>

<script>
export default {
  name: 'slider',
  props: {
    initialSpeed: {
      type: Number,
      default: 30
    },
    initialInterval: {
      type: Number,
      default: 3
    }
  },
  data () {
    return {
      sliders:[

        {img:'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.files.mozhan.com%2Fmozhan%2F20170307%2Fc681db17815806adc306c34d376179cd.jpg%3Fx-oss-process%3Dimage%2Fresize%2Climit_0%2Cm_fill%2Cw_1920%2Ch_600%2Fformat%2Cgif&refer=http%3A%2F%2Fpic.files.mozhan.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672386515&t=1c327e41134b9968a82e730a51f92ce5'},
        {img:'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fpic.xtuan.com%2Fupload%2FcasePictures%2F20170620%2F10523485526_w.jpg&refer=http%3A%2F%2Fpic.xtuan.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672388013&t=4088e7ed212fbc3a9776901b475d6ec3'},
        {img:'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fseopic.699pic.com%2Fphoto_origin%2F40190%2F2433.jpg%21bd800&refer=http%3A%2F%2Fseopic.699pic.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672388162&t=7821bdedd5763a91d3808c2b260e1489'},
        {img:'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fwww.yanqihu.com%2Fwp-content%2Fuploads%2F2016%2F12%2F2016-12-2170.jpg&refer=http%3A%2F%2Fwww.yanqihu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672388674&t=158eda8077fbcb5dd4f01caece341c2f'},
        {img:'https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fbkimg.cdn.bcebos.com%2Fpic%2F6c224f4a20a4462327c19d9f9822720e0df3d7c7&refer=http%3A%2F%2Fbkimg.cdn.bcebos.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=auto?sec=1672387961&t=fd8fec794edb045cdead95e2478aa29d'}
      ],
      imgWidth:1500,
      currentIndex:1,
      distance:-1500,
      transitionEnd: true,
      speed: this.initialSpeed
    }
  },
  computed:{
    containerStyle() {
      return {
        transform:`translate3d(${this.distance}px, 0, 0)`
      }
    },
    interval() {
      return this.initialInterval * 1000
    }
  },
  mounted() {
    this.init()
  },
  methods:{
    handleSelect(id) {
      switch (id){
          // case '8':
          //   this.$router.push('/register')
          //   break;
        case '9':
          this.$router.push('/login')
          break;
      }
    },
    init() {
      this.play()
      window.onblur = function() { this.stop() }.bind(this)
      window.onfocus = function() { this.play() }.bind(this)
    },
    move(offset, direction, speed) {
      console.log(speed)
      if (!this.transitionEnd) return
      this.transitionEnd = false
      direction === -1 ? this.currentIndex += offset/1500 : this.currentIndex -= offset/1500
      if (this.currentIndex > 5) this.currentIndex = 1
      if (this.currentIndex < 1) this.currentIndex = 5

      const destination = this.distance + offset * direction
      this.animate(destination, direction, speed)
    },
    animate(des, direc, speed) {
      if (this.temp) {
        window.clearInterval(this.temp);
        this.temp = null ;
      }
      this.temp = window.setInterval(() => {
        if ((direc === -1 && des < this.distance) || (direc === 1 && des > this.distance)) {
          this.distance += speed * direc
        } else {
          this.transitionEnd = true
          window.clearInterval(this.temp)
          this.distance = des
          if (des < -7500) this.distance = -1500
          if (des > -1500) this.distance = -7500
        }
      }, 20)
    },
    jump(index) {
      const direction = index - this.currentIndex >= 0 ? -1 : 1;
      const offset = Math.abs(index - this.currentIndex) * 1500;
      const jumpSpeed = Math.abs(index - this.currentIndex) === 0 ? this.speed : Math.abs(index - this.currentIndex) * this.speed ;
      this.move(offset, direction, jumpSpeed)
    },
    play() {
      if (this.timer) {
        window.clearInterval(this.timer)
        this.timer = null
      }
      this.timer = window.setInterval(() => {
        this.move(1500, -1, this.speed)
      }, this.interval)
    },
    stop() {
      window.clearInterval(this.timer)
      this.timer = null
    }
  }
}

</script>
<style scoped>
.title{
  height: 60px;
  /*background-color: #409eff;*/
}
.title-left{
  width: 20%;
  height: 100%;
  float: left;
  background-color: #242424;
}
.title-menu{
  width: 74%;
  height: 100%;
  float: left;
}

.title-right{
  width: 6%;
  height: 100%;

  float: right;
  background-color: #242424;
}
.menu-button{
  font-size: 15px;
  text-align: center;

}
.el-menu.el-menu--horizontal {
  border-bottom: solid 1px #e6e6e6;
  /*width: 80%;*/
}
.el-menu--collapse .el-menu .el-submenu, .el-menu--popup{
  min-width: 80px!important;
}
.el-menu-item,el-submenu  {
  width: 100px;
  text-align: center;
  font-weight: bold;

}
li.el-submenu {
  font-weight: bold;
  width: 100px;

}

*{
  box-sizing: border-box;
  margin:0;
  padding:0;
}
ol,ul{
  list-style: none;
}
#slider{
  text-align: center;
}
.window{
  position:relative;
  width:100%;
  height:450px;
  margin:0 auto;
  overflow:hidden;
}
.container{
  display:flex;
  position:absolute;
}
.left, .right{
  position:absolute;
  top:50%;
  transform:translateY(-50%);
  width:50px;
  height:50px;
  background-color:rgba(0,0,0,.3);
  border-radius:50%;
  cursor:pointer;
}
.left{
  left:3%;
  padding-left:12px;
  padding-top:10px;
}
.right{
  right:3%;
  padding-right:12px;
  padding-top:10px;
}
img{
  user-select: none;
}
.dots{
  position:absolute;
  bottom:10px;
  left:50%;
  transform:translateX(-50%);
}
.dots li{
  display:inline-block;
  width:15px;
  height:15px;
  margin:0 3px;
  border:1px solid white;
  border-radius:50%;
  background-color:#333;
  cursor:pointer;
}
.dots .dotted{
  background-color:orange;
}
.home-box {
  width: 100%;
  height: 500px;
  margin-top: 5px;
  margin-bottom: 10px;
  background-color: darkkhaki;
  display: flex;
}
.home-box-content{
  height: 96%;
  width: 24%;
  margin: 10px;
  background-color: beige;
  text-align: center;
  color: #6c6d6d;
}
#bottom{
  width: 100%;
  height: 160px;
  font-size: 14px;
  color: #dfdfdf;
  background-color: #363743;
  text-align: center;
}
.i-a {
  background-color: #fff;
  height: 500px;
  width: 90%;
  padding-top: 20px;
  text-align: center;
  margin: 20px auto 50px;
}

.i-title {
  text-align: center;
  font-size: 18px;
  padding-top: 10px;
  color: #888;
}
.cp2 {
  width: 25%;
  text-align: center;
}

td {
  display: table-cell;
  vertical-align: inherit;
}
.i-title a {
  display: block;
  width: 135px;
  height: 40px;
  background-color: #f5ec87;
  margin: 0 auto;
  line-height: 40px;
  font-weight: bold;
}
a {
  color: #b58927;
  text-decoration: none;
}
.tab0{
  width: 1000px;
}
.cp2 p {
  width: 80%;
  text-align: center;
  margin: 0 auto;
  padding: 0;
  font-weight: bold;
  line-height: 50px;
  border-bottom: 1px solid #ccc;
  font-size: 20px;
}
.cp2 i {
  display: block;
  width: 80%;
  height: 100px;
  overflow: hidden;
  margin: 0 auto;
  text-align: center;
  font-size: 16px;
  font-style: normal;
  padding-top: 10px;
  color: #555;
}
table {
  display: table;
  border-collapse: separate;
  box-sizing: border-box;
  text-indent: initial;
  border-spacing: 2px;
  border-color: gray;
  margin: auto;
}
.i-gy {
  background-color: #fffbd2;
  height: 570px;
  width: 100%;
  padding-top: 20px;
  text-align: center;
  margin: 20px auto 50px;
}
.pic img {
  width: 235px;
  margin: 30px 5px 0 5px;
}
.pic1 img {
  cursor: pointer;
  transition: all 0.6s;
}
.most {
  display: block;
  width: 150px;
  height: 30px;
  margin: 0 auto;
  font-size: 14px;
  border: 1px solid #666;
  background-color: #fff;
  text-align: center;
  line-height: 30px;
}
</style>
