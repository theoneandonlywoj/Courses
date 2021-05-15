import { ref, watch, onMounted, onUnmounted } from 'vue';

const useLocalStorage = (key, defaultValue) => {
    const value = ref(defaultValue);

    // Loading the initial value if it exists.
    const read = () => {
        const v = window.localStorage.getItem(key)
        if (v != null) value.value = JSON.parse(v)
    }

    read()

    // Reading if the value was changes
    onMounted(() => {
        window.addEventListener('storage', read)
    })
    onUnmounted(() => {
        window.removeEventListener('storage', read)
    })

    // Writing a new value
    const write = () => {
        window.localStorage.setItem(key, JSON.stringify(value.value))
    }

    // Watching if the value changes
    watch([value], write, { deep: true })

    return value
}

export const useLocalNotes = () => {
    return useLocalStorage('notes', [])
}