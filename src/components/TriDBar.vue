<template>
    <div>
        <div>
            <el-card>
                <iframe ref="iframe" :src="t2" width="100%" height="505" frameborder="0">
                </iframe>
            </el-card>
        </div>
        <div style="text-align: center; font-size: 20px; font-weight: bold;">
            <span>
                Visualisation of expected graphical presentation
            </span>
        </div>
        <div class="graph">
            <div id="bar1" style="width: 600px;height:400px;"></div>
            <div id="abundance" style="width: 600px;height:400px;"></div>
            <div id="pie" style="width: 600px;height:400px;"></div>
            <div id="lefse" style="width: 600px;height:400px;"></div>
        </div>

    </div>
</template>
<script>
import { ref, onMounted } from "vue";
import * as echarts from 'echarts';
import 'echarts-gl';
export default {
    name: "triDBar",

    setup() {

        onMounted(
            () => {
                bargraphinit()
                abundancegraphinit()
                piegraphinit()
                lefsegraphinit()
            }
        );
        function bargraphinit() {
            var myChart = echarts.init(document.getElementById('bar1'));
            window.addEventListener('resize', function () {
                myChart.resize();
            });
            // 指定图表的配置项和数据
            var option;
            option = {
                title: {
                    // text: "Relative abundance",
                    // subtext: 'phylum',
                    left: 'center'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        // Use axis to trigger tooltip
                        type: 'shadow' // 'shadow' as default; can also be 'line' or 'shadow'
                    }
                },
                legend: {},
                grid: {
                    left: '3%',
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: {
                    type: 'value'
                },
                yAxis: {
                    type: 'category',
                    data: ['SRR11637492', 'SRR11637493', 'SRR11637494', 'SRR11637495', 'SRR11637496', 'SRR11637497', 'SRR11637498', 'SRR11637499']
                },
                series: [
                    {
                        name: 'Bacteroidetes',
                        type: 'bar',
                        stack: 'total',
                        label: {
                            show: true
                        },
                        emphasis: {
                            focus: 'series'
                        },
                        data: [320, 302, 301, 334, 390, 330, 320, 322]
                    },
                    {
                        name: 'Proteobacteria',
                        type: 'bar',
                        stack: 'total',
                        label: {
                            show: true
                        },
                        emphasis: {
                            focus: 'series'
                        },
                        data: [320, 302, 301, 334, 390, 330, 320, 330]
                    },
                    {
                        name: 'Spirochaetes',
                        type: 'bar',
                        stack: 'total',
                        label: {
                            show: true
                        },
                        emphasis: {
                            focus: 'series'
                        },
                        data: [120, 132, 101, 134, 90, 230, 85, 56]
                    },
                    {
                        name: 'Actinobacteria',
                        type: 'bar',
                        stack: 'total',
                        label: {
                            show: true
                        },
                        emphasis: {
                            focus: 'series'
                        },
                        data: [220, 182, 191, 234, 290, 330, 310, 200]
                    },
                    {
                        name: 'Firmicutes',
                        type: 'bar',
                        stack: 'total',
                        label: {
                            show: true
                        },
                        emphasis: {
                            focus: 'series'
                        },
                        data: [150, 212, 201, 154, 190, 330, 410, 300]
                    }
                ]
            };
            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }

        function abundancegraphinit() {
            var myChart = echarts.init(document.getElementById('abundance'));
            window.addEventListener('resize', function () {
                myChart.resize();
            });
            // 指定图表的配置项和数据
            var option;
            option = {
                title: {
                    text: "Relative abundance",
                    subtext: 'phylum',
                    left: 'center'
                },
                dataset: [
                    {
                        dimensions: ['name', 'score'],
                        source: [
                            ['Proteobacteria', 8689],
                            ['Bacteroidetes', 14288],
                            ['Spirochaetes', 2069],
                            ['Actinobacteria', 1211],
                            ['Firmicutes', 8353],
                            ['Tenericutes', 5],
                            ['Deferribacteres', 148],
                            ['Fibrobacteres', 18]
                        ]
                    },
                    {
                        transform: {
                            type: 'sort',
                            config: { dimension: 'score', order: 'desc' }
                        }
                    }
                ],
                xAxis: {
                    type: 'category',
                    axisLabel: { interval: 0, rotate: 30 }
                },
                yAxis: {},
                series: {
                    type: 'bar',
                    encode: { x: 'name', y: 'score' },
                    datasetIndex: 1
                }
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }
        function piegraphinit() {
            var myChart = echarts.init(document.getElementById('pie'));
            window.addEventListener('resize', function () {
                myChart.resize();
            });
            // 指定图表的配置项和数据
            var option;
            option = {
                tooltip: {
                    trigger: 'item'
                },
                legend: {
                    top: '5%',
                    left: 'center'
                },
                series: [
                    {
                        name: 'Access From',
                        type: 'pie',
                        radius: ['40%', '70%'],
                        avoidLabelOverlap: false,
                        label: {
                            show: false,
                            position: 'center'
                        },
                        emphasis: {
                            label: {
                                show: true,
                                fontSize: 20,
                                //fontWeight: 'bold'
                            }
                        },
                        labelLine: {
                            show: false
                        },
                        data: [
                            { value: 8689, name: 'p_Proteobacteria' },
                            { value: 14288, name: 'p_Bacteroidetes' },
                            { value: 2069, name: 'p_Spirochaetes' },
                            { value: 1211, name: 'p_Actinobacteria' },
                            { value: 8353, name: 'p_Firmicutes' },
                            { value: 5, name: 'p_Tenericutes' },
                            { value: 148, name: 'p_Deferribacteres' },
                            { value: 18, name: 'p_Fibrobacteres' }
                        ]
                    }
                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }

        function lefsegraphinit() {
            var myChart = echarts.init(document.getElementById('lefse'));
            window.addEventListener('resize', function () {
                myChart.resize();
            });


            // 指定图表的配置项和数据
            var option;
            const labelRight = {
                position: 'right'
            };
            option = {
                title: {
                    text: "LDA SCORE(log10)",
                    left: 'center'
                },
                tooltip: {
                    trigger: 'axis',
                    axisPointer: {
                        type: 'shadow'
                    }
                },
                legend: {
                    orient: 'vertical',
                    left: 'right'
                },
                grid: {
                    top: 80,
                    bottom: 30
                },
                xAxis: {
                    type: 'value',
                    position: 'top',
                    splitLine: {
                        lineStyle: {
                            type: 'dashed'
                        }
                    }
                },
                yAxis: {
                    type: 'category',
                    axisLine: { show: false },
                    axisLabel: { show: false },
                    axisTick: { show: false },
                    splitLine: { show: false },
                    data: [
                        'o__SHA_98',
                        'g__CF231',
                        'p__Fusobacteria',
                        'f__Peptostreptococcaceae',
                        'g__Ruminobacter',
                        'f__Bacteroidaceae',
                        's__inulinivorans',

                    ]
                },
                series: [
                    {
                        name: 'ARG',
                        type: 'bar',
                        stack: 'Total',
                        label: {
                            show: true,
                            formatter: '{b}'
                        },
                        data: [
                            { value: -4.5, label: labelRight },
                            { value: -4.58, label: labelRight },
                            { value: -4.7, label: labelRight },
                            { value: -5.4, label: labelRight },
                            { value: -4.3, label: labelRight }
                        ]
                    },
                    {
                        name: 'CON',
                        type: 'bar',
                        stack: 'Total',
                        label: {
                            show: true,
                            formatter: '{b}'
                        },
                        data: [
                            0,
                            0,
                            0,
                            0,
                            0,
                            3.7,
                            5.4
                        ]
                    }
                ]
            };

            // 使用刚指定的配置项和数据显示图表。
            myChart.setOption(option);
        }


        return {
            t2: "../../public/static/test2.html"
        }
    }

}

</script>
<style>
el-crad {

    height: 1000px;
    background-color: blue;
}

.graph{
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
}
</style>