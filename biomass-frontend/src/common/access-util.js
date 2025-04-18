import router from '@/router'
export function getToken() {
  return localStorage.getItem('token')
}
export function setToken(token) {
  localStorage.setItem('token', token)
}
export function clearToken() {
  localStorage.removeItem('token')
}
export function redirectToLogin() {
  router.replace({ name: 'login' })
}
