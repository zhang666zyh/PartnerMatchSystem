<template>
  <van-card
      v-for="user in userList"
      :desc="user.profile"
      :title="`${user.username} (${user.planetCode})`"
      :thumb="user.avatarUrl"
  >
    <template #tags>
      <van-tag v-for="tag in user.tags" plain type="danger" style="margin:9px 9px 0 0">{{tag}}</van-tag>
    </template>
    <template #footer>
      <van-button size="mini">联系我</van-button>
    </template>
  </van-card>

</template>

<script setup>
import {ref} from 'vue'
import {useRoute} from 'vue-router'

const route = useRoute()
const {tags} = route.query

const mockUser = {
  id: 2,
  username: "yupi",
  userAccount: "yupi",
  avatarUrl: "http://usercenter.zhangyuhang.games/img/avatar.png",
  profile: "一名精神小伙, 目前还有头发, 谢谢大家",
  gender: 0,
  phone: "13390389320",
  email: "zhangzyh666@163.com",
  planetCode: "1234",
  tags: ['java', 'c++', 'python'],
  createTime: new Date(),
}
const userList = ref([mockUser])


</script>

<style scoped>

</style>