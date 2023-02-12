<template>
  <div v-if="streamManager">
      <!-- 사전 파일 -  -->
      <!-- lazy-src를 대기로 바꿀것 -->
    <div>
      <ov-video
        :stream-manager="streamManager"
        :test=true
      />
    </div>
    <div><p>{{ clientData }}</p></div>
  </div>
  </template>
  
  <script>
  import OvVideo from './OvVideo';
  
  export default {
    name: 'UserVideo',
  
    components: {
      OvVideo,
    },
  
    props: {
      streamManager: Object,
    },
  
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