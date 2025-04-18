<template>
  <div>
    <CommonGrid ref="gridRef" :columns="columns" :data-loader="loadMangrovePage">
      <template #buttons>
        <el-button type="primary" @click="onAddMangrove">新增</el-button>
      </template>
    </CommonGrid>
    <ModifyMangroveDialog v-model:visible="modifyShow" @done="onModifyDone" v-if="modifyShow" />
  </div>
</template>
<script setup>
import { ref } from 'vue'
import CommonGrid from '@/components/form/CommonGrid.vue'
import ModifyMangroveDialog from './components/ModifyMangroveDialog.vue'
import { loadMangrovePage } from '@/api/mangrove'
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
const onAddMangrove = () => {
  modifyShow.value = true
}
const onModifyDone = () => {
  gridRef.value.loadData()
}
</script>
