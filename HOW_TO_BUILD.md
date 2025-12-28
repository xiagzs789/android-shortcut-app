# 快捷指令 Android 应用

## 如何获取APK

### 方法1：使用GitHub远程构建（最简单）

1. 访问 https://github.com/new 创建一个新仓库
2. 将这个项目的所有文件上传到您的GitHub仓库
3. 上传后，GitHub会自动运行构建流程
4. 进入 "Actions" 标签页
5. 点击最新的工作流程
6. 等待构建完成
7. 下载生成的APK文件

### 方法2：使用Android Studio（本地构建）

1. 下载安装 Android Studio
2. 将整个项目文件夹导入 Android Studio
3. 点击 Build > Build Bundle(s) / APK(s)
4. 系统会自动构建APK文件

## 应用功能

这个应用完全按照您的要求设计：
- 点击图标时显示"正在执行指令"
- 读取剪贴板内容
- 发送POST请求到 http://daniu.7766.org:8081/webhook/save_article
- 请求体格式为 {"url": "剪贴板内容", "user":"YX"}
- 完成后显示"完成"消息

## 项目文件说明

项目包含完整的Android项目结构：
- MainActivity.java - 核心逻辑
- AndroidManifest.xml - 权限声明
- build.gradle - 构建配置
- res/ - 资源文件
- .github/workflows/ - 自动构建配置