<template>
  <div>
    <h1>管理员登录</h1>
    <input v-model="username" placeholder="用户名" />
    <input v-model="password" type="password" placeholder="密码" />
    <button @click="login">登录</button>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import axios from 'axios';
import { useRouter } from 'vue-router';

const username = ref('');
const password = ref('');
const router = useRouter();

const login = async () => {
  try {
    // 创建表单数据
    const formData = new URLSearchParams();
    formData.append('admin_username', username.value);
    formData.append('admin_password', password.value);

    // 使用代理路径，而不是直接访问后端地址
    const response = await axios.post('/api/admin/login', formData, {
      headers: {
        'Content-Type': 'application/x-www-form-urlencoded'
      }
    });

    if (response.data.includes('登录成功')) {
      // 登录成功，跳转到订单列表页面
      router.push('/index');
    } else {
      alert('用户名或密码错误');
    }
  } catch (error) {
    console.error('登录失败', error);
    alert('登录失败，请稍后重试');
  }
};
</script>


<style scoped>
/* 容器样式 */
div {
  max-width: 400px;
  margin: 100px auto;
  padding: 2rem;
  box-shadow: 0 0 15px rgba(0, 0, 0, 0.1);
  border-radius: 10px;
  background-color: #fff;
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

/* 标题样式 */
h1 {
  text-align: center;
  color: #2c3e50;
  font-size: 1.8rem;
  margin-bottom: 1rem;
}

/* 输入框通用样式 */
input {
  width: 95%;
  padding: 12px;
  
  border: 2px solid #e0e0e0;
  border-radius: 6px;
  font-size: 1rem;
  transition: border-color 0.3s ease;
}

input:focus {
  outline: none;
  border-color: #3498db;
}

input::placeholder {
  color: #95a5a6;
}

/* 密码输入框特定样式 */
input[type="password"] {
  letter-spacing: 2px;
}

/* 登录按钮样式 */
button {
  background-color: #3498db;
  color: white;
  padding: 12px;
  border: none;
  border-radius: 6px;
  font-size: 1.1rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #2980b9;
}

button:active {
  transform: scale(0.98);
}

/* 响应式设计 */
@media (max-width: 480px) {
  div {
    margin: 50px 20px;
    padding: 1.5rem;
  }
  
  h1 {
    font-size: 1.5rem;
  }
}
</style>