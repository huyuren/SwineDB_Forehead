<template>
  <div id="building">
    <h2 align="center" color: aqua>欢迎使用生猪肠道微生物大数据库</h2>
    <div align="center">
      <span><a href="http://www.hzau.edu.cn/index.htm" target="_blank" class="a">@华中农业大学</a> </span>
      <span><a href="http://my.hzau.edu.cn/info/1168/3835.htm" target="_blank" class="a"> | 生猪精准饲养团队</a></span>
    </div>
    <div class="layout">
      <el-tabs type="border-card">
        <el-tab-pane label="用户登录">
          <el-form label-position="right" label-width="70px" style="max-width: 460px" class="loginForm">
            <el-form-item label="用户名：">
              <el-input v-model="commonUser.name" />
            </el-form-item>
            <el-form-item label="密码：">
              <el-input type="password" v-model="commonUser.password" />
            </el-form-item>
            <el-button v-if="commonUserLoginAgree" type="primary" class="loginBtn" @click="commonUserLogin">
              登录
            </el-button>
          </el-form>
        </el-tab-pane>
        <el-tab-pane label="管理员登录">
          <el-form label-position="right" label-width="70px" style="max-width: 460px" class="loginForm">
            <el-form-item label="用户名：">
              <el-input v-model="administrator.name" />
            </el-form-item>
            <el-form-item label="密码：">
              <el-input type="password" v-model="administrator.password" />
            </el-form-item>
            <el-button v-if="administratorLoginAgree" type="primary" class="loginBtn" @click="administratorLogin">
              登录
            </el-button>
          </el-form>
        </el-tab-pane>

        <el-tab-pane label="注册">
          <el-form label-position="right" label-width="70px" style="max-width: 460px" class="loginForm">
            <el-form-item label="用户名：">
              <el-input v-model="registerUser.name" />
            </el-form-item>
            <el-form-item label="密码：">
              <el-input type="password" v-model="registerUser.password" />
            </el-form-item>
            <el-form-item label="邮箱：">
              <el-input v-model="registerUser.mail" />
            </el-form-item>
            <el-button v-if="registerAgree" type="primary" class="loginBtn" @click="register">
              注册
            </el-button>
          </el-form>
        </el-tab-pane>

      </el-tabs>
    </div>
  </div>
</template>
<script>
import { computed, getCurrentInstance, reactive } from "vue";
import { ElMessageBox } from 'element-plus'
import axios from 'axios'


export default {
  name: 'Login',

  setup(props, context) {


    const commonUser = reactive({
      name: "",
      password: "",
    });
    const administrator = reactive({
      name: "",
      password: "",
    });

    const registerUser = reactive({
      name: "",
      password: "",
      mail: "",
    });

    const commonUserLoginAgree = computed(() => {
      return commonUser.name !== "" && commonUser.password !== ""
    });

    const administratorLoginAgree = computed(() => {
      return administrator.name !== "" && administrator.password !== ""

    });

    const registerAgree = computed(() => {
      return registerUser.name !== "" && registerUser.password !== ""
    });

    // 仅可在setup()直接调动getCurrentInstance()函数
    const appConfig = getCurrentInstance().appContext.config;

    const commonRequest = getCurrentInstance().appContext.config.globalProperties.$commonRequest;

    function commonUserLogin() {
      commonRequest({
        method: "post",
        url: '/login/user',
        data: {
          name: commonUser.name,
          password: commonUser.password,
        }
      }).then(response => {
        // console.log(response.data.data.token);
        context.emit("getLoginState", true)
        // 获取登录令牌
        const token = response.data.data.token;
        // 浏览器本地化存储
        window.localStorage.setItem("Authorization", token);
        window.localStorage.setItem("CommonUserName", commonUser.name);
        // 更新全局axios对象 携带token
        const commonRequest = axios.create({
          baseURL: "/api",
          timeout: 100000
        });
        commonRequest.interceptors.request.use(
          config => {
            // 在请求头中添加token
            config.headers.Authorization = token
            return config
          })
        appConfig.globalProperties.$commonRequest = commonRequest
        location.reload()
      }).catch(error => {
        console.log(error)
        ElMessageBox.alert("登录失败");
        context.emit("getLoginState", false)
      });
    }

    function administratorLogin() {
      commonRequest({
        method: "post",
        url: '/login/administrator',
        data: {
          name: administrator.name,
          password: administrator.password,
        }
      }).then(response => {

        context.emit("getLoginState", true)

        ElMessageBox.alert("登录成功,请刷新当前界面进入后台管理系统");
        const token = response.data.data.token;

        window.localStorage.setItem("Authorization", token);
        window.localStorage.setItem("AdministratorUserName", administrator.name);

        const commonRequest = axios.create({
          baseURL: "/api",
          timeout: 100000
        })
        commonRequest.interceptors.request.use(
          config => {
            // 在请求头中添加token
            config.headers.Authorization = token
            return config
          })
        appConfig.globalProperties.$commonRequest = commonRequest
        location.reload()
      }).catch(error => {
        console.error("登录失败", error);
        ElMessageBox.alert("登录失败，请检查用户名和密码是否正确");
        context.emit("getLoginState", false)
      });
    }

    function register() {
      axios.post("http://localhost:8080/register/user", {
        name: registerUser.name,
        password: registerUser.password,
        mail: registerUser.mail,
      }).then(response => {
        console.log("请登录")
      }).catch(function (error) {
        console.log(error)
      });
    }

    return {
      commonUser,
      administrator,
      registerUser,
      commonUserLoginAgree,
      administratorLoginAgree,
      registerAgree,
      commonUserLogin,
      administratorLogin,
      register,
    };
  }
}
</script>

<style scoped>
a {
  text-decoration: none;
}

.layout {
  position: absolute;
  left: calc(50% - 200px);
  top: 20%;
  width: 400px;
  opacity: 0.70;
}

.loginBtn {
  width: 100px;
}

.loginForm {
  text-align: center;
}

#building {
  background-image: url("../src/assets/img/pig1.jpg");
  width: 100%;
  height: 100%;
  position: fixed;
  background-size: cover;
  background-position: center;
}

#app {
  font-family: "Avenir", Arial, Helvetica, sans-serif;
  text-align: center;

}
</style>
