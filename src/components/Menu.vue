<template>
  <div class="home-container">
    <el-container>
      <!-- <el-header>
   
      </el-header> -->

      <!-- <el-menu router :default-active="activeIndex" class="el-menu-demo" mode="horizontal" @select="handleSelect"> -->
      <!-- <el-menu-item index="/menu/home">
          <el-icon>
            <IconMenu />
          </el-icon>首页</el-menu-item> -->
      <!-- <el-menu router :default-active="activeIndex"  class="el-menu-demo" @select="handleSelect">
        <el-sub-menu index="2">
          <template #title>
            <el-icon>
              <Document />
            </el-icon>数据</template>
          <el-menu-item index="/menu/data/Swine?class=Swine">
            <el-icon>
              <Folder />
            </el-icon>
            <span>swine</span></el-menu-item>
          <el-menu-item index="/menu/data/Microbe?class=Microbe">
            <el-icon>
              <Folder />
            </el-icon>
            <span>Microbe</span>
          </el-menu-item>
          <el-menu-item index="/menu/data/Metabolism?class=Metabolism">
            <el-icon>
              <Folder />
            </el-icon>
            <span>Metabolism</span>
          </el-menu-item>
          <el-menu-item index="/menu/data/microbial?class=microbial">
            <el-icon>
              <Folder />
            </el-icon>
            <span>Microbial</span>
          </el-menu-item>
          <el-menu-item index="/menu/data/Feed?class=Feed">
            <el-icon>
              <Folder />
            </el-icon>
            <span>Feed</span>
          </el-menu-item>
          <el-menu-item index="/menu/data/samples?class=samples">
              <template #title>
                <el-icon>
                  <Folder />
                </el-icon>
                <span >Samples</span>
              </template>
            </el-menu-item>
          <el-sub-menu index="4">
            <template #title>
              <el-icon>
                <Document />
              </el-icon>Graph</template>
            <el-menu-item index="/menu/triDBar">
              <el-icon>
                <Folder />
              </el-icon>
              <span>3D Bar Graph</span>
            </el-menu-item>
            <el-menu-item index="/menu/pie">
              <el-icon>
                <Folder />
              </el-icon>
              <span>Line Chart</span>
            </el-menu-item>
            <el-menu-item index="/menu/radial">
              <el-icon>
                <Folder />
              </el-icon>
              <span>Histogram</span>
            </el-menu-item>
          </el-sub-menu>
        </el-sub-menu>
      </el-menu> -->

      <el-main>
        <router-view :key="$route.fullPath"></router-view>
      </el-main>

    </el-container>
  </div>
</template>

<script>
import {
  Document,
  Menu as IconMenu,
  Setting,
  Avatar,
  Folder

} from '@element-plus/icons-vue';

import { ElMessage, ElMessageBox } from "element-plus";
import axios from "axios";
import { getCurrentInstance, ref } from "vue";
import { useRouter } from 'vue-router'

export default {
  name: "Menu",
  components: {
    Document,
    Setting,
    IconMenu,
    Avatar,
    Folder
  },
  setup() {
    const appConfig = getCurrentInstance().appContext.config;
    const rowsPerPage = ref()

    // 登出
    function logout() {
      ElMessageBox.confirm(
        '是否要退出后台管理系统',
        {
          confirmButtonText: '确定退出',
          cancelButtonText: '取消',
          type: 'warning',
        }
      ).then(() => {
        ElMessage({
          type: 'success',
          message: '成功退出',
        })
        window.localStorage.removeItem("Authorization")
        const commonRequest = axios.create({
          baseURL: "http://localhost:5173/api",
          timeout: 100000
        });
        appConfig.globalProperties.commonRequest = commonRequest
        // 刷新界面
        location.reload()
      }).catch(() => {
        ElMessage({
          type: 'info',
          message: '取消',
        })
      })
    }

    return {
      logout,
      rowsPerPage
    }
  }
}
</script>

<style scoped lang="less">
.home-container {
  height: 100%;


}

.el-header {
  background-color: black;
  display: flex;
  justify-content: center;
  padding-left: 0px;
  align-items: center;
  color: black;
  font-size: 40px;
  height: 85px;

  >div {
    display: flex;
    align-items: center;

    >span {
      margin-left: 20px;
      color: white;
      font-weight: bold;
    }
  }
}

.el-aside {
  background-color: #001529;
  width: 200px;
  height: 100vh;
}

.el-main {
  background-color: whitesmoke;
  background-image: url("../assets/img/8.jpg");


}

.el-footer {

  background-image: url("../assets/img/8.jpg");
}

.el-button {
  position: absolute;
  left: 0;
  top: 0;
}
</style>