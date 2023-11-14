<template>
    <el-card class="box-card" wrap>
      <template #header>
        <el-button type="primary" @click="addSelectionRow" :icon="Plus">增加新数据</el-button>
        <el-dialog
          v-model="dialogVisible"
          title="添加新数据"
          width="30%">
        <el-upload
            drag
            class="upload-demo"
            :auto-upload="false"
            :on-change="upload">
          <el-icon class="el-icon--upload">
            <UploadFilled/>
          </el-icon>
          <div class="el-upload__text">
            拖拽文件上传或<em>点击上传</em>
          </div>
          <template #tip>
            <div class="el-upload__tip">
              仅可提交.xls/.xlsx类型文件
            </div>
          </template>
        </el-upload>
      </el-dialog>
        <el-popconfirm
            title="确定删除数据 "
            @confirm="deleteSelectionRows()"
            confirm-button-type="danger"
            cancel-button-type="primary">
          <template #reference>
            <el-button type="danger" :icon="Delete">删除所选数据</el-button>
          </template>
        </el-popconfirm>
        <el-button @click="checkAll">全选</el-button>

   
              <el-input
                  v-model="searchVal"
                  class="search_input"
                  placeholder="请输入时间查找数据"
                  :prefix-icon="Search"
                  @keyup.enter="searchBaseName"
                   />

      </template>
      <div class="el-table">
        <el-table :data="dataList" stripe style="width: 100%" @selection-change="handleSelectionChange" ref="tableRef">
          <el-table-column type="selection" width="55" />
          <el-table-column property=feedIndex label="Id" width="60" align="left"/>
          <el-table-column property=tag label="Tag" width="60" align="left"/>
          <el-table-column property="time" label="Time" width="100" align="center"/>
          <el-table-column property="nurseingFeedPercentage" label="Nurseing_Feed_Percentage"  align="center"/>
          <el-table-column property="phase1FeedPercentage" label="PhaseⅠFeedPercentage" align="center"/>
          <el-table-column property="phase2FeedPercentage" label="WeigphaseⅡFeedPercentage" align="center"/>
          <el-table-column property="phase3FeedPercentage" label="phaseⅢFeedPercentage" align="center"/>
          <el-table-column property="dayOfIntake" label="dayOfIntake(allPig)" align="center"/>
          <el-table-column align="right">
            <!-- <template #header>
              <el-input
                  v-model="searchVal"
                  class="search_input"
                  placeholder="请输入时间查找数据"
                  :prefix-icon="Search"
                  @keyup.enter="searchBaseName"
                   />
            </template> -->
            <template #default="scope">
              <el-popover placement="left" :width="400" trigger="click">
                <template #reference>
                  <el-button style="margin-right: 16px" @click="showDetailedInfo">展示详细消息</el-button>
                </template>
              </el-popover>
            </template>
        </el-table-column>
        </el-table>
      </div>
      <div class="el-pagination">
        <el-pagination
            :page-count="page"
            :total="rows"
            v-model:current-page="currentPage"
            @current-change="handleCurrentChange"
            background
            layout="total, prev, pager, next, jumper"/>
      </div>
    </el-card>
</template>
  
  <script>
  import {computed, getCurrentInstance, onMounted, ref, toRefs} from "vue";
  import {Delete,Plus,Search,UploadFilled} from "@element-plus/icons-vue/global";
  import {ElMessage} from "element-plus";

  export default {
    name: "Feed",
    components: {
      UploadFilled,
  },

    setup() {
  
      const commonRequest = getCurrentInstance().appContext.config.globalProperties.$commonRequest;
      const dataList = ref([])
      const searchVal = ref()
      const page = ref();
      const currentPage=ref(1)
      const tableRef=ref()
      const keyOfCheckedData = ref([])
      const rows = ref()
      const dialogVisible = ref(false)

      // const idsOfCheckedData=computed(()=>{
      //   const ids = []
      //   for (let data of dataList.value) {
      //     if (data.checked) {
      //       ids.push(data.feedIndex);
      //     }
      //   }
      //   return ids;
      // })
 
      // 基于当前页数获取数据
      function getData(currentSize){
        commonRequest({
          method: "get",
          url: "/Feed/page/"+currentSize
        }).then(res => {
          dataList.value = res.data.data.feedList
          for (let row of dataList.value) {
            row.checked = false;
          }
        }).catch(err => {
          console.log(err)
        });
      }

      // 获取页数
      function getPageSize() {
        commonRequest({
          method: "get",
          url: "/Feed/page-count"
        }).then(res => {
          page.value = res.data.data.pageCount
        }).catch(err => {
          console.log(err)
        });

        
      }

      // 获取数据行数
      function getRows() {
        commonRequest({
          method: "get",
          url: "/Feed/rows-size"
        }).then(res => {
          console.log(res)
          rows.value = res.data.data.rowsSize
        }).catch(err => {
          console.log(err)
        });
      }
  
      // 删除所选数据
      function deleteSelectionRows() {
        commonRequest({
          method: "delete",
          url: "/Feed/delete",
          params:{
            ids: getSerializedData()
          }
        }).then(res => {
          // console.log(res),
          // console.log("删除成功！")
          ElMessage({
          message: '数据删除成功',
          type: 'success',
          })
        }).catch(err => {
          console.log(err)
        });

        const newDataList=[]
        for (let data of dataList.value) {
          if (data.checked===false) {
            newDataList.push(data)
          }
      }
        dataList.value=newDataList
      }
  
      // 处理被选中数据checked属性
      function handleSelectionChange() {
        keyOfCheckedData.value=[]
        for (let data of dataList.value) {
          data.checked = false
        }
        
        const selectionRows = tableRef.value.getSelectionRows();
        for (let selectionRow of selectionRows) {
          selectionRow.checked=true;
          keyOfCheckedData.value.push(selectionRow.feedIndex)
        }
      }
  
      // 添加数据
      function addSelectionRow() {
        dialogVisible.value = true;
      }
  
      // 切换页数
      function handleCurrentChange(newVal){
        getData(newVal)
      }
  
      function getSerializedData() {
        let stringify = JSON.stringify(keyOfCheckedData.value);
        stringify = stringify.substring(1, stringify.length - 1);
        return stringify;
      }
  
      function searchBaseName() {
        commonRequest({
          method: "get",
          url: "/Feed/search/" + searchVal.value
        }).then(res => {
          dataList.value = res.data.data.feedList
          for (let row of dataList.value) {
            row.checked = false;
          }
        }).catch(err => {
          console.log(err)
        });
        searchVal.value = "";
    }

    // 展示某数据行详细消息
    function showDetailedInfo() {

    }

        // 文件上传
    function upload(uploadFile) {
      ElMessage({
        message: '文件上传成功',
        type: 'success',
      })
      commonRequest({
        method: "post",
        url: "/Feed/upload",
        data: {
          'file': uploadFile.raw,
        },
        headers: {
          'Content-Type': 'multipart/form-data'
        }
      }).then(res => {
        const code = res.data.code;
        const message = res.data.message;

        if (code==="A0005") {
          ElMessage({
            message: message,
            type: 'success',
          })
        }else {
          ElMessage({
            message: message,
            type: 'error',
          })
        }
      }).catch(error => {
        ElMessage({
          message: '数据添加失败',
          type: 'error',
        })
      });
    }
        // 全选数据
    function checkAll() {
      tableRef.value.toggleAllSelection();
    }

      onMounted(()=>{
        getData(1)
        getPageSize()
        getRows()
      })
  
      return {
        dataList,
        page,
        searchVal,
        Plus,
        Delete,
        Search,
        currentPage,
        tableRef,
        keyOfCheckedData,
        rows,
        dialogVisible,
        searchBaseName,
        handleSelectionChange,
        deleteSelectionRows,
        handleCurrentChange,
        addSelectionRow,
        showDetailedInfo,
        upload,
        checkAll
      };
    },
  }
  </script>
  
  
  
  <style scoped>
    .el-table{
      margin-bottom: 10px;
    }
    .el-pagination {
      margin: auto;
    }
    .search_input {
    width: 300px;
    margin-left: 10px;
  }
  </style>
  
  
  