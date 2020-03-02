<template>
  <q-page class="q-pa-md">
    <q-list
      separator
      bordered>
      <q-item
        v-for="task in tasks"
        :key="task.id"
        @click="task.completed = !task.completed"
        clickable
        v-ripple
        :class="!task.completed ? 'bg-orange-1' : 'bg-green-1' ">
        <q-item-section side top>
          <q-checkbox v-model="task.completed" />
        </q-item-section>

        <q-item-section>
          <q-item-label
            :class="{
              'text-strike': task.completed
            }">
            {{ task.name }}
          </q-item-label>
        </q-item-section>

         <q-item-section side>
          <div class="row">
            <div class="column justify-center">
              <q-icon
                name="event"
                size="18px"
                class="q-mr-xs"
              ></q-icon>
            </div>
            <div class="column">
              <q-item-label
                caption
                class="row justify-end"
              >
                {{ task.dueDate}}
              </q-item-label>
              <q-item-label
                caption
                class="row justify-end"
              >
                <small>{{ task.dueTime }}</small>
              </q-item-label>
            </div>
          </div>
        </q-item-section>

      </q-item>

    </q-list>
  </q-page>
</template>

<script>
export default {
  name: 'PageTodo',
  data () {
    return {
      tasks: [
        {
          id: 1,
          name: 'Go shopping',
          completed: false,
          dueDate: '2020-01-01',
          dueTime: '00:00'
        },
        {
          id: 2,
          name: 'Work',
          completed: false,
          dueDate: '2020-01-02',
          dueTime: '01:00'
        },
        {
          id: 3,
          name: 'Get bananas',
          completed: false,
          dueDate: '2020-01-03',
          dueTime: '02:00'
        }
      ]
    }
  }
}
</script>
