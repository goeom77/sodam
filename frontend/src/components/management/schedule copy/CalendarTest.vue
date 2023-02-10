<script>
import {defineComponent} from 'vue'
import FullCalendar from '@fullcalendar/vue3'
import dayGridPlugin from '@fullcalendar/daygrid'
import timeGridPlugin from '@fullcalendar/timegrid'
import interactionPlugin, {Draggable} from '@fullcalendar/interaction'
import {INITIAL_EVENTS, createEventId} from './event-utils'



document.addEventListener('DOMContentLoaded', function() {
  var Calendar = FullCalendar.Calendar;
  // var Draggable = FullCalendar.Draggable;

  var containerEl = document.getElementById('external-events');
  var calendarEl = document.getElementById('calendar');
  var checkbox = document.getElementById('drop-remove');

  // initialize the external events
  // -----------------------------------------------------------------

  new Draggable(containerEl, {
    itemSelector: '.fc-event',
    editable: true,
    draggable:true,
    droppable: true,
    drop: function(info) {
      // is the "remove after drop" checkbox checked?
      if (checkbox.checked) {
        // if so, remove the element from the "Draggable Events" list
        info.draggedEl.parentNode.removeChild(info.draggedEl);
      }
    },
    eventData: function(eventEl) {
      return {
        title: eventEl.innerText
      };
    }
  });

  // initialize the calendar
  // -----------------------------------------------------------------

  var calendar = Calendar(calendarEl, {
    headerToolbar: {
      left: 'prev,next today',
      center: 'title',
      right: 'dayGridMonth,timeGridWeek,timeGridDay'
    },
    editable: true,
    draggable:true,
    droppable: true, // this allows things to be dropped onto the calendar
    drop: function(info) {
      // is the "remove after drop" checkbox checked?
      if (checkbox.checked) {
        // if so, remove the element from the "Draggable Events" list
        info.draggedEl.parentNode.removeChild(info.draggedEl);
      }
    }
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
        eventDrop: this.onEventDrop,
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
      let title = prompt('Please enter a new title for your event')
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
    },
    handleEventClick(clickInfo) {
      if (confirm(`Are you sure you want to delete the event '${clickInfo.event.title}'`)) {
        clickInfo.event.remove()
      }
    },
    handleEvents(events) {
      this.currentEvents = events
    },
    onEventDrop ({ event }) {
      this.currentEvents=event
    }
  }
})

</script>

<template>
  <div id="fh5co-main">
    <div class="fh5co-narrow-content">
      <div class='demo-app'>
<!--        <div class='demo-app-sidebar'>-->
<!--          <div class='demo-app-sidebar-section'>-->
<!--            <h2>Instructions</h2>-->
<!--            <ul>-->
<!--              <li>Select dates and you will be prompted to create a new event</li>-->
<!--              <li>Drag, drop, and resize events</li>-->
<!--              <li>Click an event to delete it</li>-->
<!--            </ul>-->
<!--          </div>-->
<!--          <div class='demo-app-sidebar-section'>-->
<!--            <label>-->
<!--              <input-->
<!--                  type='checkbox'-->
<!--                  :checked='calendarOptions.weekends'-->
<!--                  @change='handleWeekendsToggle'-->
<!--              />-->
<!--              toggle weekends-->
<!--            </label>-->

<!--          </div>-->
<!--&lt;!&ndash;          <div class='demo-app-sidebar-section'>&ndash;&gt;-->
<!--&lt;!&ndash;&lt;!&ndash;            요기&ndash;&gt;&ndash;&gt;-->
<!--&lt;!&ndash;            <div id='external-events'>&ndash;&gt;-->
<!--&lt;!&ndash;              <p>&ndash;&gt;-->
<!--&lt;!&ndash;                <strong>Draggable Events</strong>&ndash;&gt;-->
<!--&lt;!&ndash;              </p>&ndash;&gt;-->

<!--&lt;!&ndash;              <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>&ndash;&gt;-->
<!--&lt;!&ndash;                <div class='fc-event-main'>My Event 1</div>&ndash;&gt;-->
<!--&lt;!&ndash;              </div>&ndash;&gt;-->
<!--&lt;!&ndash;              <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>&ndash;&gt;-->
<!--&lt;!&ndash;                <div class='fc-event-main'>My Event 2</div>&ndash;&gt;-->
<!--&lt;!&ndash;              </div>&ndash;&gt;-->
<!--&lt;!&ndash;              <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>&ndash;&gt;-->
<!--&lt;!&ndash;                <div class='fc-event-main'>My Event 3</div>&ndash;&gt;-->
<!--&lt;!&ndash;              </div>&ndash;&gt;-->
<!--&lt;!&ndash;              <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>&ndash;&gt;-->
<!--&lt;!&ndash;                <div class='fc-event-main'>My Event 4</div>&ndash;&gt;-->
<!--&lt;!&ndash;              </div>&ndash;&gt;-->
<!--&lt;!&ndash;              <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>&ndash;&gt;-->
<!--&lt;!&ndash;                <div class='fc-event-main'>My Event 5</div>&ndash;&gt;-->
<!--&lt;!&ndash;              </div>&ndash;&gt;-->

<!--&lt;!&ndash;              <p>&ndash;&gt;-->
<!--&lt;!&ndash;                <input type='checkbox' id='drop-remove' />&ndash;&gt;-->
<!--&lt;!&ndash;                <label for='drop-remove'>remove after drop</label>&ndash;&gt;-->
<!--&lt;!&ndash;              </p>&ndash;&gt;-->
<!--&lt;!&ndash;            </div>&ndash;&gt;-->
<!--&lt;!&ndash;            &lt;!&ndash;            요기&ndash;&gt;&ndash;&gt;-->
<!--&lt;!&ndash;            <div id='calendar-container'>&ndash;&gt;-->
<!--&lt;!&ndash;              <div id='calendar' class="fc fc-media-screen fc-direction-ltr fc-theme-standard"></div>&ndash;&gt;-->
<!--&lt;!&ndash;            </div>&ndash;&gt;-->
<!--&lt;!&ndash;            <h2>All Events ({{ currentEvents.length }})</h2>&ndash;&gt;-->
<!--&lt;!&ndash;            <ul>&ndash;&gt;-->
<!--&lt;!&ndash;              <li v-for='event in currentEvents' :key='event.id'>&ndash;&gt;-->
<!--&lt;!&ndash;                <b>{{ event.startStr }}</b>&ndash;&gt;-->
<!--&lt;!&ndash;                <i>{{ event.title }}</i>&ndash;&gt;-->
<!--&lt;!&ndash;              </li>&ndash;&gt;-->
<!--&lt;!&ndash;            </ul>&ndash;&gt;-->
<!--&lt;!&ndash;          </div>&ndash;&gt;-->
<!--        </div>-->
        <div class='demo-app-main'>
          <div id='external-events' class="drag-cover">
            <p>
              <strong>Draggable Events</strong>
            </p>

            <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event fc-event-draggable fc-daygrid-event-harness'>
              <div class='fc-event-main'>My Event 1</div>
            </div>
            <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event fc-event-draggable'>
              <div class='fc-event-main'>My Event 2</div>
            </div>
            <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event fc-event-draggable'>
              <div class='fc-event-main'>My Event 3</div>
            </div>
            <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
              <div class='fc-event-main'>My Event 4</div>
            </div>
            <div class='fc-event fc-h-event fc-daygrid-event fc-daygrid-block-event'>
              <div class='fc-event-main'>My Event 5</div>
            </div>

            <p>
              <input type='checkbox' id='drop-remove' />
              <label for='drop-remove'>remove after drop</label>
            </p>
          </div>
          <FullCalendar
              class='demo-app-calendar'
              :options=calendarOptions
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
