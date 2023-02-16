<script>
import {defineComponent} from 'vue'
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin, {Draggable} from '@fullcalendar/interaction'
import {createEventId} from './event-utils'
import axios from 'axios'
// import Datepicker from "vue3-datepicker";
import Datepicker from '@vuepic/vue-datepicker';
import '@vuepic/vue-datepicker/dist/main.css'

const VUE_APP_API_URL = process.env.VUE_APP_API_URL


document.addEventListener('DOMContentLoaded', function () {

  // var Draggable = FullCalendar.Draggable;

  var containerEl = document.getElementById('external-events');
  var calendarEl = document.getElementById('calendar');

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
    drop: (info) => {
      console.log("dropped" + JSON.stringify(info))
    },
    eventDrop: function (obj) {
      console.log('eventDrop' + JSON.stringify(obj));
    },
    eventDragStop: function (info) {
      console.log('eventDragStop' + JSON.stringify(info));
    }
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
    Datepicker
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
        locale:'kr',
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
        eventDragStop: function (info) {
          console.log('eventDragStop' + JSON.stringify(info));
        },

        eventAdd: function (obj) { // 이벤트가 추가되면 발생하는 이벤트
          console.log('eventAdd' + JSON.stringify(obj));
        },
        eventChange: function (obj) { // 이벤트가 수정되면 발생하는 이벤트}
          console.log('eventChange' + JSON.stringify(obj));
          let monthlyEventInfo = {
            "sessionId": obj.event.extendedProps.sessionId,
            "start": obj.event.start,
            "scheduleId": obj.event.extendedProps.scheduleId,
            "title": obj.event.title
          };
          monthlyEventInfo.start = obj.event.start;
          this.detail = monthlyEventInfo
          // var monthlyEventInfo = this.detail;
          // monthlyEventInfo.setAttribute("start",dateTime)
          console.log(" eventChange before call update api :>>>" + JSON.stringify(monthlyEventInfo))
          this.detail = null;
          axios({
            method: 'post',
            url: `${VUE_APP_API_URL}/api/schedule/update/monthly`,
            data: {
              "sessionId": monthlyEventInfo.sessionId,
              "start": monthlyEventInfo.start,
              "scheduleId": monthlyEventInfo.scheduleId
            }
          })
              .then(res => {
                monthlyEventInfo = {
                  "id": res.data.scheduleId,
                  "title": monthlyEventInfo.title,
                  "start": monthlyEventInfo.start,
                  "allDay": false,
                  "extendedProps": {
                    "sessionId": monthlyEventInfo.sessionId,
                    "scheduleId": res.data.scheduleId
                  }
                }
                // let calendarApi = obj.view.calendar
                // calendarApi.addEvent(monthlyEventInfo)
                console.log("eventChange detail after api response :>>>>" + JSON.stringify(res.data))
              })
        },
        eventRemove: function (obj) { // 이벤트가 삭제되면 발생하는 이벤트
          console.log('remove' + JSON.stringify(obj));
          obj.event.remove();
        },
        eventDrop: function (obj) {
          console.log('eventDrop' + JSON.stringify(obj));
          let monthlyEventInfo = {
            "sessionId": obj.event.extendedProps.sessionId,
            "start": obj.event.start,
            "scheduleId": obj.event.extendedProps.scheduleId,
            "title": obj.event.title
          };
          monthlyEventInfo.start = obj.event.start;
          this.detail = monthlyEventInfo
          // var monthlyEventInfo = this.detail;
          // monthlyEventInfo.setAttribute("start",dateTime)
          console.log(" eventDrop before call update api :>>>" + JSON.stringify(monthlyEventInfo))
          this.detail = null;
          axios({
            method: 'post',
            url: `${VUE_APP_API_URL}/api/schedule/update/monthly`,
            data: {
              "sessionId": monthlyEventInfo.sessionId,
              "start": monthlyEventInfo.start,
              "scheduleId": monthlyEventInfo.scheduleId
            }
          })
              .then(res => {
                monthlyEventInfo = {
                  "id": createEventId(),
                  "title": monthlyEventInfo.title,
                  "start": monthlyEventInfo.start,
                  "allDay": false,
                  "extendedProps": {
                    "sessionId": monthlyEventInfo.sessionId
                  }
                }
                // let calendarApi = obj.view.calendar
                // calendarApi.addEvent(monthlyEventInfo)
                console.log("eventDrop detail after api response :>>>>" + JSON.stringify(res.data))
              })
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
      datetime: null,
      detailData: null,
      detail: null,
      dialog: false,
    }
  },
  methods: {
    // startmeetclient() {
    //   console.log(this.detail.sessionId);
    //   // this.$router.push({name: 'VideoPage', params: {id: this.detail.sessionId}});
    // },
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
          data: {
            customData: '되나?'
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
      if (events.isEmpty) return
      let v = null;
      let obj = events.extendedProps
      console.log("handleEvents333 >>: " + JSON.stringify(events))
      if (obj === undefined) return;
      if ("scheduleId" in obj) v = events.extendedProps.scheduleId
      // if(obj.has("scheduleId") )
      console.log("handleEvents444 >>: " + JSON.stringify(events))
      this.detail = {"sessionId": events.extendedProps.sessionId, "start": events.start, "scheduleId": v}
      // this.saveNewSchedule({"sessionId": events.extendedProps.sessionId, "start": events.start, "scheduleId": v})
    },
    getApprovedData() {
      axios({
        method: 'post',
        url: `${VUE_APP_API_URL}/api/schedule/search`,
        data: {
          state: 'APPROVED',
          userId: "counselor01",
          start: new Date().toJSON().split('.')[0]
        }
      })
          .then(res => {
            this.DraggableEvents = res.data.map(it => {
              return {
                "id": it.sessionId,
                "title": it.name,
                "allDay": false,
                "extendedProps": {"sessionId": it.sessionId, "turn": it.turn}
              }
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
            this.detail = res.data
          })
    },
    saveNewSchedule: function (dateTime) {
      console.log("monthlyEventInfo :>>>> " + JSON.stringify(this.detail))
      this.detail.start = dateTime
      var monthlyEventInfo = this.detail
      // monthlyEventInfo.setAttribute("start",dateTime)
      console.log(" this.detail :>>>" + JSON.stringify(monthlyEventInfo))
      // this.detail = null
      axios({
        method: 'post',
        url: `${VUE_APP_API_URL}/api/schedule/update/monthly`,
        data: {
          "start": monthlyEventInfo.start,
          "sessionId": monthlyEventInfo.id,
          "scheduleId": null,
        }
      })
          .then(res => {
            monthlyEventInfo = {
              "id": createEventId(),
              "title": monthlyEventInfo.title,
              "start": res.start,
              "allDay": false,
              "extendedProps": {
                "sessionId": res.sessionId
              }
            }
            // this.expectedData =  [...this.expectedData,monthlyEventInfo]
            console.log("detail :>>>>" + JSON.stringify(this))
            console.log("detail :>>>>" + JSON.stringify(event))
          })
    },
    makeASchedule: function (obj) {
      console.log("makeASchedule : " + JSON.stringify(obj))
      this.dialog = true
    },
    clickApprovedData: function (obj) {
      console.log("clickApprovedData :" + JSON.stringify(obj))
      this.detail = obj
      this.dialog = true
    },
    startMeeting(id) {
      this.$router.push({name: 'VideoPage', params: {id: id}});
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
           id="offcanvasScrolling" aria-labelledby="offcanvasScrollingLabel" style="z-index: 99999 !important">
        <div class="offcanvas-header">
          <h5 class="offcanvas-title" id="offcanvasScrollingLabel">상담정보</h5>

          <button type="button" class="btn-close" data-bs-dismiss="offcanvas" aria-label="Close"></button>
        </div>
        <div class="offcanvas-body">
          <a class="btn btn-primary" role="button" v-on:click="this.startMeeting(this.detail.sessionId)">상담하러가기</a>
        </div>
      </div>
      <div class='demo-app'>
        <div class='demo-app-main'>
          <div class="d-flex flex-no-wrap justify-space-between">

            <!-- 일정 리스트  -->
            <div id='external-events' style="width:20%; margin-right: 10px; border-radius: 10px">
              <v-list density="compact">
                <v-list-subheader>내담자 목록</v-list-subheader>
                <v-list-item
                    v-for="(event, i) in DraggableEvents"
                    :key="i"
                    :value="event"
                    active-color="primary"
                    v-on:click="clickApprovedData(event)"
                >
                  <template v-slot:prepend>
                    <div class="fc-daygrid-event-dot dot" v-show="event.extendedProps.turn===1"></div>
                  </template>
                  <v-list-item-title>{{ event.title }}</v-list-item-title>
                </v-list-item>
              </v-list>

              <!--              <div id="calendar-events" class="py-3 mt-6 mb-5 border-t border-b border-slate-200/60">-->
              <!--                <div class="list-group" v-for="(event,idx) in DraggableEvents" :key="idx">-->
              <!--                  <a class="list-group-item list-group-item-action list-group-item-primary"-->
              <!--                     v-on:click="clickApprovedData(event)">{{ event.title }}</a>-->
              <!--                </div>-->
              <!--              </div>-->
            </div>
            <!-- 캘린더 -->
            <FullCalendar class="demo-app-calendar fc-scrollgrid-sync-inner fc-theme-standard" :options="calendarOptions" style="width:80%">
              <template v-slot:eventContent="arg">
                <button type="button"
                        class="fc-event fc-event-draggable fc-event-resizable fc-event-future fc-daygrid-dot-event"
                        data-bs-toggle="offcanvas" data-bs-target="#offcanvasScrolling"
                        onclick="makeASchedule(arg); detail = arg">
                  <div class="fc-daygrid-event-dot "></div>
                  <a class="fc-event-time  fc-daygrid-dot-event">{{
                      arg.event.start.toTimeString().split(' ')[0].substr(0, 5)
                    }}</a>
                  <a class="fc-event-title  fc-daygrid-dot-event">{{ arg.event.title }}님</a>
                </button>
              </template>
            </FullCalendar>
          </div>
        </div>
      </div>
      <v-row justify="center">
        <v-dialog
            v-model="dialog"
            width="auto"
        >
          <v-card>
            <v-card-title>
              <span class="text-h5">일정등록</span>
            </v-card-title>
            <v-form disabled>
              <v-text-field
                  v-model="detail.title"
                  label="First name"
              ></v-text-field>
            </v-form>

            <!--              <datepicker-->
            <!--                  class="form-control"-->
            <!--                  placeholder="YYYY-MM-DD" required="required"-->
            <!--                  v-model="date"-->
            <!--                  lang="ko"-->
            <!--                  :lowerLimit="new Date()"-->
            <!--                  :clearable="false"-->
            <!--                  label="날짜선택"-->
            <!--              />-->

            <Datepicker v-model="datetime" inline auto-apply class="form-control" placeholder="YYYY-MM-DD hh:mm:ss"/>
            <v-card-actions>
              <v-spacer></v-spacer>
              <v-btn
                  color="blue-darken-1"
                  variant="text"
                  @click="dialog = false"
              >
                Close
              </v-btn>
              <v-btn
                  color="blue-darken-1"
                  variant="text"
                  @click="[saveNewSchedule(datetime),dialog = false ]"
              >
                <!--                  v-model="datetime"-->
                Save
              </v-btn>
            </v-card-actions>
          </v-card>
        </v-dialog>
      </v-row>
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
}

.fc { /* the calendar root */
  max-width: 1100px;
  margin: 0 auto;
}

#external-events {
  z-index: 2;
  top: 20px;
  left: 20px;
  width: 150px;
  padding: 0 10px;
  border: 1px solid #ccc;
  /*background: #eee;*/
}

#external-events .fc-event {
  cursor: move;
  margin: 3px;
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

.dot {
  border-color: #122b40 !important;
  background-color: #ff0707 !important;
  border-width: 1.5px;
  width: 8px;
  height: 8px;
  border-radius: 8px;
}

:root {
  --fc-small-font-size: .85em;
  --fc-page-bg-color: #fff;
  --fc-neutral-bg-color: hsla(0,0%,82%,.3);
  --fc-neutral-text-color: grey;
  --fc-border-color: #ddd;
  --fc-button-text-color: #1a252f;
  --fc-button-bg-color: #fff;
  --fc-button-border-color: #2c3e50;
  --fc-button-hover-bg-color: #92CFA5FF;
  --fc-button-hover-border-color: #1a252f;
  --fc-button-active-bg-color: #92CFA5FF;
  --fc-button-active-border-color: #79ab88;
  --fc-event-bg-color: #92CFA5FF;
  --fc-event-border-color: #79ab88;
  --fc-event-text-color: #fff;
  --fc-event-selected-overlay-color: rgb(255, 127, 127);
  --fc-more-link-bg-color: #d0d0d0;
  --fc-more-link-text-color: inherit;
  --fc-event-resizer-thickness: 8px;
  --fc-event-resizer-dot-total-width: 8px;
  --fc-event-resizer-dot-border-width: 1px;
  --fc-non-business-color: hsla(0,0%,84%,.3);
  --fc-bg-event-color: #8fdf82;
  --fc-bg-event-opacity: 0.3;
  --fc-highlight-color: rgba(188,232,241,.3);
  --fc-today-bg-color: rgba(255,220,40,.15);
  --fc-now-indicator-color: red;
  --bs-link-hover-color: #8fdf82;
  --fc-col-header-cell-cushion: whitesmoke;
  --fc-scrollgrid-section:whitesmoke;
  --fc-scrollgrid-section-header: whitesmoke;
}

.main-green-border {
  border-color: #92CFA5FF;
}
.fc-event-time{
  color: rgba(0, 0, 0, 0.8);
}
.fc-event-title {
  color: rgba(0, 0, 0, 0.8);
}
thead{
  color: whitesmoke;
}
.fc-theme-standard{
  --v-theme-background: #fff;
  --v-theme-on-background: #fff;
  --v-theme-surface: #fff;
  --v-theme-on-surface: #fff;
  --v-theme-overlay-multiplier: 1;
  --v-scrollbar-offset: 0px;
}


</style>
