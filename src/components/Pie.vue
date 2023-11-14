<template>
    <el-card>
        <iframe
            ref="iframe"
            :src="p"
            width="100%"
            height="680"
            frameborder="0">
        </iframe>
    </el-card>
    
</template>

<script>
    export default{
        name:"pie",
        data(){
            return{
                p:"../../public/static/Pie.html"
            }
        }
    }                
</script>
<style>
                </style>