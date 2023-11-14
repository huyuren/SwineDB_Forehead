<template>
    <el-card>
        <iframe
            ref="iframe"
            :src="rbc"
            width="100%"
            height="680"
            frameborder="0">
        </iframe>
    </el-card>
    
</template>

<script>
export default{
    name:"radial",
    data(){
        return{
            rbc:"../../public/static/Radial.html"
        }
    }
}
                
</script>
<style>
</style>