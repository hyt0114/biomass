import http from '@/common/http'

export function loadUserPage(data) {
  return http.post('/sys/user/page', data)
}
export function addUser(data) {
  return http.post('/sys/user/add', data)
}
export function updateUser(data) {
  return http.put('/sys/user/update', data)
}
export function loadUserInfo(id) {
  return http.get(`/sys/user/${id}`)
}
export function deleteUser(id) {
  return http.delete(`/sys/user/${id}`)
}
