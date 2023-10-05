<template>
  <div v-if="userList.length > 0">
    <UserCardList :userList="userList"/>
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
import UserCardList from "../components/UserCardList.vue";

const route = useRoute()
const {tags} = route.query

const userList = ref([])

onMounted(async () => {
  const userListData = await myAxios.get("/user/search/tags", {
    params: {
      tagNameList: tags
    },
    paramsSerializer: params => {
      return qs.stringify(params, {indices: false})
    }
  });


  if (userListData) {
    userListData.data.forEach(item => {
      item.tags = JSON.parse(item.tags)
    })
    userList.value = userListData.data
  }
})


</script>

<style scoped>

</style>