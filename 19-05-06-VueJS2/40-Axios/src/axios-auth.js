import axios from 'axios';

const instance = axios.create({
  baseURL: 'https://axios-9dd80.firebaseio.com'
});

instance.defaults.headers.common['SOMETHING'] = 'something'

export default instance;
