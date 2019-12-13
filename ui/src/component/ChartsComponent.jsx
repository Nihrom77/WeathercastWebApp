import React, {Component} from "react";
import ReactEcharts from "echarts-for-react";

class ChartsComponent extends Component {


    getOption = () => {

        return {
            baseOption: {

                color: ["#e91e63", "#354EF6"],
                title: {
                    subtext: this.props.name,
                    textAlign: "left",
                    left: "5%"
                },
                tooltip: {backgroundColor: "#555", borderWidth: 0, padding: 10},


                xAxis: [
                    {
                        type: "category",
                        data: this.props.days
                    }
                ],
                yAxis: [
                    {
                        type: "value"
                    }
                ],
                series: [{
                    data: this.props.tempreture,
                    type: "line"
                }]

            }

        };
    };

    render() {
        return (
            <ReactEcharts
                option={this.getOption()}
                style={{height: "80vh", left: 50, top: 50, width: "90vw"}}
                opts={{renderer: "svg"}}
            />
        );
    }

}

export default ChartsComponent;
