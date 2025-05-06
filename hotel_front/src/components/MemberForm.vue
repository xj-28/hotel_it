<template>
  <div class="container">
    <h1>{{ isEdit ? '编辑会员' : '新增会员' }}</h1>
    <div class="form-container">
      <input v-model="member.member_name" placeholder="会员姓名" />
      <input v-model="member.member_phone" placeholder="会员电话" />
      <input v-model="member.member_level" placeholder="会员等级" />
      <input v-model="member.points" placeholder="积分" />
      <div class="button-group">
        <button class="save-btn" @click="saveMember">保存</button>
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
const member = ref({
  member_name: '',
  member_phone: '',
  member_level: '',
  points: ''
});
const isEdit = ref(false);

const fetchMember = async (memberId) => {
  try {
    // 修改为使用代理路径
    const response = await axios.get(`/api/members/${memberId}`);
    member.value = response.data;
  } catch (error) {
    console.error('获取会员详情失败', error);
    alert(`获取会员详情失败，请稍后重试。错误信息：${error.message}`);
  }
};

const saveMember = async () => {
  try {
    // 将 points 转换为整数类型
    member.value.points = parseInt(member.value.points, 10);
    if (isEdit.value) {
      // 修改为使用代理路径
      await axios.put('/api/members', member.value);
    } else {
      // 修改为使用代理路径
      await axios.post('/api/members', member.value);
    }
    router.push('/members');
  } catch (error) {
    console.error('保存会员信息失败', error);
    alert(`保存会员信息失败，请稍后重试。错误信息：${error.message}`);
  }
};

const cancel = () => {
  router.push('/members');
};

onMounted(() => {
  if (route.params.memberId) {
    isEdit.value = true;
    fetchMember(route.params.memberId);
  }
});
</script>    


<style scoped>
.container {
  max-width: 800px;
  margin: 2rem auto;
  padding: 2rem;
  background-color: #f8f9fa;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
}

h1 {
  color: #2c3e50;
  font-size: 2rem;
  text-align: center;
  margin-bottom: 2rem;
  font-weight: 600;
}

.form-container {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

input {
  padding: 12px;
  border: 2px solid #e0e0e0;
  border-radius: 8px;
  font-size: 1rem;
  transition: all 0.3s ease;
}

input:focus {
  border-color: #3498db;
  box-shadow: 0 0 0 3px rgba(52, 152, 219, 0.1);
  outline: none;
}

input::placeholder {
  color: #95a5a6;
}

.button-group {
  display: flex;
  gap: 1rem;
  justify-content: flex-end;
  margin-top: 1.5rem;
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
}

.cancel-btn {
  background-color: #95a5a6;
  color: white;
}

.cancel-btn:hover {
  background-color: #7f8c8d;
}

@media (max-width: 768px) {
  .container {
    margin: 1rem;
    padding: 1.5rem;
  }

  input, button {
    width: 100%;
  }

  .button-group {
    flex-direction: column;
  }
}
</style>