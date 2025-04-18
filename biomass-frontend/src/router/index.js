import { createRouter, createWebHistory } from 'vue-router'
import { getToken } from '@/common/access-util'
const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'home',
      component: () => import('@/views/HomeView.vue'),
      children: [
        {
          path: '/mangrove-list',
          name: 'mangrove-list',
          component: () => import('@/views/mangorove/ListView.vue'),
        },
        {
          path: '/mangrove-calc',
          name: 'mangrove-calc',
          component: () => import('@/views/mangorove/CalculateView.vue'),
        },
        {
          path: '/use-list',
          name: 'user-list',
          component: () => import('@/views/user/ListView.vue'),
        },
      ],
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/LoginView.vue'),
    },
  ],
})
router.beforeEach((from, to, next) => {
  const token = getToken()
  if (!token && from.name !== 'login') {
    router.replace({ name: 'login' })
  } else {
    next()
  }
})
export default router
