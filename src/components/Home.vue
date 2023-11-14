<template>
  <!-- <el-header>
    <el-button class="el-button-add" type="primary" @click="drawer=true">添加公告</el-button>
    <el-button class="el-button-delete"  type="primary" @click="deleteSelectedNotices">删除公告</el-button>
  </el-header> -->
  <!-- <el-timeline>
    <template v-for="(value,index) in notices" :key="index">
      <el-timeline-item :timestamp="value.endTime" placement="top">
        <el-card>
          <h4>{{value.title}}</h4>
          <p>{{value.content}}</p>
          <el-checkbox v-model="selectedNotices" :label="value.id">序号：{{value.id}}</el-checkbox> -->
          <!-- <span>序号：{{value.id}}</span> -->
        <!-- </el-card>
      </el-timeline-item>
    </template>
  </el-timeline> -->
  <!-- <el-drawer
      size="50%"
      v-model="drawer"
      title="添加公告"
      :direction="direction"
      :show-close="false"
      @close="handleClose"
  >
    <el-form :model="notice" label-width="80px">
      <el-form-item label="标题">
        <el-input v-model="notice.title"/>
      </el-form-item>
      <el-form-item label="内容">
        <el-input v-model="notice.content"/>
      </el-form-item>
      <div class="block">
        <el-date-picker
            class="el-date-picker"
            v-model="dataTime"
            type="datetimerange"
            range-separator="To"
            start-placeholder="开始时间"
            end-placeholder="结束时间"
        />
      </div>
      <el-button  type="primary" @click="addNotice" class="middle el-button-data-add">
        添加数据
      </el-button>
    </el-form>
  </el-drawer> -->
  <swiper :modules="modules" :pagination="{clickable:true}">
    <swiper-slide style="display:flex;justify-content: center;align-items: center;">
      <img src="../assets/img/11.jpg" alt="">
    </swiper-slide>
    <!--<swiper-slide style="display:flex;justify-content: center;align-items: center;">
      <img src="../assets/img/12.jpg" alt="">
    </swiper-slide >-->
    <swiper-slide style="display:flex;justify-content: center;align-items: center;">

      <img src="../assets/img/9.jpg" alt="">
    </swiper-slide>
   
  </swiper>
  <h3>生猪肠道微生物数据库查询平台： </h3>
  <div>
    <div style="margin: 1px 0" />
  </div>
  <p>用户您好，您可以在数据中查询到生猪（Swine），微生物（Mircobe）等信息，感谢您的使用！
  </p>

 
</template>

<script>
import {getCurrentInstance, onMounted, reactive, ref} from "vue";
import { Swiper, SwiperSlide } from 'swiper/vue';
import { Pagination } from 'swiper';
import 'swiper/css';
import 'swiper/css/pagination';

export default {
  name: "Home",
  components:{
     Swiper,
     SwiperSlide,
  
  
  },
  data(){
    return{
      modules:[Pagination]

  }
  
  },


  setup() {
    const commonRequest = getCurrentInstance().appContext.config.globalProperties.$commonRequest
    const dataTime=ref([])
    const notice=reactive({
      id: '',
      startTime: '',
      endTime: '',
      title: '',
      content: '',
    })
    const notices = ref([]);
    const drawer = ref();
    const direction = ref('rtl');
    const selectedNotices= ref([]);

    onMounted(()=>{
      getNotice()
    })

    function getNotice() {
      commonRequest({
        method: "get",
        url: `/notice/get`,
        headers:{
          'secret':'secret',
        },
      }).then(res => {
        notices.value=res.data.data.notices
      }).catch(err => {
        console.log(err)
      });
    }

    function addNotice() {
      notice.startTime=dataTime.value[0]
      notice.endTime=dataTime.value[1]

      commonRequest({
        method: "post",
        url: `/notice/add`,
        data:{
          // reactive 自动分散字段
          // notice:notice
          title: notice.title,
          content: notice.content,
          startTime: notice.startTime,
          endTime: notice.endTime,
        }
      }).then(res => {
      }).catch(err => {
        console.log(err)
      });

      clearNotice()
      getNotice()
    }

    function handleClose() {
      location.reload()
    }

    function clearNotice(){
      notice.title = ''
      notice.content = ''
      notice.startTime=''
      notice.endTime=''
    }

    function deleteSelectedNotices() {
      const selectedIds = this.selectedNotices;
      console.log('selected ids:', selectedIds);
      // 根据选中的id删除对应的通知
      for (let i = 0; i < selectedIds.length; i++) {
        const id = selectedIds[i];
        this.deleteNotice(id);
      }
      // 清空选中状态
      this.selectedNotices = [];
    } 
    
    function deleteNotice(id) {
      commonRequest({
        method: "delete",
        url: `/notice/delete/${id}`
      }).then(() => {
        getNotice();
        console.log(`notice with id ${id} has been deleted`);
        // 刷新页面或重新获取通知列表
      }).catch(error => {
        console.error(`failed to delete notice with id ${id}: ${error}`);
      });
      // 根据id删除通知
      // console.log('delete notice with id', id);
    }


    return{
      selectedNotices,
      notice,
      notices,
      drawer,
      dataTime,
      direction,
      addNotice,
      handleClose,
      deleteNotice,
      deleteSelectedNotices,
      
    }
  },

}
</script>

<style scoped>
  .el-button-add {
    position: relative;
    left: 50%;
  }
  .el-button-delete {
    position: relative;
    left: 80%;
  }
  .el-button-data-add {
    margin-top: 20px;
    position: relative;
    left: 50%;
  }
  img{
  width: 80%;
  
}
  .block{
    position: relative;
    left: 16%;
  }
  .el-card{
  background-color:  #c6e2ff;
}
h3{
  font-size: 25px;
  font-family: "微软雅黑";
  font-weight: 700;
  
}
p{
  font-size: 20px;
  font-family: "微软雅黑";
  font-weight: 500;
  text-indent: 300px;
}
</style>