<template>
  <div class="room-type-container">
    <h1 class="page-title">房间类型列表</h1>
    <div v-if="loading" class="loading">加载中...</div>
    <div v-else-if="roomTypes.length === 0" class="no-data">暂无房间类型数据</div>
    <div v-else class="table-wrapper">
      <table class="room-type-table">
        <thead>
          <tr>
            <th>房间类型 ID</th>
            <th>房间类型名称</th>
            <th>价格</th>
            <th>描述</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="roomType in roomTypes" :key="roomType.id">
            <td>{{ roomType.id }}</td>
            
            <td>{{ roomType.typeName }}</td>
            <td>¥{{ roomType.price }}</td>
            <td>{{ roomType.description }}</td>
            <td>
              <div class="action-buttons">
                <button class="edit-btn" @click="editRoomType(roomType.id)">编辑</button>
                <button class="edit-btn" @click="editRoomType(roomType.id)">详情</button> 
                <button class="delete-btn" @click="deleteRoomType(roomType.id)">删除</button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <button class="add-button" @click="addRoomType">新增房间类型</button>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

const roomTypes = ref([]);
const router = useRouter();
const loading = ref(true);

const fetchRoomTypes = async () => {
  loading.value = true;
  try {
    const response = await axios.get('/api/room-types');
    console.log('房间类型数据:', response.data);
    roomTypes.value = response.data;
  } catch (error) {
    console.error('获取房间类型列表失败', error);
    alert('获取房间类型列表失败，请稍后重试');
  } finally {
    loading.value = false;
  }
};

  const deleteRoomType = async (roomTypeId) => {
    try {
      // 修改为使用代理路径
      await axios.delete(`/api/room-types/${roomTypeId}`);
      fetchRoomTypes();
    } catch (error) {
      console.error('删除房间类型失败', error);
      alert('删除房间类型失败，请稍后重试');
    }
  };
  
  const addRoomType = () => {
    router.push('/room-types/add');
  };
  
  const editRoomType = (roomTypeId) => {
    router.push(`/room-types/edit/${roomTypeId}`);
  };
  
  onMounted(() => {
    fetchRoomTypes();
  });
</script>

<style scoped>
.room-type-container {
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

.room-type-table {
  width: 100%;
  border-collapse: collapse;
}

.room-type-table th,
.room-type-table td {
  padding: 1.2rem 1.5rem;
  text-align: center;
}

.room-type-table th {
  background-color: #3498db;
  color: white;
  font-weight: 600;
  text-transform: uppercase;
  letter-spacing: 0.5px;
}

.room-type-table tr:nth-child(even) {
  background-color: #f8f9fa;
}

.room-type-table tr:hover {
  background-color: #e8f4fc;
  transition: background-color 0.2s;
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

@media (max-width: 768px) {
  .room-type-container {
    margin: 1rem;
    padding: 1.5rem;
  }

  .room-type-table th,
  .room-type-table td {
    padding: 0.8rem;
    font-size: 0.9rem;
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
/* 添加加载和无数据状态的样式 */
.loading, .no-data {
  text-align: center;
  padding: 2rem;
  font-size: 1.2rem;
  color: #7f8c8d;
  background-color: white;
  border-radius: 10px;
  margin-bottom: 2rem;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
}
</style>