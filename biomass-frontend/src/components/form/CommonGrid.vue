<template>
  <div class="common-grid">
    <div class="common-grid-buttons" v-if="$slots.buttons">
      <slot name="buttons"></slot>
    </div>
    <el-table :data="dataList" border class="common-grid-table">
      <el-table-column label="序号" width="80">
        <template #default="scope">
          {{ scope.$index + 1 + props.pageSize * (pagination.current - 1) }}
        </template>
      </el-table-column>
      <el-table-column
        :prop="column.prop"
        :label="column.label"
        :width="column.width"
        :min-width="column.minWidth"
        :align="column.align"
        :fixed="column.fixed"
        :show-overflow-tooltip="!!column.tooltip"
        v-for="(column, index) in props.columns"
        :key="index"
      >
        <template #default="scope" v-if="column.slot">
          <slot :name="column.slot" :row="scope.row"></slot>
        </template>
      </el-table-column>
    </el-table>
    <el-pagination
      background
      layout="prev, pager, next,total"
      v-model:current-page="pagination.current"
      :total="pagination.total"
      hide-on-single-page
      class="pagination-block"
    />
  </div>
</template>
<script setup>
import { onMounted, reactive, ref, watch } from 'vue'

const props = defineProps({
  columns: {
    type: Array,
  },
  auto: {
    type: Boolean,
    default: true,
  },
  dataLoader: {
    type: Function,
  },
  pageSize: {
    type: Number,
    default: 10,
  },
})

const dataList = ref([])
const pagination = reactive({
  current: 1,
  size: props.pageSize,
  total: 0,
})
watch(
  () => pagination.current,
  () => {
    loadData()
  },
)
const loadData = async (refresh = false) => {
  if (props.dataLoader) {
    if (refresh) {
      pagination.current = 1
    }
    const { records, total } = await props.dataLoader({ ...pagination })
    dataList.value = records
    pagination.total = total
  }
}
onMounted(() => {
  if (props.auto) {
    loadData()
  }
})
defineExpose({
  loadData,
})
</script>
<style lang="scss" scoped>
.common-grid {
  &-table {
    --el-table-header-bg-color: #f5f7fa;
  }
  &-buttons {
    margin-bottom: 16px;
    display: flex;
  }
  .pagination-block {
    margin-top: 16px;
    display: flex;
    justify-content: flex-end;
  }
}
</style>
