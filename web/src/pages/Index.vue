<template>
  <div v-if="userList.length > 0">
    <van-card
        v-for="user in userList"
        :desc="user.profile"
        :title="`${user.username} (${user.planetCode})`"
        :thumb="user.avatarUrl"
    >
      <template #tags>
        <van-tag v-for="tag in user.tags" plain type="danger" style="margin:9px 9px 0 0">{{ tag }}</van-tag>
      </template>
      <template #footer>
        <van-button size="mini">联系我</van-button>
      </template>
    </van-card>
  </div>
  <div v-else>
    <van-empty image="search" description="搜索结果为空"/>
  </div>

</template>

<script setup>
import {ref, onMounted} from 'vue'
import {useRoute} from 'vue-router'
import qs from 'qs'
import myAxios from "../plugins/myAxios";

const route = useRoute()

const userList = ref([])

onMounted(async () => {
  const userListData = await myAxios.get("/user/recommend", {
    params: {
      pageSize: 8,
      pageNum: 1
    }
  });


  if (userListData) {
    const userListRecords = userListData.data.records;
    
    userListRecords.forEach(item => {
      item.tags = JSON.parse(item.tags)
    })
    userList.value = userListRecords
  }
})


</script>

<style scoped>

</style>