<script>
import {defineComponent} from 'vue'
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin, {Draggable} from '@fullcalendar/interaction'
import {createEventId, INITIAL_EVENTS} from './event-utils'
import axios from 'axios'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL


document.addEventListener('DOMContentLoaded', function () {

  // var Draggable = FullCalendar.Draggable;

  var containerEl = document.getElementById('external-events');
  var calendarEl = document.getElementById('calendar');
  var checkbox = document.getElementById('drop-remove');

  // initialize the external events
  // -----------------------------------------------------------------

  new Draggable(containerEl, {
    itemSelector: '.fc-event',
    eventData: function (eventEl) {
      console.log("eventData" + JSON.stringify(eventEl))
      return {
        title: eventEl.innerText,
      };
    },
    droppable: true,
    drop: (event) => {
      console.log("dropped" + JSON.stringify(event))
    },
    eventDrop: function (obj) {
      console.log('eventDrop' + JSON.stringify(obj));
    },

  });

  // initialize the calendar
  // -----------------------------------------------------------------

  const calendar = FullCalendar.Calendar(calendarEl, {
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },
    editable: true,
    droppable: true, // this allows things to be dropped onto the calendar,

  });
  calendar.render();
});


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
          interactionPlugin,
        ],
        headerToolbar: {
          left: 'prev,next today',
          center: 'title',
          right: 'dayGridMonth,timeGridWeek,timeGridDay'
        },
        initialView: 'dayGridMonth',
        initialEvents: [], // alternatively, use the `events` setting to fetch from a feed
        editable: true,
        selectable: true,
        selectMirror: true,
        dayMaxEvents: true,
        weekends: true,
        // resources: this.getExpectedData,
        select: this.handleDateSelect, // 캘린더에서 드래그로 이벤트 생성
        eventClick: this.handleEventClick, // 있는 일정 클릭시,
        eventsSet: this.handleEvents,
        events: [],//데이터를 로딩 시킨다.


        eventAdd: function (obj) { // 이벤트가 추가되면 발생하는 이벤트
          console.log('eventAdd' + JSON.stringify(obj));
        },
        eventChange: function (obj) { // 이벤트가 수정되면 발생하는 이벤트}
          console.log('eventChange' + JSON.stringify(obj));
        },
        eventRemove: function (obj) { // 이벤트가 삭제되면 발생하는 이벤트
          console.log('remove' + JSON.stringify(obj));
          obj.event.remove();
        },
        eventDrop:function (obj){
          console.log('eventDrop' + JSON.stringify(obj));
        },
        drop: function (arg) {
          console.log('drop : ' + JSON.stringify(arg));
          if (document.getElementById("drop-remove").checked) {
            // if so, remove the element from the "Draggable Events" list
            arg.draggedEl.parentNode.removeChild(arg.draggedEl);
          }
        },
        eventReceive: function (obj) {
          console.log('eventReceive' + JSON.stringify(obj));

        },

      },
      expectedData: [],
      currentEvents: [],
      DraggableEvents: [],
      loadedEvents: [],
      detailData: null
    }
  },
  methods: {
    handleWeekendsToggle() {
      this.calendarOptions.weekends = !this.calendarOptions.weekends // update a property
    },
    handleDateSelect(selectInfo) {
      let title = prompt('Please enter a new title for your event')
      let calendarApi = selectInfo.view.calendar
      calendarApi.unselect() // clear date selection
      if (title) {
        calendarApi.addEvent({
          id: createEventId(),
          title,
          start: selectInfo.startStr,
          end: selectInfo.endStr,
          allDay: selectInfo.allDay,
          extendedProps: {
            sessionId: 'BioChemistry'
          },
        })
      }
    },
    handleEventClick(clickInfo) {
      console.log("상세화면 보여줄 것" + JSON.stringify(clickInfo))
      let data = {sessionId: clickInfo.event.extendedProps.sessionId, dateTime: clickInfo.event.start}
      this.getScheduleDetail(data)
    },
    handleEvents(events) {
      console.log("handleEvents222 >>: " + JSON.stringify(events))
      //여기서 등록 요청 해야 함.
      if(events.isEmpty) return
      let v = null;
      let obj = events.extendedProps
      console.log("handleEvents333 >>: " + JSON.stringify(events))
      if (obj === undefined) return;
      if("scheduleId" in obj)v = events.extendedProps.scheduleId
      // if(obj.has("scheduleId") )
      console.log("handleEvents444 >>: " + JSON.stringify(events))
      this.saveNewSchedule({"sessionId": events.extendedProps.sessionId, "start": events.start, "scheduleId":v})
      // if(!events.isEmpty && events.extendedProps.sessionId != undefined){
      //   this.saveNewSchedule(events.extendedProps.sessionId)
      // }

      // calendarApi.addEvent({
      //   id: createEventId(),
      //   title,
      //   start: selectInfo.startStr,
      //   end: selectInfo.endStr,
      //   allDay: selectInfo.allDay
      // }
    },
    getApprovedData() {
      axios({
        method: 'post',
        url: `${VUE_APP_API_URL}/api/schedule/search`,
        data: {
          state: 'APPROVED',
          userId: "counselor01",
          start:new Date().toJSON().split('.')[0]
        }
      })
          .then(res => {
            this.DraggableEvents = res.data.map(it => {
              return {"id": it.sessionId, "title": it.name, "allDay": false,"extendedProps":{"sessionId":it.sessionId}}
            })
            // this.DraggableEvents = res.data
            console.log("DraggableEvents:>>>> " + JSON.stringify(this.DraggableEvents))
          })
    },
    getExpectedData: function () {
      axios({
        method: 'post',
        url: `${VUE_APP_API_URL}/api/schedule/search/monthly`,
        data: {
          // counselorId: this.userId
          counselorId: "counselor01"
        }
      })
          .then(res => {
            console.log("getExpectedData:>>>> " + JSON.stringify(res.data))
            // this.loadedEvents=res.data
            this.calendarOptions.events = res.data

            console.log("getExpectedData1:>>>> " + JSON.stringify(this.calendarOptions.events))
          })
    },
    getScheduleDetail: function (schedule) {
      axios({
        method: 'post',
        url: `${VUE_APP_API_URL}/api/schedule/detail`,
        data: {
          "state": "APPROVED",
          "sessionId": schedule.sessionId
        }
      })
          .then(res => {
            console.log("detail :>>>> " + JSON.stringify(res.data))
            this.detailData = res.data
          })
    },
    saveNewSchedule: function (monthlyEventInfo) {
      console.log("monthlyEventInfo :>>>> " + JSON.stringify(monthlyEventInfo))
      axios({
        method: 'post',
        url: `${VUE_APP_API_URL}/api/schedule/update/monthly`,
        data: {
          "dateTime": monthlyEventInfo.start,
          "sessionId": monthlyEventInfo.sessionId,
          "scheduleId": 107,
        }
      })
          .then(res => {
            console.log("detail :>>>> " + JSON.stringify(res.data))
            this.detailData = res.data
          })
    },
  },
  created() {
    this.getExpectedData()
    this.getApprovedData()
  }
})

</script>

<template>
  <div id="fh5co-main">
    <div class="fh5co-narrow-content">
      <div class="offcanvas offcanvas-end" data-bs-scroll="true" data-bs-backdrop="false" tabindex="-1"
           id="offcanvasScrolling" aria-labelledby="offcanvasScrollingLabel">
        <div class="offcanvas-header">
          <h5 class="offcanvas-title" id="offcanvasScrollingLabel">Offcanvas with body scrolling</h5>
          <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">
          <p>Try scrolling the rest of the page to see this option in action.</p>
        </div>
      </div>
      <div class='demo-app'>
        <div class='demo-app-main'>
          <div id='external-events' class="drag-cover">
            <p>
              <strong>Draggable Events</strong>
              <button class="btn btn-primary" type="button" data-bs-toggle="offcanvas"
                      data-bs-target="#offcanvasScrolling" aria-controls="offcanvasScrolling">Enable body
                scrolling
              </button>
            </p>
            <div
                class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event fc-event-draggable fc-daygrid-event-harness'
                v-for="(event,idx) in DraggableEvents"
                :key="idx">
              <div class='fc-event-main'>{{ event.title }}</div>
            </div>
            <p>
              <input type='checkbox' id='drop-remove'/>
              <label for='drop-remove'>remove after drop</label>
            </p>
          </div>
          <FullCalendar class="demo-app-calendar" :options="calendarOptions">
            <template v-slot:eventContent="arg">
              <b type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close">{{
                  arg.event.start.toTimeString().split(' ')[0].substr(0, 5)
                }}</b>
              <i>{{ arg.event.title }}</i>
              <i>{{ arg.event.sessionId }}</i>
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

#external-events {
  position: fixed;
  z-index: 2;
  top: 20px;
  left: 20px;
  width: 150px;
  padding: 0 10px;
  border: 1px solid #ccc;
  background: #eee;
}

#external-events .fc-event {
  cursor: move;
  margin: 3px 0;
}

#calendar-container {
  position: relative;
  z-index: 1;
  margin-left: 200px;
}

#calendar {
  max-width: 1100px;
  margin: 20px auto;
}

</style>
