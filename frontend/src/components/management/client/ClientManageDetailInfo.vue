<template>
  --------------
  {{ id }}
</template>

<script>
import axios from 'axios'
export default {
  name:'ClientManageDetailInfo',
  data(){
    return {

    }
  },
  props:{
    id:String
  },
  methods:{
    getUserData(){
      axios({
        method:'get'
      })
    }
  }
}
</script>

<style>

</style>