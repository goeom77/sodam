<script>
import { defineComponent } from 'vue'
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin, { Draggable } from '@fullcalendar/interaction'
import {INITIAL_EVENTS, createEventId} from './event-utils'
import axios from 'axios'
const VUE_APP_API_URL = process.env.VUE_APP_API_URL

export default defineComponent({
  components: {
    FullCalendar,
  },
  data() {
    return {
      calendarOptions: {
        plugins: [
          dayGridPlugin,
          timeGridPlugin,
          interactionPlugin // needed for dateClick
        ],
        headerToolbar: {
          left: 'prev,next today',
          center: 'title',
          right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        initialView: 'dayGridMonth',
        initialEvents: INITIAL_EVENTS, // alternatively, use the `events` setting to fetch from a feed
        editable: true, 
        selectable: true,
        selectMirror: true,
        dayMaxEvents: true,
        weekends: true,
        select: this.handleDateSelect,
        eventClick: this.handleEventClick,
        eventsSet: this.handleEvents,
        droppable: true,
        locale:'ko'
        /* you can update a remote database when these fire:
        eventAdd:
        eventChange:
        eventRemove:
        */
      },
      currentEvents: [],
    }
  },
  methods: {
    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
    },
    handleDateSelect(selectInfo) {
      let title = prompt('상담 일정을 입력해주세요')
      let calendarApi = selectInfo.view.calendar

      calendarApi.unselect() // clear date selection

      if (title) {
        calendarApi.addEvent({
          id: createEventId(),
          title,
          start: selectInfo.startStr,
          end: selectInfo.endStr,
          allDay: selectInfo.allDay
        })
      }

      // axios({
      //   method:'post',
      //   url:`${VUE_APP_API_URL}/api/schedule/search`,
      //   data:{
      //     "userId": this.$store.state.payload.id,
      //   }
      // })
      // .then(res=>{
      //   console.log(res)
      // })

      console.log(selectInfo)
      console.log(title)
    },
    handleEventClick(clickInfo) {
      if (confirm(`Are you sure you want to delete the event '${clickInfo.event.title}'`)) {
        clickInfo.event.remove()
      }
    },
    // handleEvents(events) {
    //   this.currentEvents = events
    // },
    getSessionData(){
      axios({
        method:'post',
        url:`${VUE_APP_API_URL}/api/schedule/search`,
        data:{
          "userId": this.$store.state.payload.id,
        }
      })
      .then(res=>{
        console.log(res.data)
        this.currentEvents = res.data
      })
    }
  },
  created(){
    this.getSessionData()
  }
})
</script>

<template>
  <div id="fh5co-main">
    <div class="fh5co-narrow-content">
      <div class='demo-app'>
        <div class='demo-app-sidebar'>
          <div class='demo-app-sidebar-section'>
            <label>
              <input
                type='checkbox'
                :checked='calendarOptions.weekends'
                @change='handleWeekendsToggle'
              />
              주말 포함
            </label>
          </div>
          <div class='demo-app-sidebar-section'>
            <h2>All Events ({{ currentEvents.length }})</h2>
            <ul>
              <li v-for='event in currentEvents' :key='event.id'>

                <i>{{ event.name }}</i>
              </li>
            </ul>
          </div>
        </div>
        <div class='demo-app-main'>
          <FullCalendar
            class='demo-app-calendar'
            :options='calendarOptions'
          >
            <template v-slot:eventContent='arg'>
              <b>{{ arg.timeText }}</b>
              <i>{{ arg.event.title }}</i>
            </template>
          </FullCalendar>
        </div>
      </div>
    </div>
  </div>
</template>

<style lang='css'>

h2 {
  margin: 0;
  font-size: 16px;
}

ul {
  margin: 0;
  padding: 0 0 0 1.5em;
}

li {
  margin: 1.5em 0;
  padding: 0;
}

b { /* used for event dates/times */
  margin-right: 3px;
}

.demo-app {
  display: flex;
  min-height: 100%;
  font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
  font-size: 14px;
}

.demo-app-sidebar {
  width: 300px;
  line-height: 1.5;
  background: #eaf9ff;
  border-right: 1px solid #d3e2e8;
}

.demo-app-sidebar-section {
  padding: 2em;
}

.demo-app-main {
  flex-grow: 1;
  padding: 3em;
}

.fc { /* the calendar root */
  max-width: 1100px;
  margin: 0 auto;
}
.fc-daygrid-day-number{
  color: black;
}
.fc-day-number.fc-sat.fc-past { color:#0000FF; }     /* 토요일 */
.fc-day-number.fc-sun.fc-past { color:#FF0000; }    /* 일요일 */


</style>
