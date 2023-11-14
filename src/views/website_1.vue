<template>
	<div class="all-context">
		<div class="swiper">
			<swiper :modules="modules" :options="swiperOptions" :loop="true" :centeredSlides="true" :space-between="50"
				:autoplay="{ delay: 5000, disableOnInteraction: false }" navigation :pagination="{ clickable: true }"
				:scrollbar="{ draggable: true }" @slideChange="onSlideChange" @swiper="onSwiper">
				<swiper-slide><img src="../assets/img/pig1.jpg" alt=""></swiper-slide>
				<swiper-slide><img src="../assets/img/feed1.jpg" alt=""></swiper-slide>
				<swiper-slide><img src="../assets/img/Campylobacter.jpg" alt=""></swiper-slide>
				<swiper-slide><img src="../assets/img/yanlaoshiteam.jpg" alt=""></swiper-slide>
			</swiper>
		</div>
		<div class="header_title">
			<div>
				<h1>Welcome to <span style="color: pink;">PGMD</span>！</h1>
			</div>
			<div class="header_title_container">
				Animal gut microbiome plays important roles in the health and diseases of animal hosts. In recent years, the number and total volume of animal gut metagenomic data (including both 16S and metagenomic sequencing data) have been increasing rapidly. Here, we present the ADDAGMA (A Database for Domestic Animal Gut Microbiome Atlas) database which hosts all publicly available microbe-phenotype associations data from domestic animal species. Users can browse, search and download data of interest on gut microbiota associated with phenotype.
    AMDB provides four main functions: 1) Taxa: It shows samples enriched with the bacterial taxon of interest.
	 2) Samples: It provides the gut microbiota composition of the sample of interest.
	  3) Projects/Hosts: It gives you summary information for the project or the host. 
	4) Visualization: It visually presents valuable information about the relationships between the samples and the gut microbiota.
			</div>
		</div>
		<main>
			<div class="main-container">
				<article>
					<h2>研究方向</h2>
					<p>我们的研究方向是生猪肠道微生物的多样性和种类研究，以及微生物在生猪生长发育、健康和疾病等方面的作用机制研究。</p>
				</article>
				<article>
					<h2>研究成果</h2>
					<p>我们的研究成果包括多篇SCI论文和多项国家级课题，涉及生猪肠道微生物的多样性、代谢功能、作用机制等方面。</p>
				</article>
				<article>
					<h2>团队介绍</h2>
					<p>我们的团队由多名硕士和本科生组成，拥有丰富的科研经验和实践能力。</p>
				</article>
			</div>
			<div class="wrap1">
				<iframe ref="iframe" :src="htmlSrcs1" width="100%" height="100%" frameborder="0">
				</iframe>
			</div>
		</main>
		<div class="notices-container">
			<div class="notices-title">
				<h2>Update Records</h2>
			</div>
			<el-scrollbar height="200px" :min-size=10>
				<div class="notices-list-container">

					<ul class="notices-list">
						<!-- <el-scrollbar  height="200px" :min-size=10> -->
						<li v-for="notice in notices" :key="notice.id" class="notice-item">
							<h4 class="notice-title">{{ notice.title }}</h4>
							<p class="notice-content">{{ notice.content }}</p>
							<p class="notice-time">{{ notice.startTime }} - {{ notice.endTime }}</p>
							<!-- <div class="notice-divider"></div> -->
						</li>
						<!-- </el-scrollbar> -->
					</ul>

				</div>
			</el-scrollbar>
		</div>

		<div>
			<!-- <h1>{{ pageName }}</h1> -->
			<p>访问量：{{ count }}</p>
		</div>
		<footer>
			<p>版权所有 © 生猪肠道微生物官网</p>
		</footer>
	</div>
</template>

<script>
// Import Swiper Vue.js components
import { Swiper, SwiperSlide } from 'swiper/vue';
import { Navigation, Pagination, Scrollbar, A11y, Autoplay } from 'swiper';
// Import Swiper styles
import 'swiper/css';
import 'swiper/swiper-bundle.css';
import { ElScrollbar } from 'element-plus'
import axios from 'axios';
import { useRouter, useRoute } from 'vue-router'
export default {
	components: {
		Swiper,
		SwiperSlide,
	},
	setup() {
		const route = useRoute();
		const notices = ref([]);
		// console.log(route.path.split('/').pop());
		const pageName = ref('website'); // 获取路径中的最后一个部分
		const count = ref(parseInt(localStorage.getItem(`${pageName}-count`)) || 0); // 页面访问量
		const commonRequest = getCurrentInstance().appContext.config.globalProperties.$commonRequest;
		// const swiperRef = ref(null); // define swiper as a ref
		// const onSwiper = (swiper) => {
		//   console.log(swiper);
		// };
		// const onSlideChange = () => {
		//   console.log('slide change');
		// };

		// 定义一个获取页面访问量的方法

		const getCount = () => {
			// axios.get(`/api/counter/${pageName}`, {
			//   headers: {
			//     'secret': 'secret',
			//   },
			// })
			commonRequest({
				method: "get",
				url: `/counter/${pageName.value}`,
				headers: {
					'secret': 'secret',
				},
			})
				.then(res => {
					if (res.status === 404) {
						// 如果页面不存在，则说明是第一次访问该页面，需要重新创建页面并更新页面访问量
						axios.post(`/api/counter/${pageName.value}`, null, {
							headers: {
								'secret': 'secret',
							},
						}); // 调用后端的创建计数器接口
						count.value = 1; // 将页面访问量赋值为1
						localStorage.setItem(`${pageName}-count`, count.value); // 存储页面访问量
					} else {
						// console.log(res.data);
						count.value = res.data.count; // 更新页面访问量
						localStorage.setItem(`${pageName}-count`, count.value); // 存储页面访问量
					}
				}).catch(error => {
					// console.log(error); // 输出错误信息
				});
		};

		const updateCount = () => {
			// const url = `/counter/${pageName}`; // 接口的地址
			axios.post(`/api/counter/${pageName.value}`, null, {
				headers: {
					'secret': 'secret',
				},
			}).catch(error => {
				// console.log(error); // 输出错误信息
			});

			// const res =  commonRequest.post(url,config); // 调用后端的更新计数器接口
		};

		onMounted(() => {
			axios.get('/api/notice/get', {
				headers: {
					'secret': 'secret',
				},
			})
				.then(response => {
					notices.value = response.data.data.notices;
					// console.log(notices.value);
				})
				.catch(error => {
					console.error(error);
				});
			getCount();
			setInterval(getCount, 10000);
			// updateCount()
		});
		// commonRequest({
		// 	method: "post",
		// 	url: '/login/user',
		// 	data: {
		// 		name: commonUser.name,
		// 		password: commonUser.password,
		// 	},
		// 	headers: {
		// 		'secret': 'secret',
		// 	},
		// })
		onBeforeUnmount(() => {
			updateCount()
			console.log('销毁之前==');
		})
		return {
			// swiperRef,
			// onSwiper,
			// onSlideChange,
			// swiperOptions,
			// scroll,
			pageName,
			count,
			notices,
			modules: [Navigation, Pagination, Scrollbar, A11y, Autoplay],
			// htmlSrc: '/public/static/test1.html',
			htmlSrcs1: '../../public/static/test2.html',
		};

	},
}
</script>


<style scoped>
body {
	font-family: Arial, sans-serif;
	background-color: #f2f2f2;
	margin: 0;
	padding: 0;
}

/* .all-context {
	background-image: url("../assets/img/8.jpg");
} */


.swiper {
	border-radius: 5px;
	width: 90%;
	height: 600px;
}

.swiper img {
	display: block;
	margin: 0 auto;
	width: 90vw;
	/* margin: 0 10vw; */
	height: 100%;
	object-fit: cover;
	/* object-position:  30%; */
	object-position:  50% 45%;
}
header {
	background-color: #333;
	color: #fff;
	padding: 20px;
	text-align: center;
}

h1 {
	margin: 0;
	font-size: 36px;
	font-weight: normal;
}

main {
	padding: 20px;
	display: flex;
	flex-wrap: wrap;
	justify-content: center;
	align-items: center;

}

.header_title {
	
	margin-top: 10px;
	text-align: center;
}
.header_title h1{
	font-size: 60px;
}
.header_title_container {
	display: flex;
	justify-content: center;
	align-items: center;
	height: 220px;
	width: 80VW;
	background-color: #b6b0b3;
	margin: 10px auto;
	font-size: 15px;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
	opacity: 0.9;
	border-radius: 5px;
}

main .main-container {
	display: flex;
	justify-content: space-between;
}

main .main-container article {
	/* background-color: #fff; */
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.2);
	margin: 10px;
	padding: 20px;
	border-radius: 5px;
	min-width: 300px;
	max-width: 395px;
	text-align: center;
}

main .main-container article h2 {
	margin: 0;
	font-size: 24px;
	font-weight: normal;
}

main .main-container article p {
	margin: 10px 0;
	font-size: 16px;
	line-height: 1.5;
}


/* .notice-divider {
  height: 1px;
  background-color: #ccc;
  margin-top: 10px;
  margin-bottom: 10px;
} */
.notices-container {
	flex-direction: column;
	display: flex;
	/* justify-content: center; */
	align-items: center;
	padding: 20px;
	/* width: 800px; */
	/* background-color: #f2f2f2; */
	/* background: var(--el-color-primary-light-9); */
}

.notices-title {
	font-size: 28px;
	font-weight: bold;
	margin-bottom: 20px;
	text-align: left;
	font-family: Arial, sans-serif;
	/* 更改字体 */
}

.notices-list-container {
	width: 600px;
	max-height: 400px;
	/* 设置容器最大高度，超出部分会出现滚动条 */
	/* overflow-y: scroll; */
	/* 显示垂直滚动条 */
	display: flex;
	align-items: center;
	justify-content: center;
	background: var(--el-color-primary-light-9);
	border-radius: 5px;
}

.notices-list {
	list-style: none;
	padding: 0;
	margin: 0;
	/* 去除默认的 margin */
	/* display: flex;
	align-items: center;
	justify-content: center; */
	/* height: 50px; */
	/* margin: 10px; */
	text-align: center;
	border-radius: 4px;
	/* background: var(--el-color-primary-light-9); */
	color: var(--el-color-primary);
}

.notice-item {
	margin-bottom: 20px;
	border-bottom: 1px solid #ccc;
	/* 添加分割线 */
	text-align: left;
	/* 左对齐 */
}

.notice-title {
	font-size: 24px;
	margin-bottom: 10px;
	font-family: Arial, sans-serif;
	/* 更改字体 */
}

.notice-content {
	font-size: 16px;
	margin-bottom: 10px;
	font-family: Arial, sans-serif;
	/* 更改字体 */
	text-indent: 2em;
}

.notice-time {
	font-size: 14px;
	color: #999;
	margin-bottom: 10px;
	font-family: Arial, sans-serif;
	/* 更改字体 */
}

footer {
	background-color: #333;
	color: #fff;
	padding: 10px;
	text-align: center;
}

footer p {
	margin: 0;
}

.wrap {
	width: 50%;
	height: 350px;
}

.wrap1 {
	width: 100%;
	height: 350px;
}
</style>