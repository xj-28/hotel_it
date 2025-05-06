<template>
  <div class="form-container">
    <h1 class="form-title">{{ isEdit ? '编辑房间类型' : '新增房间类型' }}</h1>
    <div class="form-content">
      <div class="form-group">
        <label>房间类型名称</label>
        <input v-model="roomType.typeName" placeholder="请输入房间类型名称" />
      </div>
      <div class="form-group">
        <label>价格</label>
        <input v-model="roomType.price" placeholder="请输入价格" type="number" />
      </div>
      <div class="form-group">
        <label>描述</label>
        <textarea v-model="roomType.description" placeholder="请输入房间类型描述" rows="4"></textarea>
      </div>
      <div class="button-group">
        <button class="save-btn" @click="saveRoomType">保存</button>
        <button class="cancel-btn" @click="cancel">取消</button>
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRouter, useRoute } from 'vue-router';

const router = useRouter();
const route = useRoute();
const roomType = ref({
  typeName: '',
  price: '',
  description: ''
});
const isEdit = ref(false);

const fetchRoomType = async (roomTypeId) => {
  try {
    // 获取存储在本地的 token
    const token = localStorage.getItem('token');
    
    // 修改为使用代理路径，并添加认证头
    const response = await axios.get(`/api/room-types/${roomTypeId}`, {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    });
    roomType.value = response.data;
  } catch (error) {
    console.error('获取房间类型详情失败', error);
    // 如果是 401 错误，可能是 token 过期或无效，重定向到登录页
    if (error.response && error.response.status === 401) {
      alert('登录已过期，请重新登录');
      router.push('/login');
      return;
    }
    alert('获取房间类型详情失败，请稍后重试');
  }
};

const saveRoomType = async () => {
  try {
    // 获取存储在本地的 token
    const token = localStorage.getItem('token');
    
    // 设置请求头
    const config = {
      headers: {
        'Authorization': `Bearer ${token}`
      }
    };
    
    if (isEdit.value) {
      // 使用代理路径
      await axios.put('/api/room-types', roomType.value, config);
    } else {
      // 使用代理路径
      await axios.post('/api/room-types', roomType.value, config);
    }
    router.push('/room-types');
  } catch (error) {
    console.error('保存房间类型信息失败', error);
    // 如果是 401 错误，可能是 token 过期或无效，重定向到登录页
    if (error.response && error.response.status === 401) {
      alert('登录已过期，请重新登录');
      router.push('/login');
      return;
    }
    alert('保存房间类型信息失败，请稍后重试');
  }
};

const cancel = () => {
  router.push('/room-types');
};

onMounted(() => {
  if (route.params.roomTypeId) {
    isEdit.value = true;
    fetchRoomType(route.params.roomTypeId);
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

input, textarea {
  padding: 12px;
  border: 2px solid #e0e0e0;
  border-radius: 8px;
  font-size: 1rem;
  transition: all 0.3s ease;
  font-family: inherit;
}

input:focus, textarea:focus {
  border-color: #3498db;
  box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.1);
  outline: none;
}

textarea {
  resize: vertical;
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