<template>
  <van-nav-bar
      title="标题"
      left-text="返回"
      right-text="按钮"
      left-arrow
      @click-left="onClickLeft"
      @click-right="onClickRight"
      :fixed="true"
      ref="headerRef"
  >
    <template #right>
      <van-icon name="search" size="18"/>
    </template>
  </van-nav-bar>

  <div id="content"
       :style="{
                'padding-bottom': footerHeight + 'px',
                'padding-top': headerHeight + 'px'
        }">
    <router-view></router-view>
  </div>

  <van-tabbar v-model="active" @change="onChange" ref="footerRef">
    <van-tabbar-item icon="home-o" name="">主页</van-tabbar-item>
    <van-tabbar-item icon="search" name="team">队伍</van-tabbar-item>
    <van-tabbar-item icon="friends-o" name="user">个人</van-tabbar-item>
  </van-tabbar>

</template>

<script setup>
import {onMounted, ref} from 'vue'
import {useRouter} from 'vue-router'

const router = useRouter()
const headerRef = ref(null)
const footerRef = ref(null)
const headerHeight = ref(50)
const footerHeight = ref(50)

onMounted(() => {
  headerHeight.value = headerRef.value.$el.clientHeight;
  footerHeight.value = footerRef.value.$el.clientHeight;
})

// nav's left and right click event
const onClickLeft = () => {
  router.back()
}
const onClickRight = () => {
  router.push("/search")
}

// tabbar changed
const active = ref('');
const onChange = (index) => {
  router.push(`/${index}`)
}
</script>

<style scoped>

</style>