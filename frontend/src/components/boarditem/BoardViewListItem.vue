<template>
  <div id="BoardViewListItem" class="flex-container space-between" v-if="(limit-1)*5 <= index && index < limit * 5">
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

  </div>
</template>

<script>

export default {
  name: 'BoardViewListItem',
  props: {
    Boardarticle: Object,
    index: Number,
    limit: Number,
  },
  methods: {

  }
}
</script>

<style>
#BoardViewListItem {
  margin-left: 60px;
  margin-right: 60px;
}

.flex-container{
            display: flex;
            padding: 10px;
            border: 1px solid lightgray;
            border-radius: 10px;
            margin:5px;
            background-color:SEASHELL;
        }
        .flex-container.flex-start{justify-content: flex-start;}
        .flex-container.flex-end{justify-content: flex-end;}
        .flex-container.center{justify-content: center;}
        .flex-container.space-around{justify-content: space-around;}
        .flex-container.space-between{justify-content: space-between;}
        .flex-container.space-evenly{justify-content: space-evenly;}
</style>

