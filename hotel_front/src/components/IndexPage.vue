
<template>
  <div class="background-container">
    <div class="container">
      <h1 class="title">
        
        <text class="title-content">
          <img src="/src/static/hotel.png" alt="酒店图标" class="title-icon" >
          欢迎来到酒店管理系统
          <!-- <img src="C:\Users\txj\Desktop\酒店\hotel_front\src\static\hotel.png" alt="系统标题" class="title-image"> -->
        </text>
      </h1>
      <nav class="nav-container">
        <ul class="nav-list">
          <li class="nav-item">
            <router-link to="/orders" class="nav-link">订单管理</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/members" class="nav-link">会员管理</router-link>
          </li>
          <li class="nav-item">
            <router-link to="/room-types" class="nav-link">房间类型管理</router-link>
          </li>
        </ul>
      </nav>
    </div>
  </div>
</template>

<script setup>
</script>

<style scoped>
.title {
  display: flex;
  align-items: center;
  flex-direction: column;
  gap: 1rem;
}

.title-icon {
  width: 50px;
  height: 50px;
  /* margin-bottom: 1rem; */
}

.title-content {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 0.5rem;
}

.title-image {
  max-width: 300px;
  height: auto;
  margin-top: 0.5rem;
}
.background-container {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  z-index: -1;
  background-image: url('/src/static/VCG211347120608.webp'); /* 修改图片路径 */
  background-size: cover;
  background-position: center;
  background-repeat: no-repeat;
  background-attachment: fixed;
}
.container {
  background: rgba(255, 255, 255, 0.9);
  max-width: 600px;
  height: 450px;
  margin: 100px auto;
  padding: 2rem;
  background: linear-gradient(135deg, #f8f9fa 0%, #e9ecef 100%);
  border-radius: 15px;
  box-shadow: 0 10px 30px rgba(0,0,0,0.1);
}


.nav-container {
  background: white;
  border-radius: 12px;
  padding: 2rem;
  margin: 0 auto;
  width: fit-content;
  box-shadow: 0 4px 6px rgba(0,0,0,0.05);
}

.nav-list {
  display: flex;
  flex-direction: column;  /* 改为垂直排列 */
  gap: 1.5rem;            /* 调整垂直间距 */
  list-style: none;
  padding: 0;
  margin: 0;
  align-items: center;     /* 居中对齐 */
}

.nav-item {
  transition: transform 0.2s ease;
}

.nav-item:hover {
  transform: translateY(-2px);
}

.nav-link {
  display: block;
  padding: 1.2rem 2rem;   /* 增加垂直内边距 */
  text-decoration: none;
  color: #fff;
  background: linear-gradient(45deg, #74b9ff, #0984e3);
  border-radius: 8px;
  font-weight: 600;
  transition: all 0.3s ease;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  width: 200px;            /* 固定按钮宽度 */
  text-align: center;      /* 文字居中对齐 */
}

.nav-link:hover {
  background: linear-gradient(45deg, #0984e3, #74b9ff);
  box-shadow: 0 4px 8px rgba(0,0,0,0.2);
  transform: scale(1.05);
}

/* 响应式设计 */
@media (max-width: 768px) {
  .container {
    margin: 2rem auto;
    padding: 1.5rem;
  }
  
  .nav-link {
    width: 180px;
    padding: 1rem 1.5rem;
  }
  .title-icon {
    width: 60px;
    height: 60px;
  }
  
  .title-image {
    max-width: 250px;
  }
  
  .title {
    font-size: 2rem;
  }

  /* .nav-list {
    flex-direction: column;
    gap: 1rem;
  } */
}
</style>