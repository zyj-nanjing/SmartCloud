# 工程简介
该项目基于阿里COLA应用框架构建 采用Redis MySQL TDengin  AlertManager 提供传感器 数据解析、数据存储、数据可视化、
传感器告警推送、项目创建、结构物等核心服务。提供内置权限以及人员管理服务。助力硬件厂商快速构建自己的传感器监测与可视化平台
### 参考地址
<http://121.41.96.206:8080/> </p>
管理员 账户 admin Admin123@! </p>
前端功能在不断完善中 在不断实现服务端提供的以已有能
### 数据采集转换以及可定制化接收器
https://gitee.com/zhuyongjie1212/data-collection
# 使用方式  
该项目需要使用的组件以及对应的数据库备份文件以及放置在根目录下的source文件夹下</p>
### TDengine 
该项目采用了TDengine作为传感器数据持久化的存储方案，因此其数据导入导出方式与MySQL会有一定的不同。
#### 数据导入
```shell
   taosdump -c  /etc/taos/taos.cfg  -u root -p taosdata  -i /home/dump/data  -T 4
   DUMP命令帮助信息
    -c, --config=CONFIG_DIR    Configure directory. Default is /etc/taos/taos.cfg.
    -i, --inpath=INPATH        Input file path.     
    -T, --thread_num=THREAD_NUM   Number of thread for dump in file. Default is 5.                    
```
具体参考 TDengin：[官方文档](<https://www.taosdata.com/blog/2020/03/09/1334.html>)

#### 告警组件规范
该项目的告警模块基于TDengine中的 alert组件语法格式和提供的功能上进行二次开发 提供了预警模板 预警组 预警规则 
告警消息推送 告警消息接收者管理等诸多功能  组件文档请见:[TDengin预警模块文档](<https://github.com/taosdata/tdengine/blob/master/alert/README_cn.md>)

### AlertManager 对应配置
预警模块采用alertmanager做告警消息的发送 并在服务端接收alertManager分发的告警消息 并进行解析与邮件推送</p>
在使用时需要 修改alertManager 中的 alertmanager.yml 文件将其改成 改成以下文件格式
```shell
   global:
   resolve_timeout: 45m
   route:
   group_by: ['alertname']
   group_wait: 30s
   group_interval: 5m
   repeat_interval: 1h
   receiver: 'webhook'
   receivers:
   - name: 'webhook'
     webhook_configs:
       - url: 'http://127.0.0.1:8066/main/api/webhook'
         inhibit_rules:
         - source_match:
           severity: 'critical'
           target_match:
           severity: 'warning'
           equal: ['alertname', 'dev', 'instance']
```
## 后续扩展
1、抽取传感器解析 提供不同厂商的传感器的持续集成</p>
2、优化内置权限以及管理系统 提供更多的企业内置服务</p>
3、提供应用场景、行业领域等更多的行业概念以及技术支持提供更丰富的服务</p>
4、提供更丰富的推送方式 考虑支持短信 微信等推送方式</p>
5、优化操作逻辑</p>
