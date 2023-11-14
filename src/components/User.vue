<template>
  <el-card class="box-card" wrap>
    <template #header>
      <el-button type="primary" @click="addSelectionRow" :icon="Plus">增加</el-button>
      <el-popconfirm title="确定删除数据 " @confirm="deleteSelectionRowsOnMainTable" confirm-button-type="danger"
        cancel-button-type="primary">
        <template #reference>
          <el-button type="danger" :icon="Delete">删除所选数据</el-button>
        </template>
      </el-popconfirm>
    </template>

    <div class="el-table">
      <el-table ref="mainTableRef" :data="recordList" stripe style="width: 100%"
        @selection-change="handleSelectionChange">
        <el-table-column type="selection" width="55" />
        <el-table-column label="用户名" property="name" width="100" />
        <el-table-column label="电子邮箱地址" property="mail" width="1000" />
      </el-table>
    </div>

    <div class="el-pagination">
      <el-pagination :page-count="page" :total="rows" v-model:current-page="currentPage"
        @current-change="handleCurrentChange" background layout="total, prev, pager, next, jumper" />
    </div>
  </el-card>
</template>

<script>
import { computed, getCurrentInstance, onMounted, ref, toRefs } from "vue";
import { Delete, Plus } from "@element-plus/icons-vue/global";

export default {
  name: "User",

  setup() {

    const commonRequest = getCurrentInstance().appContext.config.globalProperties.$commonRequest;
    const recordList = ref([])
    const search = ref()
    const page = ref();
    const currentPage = ref(1)
    const mainTableRef = ref()
    const keyOfCheckedData = computed(() => {
      const ids = []
      for (let data of recordList.value) {
        if (data.checked) {
          ids.push(data.name);
        }
      }
      return ids;
    })
    const rows = ref()

    function getData(currentSize) {
      commonRequest({
        method: "get",
        url: "/user/page/" + currentSize
      }).then(res => {
        console.log(res)
        recordList.value = res.data.data.commonUserDtoList
        for (let row of recordList.value) {
          row.checked = false;
        }
      }).catch(err => {
        console.log(err)
      });
    }

    function getPageSize() {
      commonRequest({
        method: "get",
        url: "/user/page-count"
      }).then(res => {
        page.value = res.data.data.pageCount
      }).catch(err => {
        console.log(err)
      });
    }

    function getRows() {
      commonRequest({
        method: "get",
        url: "/user/rows-size"
      }).then(res => {
        rows.value = res.data.data.rowsSize
      }).catch(err => {
        console.log(err)
      });
    }

    function deleteSelectionRowsOnMainTable() {
      commonRequest({
        method: "delete",
        url: "/user/delete",
        params: {
          ids: getSerializedData()
        }
      }).then(res => {
      }).catch(err => {
        console.log(err)
      });
    }

    // 处理被选中数据checked属性
    function handleSelectionChange(rows) {
      const selectionRows = mainTableRef.value.getSelectionRows();
      for (let selectionRow of selectionRows) {
        // 可直接更改表格数据
        selectionRow.checked = true;
      }
    }

    // 添加数据
    function addSelectionRow() {

    }


    // 切换页数后获取数据
    function handleCurrentChange(newVal) {
      getData(newVal)
    }

    // 获取idsOfCheckedData序列化数据格式
    function getSerializedData() {
      let stringify = JSON.stringify(keyOfCheckedData.value);
      stringify = stringify.substring(1, stringify.length - 1);
      return stringify;
    }

    onMounted(() => {
      getData(1)
      getPageSize()
      getRows()
    })

    return {
      recordList,
      page,
      search,
      Plus,
      Delete,
      currentPage,
      mainTableRef,
      keyOfCheckedData,
      rows,
      handleSelectionChange,
      deleteSelectionRowsOnMainTable,
      handleCurrentChange,
      addSelectionRow,
    };
  },
}
</script>

<style scoped>
.el-table {
  margin-bottom: 10px;
}

.el-pagination {
  margin: auto;
}
</style>