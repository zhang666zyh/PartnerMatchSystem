<template>
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
</template>

<script lang="ts" setup>
import {UserType} from "../models/user";
import {defineProps} from 'vue'

interface UserCardListProps {
  userList: UserType[]
}

const props = defineProps<UserCardListProps>();


</script>

<style lang="less" scoped>

</style>