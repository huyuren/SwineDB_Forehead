<template>
    <div>
        <div class="browse-bg"></div>
        <ul>
            <el-button class="samples" :style="{ color: samplesColor }" @click="showForm1Fn($evt)" type="text"
                icon="el-icon-edit">
                Samples/Runs
            </el-button>

            <el-button class="browse-projects" :style="{ color: projectsColor }" @click="showForm2Fn($evt)" type="text"
                icon="el-icon-edit">
                Projects
            </el-button>
        </ul>

        <div class="line1" :style="{ backgroundColor: line1Color }"></div>
        <div class="line2" :style="{ backgroundColor: line2Color }"></div>

        <el-form id="form1" v-show="showForm1" class="samples-form">
            <el-form-item>
                <div>
                    <el-select class="sb-1" v-model="valuea11" placeholder="Breed">
                        <el-option v-for="item in options11" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="sb-2" v-model="valuea12" collapse-tags placeholder="Growth Stage"
                        style="width: 240px">
                        <el-option v-for="item in options12" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="sb-3" v-model="valuea13" collapse-tags collapse-tags-tooltip
                        placeholder="Isolation Location" style="width: 240px">
                        <el-option v-for="item in options13" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="sb-4" v-model="valuea14" collapse-tags collapse-tags-tooltip :max-collapse-tags="3"
                        placeholder="Phenotypes" style="width: 240px">
                        <el-option v-for="item in options14" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
            </el-form-item>
            <el-form-item>
                <div>
                    <el-select class="ss-1" v-model="valuea21" placeholder="Breed">
                        <el-option v-for="item in options11" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="ss-2" v-model="valuea22" collapse-tags placeholder="Growth Stage"
                        style="width: 240px">
                        <el-option v-for="item in options22" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="ss-3" v-model="valuea23" collapse-tags collapse-tags-tooltip
                        placeholder="Isolation Location" style="width: 240px">
                        <el-option v-for="item in options13" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="ss-4" v-model="valuea24" collapse-tags collapse-tags-tooltip :max-collapse-tags="3"
                        placeholder="Phenotypes" style="width: 240px">
                        <el-option v-for="item in options24" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
            </el-form-item>
            <el-form-item>
                <div>
                    <el-select class="sp-1" v-model="valuea31" placeholder="Breed">
                        <el-option v-for="item in options11" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="sp-2" v-model="valuea32" collapse-tags placeholder="Growth Stage"
                        style="width: 240px">
                        <el-option v-for="item in options32" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="sp-3" v-model="valuea33" collapse-tags collapse-tags-tooltip
                        placeholder="Isolation Location" style="width: 240px">
                        <el-option v-for="item in options13" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="sp-4" v-model="valuea34" collapse-tags collapse-tags-tooltip :max-collapse-tags="3"
                        placeholder="Phenotypes" style="width: 240px">
                        <el-option v-for="item in options34" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
            </el-form-item>
            <el-form-item>
                <div>
                    <el-select class="sg-1" v-model="valuea41" placeholder="Breed">
                        <el-option v-for="item in options11" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="sg-2" v-model="valuea42" collapse-tags placeholder="Growth Stage"
                        style="width: 240px">
                        <el-option v-for="item in options42" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="sg-3" v-model="valuea43" collapse-tags collapse-tags-tooltip
                        placeholder="Isolation Location" style="width: 240px">
                        <el-option v-for="item in options13" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="sg-4" v-model="valuea44" collapse-tags collapse-tags-tooltip :max-collapse-tags="3"
                        placeholder="Phenotypes" style="width: 240px">
                        <el-option v-for="item in options44" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
            </el-form-item>

            <div class="samples-boar-bg">
                <div class="projects-boar-bg-1">
                    <p class="projects-boar">Boar</p>
                </div>
            </div>

            <div class="samples-sow-bg">
                <div class="projects-sow-bg-1">
                    <p class="projects-sow">Sow</p>
                </div>
            </div>

            <div class="samples-piglets-bg">
                <div class="projects-piglets-bg-1">
                    <p class="projects-piglets">Piglets</p>
                </div>
            </div>

            <div class="samples-gf-bg">
                <div class="projects-gf-bg-1">
                    <p class="projects-gf">Growing-finishing Pigs</p>
                </div>
            </div>
            <el-button type="success" class="samples-sub">Submit</el-button>
        </el-form>

        <el-form id="form2" v-show="showForm2" class="projects-form">
            <el-form-item>
                <span slot="label">
                    <span class="projects-form-country">Country</span>
                </span>
                <el-checkbox-group v-model="form.type">
                    <el-checkbox label="China" name="type" />
                    <el-checkbox label="USA" name="type" />
                    <el-checkbox label="Netherlands" name="type" />
                    <el-checkbox label="Spain" name="type" />
                    <el-checkbox label="Italian" name="type" />
                </el-checkbox-group>
                <el-select class="other-country" v-model="country" placeholder="Other Country">
                    <el-option v-for="item in countries" :key="item.value" :label="item.label" :value="item.value" />
                </el-select>
            </el-form-item>
            <el-form-item>
                <div>
                    <el-select class="pb-1" v-model="valueb11" placeholder="Breed">
                        <el-option v-for="item in options11" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="pb-2" v-model="valueb12" collapse-tags placeholder="Growth Stage"
                        style="width: 240px">
                        <el-option v-for="item in options12" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="pb-3" v-model="valueb13" collapse-tags collapse-tags-tooltip
                        placeholder="Isolation Location" style="width: 240px">
                        <el-option v-for="item in options13" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="pb-4" v-model="valueb14" collapse-tags collapse-tags-tooltip :max-collapse-tags="3"
                        placeholder="Phenotypes" style="width: 240px">
                        <el-option v-for="item in options14" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
            </el-form-item>
            <el-form-item>
                <div>
                    <el-select class="ps-1" v-model="valueb21" placeholder="Breed">
                        <el-option v-for="item in options11" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="ps-2" v-model="valueb22" collapse-tags placeholder="Growth Stage"
                        style="width: 240px">
                        <el-option v-for="item in options22" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="ps-3" v-model="valueb23" collapse-tags collapse-tags-tooltip
                        placeholder="Isolation Location" style="width: 240px">
                        <el-option v-for="item in options13" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="ps-4" v-model="valueb24" collapse-tags collapse-tags-tooltip :max-collapse-tags="3"
                        placeholder="Phenotypes" style="width: 240px">
                        <el-option v-for="item in options24" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
            </el-form-item>
            <el-form-item>
                <div>
                    <el-select class="pp-1" v-model="valueb31" placeholder="Breed">
                        <el-option v-for="item in options11" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="pp-2" v-model="valueb32" collapse-tags placeholder="Growth Stage"
                        style="width: 240px">
                        <el-option v-for="item in options32" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="pp-3" v-model="valueb33" collapse-tags collapse-tags-tooltip
                        placeholder="Isolation Location" style="width: 240px">
                        <el-option v-for="item in options13" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="pp-4" v-model="valueb34" collapse-tags collapse-tags-tooltip :max-collapse-tags="3"
                        placeholder="Phenotypes" style="width: 240px">
                        <el-option v-for="item in options34" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
            </el-form-item>
            <el-form-item>
                <div>
                    <el-select class="pg-1" v-model="valueb41" placeholder="Breed">
                        <el-option v-for="item in options11" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="pg-2" v-model="valueb42" collapse-tags placeholder="Growth Stage"
                        style="width: 240px">
                        <el-option v-for="item in options42" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="pg-3" v-model="valueb43" collapse-tags collapse-tags-tooltip
                        placeholder="Isolation Location" style="width: 240px">
                        <el-option v-for="item in options13" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
                <div>
                    <el-select class="pg-4" v-model="valueb44" collapse-tags collapse-tags-tooltip :max-collapse-tags="3"
                        placeholder="Phenotypes" style="width: 240px">
                        <el-option v-for="item in options44" :key="item.value" :label="item.label" :value="item.value" />
                    </el-select>
                </div>
            </el-form-item>

            <div class="projects-boar-bg">
                <div class="projects-boar-bg-1">
                    <p class="projects-boar">Boar</p>
                </div>
            </div>

            <div class="projects-sow-bg">
                <div class="projects-sow-bg-1">
                    <p class="projects-sow">Sow</p>
                </div>
            </div>

            <div class="projects-piglets-bg">
                <div class="projects-piglets-bg-1">
                    <p class="projects-piglets">Piglets</p>
                </div>
            </div>

            <div class="projects-gf-bg">
                <div class="projects-gf-bg-1">
                    <p class="projects-gf">Growing-finishing Pigs</p>
                </div>
            </div>
            <el-button type="success" class="sub">Submit</el-button>

        </el-form>
    </div>
    <!-- 前面元素已脱离文档流，直接用盒子模型，将内容往下排列 -->
    <div style="height: 700px;"></div>
    <div style=" width: 90%; text-align: center;margin: 0 auto;">
        <div class="Datatable_title">ProjectXXXX</div>
        <div style="overflow-x: auto;">
            <DataTable :data="data" class="display" width="100%">
                <thead>
                    <tr>
                        <th>Project ID</th>
                        <th>Research Topic</th>
                        <th>Country</th>
                        <th>Breed</th>
                        <th>Category</th>
                        <th>Growth Stage</th>
                        <th>Isolation Location</th>
                        <th>Weight</th>
                        <th>Age</th>
                        <th>Sex </th>
                        <th>Feed composition </th>
                        <th>Nutritional Composition</th>
                        <th>Platform</th>
                        <th>Sample number</th>
                        <th>Title </th>
                        <th>Link </th>
                        <th>Release Date</th>
                        <th>Experimental Design</th>
                        <th>Phenotypes</th>
                    </tr>
                </thead>
        
            </DataTable>
            <!-- https://www.ncbi.nlm.nih.gov/bioproject/?term=PRJNA64409 -->
        </div>
    </div>
    <div style="background-color: aquamarine;height: 100px;"></div>

    <div>
        <div class="Project_info_title">
            <span >PRJEB31015</span>
        </div>
        <div class="Project_info_description">
            Project ID: PRJEB31015
Project description: Colonic mucosal microbiota directly adheres to intestinal enterocytes and may be considered as a platform offering host and environment interactions. This study attempts to compare the structural difference of the colonic mucosal microbiota in CRC patients and healthy volunteers, for a better understanding of the roles of mucosal microbiota in etiology of CRC. Paired biopsy samples were obtained during colonoscopy from the tumor tissue and the adjacent tissue of 36 CRC patients. Biopsies from 18 healthy individuals were collected as controls. Mucosal microbiota was profiled by 16S rRNA gene 454 pyrosequencing, and multivariate statistics were used to analyze the sequence data
Curators' note: NA
Total number of runs: 130
nr. of processed runs: 130 (100.00%)
nr. of runs with abundance information: 130 (100.00%)
nr. of failed runs: 0 (0.00%)
Associated phenotypes:
Health  
Colorectal Neoplasms  
Related publications: NA
This is a  curated project, scroll to page bottom to see more stats.
        </div>
    </div>
<div style="background-color: rgb(198, 201, 200);height: 100px;"></div>

    <div class="browse-footer">
        <div class="footer-info">
            © 2021-2023 XXXX Pig Microbiome @Yan Lab. All Rights Reserved.<br>
            National Key Laboratory of Agricultural Microbiology<br>
            Frontiers Science Center for Animal Breeding and Sustainable Production<br>
            Huazhong Agricultural University
        </div>
    </div>
</template>
<script>
import axios from 'axios';
import { useRouter, useRoute } from 'vue-router'
import DataTable from 'datatables.net-vue3';
import DataTablesCore from 'datatables.net';
DataTable.use(DataTablesCore);
export default {
    name: "Browse",
    components: {
        DataTable, // 注册 DataTable 组件
    },
    setup() {

    },
    data() {
        return {
            data: [
                ['PRJNA629013', 'Burn resuscitation strategy influences the gut microbiota-liver axis in swine', 'USA', 'Yorkshire', 'Growing-finishing Pigs', '20-50kg（生长育肥猪）', 'rectal', '41.0 ± 3.1 kg', 90, 'female ', 'info', 'info', 'Illumina MiSeq', 12, 'Burn resuscitation strategy influences the gut microbiota-liver axis in swine.', 'https://pubmed.ncbi.nlm.nih.gov/32973266/', '2013/4/7', '烧伤复苏', 'NA'],
                ['PRJEB31015', 'Colonic microbiome profiles for improved feed efficiency can be identified despite major effects of farm of origin and contemporary group in pigs', 'Ireland', 'Large White × Landrace', 'Piglets', '断奶后（仔猪）', 'colon', 15, '', 'male', 'info', 'info', 'Illumina MiSeq', 17, 'Colonic microbiome profiles for', 'https://pubmed.ncbi.nlm.nih.gov/32605690/', '2016/4/23', 'NA', '日增重;日采食量;饲料转化效率'],
            ],
            form: {
                name: '',
                region: '',
                date1: '',

                date2: '',
                delivery: false,
                type: [],
                resource: '',
                desc: '',

            },
            showForm1: true,
            showForm2: false,
            samplesColor: 'rgba(28, 104, 61, 1)',
            projectsColor: 'rgba(0, 0, 0, 1)',
            line1Color: 'rgba(28, 104, 61, 1)',
            line2Color: 'rgba(166, 166, 166, 1)',
            country: ref([]),

            valuea11: ref([]),
            valuea12: ref([]),
            valuea13: ref([]),
            valuea14: ref([]),

            valuea21: ref([]),
            valuea22: ref([]),
            valuea23: ref([]),
            valuea24: ref([]),

            valuea31: ref([]),
            valuea32: ref([]),
            valuea33: ref([]),
            valuea34: ref([]),

            valuea41: ref([]),
            valuea42: ref([]),
            valuea43: ref([]),
            valuea44: ref([]),

            valueb11: ref([]),
            valueb12: ref([]),
            valueb13: ref([]),
            valueb14: ref([]),

            valueb21: ref([]),
            valueb22: ref([]),
            valueb23: ref([]),
            valueb24: ref([]),

            valueb31: ref([]),
            valueb32: ref([]),
            valueb33: ref([]),
            valueb34: ref([]),

            valueb41: ref([]),
            valueb42: ref([]),
            valueb43: ref([]),
            valueb44: ref([]),

            countries: [
                {
                    value: 'Country1',
                    label: 'Country1',
                },
                {
                    value: 'Country2',
                    label: 'Country2',
                },
                {
                    value: 'Country3',
                    label: 'Country3',
                },
                {
                    value: 'Country4',
                    label: 'Country4',
                },
                {
                    value: 'Country5',
                    label: 'Country5',
                },
            ],

            options11: [
                {
                    value: 'Bama mini-piglets',
                    label: 'Bama mini-piglets',
                },
                {
                    value: 'Duroc',
                    label: 'Duroc',
                },
                {
                    value: 'Duroc × Iberian',
                    label: 'Duroc × Iberian',
                },
                {
                    value: 'Duroc × Landrace × Yorkshire',
                    label: 'Duroc × Landrace × Yorkshire',
                },
                {
                    value: 'Duroc × (Danish Landrace×Yorkshire)',
                    label: 'Duroc × (Danish Landrace×Yorkshire)',
                },
                {
                    value: 'Göttingen minipigs',
                    label: 'Göttingen minipigs',
                },
                {
                    value: 'Landrace × Yorkshire',
                    label: 'Landrace × Yorkshire',
                },
                {
                    value: '(Landrace × Yorkshire) × Piétrain',
                    label: '(Landrace × Yorkshire) × Piétrain',
                },
                {
                    value: 'Meishan pigs',
                    label: 'Meishan pigs',
                },
                {
                    value: 'Norwegian Landrace',
                    label: 'Norwegian Landrace',
                },
                {
                    value: 'Shanxi Black Pigs',
                    label: 'Shanxi Black Pigs',
                },
                {
                    value: 'Topigs-20',
                    label: 'Topigs-20',
                },
                {
                    value: 'Tempo × Topigs-21',
                    label: 'Tempo × Topigs-21',
                },
                {
                    value: 'Top Pi × Topigs 20',
                    label: 'Top Pi × Topigs 20',
                },
                {
                    value: 'Topics hybrid × Piétrain',
                    label: 'Topics hybrid × Piétrain',
                },
                {
                    value: 'Yorkshire',
                    label: 'Yorkshire',
                },
            ],
            options12: [
                {
                    value: 'Reserve boar',
                    label: 'Reserve boar',
                },
                {
                    value: 'stud boars',
                    label: 'stud boars',
                },
            ],
            options13: [
                {
                    value: 'Stomach',
                    label: 'Stomach',
                },
                {
                    value: 'Duodenum',
                    label: 'Duodenum',
                },
                {
                    value: 'Jejunum',
                    label: 'Jejunum',
                },
                {
                    value: 'Ileum',
                    label: 'Ileum',
                },
                {
                    value: 'Cecum',
                    label: 'Cecum',
                },
                {
                    value: 'Colon',
                    label: 'Colon',
                },
                {
                    value: 'Colon',
                    label: 'Colon',
                },
                {
                    value: 'Fecal',
                    label: 'Fecal',
                },
            ],
            options14: [
                {
                    value: 'Description of erotic behaviour',
                    label: 'Description of erotic behaviour',
                },
                {
                    value: 'semen quality',
                    label: 'semen quality',
                },
            ],
            options22: [
                {
                    value: 'gilts',
                    label: 'gilts',
                },
                {
                    value: 'pregnancy',
                    label: 'pregnancy',
                },
                {
                    value: 'lactation',
                    label: 'lactation',
                },
                {
                    value: 'non-pregnant',
                    label: 'non-pregnant',
                },
                {
                    value: 'multiparity',
                    label: 'multiparity',
                },
                {
                    value: 'primiparity',
                    label: 'primiparity',
                },
            ],
            options24: [
                {
                    value: 'litter size',
                    label: 'litter size',
                },
                {
                    value: 'oestrous interval',
                    label: 'oestrous interval',
                },
                {
                    value: 'weaning nest weight',
                    label: 'weaning nest weight',
                },
                {
                    value: 'service life',
                    label: 'service life',
                },
                {
                    value: 'Piglet survival rate',
                    label: 'Piglet survival rate',
                },
                {
                    value: 'Piglet diarrhoea rate',
                    label: 'Piglet diarrhoea rate',
                },
            ],
            options32: [
                {
                    value: 'Suckling(Pre-weaning)',
                    label: 'Suckling(Pre-weaning)',
                },
                {
                    value: 'After weaning',
                    label: 'After weaning',
                },
            ],
            options34: [
                {
                    value: 'Survival rate',
                    label: 'Survival rate',
                },
                {
                    value: 'Diarrhoea rate',
                    label: 'Diarrhoea rate',
                },
                {
                    value: 'Weaning weight',
                    label: 'Weaning weight',
                },
                {
                    value: 'Meat ratio',
                    label: 'Meat ratio',
                },
                {
                    value: 'Daily weight gain',
                    label: 'Daily weight gain',
                },
            ],
            options42: [
                {
                    value: '20-50kg',
                    label: '20-50kg',
                },
                {
                    value: '50-80kg',
                    label: '50-80kg',
                },
                {
                    value: '16-20 weeks',
                    label: '16-20 weeks',
                },
                {
                    value: '20-28 weeks',
                    label: '20-28 weeks',
                },
            ],
            options44: [
                {
                    value: 'Meat quality',
                    label: 'Meat quality',
                },
                {
                    value: 'Leanness',
                    label: 'Leanness',
                },
                {
                    value: 'Survival rate',
                    label: 'Survival rate',
                },
                {
                    value: 'Diarrhoea rate',
                    label: 'Diarrhoea rate',
                },
                {
                    value: 'Weaning weight',
                    label: 'Weaning weight',
                },
                {
                    value: 'Meat ratio',
                    label: 'Meat ratio',
                },
                {
                    value: 'Daily weight gain',
                    label: 'Daily weight gain',
                },
            ],
        }
    },
    methods: {
        showForm1Fn(event) {
            this.showForm1 = true;
            this.showForm2 = false;
            this.line1Color = 'rgba(28, 104, 61, 1)';
            this.line2Color = 'rgba(166, 166, 166, 1)';
            this.samplesColor = 'rgba(28, 104, 61, 1)';
            this.projectsColor = 'rgba(0, 0, 0, 1)';
            event.target.parentNode.blur();
            event.target.blur();
        },
        showForm2Fn(event) {
            this.showForm1 = false;
            this.showForm2 = true;
            this.projectsColor = 'rgba(28, 104, 61, 1)';
            this.samplesColor = 'rgba(0, 0, 0, 1)';
            this.line2Color = 'rgba(28, 104, 61, 1)';
            this.line1Color = 'rgba(166, 166, 166, 1)';
            event.target.blur();
        },
    }
   
  
}
</script>
<style src="../style/Browse.scss"></style>