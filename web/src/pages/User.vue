<template>
  <template v-if="user">
    <van-cell title="昵称" is-link to="/user/edit" :value="user.username"/>
    <van-cell title="账号" :value="user.userAccount"/>
    <van-cell title="头像" is-link to="/user/edit">
      <div class="avatar">
        <img style="width:50px;height: 50px;border-radius: 50%" :src="user.avatarUrl" alt="#"/>
      </div>
    </van-cell>
    <van-cell title="性别" is-link :value="user.gender" @click="toEdit('gender','性别', user.gender)"/>
    <van-cell title="电话" is-link :value="user.phone" @click="toEdit('phone','电话', user.phone)"/>
    <van-cell title="邮箱" is-link to="/user/edit" :value="user.email"/>
    <van-cell title="星球编号" :value="user.planetCode"/>
    <van-cell title="注册时间" :value="user.createTime"/>
  </template>
</template>

<script lang="ts" setup>
import {useRouter} from "vue-router";
import {onMounted, ref} from "vue";
import myAxios from "../plugins/myAxios";

const router = useRouter();
const user = ref({})

onMounted(async() => {
  const res = await myAxios.get("/user/current");

  if (res.code === 0) {
    user.value = res.data;
  } else {
    console.log("获取用户信息失败")
    router.push("/userLogin")
  }
})


const toEdit = (editKey: string, editName: string, currentValue: string) => {
  router.push({
    path: "/editUser",
    query: {
      editKey,
      editName,
      currentValue
    }
  })
}
</script>

<style lang="less" scoped>
</style>