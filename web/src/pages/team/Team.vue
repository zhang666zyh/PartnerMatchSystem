<template>
  <van-button type="primary" @click="doAddTeam">创建队伍</van-button>
</template>

<script setup>
import {useRouter} from "vue-router"

const router = useRouter()

const doAddTeam = () => {
  router.push({
    path: "/teamAdd"
  })
}
</script>

<style scoped>

</style>