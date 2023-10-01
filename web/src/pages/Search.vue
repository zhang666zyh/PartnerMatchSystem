<template>
  <form action="/">
    <van-search
        v-model="searchText"
        show-action
        placeholder="请输入搜索关键词"
        @search="onSearch"
        @cancel="onCancel"
    />
  </form>

  <van-divider content-position="left">已选标签</van-divider>
  <div v-if="activeIds.length === 0">请选择标签</div>
  <van-row gutter="16">
    <van-col v-for="tag in activeIds">
      <van-tag closeable type="primary" size="medium" @close="doClose(tag)">
        {{ tag }}
      </van-tag>
    </van-col>
  </van-row>

  <van-divider content-position="left">选择标签</van-divider>

  <van-tree-select
      v-model:active-id="activeIds"
      v-model:main-active-index="activeIndex"
      :items="tagList"
  />
  <div style="margin:12px">
    <van-button type="primary" block @click="doSearchResult">搜索</van-button>
  </div>

</template>

<script setup>
import {ref} from 'vue'
import {useRouter} from 'vue-router'

const router = useRouter()

const searchText = ref('') // 搜索框绑定数据
const activeIds = ref([]) // 已选择标签
const activeIndex = ref(0) // 当前选中标签索引
const originTagList = [
  {
    text: '性别',
    children: [
      {text: '男', id: '男'},
      {text: '女', id: '女'}
    ]
  },
  {
    text: '年纪',
    children: [
      {text: '大一', id: '大一'},
      {text: '大二', id: '大二'}
    ]
  },
] // 原始标签列表
const tagList = ref(originTagList)

// commit search
const onSearch = (val) => {
  tagList.value = originTagList.map(parentTag => {
    const tempChildren = [...parentTag.children];
    const tempParentTag = {...parentTag};
    tempParentTag.children = tempChildren.filter(item => item.text.includes(searchText.value))
    return tempParentTag;
  })

}
// cancel search
const onCancel = () => {
  console.log("search cancel")
}


// delete actived tag
const doClose = (tag) => {
  activeIds.value = activeIds.value.filter(item => item !== tag)
}

/**
 * sunmit search
 */
const doSearchResult = () => {
  router.push({
    path: "/searchResult",
    query: {
      tags: activeIds.value
    }
  })
}
</script>

<style scoped>

</style>