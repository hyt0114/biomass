import { post } from '@/common/http'

export function doLogin(formState) {
  return post('/login', formState)
}
