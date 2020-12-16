package com.itgod.restController.Linuxshell;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ServerInformation {
    /**
     * linux磁盘信息
     */
    //文件系统分区
    private String filesystem;
    //磁盘总大小
    private String size;
    //已经使用的磁盘大小。
    private String used;
    //剩余的磁盘大小。
    private String avail;
    //磁盘使用率。
    private String userate;
    //磁盘挂载的目录
    private String mountedon;
    /**
     * linux 内存信息
     */
    //物理内存总量
    private String memoryTotal;
    //使用的物理内存总量
    private String memoryUsed;
    //空闲内存总量
    private String memoryFree;
    //用作内核缓存的内存量
    private String memoryBuffers;
    /**
     * linux cup信息
     */
    private String cupInfo;
}
