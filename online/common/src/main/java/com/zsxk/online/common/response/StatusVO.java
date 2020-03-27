package com.zsxk.online.common.response;

public interface StatusVO {
    /*
    * 成功状态码
    * */
     int success =20000;
     /**
      * 没有操作权限
      * */
     int author = 30000;
    /**
     * 失败状态码
     * */
    int err = 10001;
}
