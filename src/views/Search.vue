<template>
    <el-form>
        <!-- 主搜索栏 -->
        <el-form-item>
            <el-input class="main-search" v-model="search"
            placeholder="e.g. Salmonella infection"
            :prefix-icon="Search"
        />
        <el-button type="success" class="search-button">Search</el-button>
        </el-form-item>
        <!-- 第一行 -->
        <el-form-item>
            <el-select class="select12" v-model="select12" placeholder="Health Traits">
                <el-option label="one" value="1" />
                <el-option label="two" value="2" />
            </el-select>
            <el-input class="input1" v-model="input1" placeholder="e.g. Spain"
            :prefix-icon="Search"></el-input>
        </el-form-item>

        <!-- 第二行 -->
        <el-form-item>
            <el-select class="select21" v-model="select21" placeholder="AND">
                <el-option label="one" value="1" />
                <el-option label="two" value="2" />
            </el-select>
            <el-select class="select22" v-model="select22" placeholder="Health Traits">
                <el-option label="one" value="1" />
                <el-option label="two" value="2" />
            </el-select>
            <el-input class="input2" v-model="input2" placeholder="e.g. piglets"
            :prefix-icon="Search"></el-input>
            <el-button class="button11" type="success" icon="Edit" circle />
            <el-button class="button12" type="danger" icon="Delete" circle></el-button>
        </el-form-item>

        <!-- 第三行 -->
        <el-form-item>
            <el-select class="select31" v-model="select31" placeholder="AND">
                <el-option label="one" value="1" />
                <el-option label="two" value="2" />
            </el-select>
            <el-select class="select32" v-model="select32" placeholder="Health Traits">
                <el-option label="one" value="1" />
                <el-option label="two" value="2" />
            </el-select>
            <el-input class="input3" v-model="input3" placeholder="e.g. female"
            :prefix-icon="Search"></el-input>
            <el-button class="button21" type="success" icon="Edit" circle />
            <el-button class="button22" type="danger" icon="Delete" circle></el-button>
        </el-form-item>

        <!-- 第四行 -->
        <el-form-item>
            <el-select class="select41" v-model="select41" placeholder="AND">
                <el-option label="one" value="1" />
                <el-option label="two" value="2" />
            </el-select>
            <el-select class="select42" v-model="select42" placeholder="Health Traits">
                <el-option label="one" value="1" />
                <el-option label="two" value="2" />
            </el-select>
            <el-input class="input4" v-model="input3" placeholder="e.g. Large White × Landrace"
            :prefix-icon="Search"></el-input>
            <el-button class="button31" type="success" icon="Edit" circle />
            <el-button class="button32" type="danger" icon="Delete" circle></el-button>
        </el-form-item>

        <!-- 第五行 -->
        <el-form-item>
            <el-select class="select52" v-model="select52" placeholder="ALL LEVELS">
                <el-option label="one" value="1" />
                <el-option label="two" value="2" />
            </el-select>
            <el-button type="primary" class="example-button">Example</el-button>
            <el-button type="success" class="clear-button">Clear</el-button>
            <el-button type="success" class="submit-button">Submit</el-button>
        </el-form-item>

    </el-form>

    <!-- footer -->
    <div class="search-footer">
		<div class="footer-info">
			© 2021-2023 XXXX Pig Microbiome @Yan Lab. All Rights Reserved.<br>
			National Key Laboratory of Agricultural Microbiology<br>
			Frontiers Science Center for Animal Breeding and Sustainable Production<br>
			Huazhong Agricultural University
		</div>
	</div>

</template>
<script>
import {
  Check,
  Delete,
  Edit,
  Message,
  Search,
  Star,
} from '@element-plus/icons-vue'
    export default {
        name:"Search",
        setup(){

        },
        data(){
            return{
                search: ref(''),
                input1: ref(''),
                input2: ref(''),
                input3: ref(''),
                input4: ref(''),
                select12: ref(''),
                select21: ref(''),
                select22: ref(''),
                select31: ref(''),
                select32: ref(''),
                select41: ref(''),
                select42: ref(''),
                select52: ref(''),
            }
        }
        
    }

</script>
<style src="../style/Search.scss"></style>