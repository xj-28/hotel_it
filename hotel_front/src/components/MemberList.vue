<template>
  <div class="member-container">
    <h1 class="page-title">会员管理系统</h1>
    <div class="table-wrapper">
      <table class="member-table">
        <thead>
          <tr>
            <th>会员 ID</th>
            <th>会员姓名</th>
            <th>会员电话</th>
            <th>会员等级</th>
            <th>积分</th>
            <th>注册日期</th>
            <th>操作</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="member in members" :key="member.member_id">
            <td>{{ member.member_id }}</td>
            <td>{{ member.member_name }}</td>
            <td>{{ member.member_phone }}</td>
            <td>{{ member.member_level }}</td>
            <td>{{ member.points }}</td>
            <td>{{ formatDate(member.registration_date) }}</td>
            <td>
              <div class="action-buttons">
                <button class="edit-btn" @click="editMember(member.member_id)">编辑</button>
                <button class="delete-btn" @click="deleteMember(member.member_id)">删除</button>
              </div>
            </td>
          </tr>
        </tbody>
      </table>
    </div>
    <button class="add-button" @click="addMember">新增会员</button>
  </div>
</template>
  

  <style scoped>
  .member-container {
    max-width: 1200px;
    margin: 0 auto;
    padding: 2rem;
    font-family: 'Segoe UI', system-ui, sans-serif;
  }
  
  .page-title {
    text-align: center;
    color: #2c3e50;
    font-size: 2.5rem;
    margin-bottom: 2rem;
    letter-spacing: 2px;
  }
  
  .table-wrapper {
    border-radius: 12px;
    box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
    overflow: hidden;
    margin-bottom: 2rem;
  }
  
  .member-table {
    width: 100%;
    border-collapse: collapse;
    background: white;
  }
  
  .member-table th,
  .member-table td {
    padding: 1.2rem 1.5rem;
    text-align: center;
  }
  
  .member-table th {
    background-color: #3498db;
    color: white;
    font-weight: 600;
    text-transform: uppercase;
    letter-spacing: 0.5px;
  }
  
  .member-table tr:nth-child(even) {
    background-color: #f8f9fa;
  }
  
  .member-table tr:hover {
    background-color: #f1f5f9;
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
    text-transform: uppercase;
    letter-spacing: 0.5px;
  }
  
  .add-button {
    background-color: #27ae60;
    color: white;
    font-size: 1.1rem;
    padding: 1rem 2rem;
    display: block;
    margin: 0 auto;
  }
  
  .add-button:hover {
    background-color: #219a52;
    transform: translateY(-1px);
    box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  }
  
  .edit-btn {
    background-color: #2980b9;
    color: white;
  }
  
  .edit-btn:hover {
    background-color: #2472a3;
  }
  
  .delete-btn {
    background-color: #e74c3c;
    color: white;
  }
  
  .delete-btn:hover {
    background-color: #c0392b;
  }
  
  @media (max-width: 768px) {
    .member-container {
      padding: 1rem;
    }
  
    .member-table th,
    .member-table td {
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
  </style>



  <script setup>
  import { ref, onMounted } from 'vue';
  import axios from 'axios';
  import { useRouter } from 'vue-router';

  const members = ref([]);
  const router = useRouter();

  // 日期格式化函数
  const formatDate = (dateString) => {
    const options = { year: 'numeric', month: '2-digit', day: '2-digit' };
    return new Date(dateString).toLocaleDateString('zh-CN', options);
  };
  
  
  const fetchMembers = async () => {
    try {
      // 修改为使用代理路径
      const response = await axios.get('/api/members');
      members.value = response.data;
    } catch (error) {
      console.error('获取会员列表失败', error);
      alert('获取会员列表失败，请稍后重试');
    }
  };
  
  const deleteMember = async (memberId) => {
    try {
      // 修改为使用代理路径
      await axios.delete(`/api/members/${memberId}`);
      fetchMembers();
    } catch (error) {
      console.error('删除会员失败', error);
      alert('删除会员失败，请稍后重试');
    }
  };
  
  const addMember = () => {
    router.push('/members/add');
  };
  
  const editMember = (memberId) => {
    router.push(`/members/edit/${memberId}`);
  };
  
  onMounted(() => {
    fetchMembers();
  });
  </script>