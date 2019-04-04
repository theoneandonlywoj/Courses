import Home from './components/Home.vue'
import Header from './components/Header.vue'

// Loading Routes Lazily
const User = resolve => {
  require.ensure(['./components/user/User.vue'], () => {
    resolve(require('./components/user/User.vue'));
  });
}
const UserStart = resolve => {
  require.ensure(['./components/user/UserStart.vue'], () => {
    resolve(require('./components/user/UserStart.vue'));
  });
}
const UserDetail = resolve => {
  require.ensure(['./components/user/UserDetail.vue'], () => {
    resolve(require('./components/user/UserDetail.vue'));
  });
}
const UserEdit = resolve => {
  require.ensure(['./components/user/UserEdit.vue'], () => {
    resolve(require('./components/user/UserEdit.vue'));
  });
}


export const routes = [{
    name: "home",
    path: '',
    components: {
      default: Home,
      'header-top': Header
    }
  },
  // Passing parameters
  //{ name: "user", path: '/user/:id', component: User}
  // Configuring subroutes using "children" key
  {
    name: "user",
    path: '/user',
    components: {
      default: User,
      'header-bottom': Header
    },
    children: [{
        name: 'userStart',
        path: '/userStart',
        component: UserStart
      },
      {
        name: 'userDetail',
        path: ':id',
        component: UserDetail,
        // Protecting a specific path
        beforeEnter: (to, from, next) => {
          console.log("Checking access to the User Detail Page.");
          next();
        }
      },
      {
        name: 'userEdit',
        path: ':id/edit',
        component: UserEdit
      }
    ]
  },
  // Redirecting
  {
    name: 'redirecting',
    path: '/redirect-me',
    redirect: {
      name: 'userStart'
    }
  },
  // Catching all non-existing routes
  {
    path: '*',
    redirect: '/'
  }
]
