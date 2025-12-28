# 快捷指令 Android 应用

## 项目说明

这是一个简单的Android应用，模拟iPhone快捷指令的功能。用户点击应用图标后，应用会：

1. 读取剪贴板中的内容
2. 发送一个HTTP POST请求到指定的URL
3. 显示执行状态

## 功能

- 自动读取剪贴板内容
- 发送POST请求到 `http://daniu.7766.org:8081/webhook/save_article`
- 请求体格式: `{"url": "剪贴板中的内容", "user":"YX"}`
- 显示"正在执行指令"和"完成"提示

## 如何获取APK（推荐方法）

### 方法1：使用GitHub Actions远程构建（推荐）

1. 将此项目上传到GitHub仓库
2. 推送到main分支，GitHub Actions会自动构建APK
3. 在"Actions"标签页中找到构建工作，下载生成的APK

### 方法2：使用Android Studio

1. 下载并安装 [Android Studio](https://developer.android.com/studio)
2. 打开Android Studio
3. 选择 "Open an existing Android Studio project"
4. 导航到项目根目录并打开
5. Android Studio会自动同步项目
6. 点击菜单 "Build" -> "Build Bundle(s) / APK(s)" -> "Build APK(s)"
7. 构建完成后，点击 "locate" 链接找到APK文件

## 权限

- `INTERNET`: 用于发送HTTP请求
- `ACCESS_NETWORK_STATE`: 用于检查网络状态

## 代码结构

- `MainActivity.java`: 主活动，处理剪贴板读取和HTTP请求
- `AndroidManifest.xml`: 应用配置和权限声明
- `build.gradle`: 项目构建配置
- `res/`: 资源文件（布局和字符串）"# android-shortcut-app" 
