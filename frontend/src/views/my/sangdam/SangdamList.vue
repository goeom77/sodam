<template>
  <div  class="mt-3">
    <v-card width="100%" variant="outlined">

      <template v-slot:subtitle>
        약속 날짜 : {{ dateTime }}
      </template>
      <v-card-actions>
      <v-btn variant="outlined" @click="startMeeting">
        상담하기
      </v-btn>
    </v-card-actions>
    </v-card>
  </div>
</template>
<script>

export default {
  name:'SangdamList',
  props: ['dateTime','sessionId'],

  data() {
    return {
      id : undefined,
    }
  },

  methods: {
    startMeeting() {
      this.$router.push({name: 'VideoPage', params: {id: this.sessionId}});
    },
  }
}
</script>

<style>

</style>