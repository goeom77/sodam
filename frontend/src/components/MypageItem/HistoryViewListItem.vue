<template>
  <div id="HistoryViewListItem">
    <div v-if="HistoryViewarticle.content">
      <div id="HistoryViewListItembox1">
        <h4>완료</h4>
      </div>
      <div>
        <div id="HistoryViewListItembox2">
          <!-- <p>{{ HistoryViewarticle.content }}</p> -->
        </div>
        <div id="HistoryViewListItembox3">
          <!-- <p>{{ HistoryViewarticle.consultantname }} 상담사    {{ HistoryViewarticle?.starttime }}</p> -->
        </div>
      </div>
      <div id="HistoryViewListItembox4">
        <p>상담 완료</p>
      </div>
    </div>
    <div v-else>
      <div id="HistoryViewListItembox1">
        <h4 style="color: #579BB1;">예정</h4>
      </div>
      <div>
        <div id="HistoryViewListItembox2">
          <!-- <p>아직 상담이 진행되지 않았습니다.</p> -->
        </div>
        <div id="HistoryViewListItembox3">
          <!-- <p>{{ HistoryViewarticle.consultantname }} 상담사    {{ HistoryViewarticle?.starttime }}</p> -->
        </div>
      </div>
      <div id="HistoryViewListItembox4">
        <router-link :to="{ name: 'VideoPage', params: { id: HistoryViewarticle.id,  } }">
          상담하기
        </router-link>
      </div>
    </div>
  </div>
</template>

<script>

export default {
  name: 'HistoryViewListItem',
  // props: {
  //   HistoryViewarticle: Object,
  // }
}
</script>

<style>

</style>