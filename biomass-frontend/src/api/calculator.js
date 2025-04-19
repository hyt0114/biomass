import http from '@/common/http'

export function loadTreeCategories() {
  return http.get('/mangrove/categories')
}
export function loadTreeList() {
  return http.get('/mangrove/trees')
}
export function doCalc(data) {
  return http.post('/carbon/calc/handle', data)
}
