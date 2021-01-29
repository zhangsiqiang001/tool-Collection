#!/bin/bash

#全局变量
deployBaseDir='/root/otsp' # 部署根目录
declare -A toolNameMap # 工具名与安装名map

# 检查部署环境工具是否可用
check_tool(){
  if ! type $1 >/dev/null 2>&1; then
      echo $1' 未安装';
      echo '开始安装 '$1
      yum install -y $1
  else
      echo $1' 已安装';
  fi
}

# 遍历部署
deploy(){
    for file in `ls -a $1`
    do
        if [ -d $1"/"$file ]
        then
            if [[ $file != '.' && $file != '..' ]]
            then
                deploy $1"/"$file
            fi
        else
            if [[ $file == *.jar ]];then
                currentFileDir=$deployBaseDir"/"${file%%'.jar'}
                echo $currentFileDir"/"$file
                if [ ! -f $currentFileDir"/"$file ]
                then
                  mkdir -p $currentFileDir
                  cp $1"/"$file $currentFileDir
                fi
                  cp -f ./port-listener.sh $currentFileDir
                if [ -f "$currentFileDir/start.sh" ];then
                  cat /dev/null > $currentFileDir/start.sh
                fi
                serviceName=${file%%'.jar'}
                port=`cat /root/service-config.json | jq .\"$serviceName\".\"port\"`
                port=`echo $port | sed 's/"//g'`
                containerName=`cat /root/service-config.json | jq .\"$serviceName\".\"container-name\"`
                containerName=`echo $containerName | sed 's/"//g'`
                touch $currentFileDir/start.sh
                echo "#!/bin/bash" >> $currentFileDir/start.sh
                echo "cd /root/${file%%'.jar'}" >> $currentFileDir/start.sh
                echo "java -Xms512m -Xmx512m -jar -Dcustom.file.web.root-url=https://dev2.jyhk.com/otsp-file -Dspringfox.documentation.swagger.v2.host=dev2.jyhk.com/$containerName -Dserver.port=$port -Dspring.profiles.active=devJoin "$file >> $currentFileDir/start.sh
                exist=`docker inspect --format '{{.State.Running}}' ${containerName}`
               #echo $containerName"--------------"$exist 
                if [ "${exist}" == "true" ]
                then
                  echo $serviceName"服务已经启动"
                else
                  if [ "${exist}" == "false" ]
                  then
                    docker restart $containerName
                  else
                    touch $currentFileDir/port-listener.sh
                    echo "docker run -d -e TZ="Asia/Shanghai" --net=host --name $containerName -it --privileged=true -v $currentFileDir:/root/${file%%'.jar'} -v $currentFileDir/logs:/root/${file%%'.jar'}/logs java:8 sh /root/${file%%'.jar'}/start.sh" >> $currentFileDir/port-listener.sh
                    sh $currentFileDir/port-listener.sh ${file%%'.jar'} &
                    echo $serviceName"服务正在启动"
                  fi
                fi
            fi
        fi
     done
}


# 解压部署压缩包
decompression(){
  read -p "请输入压缩包文件名:" zipFilename
  unzip -od ./jar $zipFilename
  if [ $? -ne 0 ];then
    echo "解压"$zipFilename"失败"
    return 1
  else
    return 0
  fi
}

# 部署执行代码
echo "环境检查......"
check_tool unzip
check_tool nc
check_tool jq

echo "解压项目文件......"
if [ ! -d "./jar" ];then
 decompression
  if [ $? -ne 0 ];then
    exit
  fi
else
  echo "./jar文件夹已经存在，是否要重新解压？（Y/N）"
  read unzip_again
  if [ $unzip_again == 'Y' ];then
    decompression
  if [ $? -ne 0 ];then
    exit
  fi
  fi
fi

# 项目文件部署根路径创建
if [ ! -d $deployBaseDir ];then
  mkdir -p $deployBaseDir
fi

# 开始部署
deploy ./jar
