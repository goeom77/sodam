<template>
  <div v-if="streamManager">
    <div>
      <!-- 사전 파일 -  -->
      <!-- lazy-src를 대기로 바꿀것 -->
      <v-img
        style="display:none"
        class="mx-auto"
        height="300"
        lazy-src="https://picsum.photos/id/11/100/60"
        max-width="500"
        src="https://bad.src/not/valid"
      >
        <template v-slot:placeholder>
          <div class="d-flex align-center justify-center fill-height">
            <v-progress-circular
              color="grey-lighten-4"
              indeterminate
            ></v-progress-circular>
          </div>
        </template>
      </v-img>
    </div>
    <div style="">
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