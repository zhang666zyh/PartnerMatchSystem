<template>
  <van-search v-model="searchText" placeholder="搜索队伍" @search="onSearch" />
  <TeamCardList :teamList="teamList" />
  <van-button type="primary" @click="doAddTeam">创建队伍</van-button>
  <van-empty v-if="teamList.length == 0" image="search" description="搜索结果为空"/>
</template>

<script setup>
import {ref, onMounted} from 'vue'
import {useRouter} from "vue-router"
import TeamCardList from "../../components/TeamCardList.vue"
import myAxios from "../../plugins/myAxios"
import {Toast} from 'vant'

const router = useRouter()

const doAddTeam = () => {
  router.push({
    path: "/teamAdd"
  })
}

const teamList = ref([]);
const searchText = ref("");

// 获取队伍数据(init or search)
const getListTeam = async (val = "") => {
  const res = await myAxios.get("/team/list", {
    params: {
      searchText: val
    }
  });
  if(res.code === 0){
    teamList.value = res.data;
    Toast.success("加载成功");
  }else{
    Toast.fail("加载失败, 请刷新重试");
  }
}

onMounted(() => {
  getListTeam()
})

const onSearch = (val) => {
  getListTeam(val)
}
</script>