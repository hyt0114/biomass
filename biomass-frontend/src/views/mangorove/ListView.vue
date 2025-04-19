<template>
  <div>
    <CommonGrid ref="gridRef" :columns="columns" :data-loader="loadMangrovePage">
      <template #buttons>
        <el-button type="primary" @click="onAddMangrove">新增</el-button>
      </template>
      <template #formula="{ row }">
        <span>
          <span>{{ row.formula }}</span>
          <span class="ml-4 text-info">{{ row.formulaDesc }}</span>
        </span>
      </template>
      <template #action="{ row }">
        <el-button type="primary" link @click="onEdit(row.id)">编辑</el-button>
        <el-button type="danger" link @click="onDelete(row)">删除</el-button>
      </template>
    </CommonGrid>
    <ModifyMangroveDialog
      v-model:visible="modifyShow"
      :id="currentEditId"
      @done="onModifyDone"
      v-if="modifyShow"
    />
  </div>
</template>
<script setup>
import { ref } from 'vue'
import CommonGrid from '@/components/form/CommonGrid.vue'
import ModifyMangroveDialog from './components/ModifyMangroveDialog.vue'
import { loadMangrovePage, deleteMangrove } from '@/api/mangrove'
import useConfirm from '@/hooks/useConfirm'
const columns = ref([
  {
    prop: 'id',
    label: 'id',
    width: 100,
  },
  {
    prop: 'name',
    label: '植物名称',
    width: 180,
  },
  {
    prop: 'scientificName',
    label: '学名',
    width: 180,
    tooltip: true,
  },
  {
    prop: 'density',
    label: '木材密度',
    width: 120,
  },
  {
    prop: 'carbonContentRatio',
    label: '含碳率',
    width: 120,
  },
  {
    slot: 'formula',
    label: '公式',
    width: 200,
    tooltip: true,
  },
  {
    prop: 'img',
    label: '图片',
    width: 120,
  },
  {
    prop: 'description',
    label: '描述',
    minWidth: 180,
  },
  {
    slot: 'action',
    label: '操作',
    width: 140,
    fixed: 'right',
  },
])
const gridRef = ref()
const modifyShow = ref(false)
const currentEditId = ref(null)
const onAddMangrove = () => {
  currentEditId.value = null
  modifyShow.value = true
}
const onEdit = (id) => {
  currentEditId.value = id
  modifyShow.value = true
}
const onModifyDone = () => {
  gridRef.value.loadData()
}
const onDelete = async (row) => {
  await useConfirm(`确定要删除数据 [${row.name}]？`)
  await deleteMangrove(row.id)
  onModifyDone()
}
</script>
<style lang="scss" scoped>
.ml-4 {
  margin-left: 4px;
}
</style>
