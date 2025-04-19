import http from '@/common/http'

export function loadMangrovePage(data) {
  return http.post('/mangrove/page', data)
}
export function getMangrove(id) {
  return http.get(`/mangrove/${id}`)
}
export function addMangrove(data) {
  return http.post('/mangrove/add', data)
}
export function updateMangrove(data) {
  return http.put('/mangrove/update', data)
}
export function deleteMangrove(id) {
  return http.delete(`/mangrove/${id}`)
}
export function getPresetCalculators() {
  return http.get('/mangrove/preset-calculators')
}
