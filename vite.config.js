import { defineConfig } from 'vite'
import vue from '@vitejs/plugin-vue'
import { resolve } from 'path'
import * as path from 'path';
import AutoImport from "unplugin-auto-import/vite";
//自动导入ui-组件 比如说ant-design-vue  element-plus等
import Components from 'unplugin-vue-components/vite';
import { ElementPlusResolver } from "unplugin-vue-components/resolvers";

import ElementPlus from 'unplugin-element-plus/vite'


export default defineConfig({
  plugins: [vue(),
  ElementPlus(),
  AutoImport({
    //plus按需引入
    resolvers: [ElementPlusResolver(),
      // Auto import icon components
      // 自动导入图标组件
  //     IconsResolver({
  //     prefix: 'Icon',
  // })
              ],
    //引入vue 自动注册api插件
    //安装两行后你会发现在组件中不用再导入ref，reactive等
    imports: ['vue', 'vue-router', 'vuex'],
    //存放的位置
    dts: "src/auto-import.d.ts",
    }),
  Components({
    //plus按需引入
    resolvers: [ElementPlusResolver()],
    // 配置需要将哪些后缀类型的文件进行自动按需引入
    extensions: ["vue", "md"],
    // 引入组件的,包括自定义组件
    // 存放的位置
    dts: "src/components.d.ts",
  }),
  ],
  optimizeDeps: {
    include: ['vue'],
  },
  lintOnSave: false,
  base: './', //不加打包后白屏
  // 打包配置

  build: {
    chunkSizeWarningLimit: 2000,
    assetsPublicPath:'./',
    outDir: 'js-super-web', // 生成输出的根目录。如果该目录存在，则会在生成之前将其删除。 默认文件夹名称为dist
    target: 'esnext',
    terserOptions: {
      compress: {
        drop_console: true, // 生产环境去掉控制台 console
        drop_debugger: true, // 生产环境去掉控制台 debugger 默认就是true
        dead_code: true, // 删除无法访问的代码 默认就是true
      }
    },
    rollupOptions: {
      output: {
        manualChunks(id) {
          if (id.includes('node_modules')) {
            return id.toString().split('node_modules/')[1].split('/')[0].toString();
          }
        }
      } 
    }
  },
  resolve: {
    //别名配置，引用src路径下的东西可以通过@如：import Layout from '@/layout/index.vue'
    alias: [
      {
        find: '@',
        replacement: resolve(__dirname, 'src'),
        // "@": path.resolve("./src"), // @代替src
        "~": path.resolve("./src/components"), // @代替src/components
      }
    ]
  },
  server: {
    // 使用IP能访问
    host: "0.0.0.0",
    port: 5100,
    // 热更新
    hmr: true,
    proxy: {
      "/api": {
        target: "http://122.205.95.110:8090",
        // target: "http://localhost:8080",
        changeOrigin: true,
        rewrite: (path) => path.replace(/^\/api/, ""),
      }
    }
  },
})