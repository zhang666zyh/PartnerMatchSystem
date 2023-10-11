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
import {useRoute, useRouter} from "vue-router";
import {ref} from 'vue'
import myAxios from "../../plugins/myAxios";
import {getCurrentUser} from "../../services/user";
import {getCurrentUserState, setCurrentUserState} from "../../state/user";

const router = useRouter();
const route = useRoute();

const editUser = ref({
  editKey: route.query.editKey,
  editName: route.query.editName,
  currentValue: route.query.currentValue
});

const onSubmit = async () => {
  // TODO 获取当前登录用户
  const currentUser = await getCurrentUser();

  if (!currentUser) {
    return;
  }

  // TODO 把editKey、currentValue以及editName提交到后台
  const result = await myAxios.post("user/update", {
    'id': currentUser.id,
    [editUser.value.editKey as string]: editUser.value.currentValue,
  });

  if (result.code === 0 && result.data > 0) {
    console.log("修改成功")
    setCurrentUserState({
          ...getCurrentUserState(),
          [editUser.value.editKey as string]: editUser.value.currentValue,
        }
    )
    router.back();
  } else {
    console.log("修改错误")
  }
}

</script>