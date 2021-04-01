# care

仓库的配置aliyun-java-vod-upload-1.4.13.jar未开源， 需手动下载 打开例子VODUploadDemo-java-1.4.13 项目 进入命令行进入lib目录，输入CMD 执行以下代码
mvn install:install-file -DgroupId=com.aliyun -DartifactId=aliyun-sdk-vod-upload -Dversion=1.4.11 -Dpackaging=jar -Dfile=aliyun-java-vod-upload-1.4.11.jar
就会生成jar包再引入到仓库中即可
