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
    <van-empty image="search" description="搜索结果为空" />
  </div>

</template>

<script setup>
import {ref, onMounted} from 'vue'
import {useRoute} from 'vue-router'
import qs from 'qs'
import myAxios from "../plugins/myAxios";

const route = useRoute()
const {tags} = route.query

//
// const mockUser = {
//   id: 2,
//   username: "yupi",
//   userAccount: "yupi",
//   avatarUrl: "http://usercenter.zhangyuhang.games/img/avatar.png",
//   profile: "一名精神小伙, 目前还有头发, 谢谢大家",
//   gender: 0,
//   phone: "13390389320",
//   email: "zhangzyh666@163.com",
//   planetCode: "1234",
//   tags: ['java', 'c++', 'python'],
//   createTime: new Date(),
// }
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

  console.log(userListData)

  if (userListData) {
    // userListData.data.tags = JSON.parse(userListData.data.tags)
    userListData.data.data.forEach(item => {
      item.tags = JSON.parse(item.tags)
    })
    userList.value = userListData.data.data
  }
})


</script>

<style scoped>

</style>