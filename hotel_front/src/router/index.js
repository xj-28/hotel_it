import { createRouter, createWebHistory } from 'vue-router';
import AdminLogin from '../components/AdminLogin.vue';
import IndexPage from '../components/IndexPage.vue';
import OrderList from '../components/OrderList.vue';
import OrderForm from '../components/OrderForm.vue';
import MemberList from '../components/MemberList.vue';
import MemberForm from '../components/MemberForm.vue';
import RoomTypeList from '../components/RoomTypeList.vue';
import RoomTypeForm from '../components/RoomTypeForm.vue';

const routes = [
  {
    path: '/',
    redirect: '/admin/login'
  },
  {
    path: '/admin/login',
    component: AdminLogin
  },
  {
    path: '/index',
    component: IndexPage
  },
  {
    path: '/orders',
    component: OrderList
  },
  {
    path: '/orders/add',
    component: OrderForm
  },
  {
    path: '/orders/edit/:orderId',
    component: OrderForm
  },
  {
    path: '/members',
    component: MemberList
  },
  {
    path: '/members/add',
    component: MemberForm
  },
  {
    path: '/members/edit/:memberId',
    component: MemberForm
  },
  {
    path: '/room-types',
    component: RoomTypeList
  },
  {
    path: '/room-types/add',
    component: RoomTypeForm
  },
  {
    path: '/room-types/edit/:roomTypeId',
    component: RoomTypeForm
  }
];

const router = createRouter({
  history: createWebHistory(),
  routes
});

export default router;    