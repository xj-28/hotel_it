<template>
  <div class="order-container">
    <h1 class="page-title">订单管理系统</h1>
    <div class="table-wrapper">
      <table class="order-table">
        <thead>
          <tr>
            <th>订单 ID</th>
            <th>会员 ID</th>
            <th>房间类型 ID</th>
            <th>入住日期</th>
            <th>退房日期</th>
            <th>订单状态</th>
            <th>订单金额</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="order in orders" :key="order.order_id">
            <td>{{ order.order_id }}</td>
            <td>{{ order.member_id }}</td>
            <td>{{ order.room_type_id }}</td>
            <td>{{ order.check_in_date }}</td>
            <td>{{ order.check_out_date }}</td>
            <td>
              <span class="status-badge" :class="getStatusClass(order.order_status)">
                {{ order.order_status }}
              </span>
            </td>
            <td>¥{{ order.order_amount }}</td>
            <td>
              <div class="action-buttons">
                <button class="edit-btn" @click="editOrder(order.order_id)">编辑</button>
                <button class="delete-btn" @click="deleteOrder(order.order_id)">删除</button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <button class="add-button" @click="addOrder">新增订单</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

const orders = ref([]);
const router = useRouter();

const fetchOrders = async () => {
  try {
    // 修改为使用代理路径
    const response = await axios.get('/api/orders');
    orders.value = response.data;
  } catch (error) {
    console.error('获取订单列表失败', error);
    alert('获取订单列表失败，请稍后重试');
  }
};

const deleteOrder = async (orderId) => {
  try {
    // 修改为使用代理路径
    await axios.delete(`/api/orders/${orderId}`);
    fetchOrders();
  } catch (error) {
    console.error('删除订单失败', error);
    alert('删除订单失败，请稍后重试');
  }
};

const addOrder = () => {
  router.push('/orders/add');
};

const editOrder = (orderId) => {
  router.push(`/orders/edit/${orderId}`);
};

// 根据订单状态返回对应的CSS类名
const getStatusClass = (status) => {
  switch(status) {
    case '已确认':
      return 'status-confirmed';
    case '已取消':
      return 'status-cancelled';
    case '已完成':
      return 'status-completed';
    default:
      return 'status-pending';
  }
};

onMounted(() => {
  fetchOrders();
});
</script>

<style scoped>
.order-container {
  max-width: 1200px;
  margin: 2rem auto;
  padding: 2rem;
  font-family: 'Segoe UI', system-ui, sans-serif;
  background-color: #f8f9fa;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
}

.page-title {
  text-align: center;
  color: #2c3e50;
  font-size: 2.5rem;
  margin-bottom: 2rem;
  font-weight: 600;
  letter-spacing: 1px;
}

.table-wrapper {
  border-radius: 10px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
  overflow: hidden;
  margin-bottom: 2rem;
  background-color: white;
}

.order-table {
  width: 100%;
  border-collapse: collapse;
}

.order-table th,
.order-table td {
  padding: 1.2rem 1rem;
  text-align: center;
}

.order-table th {
  background-color: #3498db;
  color: white;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.5px;
  font-size: 0.9rem;
}

.order-table tr:nth-child(even) {
  background-color: #f8f9fa;
}

.order-table tr:hover {
  background-color: #e8f4fc;
  transition: background-color 0.2s;
}

.status-badge {
  display: inline-block;
  padding: 0.4rem 0.8rem;
  border-radius: 20px;
  font-size: 0.85rem;
  font-weight: 500;
  color: white;
}

.status-confirmed {
  background-color: #27ae60;
}

.status-cancelled {
  background-color: #e74c3c;
}

.status-completed {
  background-color: #3498db;
}

.status-pending {
  background-color: #f39c12;
}

.action-buttons {
  display: flex;
  gap: 0.8rem;
  justify-content: center;
}

button {
  padding: 0.6rem 1.2rem;
  border: none;
  border-radius: 6px;
  cursor: pointer;
  font-weight: 500;
  transition: all 0.2s;
}

.add-button {
  background-color: #27ae60;
  color: white;
  font-size: 1.1rem;
  padding: 1rem 2rem;
  display: block;
  margin: 0 auto;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.add-button:hover {
  background-color: #219a52;
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.edit-btn {
  background-color: #2980b9;
  color: white;
}

.edit-btn:hover {
  background-color: #2472a3;
  transform: translateY(-1px);
}

.delete-btn {
  background-color: #e74c3c;
  color: white;
}

.delete-btn:hover {
  background-color: #c0392b;
  transform: translateY(-1px);
}

@media (max-width: 992px) {
  .order-table th,
  .order-table td {
    padding: 1rem 0.6rem;
    font-size: 0.9rem;
  }
}

@media (max-width: 768px) {
  .order-container {
    margin: 1rem;
    padding: 1.5rem;
  }

  .order-table {
    display: block;
    overflow-x: auto;
  }

  .order-table th,
  .order-table td {
    padding: 0.8rem 0.5rem;
    font-size: 0.8rem;
  }

  button {
    padding: 0.5rem 1rem;
    font-size: 0.85rem;
  }

  .add-button {
    padding: 0.8rem 1.5rem;
    font-size: 1rem;
  }
}
</style>