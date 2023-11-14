<template>
  <div>
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
    <div>
      <el-card class="box-card" wrap>
        <template #header>
          <el-button type="primary" @click="configHideFieldDialogVisible" :icon="Plus">字段隐藏</el-button>
          <el-button @click="checkAll">全选</el-button>
          <!--字段隐藏-->
          <el-dialog v-model="hideFiledDialogVisible" title="字段隐藏" width="30%">
            <template v-for="(value, index) in fieldNames" :key="index">
              <h1>{{ value }}</h1>
              <el-switch v-model="fieldFlags[index]" size="large" active-text="显示字段" inactive-text="隐藏字段" />
            </template>
          </el-dialog>
          <el-button type="primary" round @click="isShowMultiField = !isShowMultiField">Multi-value Search</el-button>
          <el-button type="primary" round @click="isShowSingleField = !isShowSingleField">Single-value Search</el-button>
        </template>
        <div class="el-table">
          <el-table ref="mainTableRef" :data="recordList" border stripe style="width: 100%"
            @selection-change="handleSelectionChange">
            <el-table-column type="selection" width="55" />
            <template v-for="(value, index) in fieldNames" :key="index">
              <template v-if="fieldFlags[index]">
                <el-table-column :property="value" :label="BigfieldNames[index]" width="200" />
                <h1>{{ value }}</h1>
              </template>
            </template>
          </el-table>
        </div>
        <div class="el-pagination">
          <el-pagination v-model:current-page="currentPage" :page-count="page" :total="rows" background
            layout="total, prev, pager, next, jumper" @current-change="handleCurrentChange" />
        </div>
      </el-card>
    </div>
    <div>
      <el-card class="box-card" wrap v-if="isShowMultiField">
        <template #header>
          <el-button type="primary" class="middle" @click="drawer = true">
            高级搜索
          </el-button>
          <div>
            <template v-for="(value, index) in fieldNames" :key="index">
              <el-form :label-position="labelPosition" label-width="100px" :model="formLabelAlign"
                style="max-width: 460px">
                <el-form-item :label="value" v-if="value !== 'id'">
                  <el-input v-model="form[value]" @keydown.enter="handleClose()" placeholder="输入搜索值"
                    style="width: 300px;" />
                  <!-- <button @click="handleClose()">搜索</button>  -->
                </el-form-item>
              </el-form>
            </template>
            <button @click="handleClose()">搜索</button>
            <!-- <input v-model="form[value]" :prefix-icon="Search" @keydown.enter="handleClose()" placeholder="输入搜索值">
        <button @click="handleClose()">搜索</button> -->
            <!-- <ul>
          <li v-for="row in recordList" :key="row.id">{{ row.name }}</li>
        </ul> -->
          </div>
        </template>
        <div class="el-table">
          <el-table ref="subTableRef" :data="searchedRecordList" border stripe style="width: 100%">
            <el-table-column type="selection" width="55" />
            <template v-for="(value, index) in fieldNames" :key="index">
              <template v-if="fieldFlags[index]">
                <el-table-column :property="value" :label="BigfieldNames[index]" width="200" />
                <h1>{{ value }}</h1>
              </template>
            </template>
          </el-table>
        </div>
        <div class="el-pagination">
          <el-pagination v-model:current-page="searchedCurrentPage" :page-count="searchedPageSize"
            :total="searchedRowCount" background layout="total, prev, pager, next, jumper"
            @current-change="searchedHandleCurrentChange" />
        </div>
      </el-card>
    </div>
    <div>
      <el-card class="box-card" wrap v-if="isShowSingleField">
        <div>
          <el-row>
            <el-input v-model="searchVal" class="w-50 m-2" :prefix-icon="Search" @keyup.enter="searchBaseName"
              placeholder="输入搜索内容">
              <template #prefix>
                <el-icon class="el-input__icon">
                  <search />
                </el-icon>
              </template>
            </el-input>
            <el-button type="success" round @click="searchBaseName">search</el-button>
          </el-row>
        </div>
        <div class="el-table">
          <el-table ref="ThirdTableRef" :data="singlesearchedRecordList" border stripe style="width: 100%">
            <el-table-column type="selection" width="55" />
            <template v-for="(value, index) in fieldNames" :key="index">
              <template v-if="fieldFlags[index]">
                <el-table-column :property="value" :label="BigfieldNames[index]" width="200" />
                <h1>{{ value }}</h1>
              </template>
            </template>
          </el-table>
        </div>
        <div class="el-pagination">
          <el-pagination v-model:current-page="singlesearchedCurrentPage" :page-count="singlesearchedPageSize"
            :total="singlesearchedRowCount" background layout="total, prev, pager, next, jumper"
            @current-change="searchBaseName" />
        </div>
      </el-card>
    </div>
    <div>
      <div>
        <h2>3D-graph测试</h2>
      </div>
      <div id="3DBarchart" ref="graphmain" style="width: 100%; height: 400px">

      </div>
    </div>
  </div>
</template>
<script>

import { getCurrentInstance, onMounted, reactive, ref, watch } from "vue";
import { useRoute } from 'vue-router';
import { Delete, Plus, Search, UploadFilled } from "@element-plus/icons-vue/global";
import { ElMessage, ElNotification } from "element-plus";
import * as echarts from 'echarts';
import 'echarts-gl';
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
    const BigfieldNames = ref([])
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

    // <----------------------third Table---------------------------->
    const ThirdTableRef = ref()
    const singlesearchedRecordList = ref([])
    const singlesearchedPageSize = ref()
    const singlesearchedCurrentPage = ref(1)
    const singlesearchedRowCount = ref()



    // <-----------------------isshow----------------------->
    const isShowSingleField = ref(false)
    const isShowMultiField = ref(false)
    // <-----------------------Other----------------------->
    const labelPosition = ref('right')
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
      graphinit()
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
        url: `/${className.value}/fieldNames`,
        headers: {
          'secret': 'secret',
        },
      }).then(res => {
        console.log("data+244");
        fieldNames.value = res.data.data.fieldNames;

        for (let i = 0; i < fieldNames.value.length; i++) {
          let fieldName = fieldNames.value[i];
          if (fieldName.charAt(0) === fieldName.charAt(0).toLowerCase()) {
            fieldName = fieldName.charAt(0).toUpperCase() + fieldName.slice(1);
          }
          BigfieldNames.value.push(fieldName);
        }
        // 初始化form
        // 怎么单独放置到函数里就不行了？？？
        let index = 0;
        for (let value of fieldNames.value) {
          form[value] = "";
          fieldFlags.value[index] = true;
          index++;
        }
        console.log(fieldNames.value);
        console.log(`/${className.value}/fieldNames`)
      }).catch(err => {
        console.log(err)
      });
    }


    // 基于当前页数获取数据
    function getData() {
      commonRequest({
        method: "get",
        url: `/${className.value}/page/${currentPage.value}`,
        headers: {
          'secret': 'secret',
        },
      }).then(res => {
        console.log("data+270！");
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
        url: `/${className.value}/page-count`,
        headers: {
          'secret': 'secret',
        },
      }).then(res => {
        console.log("data+289！");
        page.value = res.data.data.pageCount
        console.log(`/${className.value}/page-count`)
      }).catch(err => {
        console.log(err)
      });
    }

    // 获取数据行数
    function getRows() {
      commonRequest({
        method: "get",
        url: `/${className.value}/rows-size`,
        headers: {
          'secret': 'secret',
        },
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


    // 切换页数
    function handleCurrentChange(newVal) {
      getData(newVal)
    }


    function searchBaseName() {
      // searchedCurrentPage.value = 1
      commonRequest({
        method: "get",
        url: `/${className.value}/search/${searchVal.value}`,
        headers: {
          'secret': 'secret',
        },
        params: {
          'currentPage': singlesearchedCurrentPage.value,
        }
      }).then(res => {
        console.log(searchVal.value);
        singlesearchedRecordList.value = res.data.data.recordList
        // for (let row of searchedRecordList.value) {
        //   row.checked = false;
        // }
        singlesearchedPageSize.value = res.data.data.pageSize
        singlesearchedRowCount.value = res.data.data.rowCount
      }).catch(err => {
        console.log(err)
      });
      // searchVal.value = "";
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
        headers: {
          'secret': 'secret',
        },
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
        headers: {
          'secret': 'secret',
        },
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


    function configEditDialogVisible(row) {
      editDialogVisible.value = true
      editingNewRow.value = row

      for (let fieldNamesKey of fieldNames.value) {
        editingOriginRowValues.push(row[fieldNamesKey])
      }
    }

    function configAddSingleDataDialogVisible() {

      for (let fieldName of fieldNames.value) {
        singleRowData[fieldName] = "";
      }
      addSingleDataDialogVisible.value = true
    }

    const graphmain = ref() // 使用ref创建虚拟DOM引用，使用时用main.value
    function graphinit() {
      // 可视化操作
      // var BarChart = echarts.init(document.getElementById('3DBarchart'));
      var BarChart = echarts.init(graphmain.value);
      window.addEventListener('resize', function () {
        BarChart.resize();
      });

      // var myChart = echarts.init(BarChart);
      var option;

      // prettier-ignore
      var hours = ['12a', '1a', '2a', '3a', '4a', '5a', '6a',
        '7a', '8a', '9a', '10a', '11a',
        '12p', '1p', '2p', '3p', '4p', '5p',
        '6p', '7p', '8p', '9p', '10p', '11p'];
      // prettier-ignore
      var days = ['Saturday', 'Friday', 'Thursday',
        'Wednesday', 'Tuesday', 'Monday', 'Sunday', 'Sunday', 'Monday'];
      // prettier-ignore
      var data = [[0, 0, 5], [0, 1, 1], [0, 2, 0], [0, 3, 0], [0, 4, 0], [0, 5, 0], [0, 6, 0], [0, 7, 0], [0, 8, 0], [0, 9, 0], [0, 10, 0], [0, 11, 2], [0, 12, 4], [0, 13, 1], [0, 14, 1], [0, 15, 3], [0, 16, 4], [0, 17, 6], [0, 18, 4], [0, 19, 4], [0, 20, 3], [0, 21, 3], [0, 22, 2], [0, 23, 5], [1, 0, 7], [1, 1, 0], [1, 2, 0], [1, 3, 0], [1, 4, 0], [1, 5, 0], [1, 6, 0], [1, 7, 0], [1, 8, 0], [1, 9, 0], [1, 10, 5], [1, 11, 2], [1, 12, 2], [1, 13, 6], [1, 14, 9], [1, 15, 11], [1, 16, 6], [1, 17, 7], [1, 18, 8], [1, 19, 12], [1, 20, 5], [1, 21, 5], [1, 22, 7], [1, 23, 2], [2, 0, 1], [2, 1, 1], [2, 2, 0], [2, 3, 0], [2, 4, 0], [2, 5, 0], [2, 6, 0], [2, 7, 0], [2, 8, 0], [2, 9, 0], [2, 10, 3], [2, 11, 2], [2, 12, 1], [2, 13, 9], [2, 14, 8], [2, 15, 10], [2, 16, 6], [2, 17, 5], [2, 18, 5], [2, 19, 5], [2, 20, 7], [2, 21, 4], [2, 22, 2], [2, 23, 4], [3, 0, 7], [3, 1, 3], [3, 2, 0], [3, 3, 0], [3, 4, 0], [3, 5, 0], [3, 6, 0], [3, 7, 0], [3, 8, 1], [3, 9, 0], [3, 10, 5], [3, 11, 4], [3, 12, 7], [3, 13, 14], [3, 14, 13], [3, 15, 12], [3, 16, 9], [3, 17, 5], [3, 18, 5], [3, 19, 10], [3, 20, 6], [3, 21, 4], [3, 22, 4], [3, 23, 1], [4, 0, 1], [4, 1, 3], [4, 2, 0], [4, 3, 0], [4, 4, 0], [4, 5, 1], [4, 6, 0], [4, 7, 0], [4, 8, 0], [4, 9, 2], [4, 10, 4], [4, 11, 4], [4, 12, 2], [4, 13, 4], [4, 14, 4], [4, 15, 14], [4, 16, 12], [4, 17, 1], [4, 18, 8], [4, 19, 5], [4, 20, 3], [4, 21, 7], [4, 22, 3], [4, 23, 0], [5, 0, 2], [5, 1, 1], [5, 2, 0], [5, 3, 3], [5, 4, 0], [5, 5, 0], [5, 6, 0], [5, 7, 0], [5, 8, 2], [5, 9, 0], [5, 10, 4], [5, 11, 1], [5, 12, 5], [5, 13, 10], [5, 14, 5], [5, 15, 7], [5, 16, 11], [5, 17, 6], [5, 18, 0], [5, 19, 5], [5, 20, 3], [5, 21, 4], [5, 22, 2], [5, 23, 0], [6, 0, 1], [6, 1, 0], [6, 2, 0], [6, 3, 0], [6, 4, 0], [6, 5, 0], [6, 6, 0], [6, 7, 0], [6, 8, 0], [6, 9, 0], [6, 10, 1], [6, 11, 0], [6, 12, 2], [6, 13, 1], [6, 14, 3], [6, 15, 4], [6, 16, 0], [6, 17, 0], [6, 18, 0], [6, 19, 0], [6, 20, 1], [6, 21, 2], [6, 22, 2], [6, 23, 6]];
      option = {
        tooltip: {},
        visualMap: {
          max: 20,
          inRange: {
            color: [
              '#313695', '#4575b4', '#74add1', '#abd9e9', '#e0f3f8', '#ffffbf',
              '#fee090',
              '#fdae61',
              '#f46d43',
              '#d73027',
              '#a50026'
            ]
          }
        },
        xAxis3D: {
          type: 'category',
          data: hours
        },
        yAxis3D: {
          type: 'category',
          data: days
        },
        zAxis3D: {
          type: 'value'
        },
        grid3D: {
          boxWidth: 200,
          boxDepth: 80,
          light: {
            main: {
              intensity: 1.2
            },
            ambient: {
              intensity: 0.3
            }
          }
        },
        series: [
          {
            type: 'bar3D',
            data: data.map(function (item) {
              return {
                value: [item[1], item[0], item[2]]
              };
            }),
            shading: 'color',
            label: {
              show: false,
              fontSize: 16,
              borderWidth: 1
            },
            itemStyle: {
              opacity: 0.4
            },
            emphasis: {
              label: {
                fontSize: 20,
                color: '#900'
              },
              itemStyle: {
                color: '#900'
              }
            }
          }
        ]
      };
      BarChart.setOption(option);
      // window.onresize = myChart.resize;
    }
    return {
      labelPosition,
      route,
      recordList,
      page,
      searchVal,
      Plus,
      Delete,
      Search,
      graphmain,
      isShowSingleField,
      isShowMultiField,
      currentPage,
      mainTableRef: mainTableRef,
      keyOfCheckedData,
      rows,
      dialogVisible,
      fieldNames,
      BigfieldNames,
      searchBaseName,
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
      // addSingleDataDialogVisible,
      singleRowData,
      editDialogVisible,
      subTableRef,
      ThirdTableRef,
      singlesearchedRecordList,
      singlesearchedCurrentPage,
      singlesearchedRowCount,
      singlesearchedPageSize,
      handleSelectionChange,
      handleCurrentChange,
      checkAll,
      handleClose,
      clearForm,
      searchedHandleCurrentChange,
      configHideFieldDialogVisible,
      configAddSingleDataDialogVisible,
      configEditDialogVisible,
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