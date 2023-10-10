<template>
  <van-form @submit="onSubmit">
    <van-cell-group inset>
      <van-field
          v-model="addTeamData.name"
          name="name"
          label="队伍名称"
          placeholder="请输入队伍名称"
          :rules="[{ required: true, message: '请填写队伍名称' }]"
      />
      <van-field
          v-model="addTeamData.description"
          name="description"
          type="textarea"
          rows="4"
          autosize
          label="队伍描述"
          placeholder="请描述您的队伍"
          :rules="[{ required: true, message: '请填写队伍描述' }]"
      />
      <van-field
          v-model="addTeamData.expireTime"
          is-link
          readonly
          name="datetimePicker"
          label="过期时间"
          placeholder="点击选择过期时间"
          @click="showPicker = true"
      >
        <van-popup v-model:show="showPicker" position="bottom">
          <van-datetime-picker
                           type="time"
                           title="请选择过期时间"
                           :min-date="new Date()"/>
        </van-popup>
      </van-field>

      <van-field name="stepper" label="最大人数">
        <template #input>
          <van-stepper v-model="addTeamData.maxNum"/>
        </template>
      </van-field>

      <van-field name="radio" label="队伍状态">
        <template #input>
          <van-radio-group v-model="addTeamData.status" direction="horizontal">
            <van-radio name="0">公开</van-radio>
            <van-radio name="1">私有</van-radio>
            <van-radio name="2">加密</van-radio>
          </van-radio-group>
        </template>
      </van-field>

      <van-field
          v-if="addTeamData.status == 2"
          v-model="addTeamData.password"
          type="password"
          name="password"
          label="队伍密码"
          placeholder="请输入队伍密码"
          :rules="[{ required: true, message: '请填写队伍密码' }]"
      />


    </van-cell-group>
    <div style="margin: 16px;">
      <van-button round block type="primary" native-type="submit">
        提交
      </van-button>
    </div>
  </van-form>

</template>

<script setup>
import {ref} from "vue";

const initFormData = {
  "name": "",
  "description": "",
  "expireTime": "",
  "maxNum": 0,
  "password": "",
  "status": 0,
  "userId": 0,
}

// 展示日期选择器
const showPicker = ref(false)

// 用户填写的表单数据
const addTeamData = ref({...initFormData})

const onSubmit = () => {

}
</script>

<style scoped>

</style>