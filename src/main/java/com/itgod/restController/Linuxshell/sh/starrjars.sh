#!/usr/bin/env bash

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