<template>
  <div class="form-container">
    <h1 class="form-title">{{ isEdit ? '编辑订单' : '新增订单' }}</h1>
    <div class="form-content">
      <div class="form-group">
        <label>会员 ID</label>
        <input v-model="order.member_id" placeholder="请输入会员 ID" />
      </div>
      <div class="form-group">
        <label>房间类型 ID</label>
        <input v-model="order.room_type_id" placeholder="请输入房间类型 ID" />
      </div>
      <div class="form-group">
        <label>入住日期</label>
        <input v-model="order.check_in_date" placeholder="请输入入住日期" type="date" />
      </div>
      <div class="form-group">
        <label>退房日期</label>
        <input v-model="order.check_out_date" placeholder="请输入退房日期" type="date" />
      </div>
      <div class="form-group">
        <label>订单状态</label>
        <select v-model="order.order_status">
          <option value="">请选择订单状态</option>
          <option value="已预订">已预订</option>
          <option value="已入住">已入住</option>
          <option value="已退房">已退房</option>
          <option value="已取消">已取消</option>
        </select>
      </div>
      <div class="form-group">
        <label>订单金额</label>
        <input v-model="order.order_amount" placeholder="请输入订单金额" type="number" />
      </div>
      <div class="button-group">
        <button class="save-btn" @click="saveOrder">保存</button>
        <button class="cancel-btn" @click="cancel">取消</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted, computed } from 'vue';
import axios from 'axios';
import { useRouter, useRoute } from 'vue-router';

const router = useRouter();
const route = useRoute();
const order = ref({
  order_id: '', // 添加 order_id 字段
  member_id: '',
  room_type_id: '',
  check_in_date: '',
  check_out_date: '',
  order_status: '',
  order_amount: ''
});
const isEdit = ref(false);

// 添加数据验证
const validateOrder = () => {
  if (!order.value.member_id) {
    alert('请输入会员ID');
    return false;
  }
  if (!order.value.room_type_id) {
    alert('请输入房间类型ID');
    return false;
  }
  if (!order.value.check_in_date) {
    alert('请选择入住日期');
    return false;
  }
  if (!order.value.check_out_date) {
    alert('请选择退房日期');
    return false;
  }
  if (!order.value.order_status) {
    alert('请选择订单状态');
    return false;
  }
  if (!order.value.order_amount) {
    alert('请输入订单金额');
    return false;
  }
  return true;
};

const fetchOrder = async (orderId) => {
  try {
    const token = localStorage.getItem('token');
    const response = await axios.get(`/api/orders/${orderId}`, {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
    
    // 打印原始数据，查看结构
    console.log('获取到的订单数据:', response.data);
    
    // 确保日期格式正确
    const data = response.data;
    if (data.check_in_date) {
      data.check_in_date = data.check_in_date.split('T')[0];
    }
    if (data.check_out_date) {
      data.check_out_date = data.check_out_date.split('T')[0];
    }
    
    order.value = data;
  } catch (error) {
    console.error('获取订单详情失败', error);
    if (error.response && error.response.status === 401) {
      alert('登录已过期，请重新登录');
      router.push('/login');
      return;
    }
    alert('获取订单详情失败，请稍后重试');
  }
};

const saveOrder = async () => {
  try {
    // 验证数据
    if (!validateOrder()) {
      return;
    }
    
    // 打印要发送的数据，检查格式
    console.log('发送前的订单数据:', order.value);
    
    // 准备发送的数据
    const orderData = { ...order.value };
    
    // 确保数值字段是数字类型
    orderData.member_id = Number(orderData.member_id);
    orderData.room_type_id = Number(orderData.room_type_id);
    orderData.order_amount = Number(orderData.order_amount);
    
    // 确保日期格式正确 (ISO 格式)
    if (orderData.check_in_date && !orderData.check_in_date.includes('T')) {
      orderData.check_in_date = new Date(orderData.check_in_date).toISOString();
    }
    if (orderData.check_out_date && !orderData.check_out_date.includes('T')) {
      orderData.check_out_date = new Date(orderData.check_out_date).toISOString();
    }
    
    // 获取token
    const token = localStorage.getItem('token');
    const config = {
      headers: {
        'Authorization': `Bearer ${token}`,
        'Content-Type': 'application/json'
      }
    };
    
    console.log('最终发送的数据:', JSON.stringify(orderData, null, 2));
    
    let response;
    if (isEdit.value) {
      // 确保编辑时有 order_id
      if (!orderData.order_id) {
        orderData.order_id = Number(route.params.orderId);
      }
      
      // 尝试使用不同的 API 端点格式
      response = await axios.put(`/api/orders/${orderData.order_id}`, orderData, config);
    } else {
      response = await axios.post('/api/orders', orderData, config);
    }
    
    console.log('保存成功，响应:', response.data);
    router.push('/orders');
  } catch (error) {
    console.error('保存订单失败', error);
    if (error.response) {
      console.log('错误响应状态:', error.response.status);
      console.log('错误响应数据:', error.response.data);
      
      // 显示更具体的错误信息
      if (error.response.data && error.response.data.message) {
        alert(`保存失败: ${error.response.data.message}`);
        return;
      }
      
      if (error.response.status === 401) {
        alert('登录已过期，请重新登录');
        router.push('/login');
        return;
      }
    }
    alert('保存订单失败，请稍后重试');
  }
};

const cancel = () => {
  router.push('/orders');
};

onMounted(() => {
  if (route.params.orderId) {
    isEdit.value = true;
    fetchOrder(route.params.orderId);
  }
});
</script>

<style scoped>
.form-container {
  max-width: 800px;
  margin: 2rem auto;
  padding: 2rem;
  background-color: #f8f9fa;
  border-radius: 12px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.1);
  font-family: 'Segoe UI', system-ui, sans-serif;
}

.form-title {
  text-align: center;
  color: #2c3e50;
  font-size: 2rem;
  margin-bottom: 2rem;
  font-weight: 600;
}

.form-content {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

.form-group {
  display: flex;
  flex-direction: column;
  gap: 0.5rem;
}

label {
  font-weight: 500;
  color: #2c3e50;
  font-size: 1rem;
}

input, select, textarea {
  padding: 12px;
  border: 2px solid #e0e0e0;
  border-radius: 8px;
  font-size: 1rem;
  transition: all 0.3s ease;
  font-family: inherit;
}

input:focus, select:focus, textarea:focus {
  border-color: #3498db;
  box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.1);
  outline: none;
}

select {
  background-color: white;
  cursor: pointer;
}

.button-group {
  display: flex;
  gap: 1rem;
  justify-content: flex-end;
  margin-top: 1rem;
}

button {
  padding: 12px 24px;
  border: none;
  border-radius: 8px;
  font-size: 1rem;
  font-weight: 500;
  cursor: pointer;
  transition: all 0.3s ease;
}

.save-btn {
  background-color: #27ae60;
  color: white;
}

.save-btn:hover {
  background-color: #219a52;
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

.cancel-btn {
  background-color: #95a5a6;
  color: white;
}

.cancel-btn:hover {
  background-color: #7f8c8d;
  transform: translateY(-2px);
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.1);
}

@media (max-width: 768px) {
  .form-container {
    margin: 1rem;
    padding: 1.5rem;
  }
  
  .button-group {
    flex-direction: column;
  }
  
  button {
    width: 100%;
  }
}
</style>