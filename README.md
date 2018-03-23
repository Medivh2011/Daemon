# Daemon
### Android 服务保活/常驻 (Android Service Daemon)

## 引入

### 1. 添加二进制

```
project build.gradle 中添加
	allprojects {
		repositories {
			maven { url 'https://jitpack.io' }
		}
	}
app build.gradle 中添加
    dependencies {
    	        compile 'com.github.Medivh2011:Daemon:1.0'
    	}
 ```
### 2. 继承 AbsWorkService, 实现 6 个抽象方法

```
/**
 * 是否 任务完成, 不再需要服务运行?
 * @return 应当停止服务, true; 应当启动服务, false; 无法判断, null.
 */
boolean isShouldStopService();

/**
 * 任务是否正在运行?
 * @return 任务正在运行, true; 任务当前不在运行, false; 无法判断, null.
 */
boolean isTaskRunning();

void startWork();

void stopWork();

//Service.onBind(Intent intent)
@Nullable IBinder onBind(Intent intent, Void unused);

//服务被杀时调用, 可以在这里面保存数据.
void onServiceKilled();

### 3. 自定义 Application

在 Application 的 `onCreate()` 中, 调用

```
```
Daemon.initialize(
  Context app,  //Application Context.
  Class<? extends AbsWorkService> serviceClass, //刚才创建的 Service 对应的 Class 对象.
  @Nullable Integer wakeUpInterval);  //定时唤醒的时间间隔(ms), 默认 6 分钟.

Context.startService(new Intent(Context app, Class<? extends AbsWorkService> serviceClass));
```