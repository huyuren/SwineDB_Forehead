<template>
    <el-drawer v-model="drawer" title="高级搜索" :direction="direction" :show-close="false" @close="handleClose">
      <el-form :model="form" label-width="80px">
        <template v-for="(value, index) in fieldNames" :key="index">
          <el-form-item :label="value" v-if="value !== 'id'">
            <el-input v-model="form[value]" />
          </el-form-item>
        </template>
        <el-button type="warning" @click="clearForm" class="middle">
          清空搜索条件
        </el-button>
      </el-form>
    </el-drawer>
  
    <el-card class="box-card" wrap>
      <template #header>
        <el-button type="primary" @click="configAddSingleDataDialogVisible" :icon="Plus">单条数据添加</el-button>
        <el-button type="primary" @click="addSelectionRow" :icon="Plus">多条数据添加</el-button>
        <el-button type="primary" @click="configHideFieldDialogVisible" :icon="Plus">字段隐藏</el-button>
        <el-popconfirm title="确定删除数据 " @confirm="deleteRowsByIds(mainTableRef.getSelectionRows())"
          confirm-button-type="danger" cancel-button-type="primary">
          <template #reference>
            <el-button type="danger" :icon="Delete">删除所选数据</el-button>
          </template>
        </el-popconfirm>
        <el-button @click="checkAll">全选</el-button>
  
        <!--单条数据添加-->
        <el-dialog v-model="addSingleDataDialogVisible" title="单条数据添加" width="60%">
          <h1>请注意字段数据类型：将字符赋予数字类型字段可能导致异常</h1>
          <el-form label-position="left" label-width="100px" style="max-width: 700px">
            <template v-for="(value, index) in fieldNames" :key="index">
              <el-form-item :label="value" v-if="value !== 'id'">
                <el-input v-model="singleRowData[value]" />
              </el-form-item>
            </template>
            <el-form-item>
              <el-button type="primary" @click="postSingleRowData">确定增加数据</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>
        <!--多条数据添加-->
        <el-dialog v-model="dialogVisible" title="多条数据添加" width="30%">
          <el-upload drag class="upload-demo" :auto-upload="false" :on-change="upload">
            <el-icon class="el-icon--upload">
              <UploadFilled />
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
        <!--字段隐藏-->
        <el-dialog v-model="hideFiledDialogVisible" title="字段隐藏" width="30%">
          <template v-for="(value, index) in fieldNames" :key="index">
            <h1>{{ value }}</h1>
            <el-switch v-model="fieldFlags[index]" size="large" active-text="显示字段" inactive-text="隐藏字段" />
          </template>
        </el-dialog>
        <!--更新数据-->
        <el-dialog v-model="editDialogVisible" title="更新数据" width="40%">
          <el-form label-position="left" label-width="100px" style="max-width: 460px">
            <template v-for="(value, index) in fieldNames" :key="index">
              <el-form-item :label="value">
                <el-input v-model="editingNewRow[value]" />
              </el-form-item>
            </template>
            <el-form-item>
              <el-button type="primary" @click="updateEditingData">确定更新数据</el-button>
            </el-form-item>
          </el-form>
        </el-dialog>
      </template>
      <div class="el-table">
        <el-table ref="mainTableRef" :data="recordList" border stripe style="width: 100%"
          @selection-change="handleSelectionChange">
          <el-table-column type="selection" width="55" />
          <template v-for="(value, index) in fieldNames" :key="index">
            <template v-if="fieldFlags[index]">
              <el-table-column :property="value" :label="value" width="200" />
              <h1>{{ value }}</h1>
            </template>
          </template>
          <el-table-column fixed="right" label="Operations" width="240">
            <template #default="scope">
              <el-popover placement="left" :width="400" trigger="click">
                <template #reference>
                  <el-button size="small" type="default">Detail</el-button>
                </template>
              </el-popover>
              <el-button size="small" type="primary" @click="configEditDialogVisible(scope.row)">Edit</el-button>
              <el-button size="small" type="danger" @click="deleteSingleRowById(scope.row)">Delete</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="el-pagination">
        <el-pagination v-model:current-page="currentPage" :page-count="page" :total="rows" background
          layout="total, prev, pager, next, jumper" @current-change="handleCurrentChange" />
      </div>
    </el-card>
  
    <el-card class="box-card" wrap>
      <template #header>
        <el-button type="primary" class="middle" @click="drawer = true">
          高级搜索
        </el-button>
        <el-popconfirm cancel-button-type="primary" confirm-button-type="danger" title="确定删除数据 "
          @confirm="deleteRowsByIds(subTableRef.getSelectionRows())">
          <template #reference>
            <el-button :icon="Delete" type="danger">删除所选数据</el-button>
          </template>
        </el-popconfirm>
        <el-button type="danger" class="middle" @click="deleteSearchedData">
          删除所有数据
        </el-button>
      </template>
      <div class="el-table">
        <el-table ref="subTableRef" :data="searchedRecordList" border stripe style="width: 100%">
          <el-table-column type="selection" width="55" />
          <template v-for="(value, index) in fieldNames" :key="index">
            <template v-if="fieldFlags[index]">
              <el-table-column :property="value" :label="value" width="200" />
              <h1>{{ value }}</h1>
            </template>
          </template>
          <el-table-column fixed="right" label="Operations" width="240">
            <template #default="scope">
              <el-popover placement="left" :width="400" trigger="click">
                <template #reference>
                  <el-button size="small" type="default">Detail</el-button>
                </template>
              </el-popover>
              <el-button size="small" type="primary" @click="configEditDialogVisible(scope.row)">Edit</el-button>
              <el-button size="small" type="danger" @click="deleteSingleRowById(scope.row)">Delete</el-button>
            </template>
          </el-table-column>
        </el-table>
      </div>
      <div class="el-pagination">
        <el-pagination v-model:current-page="searchedCurrentPage" :page-count="searchedPageSize" :total="searchedRowCount"
          background layout="total, prev, pager, next, jumper" @current-change="searchedHandleCurrentChange" />
      </div>
    </el-card>
  </template>
  <script>
  
  import { getCurrentInstance, onMounted, reactive, ref, watch } from "vue";
  import { useRoute } from 'vue-router';
  import { Delete, Plus, Search, UploadFilled } from "@element-plus/icons-vue/global";
  import { ElMessage, ElNotification } from "element-plus";
  
  export default {
    name: "Data",
  
    components: {
      UploadFilled,
    },
  
    setup() {
  
      const route = useRoute()
      const commonRequest = getCurrentInstance().appContext.config.globalProperties.$commonRequest;
  
      const className = ref()
      const fieldNames = ref([])
      const fieldFlags = ref([])
  
      // <----------------------MainTable---------------------------->
      const searchVal = ref()
      const recordList = ref([])
      const page = ref()
      const currentPage = ref(1)
      const mainTableRef = ref()
      const keyOfCheckedData = ref([])
      const rows = ref()
      const dialogVisible = ref(false)
      const hideFiledDialogVisible = ref(false)
      const addSingleDataDialogVisible = ref(false)
      const singleRowData = reactive({})
      // <----------------------SubTable---------------------------->
  
      const drawer = ref(false)
      const direction = ref('rtl')
      const searchedRecordList = ref([])
      const searchedPageSize = ref()
      const searchedCurrentPage = ref(1)
      const searchedRowCount = ref()
      const subTableRef = ref()
  
      // <-----------------------Other----------------------->
      const form = reactive({})
      let editingOriginRowValues = []
      const editingNewRow = ref();
      const editDialogVisible = ref(false)
  
      let formValues = []
  
      onMounted(() => {
        className.value = route.query.class
        getFieldNames()
        getData(1)
        getPageSize()
        getRows()
      });
  
      // 监视form
      watch(form, (newVal, oldVal) => {
        formValues = []
        for (let fieldNamesKey of fieldNames.value) {
          const tempValue = newVal[fieldNamesKey]
          if (tempValue === null) {
            formValues.push("null");
          } else {
            formValues.push(tempValue)
          }
        }
      })
  
  
      // 获取实体字段名列表
      function getFieldNames() {
        commonRequest({
          method: "get",
          url: `/${className.value}/fieldNames`
        }).then(res => {
          fieldNames.value = res.data.data.fieldNames;
          // 初始化form
          // 怎么单独放置到函数里就不行了？？？
          let index = 0;
          for (let value of fieldNames.value) {
            form[value] = "";
            fieldFlags.value[index] = true;
            index++;
          }
        }).catch(err => {
          console.log(err)
        });
      }
  
  
      // 基于当前页数获取数据
      function getData() {
        commonRequest({
          method: "get",
          url: `/${className.value}/page/${currentPage.value}`
        }).then(res => {
          recordList.value = res.data.data.recordList
          for (let row of recordList.value) {
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
          url: `/${className.value}/page-count`
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
          url: `/${className.value}/rows-size`
        }).then(res => {
          rows.value = res.data.data.rowsSize
        }).catch(err => {
          console.log(err)
        });
      }
  
      // 处理被选中数据checked属性
      // 手动选择实体主键
      function handleSelectionChange() {
        keyOfCheckedData.value = []
  
        for (let data of recordList.value) {
          data.checked = false
        }
        const selectionRows = mainTableRef.value.getSelectionRows();
        for (let selectionRow of selectionRows) {
          selectionRow.checked = true;
          // 直接向keyOfCheckedData里填充完整数据 而不是主键值
          const classNameValue = className.value;
          // todo 是不是应该为所有表增加主键 统一主键名称为id
          if (classNameValue === "cds") {
            keyOfCheckedData.value.push(selectionRow.cdsId);
          }
        }
      }
  
      function deleteSingleRowById(row) {
        commonRequest({
          method: "delete",
          url: `/${className.value}/delete-single-row`,
          headers: {
            'Content-Type': 'application/json',
          },
          data: JSON.stringify(row),
        }).then(res => {
          getData()
          getRows()
          getPageSize()
          searchedHandleCurrentChange()
        }).catch(err => {
          console.log(err)
        });
      }
  
      // 基于id删除多条数据
      function deleteRowsByIds(rows) {
        commonRequest({
          method: "delete",
          url: `/${className.value}/delete-rows`,
          headers: {
            'Content-Type': 'application/json',
          },
          data: JSON.stringify(rows),
        }).then(res => {
          getData()
          getRows()
          getPageSize()
          searchedHandleCurrentChange()
        }).catch(err => {
        });
      }
  
      // 删除所选数据
      function deleteSelectionRowsOnMainTable() {
        commonRequest({
          method: "delete",
          url: `/${className.value}/delete-multi-data-main`,
          headers: {
            'Content-Type': 'application/json',
          },
          data: JSON.stringify(mainTableRef.value.getSelectionRows())
        }).then(res => {
          if (res.data.code === "A0003") {
            ElNotification({
              title: '数据删除操作状态通知',
              message: res.data.message,
              type: 'success',
            });
          } else {
            ElNotification({
              title: '数据删除操作状态通知',
              message: res.data.message,
              type: 'error',
            });
          }
          getData()
          getRows()
          getPageSize()
          searchedHandleCurrentChange()
        }).catch(err => {
          console.log(err)
        });
      }
  
      function deleteSelectionRowsOnSubTable() {
        commonRequest({
          method: "delete",
          url: `/${className.value}/delete-multi-data-main`,
          headers: {
            'Content-Type': 'application/json',
          },
          data: JSON.stringify(subTableRef.value.getSelectionRows())
        }).then(res => {
          if (res.data.code === "A0003") {
            ElNotification({
              title: '数据删除操作状态通知',
              message: res.data.message,
              type: 'success',
            });
          } else {
            ElNotification({
              title: '数据删除操作状态通知',
              message: res.data.message,
              type: 'error',
            });
          }
          getData()
          getRows()
          getPageSize()
          searchedHandleCurrentChange()
        }).catch(err => {
          console.log(err)
        });
      }
  
      // 添加数据
      function addSelectionRow() {
        dialogVisible.value = true;
      }
  
      // 切换页数
      function handleCurrentChange(newVal) {
        getData(newVal)
      }
  
      function searchBaseName() {
        commonRequest({
          method: "get",
          url: `/${className.value}/search/${searchVal.value}`
        }).then(res => {
          recordList.value = res.data.data.recordList
          for (let row of recordList.value) {
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
          url: `/${className.value}/upload`,
          data: {
            'file': uploadFile.raw,
          },
          headers: {
            'Content-Type': 'multipart/form-data'
          }
        }).then(res => {
          const code = res.data.code;
          const message = res.data.message;
  
          if (code === "A0005") {
            ElMessage({
              message: message,
              type: 'success',
            })
          } else {
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
        mainTableRef.value.toggleAllSelection();
      }
  
      function getSerializedData(value) {
        let stringify = JSON.stringify(value);
        stringify = stringify.substring(1, stringify.length - 1);
        return stringify;
      }
  
      // drawer关闭前回调
      function handleClose() {
  
        searchedCurrentPage.value = 1
  
        commonRequest({
          method: "get",
          url: `/${className.value}/search-multi-key`,
          params: {
            'currentPage': searchedCurrentPage.value,
            'formValues': getSerializedData(formValues),
          }
        }).then(res => {
  
          searchedRecordList.value = res.data.data.recordList
          searchedPageSize.value = res.data.data.pageSize
          searchedRowCount.value = res.data.data.rowCount
        }).catch(err => {
          console.log(err)
        });
  
      }
  
      // 清空form
      function clearForm() {
        // 初始化form
        for (let value of fieldNames.value) {
          form[value] = "";
        }
      }
  
      function searchedHandleCurrentChange() {
        commonRequest({
          method: "get",
          url: `/${className.value}/search-multi-key`,
          params: {
            'currentPage': searchedCurrentPage.value,
            'formValues': getSerializedData(formValues),
          }
        }).then(res => {
          searchedRecordList.value = res.data.data.recordList
          searchedPageSize.value = res.data.data.pageSize
          searchedRowCount.value = res.data.data.rowCount
        }).catch(err => {
          console.log(err)
        });
      }
  
      function configHideFieldDialogVisible() {
        hideFiledDialogVisible.value = true;
      }
  
      function deleteSingleData(index, row) {
  
        let singleRowValues = []
  
        for (let fieldNamesKey of fieldNames.value) {
          singleRowValues.push(row[fieldNamesKey])
        }
  
        commonRequest({
          method: "delete",
          url: `/${className.value}/delete-single-data`,
          params: {
            'singleRowValues': getSerializedData(singleRowValues),
          }
        }).then(res => {
          getData()
          getRows()
          getPageSize()
          searchedHandleCurrentChange()
        }).catch(err => {
          console.log(err)
        });
  
      }
  
      function configEditDialogVisible(row) {
        editDialogVisible.value = true
        editingNewRow.value = row
  
        for (let fieldNamesKey of fieldNames.value) {
          editingOriginRowValues.push(row[fieldNamesKey])
        }
      }
  
      function updateEditingData() {
  
        editDialogVisible.value = false
  
        commonRequest({
          method: "put",
          url: `/${className.value}/update-single-data`,
          headers: {
            'Content-Type': 'application/json',
          },
          params: {
            'values': getSerializedData(editingOriginRowValues),
          },
          data: JSON.stringify(editingNewRow.value)
        }).then(res => {
          if (res.data.code === "A0009") {
            ElNotification({
              title: '数据更新操作状态通知',
              message: res.data.message,
              type: 'error',
            });
          } else {
            ElNotification({
              title: '数据更新操作状态通知',
              message: res.data.message,
              type: 'success',
            });
          }
          getData()
          searchedHandleCurrentChange()
          editingOriginRowValues = []
          for (let fieldName of fieldNames.value) {
            editingNewRow.value[fieldName] = '';
          }
        }).catch(err => {
          console.log(err)
        });
  
      }
  
      function configAddSingleDataDialogVisible() {
  
        for (let fieldName of fieldNames.value) {
          singleRowData[fieldName] = "";
        }
        addSingleDataDialogVisible.value = true
      }
  
      function postSingleRowData() {
        addSingleDataDialogVisible.value = false
        commonRequest({
          method: "post",
          url: `/${className.value}/save-single-data`,
          headers: {
            'Content-Type': 'application/json',
          },
          data: JSON.stringify(singleRowData)
        }).then(res => {
          if (res.data.code === "A0006") {
            ElNotification({
              title: '数据添加操作状态通知',
              message: res.data.message,
              type: 'error',
            });
          }
          getData()
          getRows()
          getPageSize()
          searchedHandleCurrentChange()
        }).catch(err => {
          console.log(err)
        });
      }
  
  
      function deleteSearchedData() {
        commonRequest({
          method: "delete",
          url: `/${className.value}/delete-multi-data-sub`,
          params: {
            'formValues': getSerializedData(formValues),
          }
        }).then(res => {
          console.log(res)
          getData()
          getRows()
          searchedHandleCurrentChange()
        }).catch(err => {
          console.log(err)
        });
      }
  
      return {
        route,
        recordList,
        page,
        searchVal,
        Plus,
        Delete,
        Search,
        currentPage,
        mainTableRef: mainTableRef,
        keyOfCheckedData,
        rows,
        dialogVisible,
        fieldNames,
        searchedRecordList,
        searchedPageSize,
        searchedCurrentPage,
        searchedRowCount,
        drawer,
        direction,
        form,
        hideFiledDialogVisible,
        fieldFlags,
        editingNewRow,
        addSingleDataDialogVisible,
        singleRowData,
        editDialogVisible,
        subTableRef,
        handleSelectionChange,
        deleteSelectionRowsOnMainTable: deleteSelectionRowsOnMainTable,
        handleCurrentChange,
        addSelectionRow,
        upload,
        checkAll,
        handleClose,
        clearForm,
        searchedHandleCurrentChange,
        configHideFieldDialogVisible,
        configAddSingleDataDialogVisible,
        postSingleRowData,
        deleteSingleData,
        updateEditingData,
        configEditDialogVisible,
        deleteSearchedData,
        deleteSelectionRowsOnSubTable,
        deleteRowsByIds,
        deleteSingleRowById,
  
  
      };
    },
  }
  
  </script>
  
  <style scoped>
  .box-card {
    min-width: 800px;
  }
  
  .el-table {
    margin-bottom: 10px;
  }
  
  .el-pagination {
    margin: auto;
  }
  </style>