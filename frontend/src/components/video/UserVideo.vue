<template>
  <p>상담사 사이트</p>
  <div v-if="streamManager">
    <div>
      <div>
        <ov-video :stream-manager="streamManager"/>
      </div>
      <div><p>{{ clientData }}</p></div>
      <p>{{ streamManager }}</p>
    </div>
  </div>
</template>
	
<script>
import OvVideo from '@/components/video/OvVideo.vue';
export default {
  name: 'UserVideo',

  components: {
    OvVideo,
  },
  // 상담사가 누구인지 
  props: {
    streamManager: Object,
  },
  // 클라이언트 데이터가 없으면 -> 받아오는 것???
  computed: {
    clientData () {
      const { clientData } = this.getConnectionData();
      return clientData;
    },
  },

  methods: {
    getConnectionData () {
      const { connection } = this.streamManager.stream;
      return JSON.parse(connection.data);
    },
  },
};
</script>
