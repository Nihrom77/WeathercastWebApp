import axios from 'axios';

/**
 * Класс, посылающий запросы в бекэнд.
 */
class ApiService {

    fetchWeather(daysAndLocation) {
        return axios.post('weather', daysAndLocation);
    }

    fetchCityList() {
        return axios.get('cities');
    }

}

export default new ApiService();
