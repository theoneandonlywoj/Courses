import { Dialog } from 'quasar'
export function showErrorMessage (errorMessage) {
  Dialog.create({
    title: 'Error',
    message: errorMessage
  })
}
