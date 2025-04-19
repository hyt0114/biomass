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
export function downloadTemplate() {
  return http.download('/carbon/calc/download-template')
}
export function importData(data) {
  return http.post('/carbon/calc/import', data)
}
