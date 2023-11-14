<template>
    <div class="introduce">
        This module allows users to filter the Projects they are interested in by criteria.
    </div>

    <div>
        <el-button-group>
            <el-button @click="showBrowseFn()" class="browse1-button">Browse</el-button>
            <el-button @click="showSearchFn()" class="search1-button">Search</el-button>
            <el-button @click="showDataFn()" class="data1-button">Data</el-button>
            <el-button @click="showToolsFn()" class="tools1-button">Tools</el-button>
        </el-button-group>
        
    </div>



    <div class="side-title">How to use PGMD</div>

    <!-- show browse -->
    <div v-show="showBrowse">
        <img class="help-img" src="../images/help.png">
        <div class="help-text">
            1. Country selection bar: China, USA, Italy, etc.
            2. Pig type selection field: boar, sow, fattening pig, piglet. 
            3. Breed selection field: select different pig breeds (Duroc, etc.).
            4. Growth stage selection bar: select the growth stage.
            5. Sampling location selection field: stomach, small intestine, faeces, etc.
            6. Phenotype selection field: meat quality, growth rate, semen quality, etc.
            7. Submit: return results via the submit button.
        </div>
    </div>

    <div v-show="showSearch"></div>
    <div v-show="showData"></div>
    <div v-show="showTools"></div>
    <!-- footer -->
	<div class="help-footer">
		<div class="footer-info">
			© 2021-2023 XXXX Pig Microbiome @Yan Lab. All Rights Reserved.<br>
			National Key Laboratory of Agricultural Microbiology<br>
			Frontiers Science Center for Animal Breeding and Sustainable Production<br>
			Huazhong Agricultural University
		</div>
	</div>
</template>

<script>
    import { reactive } from 'vue';

    export default {
        name: "Help",
        setup() {
            const state = reactive({
                showBrowse: true,
                showSearch: false,
                showData: false,
                showTools: false,
            });

            const showBrowseFn = () => {
                state.showBrowse = true;
                state.showSearch = false;
                state.showData = false;
                state.showTools = false;
            };

            const showSearchFn = () => {
                state.showBrowse = false;
                state.showSearch = true;
                state.showData = false;
                state.showTools = false;
            };

            const showDataFn = () => {
                state.showBrowse = false;
                state.showSearch = false;
                state.showData = true;
                state.showTools = false;
            };

            const showToolsFn = () => {
                state.showBrowse = false;
                state.showSearch = false;
                state.showData = false;
                state.showTools = true;
            };

            return {
                ...state,
                showBrowseFn,
                showSearchFn,
                showDataFn,
                showToolsFn
            }
        }
    }
</script>

<style scoped>
.introduce{
    position: absolute;
    left: 512px;
    top: 138px;
    width: 887px;
    height: 43px;
    opacity: 1;
    /** 文本1 */
    font-size: 24px;
    font-weight: 400;
    letter-spacing: 0px;
    line-height: 34.75px;
    color: rgba(0, 0, 0, 1);
    text-align: left;
    vertical-align: top;
}

.side-title{
    position: absolute;
    left: 117px;
    top: 217px;
    width: 136px;
    height: 24px;
    opacity: 1;
    /** 文本1 */
    font-size: 16px;
    font-weight: 500;
    letter-spacing: 0px;
    line-height: 24px;
    color: rgba(0, 0, 0, 0.85);
    text-align: left;
    vertical-align: top;
}

.browse1-button{
    position: absolute;
    left: 93px;
    top: 264px;
    width: 256px;
    height: 40px;
    opacity: 1;
}
.search1-button{
    position: absolute;
    left: 93px;
    top: 303px;
    width: 256px;
    height: 40px;
    opacity: 1;
    background: rgba(255, 255, 255, 1);

}

.data1-button{
    position: absolute;
    left: 93px;
    top: 342px;
    width: 256px;
    height: 40px;
    opacity: 1;
    background: rgba(255, 255, 255, 1);
}

.tools1-button{
    position: absolute;
    left: 93px;
    top: 381px;
    width: 256px;
    height: 40px;
    opacity: 1;
    background: rgba(255, 255, 255, 1);
}

.help-img{
    position: absolute;
    left: 512px;
    top: 205px;
    width: 1300px;
    height: 388px;
    opacity: 1;
}

.help-text{
    position: absolute;
    left: 509px;
    top: 604px;
    width: 1310px;
    height: 180px;
    opacity: 1;
    /** 文本1 */
    font-size: 24px;
    font-weight: 400;
    letter-spacing: 0px;
    line-height: 34.75px;
    color: rgba(0, 0, 0, 1);
    text-align: left;
    vertical-align: top;
}

.help-footer{
    position: absolute;
	left: 0px;
	top: 925px;
	width: 1920px;
	height: 155px;
	opacity: 1;
	background: rgba(0, 126, 55, 1);
	box-shadow: 0px 2px 4px  rgba(0, 0, 0, 0.25);
}

</style>