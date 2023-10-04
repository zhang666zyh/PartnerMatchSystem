<template>
  <van-form @submit="onSubmit">
    <van-field v-model="editUser.currentValue"
               :name="editUser.editKey"
               :label="editUser.editName"
    />
    <div style="margin: 16px">
      <van-button round block type="primary" native-type="submit">提交</van-button>
    </div>
  </van-form>
</template>

<script lang="ts" setup>
import {useRouter, useRoute} from "vue-router";
import {ref} from 'vue'
import myAxios from "../plugins/myAxios";

const router = useRouter();
const route = useRoute();

const editUser = ref({
  editKey: route.query.editKey,
  editName: route.query.editName,
  currentValue: route.query.currentValue
});

const onSubmit = async () => {
  // TODO 把editKey、currentValue以及editName提交到后台
  const result = await myAxios.post("user/update", {
    'id': 1,
    [editUser.value.editKey as string]: editUser.value.currentValue,
  });

  if (result.code === 0 && res.data > 0) {
    console.log("修改成功")
    router.back();
  }else{
    console.log("修改错误")
  }
}

</script>