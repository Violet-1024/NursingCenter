import Axios from "axios";
import router from './../router';
import { Loading } from "element-ui";

// 创建一个Axios的实例：
let http = Axios.create({
  timeout: 10000,
  baseURL: "http://121.5.35.178:8081/admin"
})

export default http;

let loadingInstance; // 加载动画的对象；
// 请求前，拦截请求

// http.interceptors.request.use(requestConfig => {
//   //console.log('请求前的config:', requestConfig)  // 请求对象{}
//   let token = sessionStorage.getItem('token');
//
//   //console.log("token",token)
//   // 判断有没有token，如果有就添加到请求头中,
//   // authenticate属性，要和后端对接一下，是否是这个属性。也可能是其它
//   token && (requestConfig.headers['token'] = token);
//   //console.log('请求前的config:', requestConfig)
//   //显示 加载数据的动画
//   loadingInstance = Loading.service({
//     fullscreen: true,
//     text: '正在为您努力加载中...',
//     background: 'rgba(0,0,0,0.5)'
//   })
//
//   return requestConfig;
//
// }, error => Promise.reject(error));
//
// // 响应后，拦截响应；
// http.interceptors.response.use(res => {
//   //console.log("响应后:", res.data.data)  //看一下如何正确的获取token值；
//   // 判断toKen如果获取，决定下一行代码的写法：
//   // 还有可能是： res.data.data.token;
//   //   res.data.data && sessionStorage.setItem('token', res.data.data)
//   //console.log(sessionStorage.getItem('token'))
//   // 成功响应后，关闭动画
//   loadingInstance.close();
//   return res;
// }, error => {
//   // console.log("响应后err:", error)
//   // 可以根据响应的状态码，判断应该如何处理token;
//   // 直接输出时，只能看到error的信息，但error也是一个对象。
//   let _resp = error.response;
//   // console.log(_resp)
//   // 主要是应对token过期，或者是无效的token.
//   switch (_resp.status) {
//     case 401:
//       // case 500:
//       sessionStorage.removeItem('token');
//       return router.push('/login');
//   }
//   return Promise.reject(error.response.data)
// });