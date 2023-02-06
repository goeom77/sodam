<template>
  <div style="width: 100%">
    <p>1. Basic usage</p>
    <div class="date">
      <font-awesome-icon
        icon="fa-solid fa-calendar-days"
        transform="down-2.5 right-20"
        style="z-index: 1; cursor: default"

      />
      <Datepicker
        v-model="picked"
        class="datepicker"
        :locale="locale"
        :weekStartsOn="0"
        :inputFormat="inputFormat"
        :clearable="true"
      />
      <button type="button" @click="getCalValue('dp1')">Get Value</button>
    </div>
    <hr />
  </div>
</template>

<script>
import { ref, reactive, defineComponent } from 'vue';
// vue3-datepicker
import Datepicker from 'vue3-datepicker';
import { ko } from 'date-fns/locale';

export default defineComponent({
  name: 'App',
  components: {
    Datepicker,
  },
  setup() {
    // :weekStartsOn="0" 'Sunday' is first
    const picked = ref(new Date());
    const locale = reactive(ko);
    const inputFormat = ref('yyyy-MM-dd');

    

    return {
      picked,
      locale,
      inputFormat,
      // datepicker1,

      getCalValue,
      dp2From,
      dp2To,
      dp2,
      setOldValue,
      validateFromTo,
      dp3,
      isTodayOver,
    };
  },
});
</script>

<style scoped>
div {
  text-align: center;
}
div.date {
  display: inline-flex;
}
</style>