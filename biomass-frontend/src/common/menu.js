// TODO 后面改成后端读取菜单

export const menus = [
  {
    id: 'hsl',
    title: '红树林',
    icon: 'Guide',
    router: '',
    children: [
      {
        id: 'zw',
        title: '植物管理',
        icon: 'Orange',
        router: 'mangrove-list',
      },
      {
        id: 'js',
        title: '计算',
        icon: 'Iphone',
        router: 'mangrove-calc',
      },
    ],
  },
  {
    id: 'xtgl',
    title: '系统管理',
    icon: 'Menu',
    router: '',
    children: [
      {
        id: 'yhgl',
        title: '用户管理',
        icon: 'User',
        router: 'user-list',
      },
    ],
  },
]
