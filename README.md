# care

仓库的配置aliyun-java-vod-upload-1.4.13.jar未开源， 需手动下载 打开例子VODUploadDemo-java-1.4.13 项目 进入命令行进入lib目录，输入CMD 执行以下代码
mvn install:install-file -DgroupId=com.aliyun -DartifactId=aliyun-sdk-vod-upload -Dversion=1.4.13 -Dpackaging=jar -Dfile=aliyun-java-vod-upload-1.4.13.jar
就会生成jar包再引入到仓库中即可

项目开启方式，，后台运行，需要先打开nacos 和nginx window版(开启命令为:nginx.exe  停止命令为:nginx -s stop)
前端首先加载需先 npm install 安装， 然后都是 npm run dev 开启命令
