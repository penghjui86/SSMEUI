package com.fceg.util;

import org.apache.shiro.crypto.hash.Md5Hash;

public class UtilMd5 {
    public static String md5(String str,String salt){
        return new Md5Hash(str,salt,1).toString();
    }
    public static String md5(String str,String salt,int hashIterations){
        return new Md5Hash(str,salt,hashIterations).toString();
    }
}
