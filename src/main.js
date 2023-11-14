import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import router from "./router/router.js"
import axios from 'axios'
import '@/style/index.scss'
import * as ElementPlusIconsVue from '@element-plus/icons-vue'

// 引入echarts
import echarts from './echarts.js'

// 定义全局axios对象
const commonRequest = axios.create({
    baseURL: "/api",
    timeout: 100000,
});

// commonRequest.interceptors.request.use(config => {
//     // 在请求头中添加token
//     config.headers.Authorization = window.localStorage.getItem("Authorization");
//     return config
// })

const app = createApp(App)
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    app.component(key, component)
  }
app.use(ElementPlus)
app.use(router)

// 挂载到vue实例中
// Vue.prototype.$echarts = echarts;//vue2的挂载方式
app.config.globalProperties.$echarts = echarts;//vue3的挂载方式
app.config.globalProperties.$commonRequest = commonRequest
app.mount('#app')

// export default { commonRequest }