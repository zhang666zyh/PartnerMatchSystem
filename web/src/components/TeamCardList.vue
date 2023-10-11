<template>
  <van-card
      v-for="team in teamList"
      :desc="team.description"
      thumb="http://usercenter.zhangyuhang.games/img/avatar.png"
      :title="team.name"
  >
    <template #tags>
      <van-tag plain type="danger" style="margin:9px 9px 0 0">
        {{ teamStatusEnum[team.status] }}</van-tag>
    </template>
    <template #bottom>
      <div>
        最大人数: {{team.maxNum}}
      </div>
      <div>
        过期时间: {{team.expireTime}}
      </div>
      <div>
        创建时间: {{team.createTime}}
      </div>
    </template>
    <template #footer>
      <van-button size="mini" type="primary" 
                  plain @click="doJoinTeam(team.id)">
        加入队伍
      </van-button>
      <van-button v-if="team.createUser.id === currentUser?.id" 
                  size="mini" type="primary"
                  @click="doUpdateTeam(team.id)">
        更新队伍
      </van-button>
      <van-button v-if="team.createUser.id === currentUser?.id" 
                  size="mini" type="primary"
                  @click="doUpdateTeam(team.id)">
        退出队伍
      </van-button>
      <van-button v-if="team.createUser.id === currentUser?.id" 
                  size="mini" type="primary"
                  @click="doUpdateTeam(team.id)">
        解散队伍
      </van-button>
    </template>
  </van-card>
</template>

<script lang="ts" setup>
import {TeamType} from "../models/team.ts";
import {teamStatusEnum} from "../constants/team.ts"
import {defineProps, ref, onMounted} from 'vue'
import {useRouter} from 'vue-router'
import myAxios from "../plugins/myAxios";
import {Toast} from 'vant'
import { getCurrentUser } from '../services/user'

const currentUser = ref({})
const router = useRouter()

interface TeamCardListProps {
  teamList: TeamType[]
}

const props = defineProps<TeamCardListProps>();

// 初始化当前用户
onMounted(async () => {
  currentUser.value = await getCurrentUser();
})

/**
 * 加入队伍
 */
const doJoinTeam = async(id:number) => {
  const res = await myAxios.post("/team/join",{
    teamId: id
  });

  if(res.code == 0){
    Toast.success("加入成功");
  }else{
    Toast.fail("加入失败");
  }
}

// 修改队伍
const doUpdateTeam = (id:number) => {
  router.push({
    path: "/teamUpdate",
    query: {
      id
    }
  })
}

</script>

<style lang="less" scoped>

</style>