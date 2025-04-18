import { get, post } from '@/common/http'

export function loadTreeCategories() {
  return get('/mangrove/categories')
}
export function loadTreeList() {
  return get('/mangrove/trees')
}
export function doCalc(data) {
  return post('/carbon/calc/handle', data)
}
