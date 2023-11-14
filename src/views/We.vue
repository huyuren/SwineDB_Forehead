<template>
	<div class="common-layout">
		<div style="height: 10px;"></div>
		<div class="main_top">
			<h2 class="main_title">Contact:</h2>
			<div class="main_container">
				You can contact us at the email address below!
			</div>
			<div class="main_mail">
				<el-row :gutter="20" style="margin-top:10px;">
					<el-col :span="10">
						<el-card style="height: 100px"><el-icon>
								<Message />
							</el-icon>794301701@qq.com
						</el-card>
					</el-col>
					<el-col :span="14">
						<el-card style="height: 100px"><el-icon>
								<Message />
							</el-icon>huyuren@webmail.hzau.edu.cn</el-card>
					</el-col>
				</el-row>
			</div>
		</div>
		<div class="main_form">
			<div class="main_form_content">
				<h2>Feedback</h2>
				<div>
					<ul>
						<li>You can submit your feedback via the form below. Your comments will help us to build a better
							pig gut microbiology data platform!</li>
						<!-- <li>我们也希望，你可以参与到生猪肠道微生物数据平台的建设，可以将你们的研究数据提交到该系统中（每一位贡献者都会在该系统中被记录）。</li> -->
						<li>
							<p>We also hope that you will participate in the development of the pig gut microbiology data
								platform by submitting your research data to the system (each contributor will be recorded in the system).</p>
						</li>
					</ul>
				</div>
			</div>
		</div>
		<div>
			<div>
				<!-- 第2版 -->
				<!-- :model="mailRequest" @submit.prevent="sendMail" -->
				<!-- <el-form @submit.prevent="sendMail" label-width="160px">
					<el-form-item label="Your Email：" for="to">
						<el-input id="to" v-model="mailRequest.to" type="text" placeholder="please input your Email"
							required />
					</el-form-item>
					<el-form-item label="Topics：" for="subject">
						<el-select id="subject" v-model="mailRequest.subject" placeholder="please select your Topics"
							required>
							<el-option label="Feedback" value="Feedback" />
							<el-option label="Data sharing" value="Data sharing" />
							<el-option label="Others" value="Others" />
						</el-select>
					</el-form-item>
					<el-form-item label="Content：" for="text">
						<el-input id="text" v-model="mailRequest.text" type="textarea" required
							:autosize="{ minRows: 6, maxRows: 10 }" />
					</el-form-item>
					<el-form-item label="Attachment：(Optional)" for="attachment">
						<input id="attachment" @change="e => mailRequest.attachment = e.target.files[0]" type="file" />
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="sendMail">Submit</el-button>
					</el-form-item>
				</el-form> -->
				<!-- <div v-if="mailResult">{{ mailResult }}</div> -->
				<!-- 第三版 -->
				<el-form ref="mailForm"  @submit.prevent="submitForm" :model="mailRequest" :rules="mailRules" label-width="160px">
					<el-form-item label="Your Email：" prop="to">
						<el-input v-model="mailRequest.to" type="text" placeholder="please input your Email" />
					</el-form-item>
					<el-form-item label="Topics：" prop="subject">
						<el-select v-model="mailRequest.subject" placeholder="please select your Topics">
							<el-option label="Feedback" value="Feedback" />
							<el-option label="Data sharing" value="Data sharing" />
							<el-option label="Others" value="Others" />
						</el-select>
					</el-form-item>
					<el-form-item label="Content：" prop="text">
						<el-input v-model="mailRequest.text" type="textarea" :autosize="{ minRows: 6, maxRows: 10 }" />
					</el-form-item>
					<el-form-item label="Attachment：" prop="attachment">
						<input @change="handleFileChange" type="file" />
					</el-form-item>
					<el-form-item>
						<el-button type="primary" @click="submitForm">Submit</el-button>
					</el-form-item>
				</el-form>
				<div v-if="mailResult">{{ mailResult }}</div>
				<!-- 第一版 -->
				<!-- <form @submit.prevent="sendMail">
					<label for="to">Your Email：</label>
					<input id="to" v-model="mailRequest.to" type="text" required />
					<br />
					<label for="subject">Topics：</label>
					<input id="subject" v-model="mailRequest.subject" type="text" required />
					<br />
					<label for="text">Content：</label>
					<textarea id="text" v-model="mailRequest.text" required></textarea>
					<br />
					<label for="attachment">Attachment：</label>
					<input id="attachment" type="file" @change="e => mailRequest.attachment = e.target.files[0]" />
					<br />
					<button type="submit">Submit</button>
				</form> 
				<p>{{ mailResult }}</p> -->
			</div>
		</div>
		<footer>
			<p>Contact us at:</p>
			<p>Address:1 Shizishan Street, Hongshan District, Wuhan City, Hubei Province</p>
			<p>地址：湖北省武汉市洪山区狮子山街1号</p>

		</footer>
	</div>
</template>

<script>
import { ref } from 'vue';
import axios from 'axios';
import { ElMessage } from 'element-plus';
export default {
	name: 'We',
	setup() {
		// const commonRequest = getCurrentInstance().appContext.config.globalProperties.$commonRequest;
		// 定义邮件发送结果的响应式变量
		const mailResult = ref('');

		// 定义邮件请求对象的响应式变量
		const mailRequest = ref({
			to: '',
			subject: '',
			text: '',
			attachment: null
		});
		// 定义表单验证规则
		const mailRules = {
			to: [
				{ required: true, message: 'Please input your Email', trigger: 'blur' },
				{ type: 'email', message: 'Please input a valid Email', trigger: ['blur', 'change'] },
			],
			subject: [{ required: true, message: 'Please select your Topics', trigger: 'change' }],
			text: [{ required: true, message: 'Please input your Content', trigger: 'blur' }],
			attachment: [{ trigger: 'change' }],
		};
		// 处理附件输入框的文件变化事件
		const handleFileChange = (event) => {
			mailRequest.value.attachment = event.target.files[0];
		};
		// 定义发送邮件的方法
		// const sendMail = async () => {
		// 	try {
		// 		// await this.$refs.mailForm.validate();
		// 		const formData = new FormData();
		// 		formData.append('to', mailRequest.value.to);
		// 		formData.append('subject', mailRequest.value.subject);
		// 		formData.append('text', mailRequest.value.text);
		// 		if (mailRequest.value.attachment !== null) {
		// 			formData.append('attachment', mailRequest.value.attachment);
		// 		}
		// 		// formData.append('attachment', mailRequest.value.attachment);
		// 		// 发送邮件请求
		// 		const response = await axios.post('/api/mail/sendMail', formData, {
		// 			headers: {
		// 				'Content-Type': 'multipart/form-data',
		// 				'secret': 'secret',
		// 			}
		// 		});
		// 		// 清空表单元素中的值
		// 		console.log(mailRequest.value.subject);
		// 		mailRequest.value = {};
		// 		// 更新邮件发送结果
		// 		mailResult.value = response.data;
		// 		console.log(mailResult);
		// 	} catch (error) {
		// 		// 处理错误响应
		// 		mailResult.value = error.response.data;
		// 	}
		// };
		// 提交表单数据到服务器
		const submitForm = async () => {
			try {
				// 调用表单验证方法，验证表单是否通过验证
				// await this.$refs.mailForm.validate();
				// 创建 FormData 对象，将表单数据添加到其中
				const formData = new FormData();
				formData.append('to', mailRequest.value.to);
				formData.append('subject', mailRequest.value.subject);
				formData.append('text', mailRequest.value.text);
				if (mailRequest.value.attachment !== null) {
					formData.append('attachment', mailRequest.value.attachment);
				}
				// formData.append('attachment', mailRequest.value.attachment);
				// 发送 POST 请求到服务器，提交表单数据
				const response = await axios.post('/api/mail/sendMail', formData, {
					headers: {
						'Content-Type': 'multipart/form-data',
						'secret': 'secret',
					},
				});
				// 清空表单数据
				mailRequest.value = {
					to: '',
					subject: '',
					text: '',
					attachment: null,
				};
				console.log(response);
				mailResult.value = response.data;
				console.log(mailResult);
				// 显示成功消息
				ElMessage.success('Email sent successfully');
			} catch (error) {
				// 显示错误消息
				mailResult.value = error.response.data;
				ElMessage.error('Failed to send email: ' + error.response.data);
			}
		};
		// 返回模板中需要用的变量和方法
		return {
			mailRequest,
			mailResult,
			mailRules,
			// sendMail,
			handleFileChange,
			submitForm,
		};
	}
};
</script>

<style scoped>
.common-layout {
	background-image: url("../assets/img/8.jpg");

}

.common-layout h1 {
	margin: 0;
	font-size: 36px;
}

.main_top {
	/* display: flex; */
	text-align: center;
	font-size: 16px;
	padding: 20px;
	border: 1px solid #ccc;
	width: 80%;
	margin: 0 auto;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 5px;
	margin-bottom: 20px;

}

.main_top .main_mail {
	display: flex;
	justify-content: center;
}

.el-row {
	margin-bottom: 20px;
	display: flex;
	flex-wrap: wrap;
	text-align: center;
}

.el-row .el-card {
	min-width: 100%;
	height: 100%;
	margin-right: 20px;
	transition: all .5s;
	/* box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); */
	opacity: 0.8;

}

.main_form_content {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	font-size: 16px;
	padding: 20px;
	border: 1px solid #ccc;
	width: 80%;
	margin: 0 auto;
	box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
	border-radius: 5px;

}

.main_form_content li {
	list-style: none;
}

.common-layout form {
	margin: 50px auto;
	width: 50%;
	background-color: #c1e0f4;
	padding: 20px;
	border-radius: 10px;
	box-shadow: 0px 0px 10px #bed5f5;
}

label {
	display: block;
	margin-bottom: 10px;
	color: #333;
}

input[type="text"],
input[type="email"],
textarea {
	width: 100%;
	padding: 10px;
	border-radius: 5px;
	border: none;
	margin-bottom: 20px;
	box-sizing: border-box;
	font-size: 16px;
	background-color: #c1e0f4;
}

textarea {
	height: 150px;
}

input[type="submit"] {
	background-color: #333;
	color: #cadef9;
	padding: 10px 20px;
	border: none;
	border-radius: 5px;
	cursor: pointer;
	font-size: 16px;
}

input[type="submit"]:hover {
	background-color: #555;
}

footer {
	background-color: #333;
	color: #fff;
	padding: 3px;
	text-align: center;
}
</style>  