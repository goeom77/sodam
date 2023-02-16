<template>
  <div v-if="(limit-1)*10 <= index && index < limit * 10">
  <div class="container BoardCard">
    <div class="row" @click="moveTo" style="cursor: pointer;">
      <div class="col-1" style="text-align: center; margin:auto;">
        {{ Boardarticle.category }}
      </div>
      <div class="col-7" style="margin:auto; padding-left: 30px;">
        {{ Boardarticle.title }}
        <br>
        {{ Boardarticle.clientId }}
      </div>
      <div class="col-1" style="text-align: center; margin:auto;">
        {{ Boardarticle.commentCount }}
      </div>
      <div class="col-1" style="text-align: center; margin:auto;">
        {{ Boardarticle.views }}
      </div>
      <div class="col-2" style="text-align: center; margin:auto;">
        {{ this.date }}
      </div>
    </div>
  </div>
  </div>
  <!-- <div id="BoardViewListItem" class="flex-container space-between" v-if="(limit-1)*5 <= index && index < limit * 5">
    <div>
      <h5>{{ Boardarticle.postId }}</h5>
    </div>
    <div >
      <div>
        <router-link 
          style="color:black" 
          :to="{ 
            name: 'BoardDetailView', 
            params: { postId: Boardarticle.postId } 
          }"
        >
          {{ Boardarticle.title }}
        </router-link>
       
      </div>
      <div>
        <p>{{ Boardarticle.clientId }}  {{ Boardarticle?.createdAt }}</p>
      </div>
    </div>
    <div >
      <h3>{{ Boardarticle.views }}, {{ Boardarticle.commentCount }}</h3>
    </div>
    <hr>

  </div> -->
</template>

<script>

export default {
  name: 'BoardViewListItem',
  props: {
    Boardarticle: Object,
    index: Number,
    limit: Number,
  },
  data(){
    return{
      date: this.Boardarticle.createdAt.split(' ')[0]
    }
  },
  methods: {
    moveTo(){
      this.$router.push({name:'BoardDetailView', params: { postId: this.Boardarticle.postId }  })
    }
  }
}
</script>

<style>
#BoardViewListItem {
  margin-left: 60px;
  margin-right: 60px;
}
.BoardCard{
  border-bottom: 1px solid #ccc;
  padding:20px;
}

.flex-container{
            display: flex;
            padding: 10px;
            border: 1px solid lightgray;
            border-radius: 10px;
            margin:5px;
            background-color:white;
        }
        .flex-container.flex-start{justify-content: flex-start;}
        .flex-container.flex-end{justify-content: flex-end;}
        .flex-container.center{justify-content: center;}
        .flex-container.space-around{justify-content: space-around;}
        .flex-container.space-between{justify-content: space-between;}
        .flex-container.space-evenly{justify-content: space-evenly;}
</style>

