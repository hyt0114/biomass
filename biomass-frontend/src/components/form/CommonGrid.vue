<template>
  <div class="common-grid">
    <div class="common-grid-buttons" v-if="$slots.buttons">
      <slot name="buttons"></slot>
    </div>
    <el-table :data="dataList" border class="common-grid-table">
      <el-table-column
        :prop="column.prop"
        :label="column.label"
        :width="column.width"
        :min-width="column.minWidth"
        :fixed="column.fixed"
        v-for="(column, index) in columns"
        :key="index"
      />
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
<script lang="ts" setup>
import { onMounted, reactive, ref, watch } from 'vue'
interface ColumnType {
  prop?: string
  slot?: string
  label: string
  width?: number
  minWidth?: number
  [key: string]: unknown
}
interface PropsType {
  columns: Array<ColumnType>
  auto?: boolean
  dataLoader: (data: unknown) => Promise<{
    current: number
    records: Array<unknown>
    pages: number
    size: number
    total: number
  }>
  pageSize?: number
}
const { columns, auto = true, dataLoader, pageSize = 10 } = defineProps<PropsType>()

const dataList = ref<Array<unknown>>([])
const pagination = reactive({
  current: 1,
  size: pageSize,
  total: 0,
})
watch(
  () => pagination.current,
  () => {
    loadData()
  },
)
const loadData = async (refresh = false) => {
  if (dataLoader) {
    if (refresh) {
      pagination.current = 1
    }
    const { records, total } = await dataLoader({ ...pagination })
    dataList.value = records
    pagination.total = total
  }
}
onMounted(() => {
  if (auto) {
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
