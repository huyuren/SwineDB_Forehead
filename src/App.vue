<template>
  <head>
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Noto+Sans+SC:100,300,400,500,700,900">

  </head>
  <header>
    <div class="view" id="view">
      <div class="website-nav">
        <nav>
          <p class="logobg"> <img src="./assets/img/logo.png" alt=""></p>
          <!-- <p class="logo"></p> -->
          <p class="title">Pig Gut Microbiome Database</p>
          <el-menu router :default-active="activeIndex" class="el-menu-demo" mode="horizontal" background-color="#1ed6eeda"
            active-text-color="#f88519" @select="handleSelect" popper-effect="light">
            <el-menu-item index="/website">Home</el-menu-item>
            <el-menu-item index="/Browse">Browse</el-menu-item>
            <el-menu-item index="/Search">Search</el-menu-item>
            <el-menu-item index="/Data">Data</el-menu-item>
            <!-- <el-sub-menu index="2">
              <template #title>Data</template>
              <el-menu-item index="/menu/data/Swine?class=Swine">swine</el-menu-item>
              <el-menu-item index="/menu/data/Microbe?class=Microbe">Microbe</el-menu-item>
              <el-menu-item index="/menu/data/microbial?class=microbial">microbial</el-menu-item>
              <el-menu-item index="/menu/data/samples?class=samples">sample</el-menu-item>
            </el-sub-menu>
            <el-menu-item index="/datasample">datasample</el-menu-item>
            <el-menu-item index="/team">Team</el-menu-item>
            <el-menu-item index="/we">Contacts</el-menu-item>
            <el-sub-menu index="2-4">
              <template #title>Graph</template>
              <el-menu-item index="/triDBar">triDBar</el-menu-item>
              <el-menu-item index="/pie">pie</el-menu-item>
              <el-menu-item index="/radial">radial</el-menu-item>
            </el-sub-menu> -->
            <el-menu-item index="/Tools">Tools</el-menu-item>
            <el-menu-item index="/Resource">Resource</el-menu-item>
            <el-menu-item index="/help">Help</el-menu-item>
          </el-menu>
        </nav>

      </div>
    </div>
  </header>
  <body>
    <div id="v-cotent" v-bind:style = "{minHeight:Height+'px'}"></div>
   <router-view></router-view>
  </body>
  <footer></footer>
 
</template>

<script>
import Menu from './components/Menu.vue'
import Data from './components/Data.vue'
import Browse from './views/Browse.vue'
import Search from './views/Search.vue'
import direction from './views/direction.vue'
import Help from './views/help.vue'
import Tools from './views/Tools.vue'
import Resource from './views/Resource.vue'
import Team from './components/Menu.vue'
import We from './views/We.vue'
import Pie from './components/Pie.vue'
import TriDBar from './components/TriDBar.vue'
import Radial from './components/Radial.vue'
import { Document, Menu as IconMenu, Setting, Avatar, Folder, ArrowDown, } from '@element-plus/icons-vue';
import { onMounted, ref, onUnmounted } from "vue";
import { useRouter, useRoute } from 'vue-router'
import axios from 'axios'
// import commonRequest from "@/main.js";
export default {
  name: 'App',
  components: {
    Menu, Data, direction, Team, We, Pie, TriDBar, Radial, Document, Browse, Search,
    Setting,
    IconMenu,
    Avatar,
    Folder,
    Tools, 
    Resource,
    Help,
  },
  setup() {
    const route = useRoute();
    // console.log(route.path);
    const showButton = ref(false);
    const pageName = ref('website'); // 获取路径中的最后一个部分
    // console.log(pageName.value);
    // const pageName= ref('website') 
    const count = ref(parseInt(localStorage.getItem(`${pageName}-count`)) || 0); // 页面访问量
    // console.log(pageName); // 打印页面名称,后续功能使用。
    const activeIndex = ref('/website') //页面加载时默认激活菜单的 index
    const commonRequest = getCurrentInstance().appContext.config.globalProperties.$commonRequest;

    const handleScroll = () => {
      showButton.value = window.pageYOffset > 100;
    };

    const scrollToTop = () => {
      window.scrollTo({
        top: 0,
        behavior: 'smooth',
      });
    };

    // 定义一个获取页面访问量的方法
    const getCount = () => {
      // axios.get(`/api/counter/${pageName}`, {
      //   headers: {
      //     'secret': 'secret',
      //   },
      // })
      commonRequest({
        method: "get",
        url: `/counter/${pageName.value}`,
        headers: {
          'secret': 'secret',
        },
      })
        .then(res => {
          if (res.status === 404) {
            // 如果页面不存在，则说明是第一次访问该页面，需要重新创建页面并更新页面访问量
            axios.post(`/api/counter/${pageName.value}`, {
              headers: {
                'secret': 'secret',
              },
            }); // 调用后端的创建计数器接口
            count.value = 1; // 将页面访问量赋值为1
            localStorage.setItem(`${pageName}-count`, count.value); // 存储页面访问量
          } else {
            count.value = res.data.count; // 更新页面访问量
            // updateCount();
            localStorage.setItem(`${pageName}-count`, count.value); // 存储页面访问量
          }
        }).catch(error => {
          // console.log(error); // 输出错误信息
        });;

    };
    // 定义一个更新页面访问量的方法
    const updateCount = () => {
      // const url = `/counter/${pageName}`; // 接口的地址
      axios.post(`/api/counter/${pageName.value}`, {
        headers: {
          'secret': 'secret',
        },
      }).catch(error => {
        // console.log(error); // 输出错误信息
      });

      // const res =  commonRequest.post(url,config); // 调用后端的更新计数器接口
    };


//     mounted(() => {
//     //动态设置内容高度 让footer始终居底   header+footer的高度是100
//     this.Height = document.documentElement.clientHeight - 100;  
// 　　//监听浏览器窗口变化　
//     window.onresize = ()=> {this.Height = document.documentElement.clientHeight -100}
//     });


    onBeforeMount(() => {
      updateCount();
      // pageName = route.path.split('/').pop();
      // getCount();
    });
    onMounted(() => {
      window.addEventListener('scroll', handleScroll);
      getCount();
      setInterval(getCount, 10000);
      updateCount();
    });

    onUnmounted(() => {
      window.removeEventListener('scroll', handleScroll);
    });

    return {
      activeIndex,

      showButton,
      pageName,
      count,
      updateCount,
      scrollToTop,
    };
  },
}
</script>

<style scoped>
/* header {
  background-color: #333;
  color: #fff;
  padding: 20px;
  text-align: center;
} */



body {
  font-family: "Noto Sans SC";
}

header h1 {
  margin: 0;
  font-size: 36px;
  font-weight: normal;
}

/* .website-nav{
  margin-bottom: 1px;
} */
nav {
  left: 0px;
  top: 1px;
  width: 1920px;
  height: 86px;
  opacity: 1;
  box-shadow: 0px 2px 4px  rgba(0, 0, 0, 0.25);
  /* background-color: #f88519; */
  background-color: rgba(0, 126, 55, 1);
  color: rgba(245, 245, 245, 1);
  padding: 10px;
  text-align: center;
  z-index: -1;
  flex: 1;
}


.view{
  flex: 1;
}
.logo{
  /* position: absolute; */
  /* left: 35px;
  top: 22px;
  width: 50px;
  height: 37px; */
  opacity: 1;
  font-size: 24px;
  font-weight: 400;
  letter-spacing: 0px;
  line-height: 34.75px;
  color: rgba(255, 255, 255, 1);
  text-align: left;
  vertical-align: top;

}

.logobg img{
  height: 86px;
  width: 120px;
  margin-left: 10px;
}

.logobg{
  position: absolute;
  left: 0px;
  top: 0px;
  width: 129px;
  height: 86px;
  
  opacity: 1;
  background: rgba(204, 204, 204, 0.5);
}
.logobg img{
  height: 86px;
  width: 120px;
  margin-left: 10px;
}


.title {
  position: absolute;
  left: 129px;
  top: 18px;
  width: 498px;
  height: 53px;
  opacity: 1;

  font-size: 36px;
  font-weight: 400;
  letter-spacing: 0px;
  line-height: 52.13px;
  color: rgba(245, 245, 245, 1);
  text-align: center;
  vertical-align: top;
  z-index: 0;
}
.el-menu-demo {
  position: absolute;
  /* 垂直居中 */
  top: 0px;
  left: 785px;
  width: 1100px;
  height: 86px;
  opacity: 1;
  border-bottom: 0;
  /* border-radius: 5px; */
  background: rgba(0, 126, 55, 1);
  z-index: 10;
}

.el-submenu__title:hover{
  background-color: rgb(0, 0, 0);
}

.el-menu-item{
  width: 142px;
  font-size: 36px;
  font-weight: 400;
  letter-spacing: 0px;
  line-height: 52.13px;
  color: rgba(255, 255, 255, 1);
  text-align: center;
  vertical-align: top;
}

.el-menu-item:hover{
  outline: 0;
  background-color: rgb(0, 0, 0);
}

.el-menu-item:focus{
  color: rgb(0, 0, 0);
}

.el-menu-item:is-active{
  color: rgb(0, 0, 0);
}

nav li {
  display: inline-block;
  margin: 0 10px;
}

nav a {
  color: #fff;
  text-decoration: none;
  padding: 10px;
  border-radius: 5px;
  transition: background-color 0.3s ease;
}

nav a:hover {
  background-color: #999;
}

.scroll-top-wrapper {
  position: fixed;
  bottom: 20px;
  right: 20px;
  cursor: pointer;
  opacity: 0.7;
  transition: opacity 0.3s ease-in-out;
  z-index: 9;
}

.scroll-top {
  padding: 10px 20px;
  background-color: #333;
  color: #fff;
  border: none;
  border-radius: 4px;
  opacity: 0.4;
}

.scroll-top:hover {
  opacity: 1;
}

.arrow {
  display: inline-block;
  margin-right: 5px;
  transform: rotate(90deg);
}

footer{
  position: relative;
  top: height + 100px;

}
</style> 