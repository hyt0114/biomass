import { post, put } from '@/common/http'

export function loadMangrovePag(data) {
  return post('/mangrove/page', data)
}
export function addMangrove(data) {
  return post('/mangrove/add', data)
}
export function updateMangrove(data) {
  return put('/mangrove/update', data)
}
