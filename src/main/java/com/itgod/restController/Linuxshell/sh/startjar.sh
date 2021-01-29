#!/usr/bin/env bash
#全局变量
echo "开始检测服务器安装配置"
#判断是否安装docker
#if [ `command -v docker` ];then
#    echo 'docker 已经安装'
#else
#    echo 'docker 未安装,开始安装docker'
##安装docker
#    brew install jq
#fi










read -p "是否安装docker" cai
num=$cai

echo "$num"


# 使用 read 提示用户猜数字
# 使用 if 判断用户猜数字的大小关系:‐eq(等于),‐ne(不等于),‐gt(大于),‐ge(大于等于),
# ‐lt(小于),‐le(小于等于)
while  :
do
	read -p "计算机生成了一个 1‐100 的随机数,你猜: " cai
    if [ $cai -eq $num ]
    then
       	echo "恭喜,猜对了"
       	exit
    	elif [ $cai -gt $num ]
    	then
           	echo "Oops,猜大了"
      	else
           	echo "Oops,猜小了"
 	fi
done
