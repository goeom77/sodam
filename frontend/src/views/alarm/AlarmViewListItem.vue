<template>
  <div id="AlarmViewListItem" v-if="(limit-1)*10 <= index && index < limit * 10">
    <div v-if="AlarmViewarticle.read === true" style="float:left; margin-left: 60px;">
      <h5>확인</h5>
    </div>
    <div v-else  style="float:left; margin-left: 60px;">
      <h5>미확인</h5>
    </div>
    <div >
      <div>
        <button @click="AlarmViewDetail">

          {{ AlarmViewarticle.title }}
        </button>
        <!-- <button style="color:black" @click="clickList">{{ AlarmViewarticle.title }}</button> -->
      </div>
      <div>
        <p>관리자   {{ AlarmViewarticle?.createdAt }}</p>
      </div>
    </div>
    <div>
      <button @click="AlarmViewarticleDelete">알림 삭제</button>
    </div>
    <hr>

  </div>
</template>

<script>
import axios from 'axios'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default {
  name: 'AlarmViewListItem',
  props: {
    AlarmViewarticle: Object,
    index: Number,
    limit: Number,
  },
  data() {
    return {
      // read: false,
    }
  },
  methods: {
    AlarmViewDetail() {
      axios({
        method: 'get',
        url: `${VUE_APP_API_URL}/api/my-page/notification/${this.AlarmViewarticle.id}`,
        headers: { 
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(() => {
        this.$swal(this.AlarmViewarticle.content);
        this.$emit('refresh-alarm')
        // this.read=true
      })
      .catch((err) => {
        console.log('안됨')
      })
    },
    
    AlarmViewarticleDelete() {
      axios({
        method: 'delete',
        url: `${VUE_APP_API_URL}/api/my-page/notification/${this.AlarmViewarticle.id}`,
        headers: { 
          Authorization : `Bearer ${this.$store.state.token.token.access_token}`
        }
      })
      .then(() => {
        console.log('됨')
        this.$emit('delete-alarm')
        // this.$router.push({ name: 'AlarmViewList' });
      })
      .catch((err) => {
        console.log('안됨')
        console.log(err)
      })
    },
  }
}
</script>

<style>
#AlarmViewListItem {
  /* width: 1255px; */
  margin-left: 60px;
  margin-right: 60px;
}
</style>