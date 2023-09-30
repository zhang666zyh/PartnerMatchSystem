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

const router = useRouter();
const route = useRoute();

const editUser = ref({
  editKey: route.query.editKey,
  editName: route.query.editName,
  currentValue: route.query.currentValue
});

const onSubmit = (values) => {
  // TODO 把editKey、currentValue以及editName提交到后台

}

</script>