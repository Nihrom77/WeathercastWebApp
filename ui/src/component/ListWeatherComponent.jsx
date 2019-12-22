import React, {Component} from 'react'
import ApiService from "../service/ApiService";

import Grid from '@material-ui/core/Grid';
import TextField from '@material-ui/core/TextField';
import ChartsComponent from "./ChartsComponent";

import 'date-fns';
import DateFnsUtils from '@date-io/date-fns';
import {KeyboardDatePicker, MuiPickersUtilsProvider,} from '@material-ui/pickers';

/**
 * Компонент, отображающий основную страницу.
 * Содержит имя, список дат, выпадающий список городов и компоненты графиков.
 */
class ListWeatherComponent extends Component {

    constructor(props) {
        super(props);
        let today = new Date();
        let tomorrow = new Date();
        tomorrow.setDate(tomorrow.getDate() + 1);
        this.state = {
            forecasts: [],
            currentCity: "Moscow",
            menuAnchor: null,
            dateFrom: today,
            dateTo: tomorrow,

            dateList: [],
            temperatureForecast: [],
            humidityForecast: [],
            pressureForecast: []
        };

        this.loadForecast = this.loadForecast.bind(this);
        this.loadCityLists = this.loadCityLists.bind(this);
        this.onChange = this.onChange.bind(this);
        this.handleDateChangeFrom = this.handleDateChangeFrom.bind(this);
        this.handleDateChangeTo = this.handleDateChangeTo.bind(this);
        this.updateCharts = this.updateCharts.bind(this);

    }


    loadForecast() {
        const formattedDateF = Intl.DateTimeFormat('ru-RU', {
            year: 'numeric',
            month: 'numeric',
            day: '2-digit'
        }).format(this.state.dateFrom);
        const formattedDateT = Intl.DateTimeFormat('ru-RU', {
            year: 'numeric',
            month: 'numeric',
            day: '2-digit'
        }).format(this.state.dateTo);
        let daysAndLocation = {
            id: Math.random() * 1000,
            dateFrom: formattedDateF,
            dateTo: formattedDateT,
            // dateFrom: this.getParsedDate(this.state.dateFrom),
            // dateTo: this.getParsedDate(this.state.dateTo),
            city: this.state.currentCity
        };
        console.log("from: " + formattedDateF + "  To: " + formattedDateT);
        console.log("Sending request: dateFrom=" + daysAndLocation.dateFrom + "  dateTo=" + daysAndLocation.dateTo);
        ApiService.fetchWeather(daysAndLocation)
            .then((res) => {
                this.updateCharts(res.data.result);
            });
    }

    updateCharts(chartModels) {

        chartModels.forEach(chartModel => {
            if (chartModel.metricName === 'TEMPERATURE') {
                this.setState({
                    temperatureForecast: chartModel.metric,
                    dateList: chartModel.dates
                });
            } else if (chartModel.metricName === 'HUMIDITY') {
                this.setState({
                    humidityForecast: chartModel.metric,
                    dateList: chartModel.dates
                });
            } else if (chartModel.metricName === 'PRESSURE') {
                this.setState({
                    pressureForecast: chartModel.metric,
                    dateList: chartModel.dates
                });
            }
        });
    }

    loadCityLists() {
        ApiService.fetchCityList()
            .then((res) => {
                this.setState({cities: res.data.result});
                console.log("Cities: " + res.data.result);
            });
    }

    onChange() {

    }

    handleDateChangeFrom(newDate) {
        console.log("Change date from:  " + newDate + "  -  To: " + this.state.dateTo);
        this.state.dateFrom = newDate;
        this.loadForecast();
    }

    handleDateChangeTo(newDate) {
        console.log("Change date from:  " + this.state.dateFrom + "  -  To: " + newDate);
        this.state.dateTo = newDate;

        console.log("stateDate to: " + this.state.dateTo);
        this.loadForecast();
    }

    render() {
        return (
            <Grid container spacing={4}>
                <TextField
                    id="name"
                    label={"Город"}
                    margin="normal"
                    value={this.state.currentCity}
                    onChange={this.onChange}
                    inputProps={{
                        pattern: "[\\w-]+",
                    }}
                    required
                    fullWidth
                />
                <MuiPickersUtilsProvider utils={DateFnsUtils}>
                    <Grid container justify="space-around">
                        <KeyboardDatePicker
                            disableToolbar
                            variant="inline"
                            format="dd/MM/yyyy"
                            margin="normal"
                            id="date-picker-inline"
                            label="Дата начала периода"
                            value={this.state.dateFrom}
                            onChange={this.handleDateChangeFrom}
                            KeyboardButtonProps={{
                                'aria-label': 'change date',
                            }}
                        />
                        <KeyboardDatePicker
                            disableToolbar
                            variant="inline"
                            format="dd/MM/yyyy"
                            margin="normal"
                            id="date-picker-inline"
                            label="Дата конца периода"
                            value={this.state.dateTo}
                            onChange={this.handleDateChangeTo}
                            KeyboardButtonProps={{
                                'aria-label': 'change date',
                            }}
                        />
                    </Grid>
                </MuiPickersUtilsProvider>
                <ChartsComponent
                    name={"Температура"}
                    days={this.state.dateList}
                    tempreture={this.state.temperatureForecast}
                />

                <ChartsComponent
                    name={"Влажность"}
                    days={this.state.dateList}
                    tempreture={this.state.humidityForecast}
                />

                <ChartsComponent
                    name={"Давление"}
                    days={this.state.dateList}
                    tempreture={this.state.pressureForecast}
                />

            </Grid>
        );
    }

}

export default ListWeatherComponent;
