/**
 * Copyright 2015 LinkedIn Corp. All rights reserved.
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance
 * with the License. You may obtain a copy of the License at http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed
 * on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * bing:https://domain/index.html#L18
 */
package com.linkedin.urls;

public enum UrlPart {
  FRAGMENT(null), // fragment：片段, #后面的就是片段，用于标识跳到页面的什么地方, https://domain/index.html#L18
  QUERY(FRAGMENT),        // query：查询，下一个是fragment，eg: index.html#L18
  PATH(QUERY),            // path：路径，下一个是query, eg: index?name=zhangsan
  PORT(PATH),             // port：端口，下一个是path, eg: 8080/index
  HOST(PORT),             // host：主机，也可以说是domain，下一个是端口，eg：127.0.0.1:8080
  USERNAME_PASSWORD(HOST),// username_password: 用户名密码，下一个是host，eg：zhangsan:123@127.0.0.1
  SCHEME(USERNAME_PASSWORD);// scheme：协议，URL的第一个，eg：http、ftp

  /**
   * This is the next url part that follows.
   */
  private UrlPart _nextPart;

  UrlPart(UrlPart nextPart) {
    _nextPart = nextPart;
  }

  public UrlPart getNextPart() {
    return _nextPart;
  }
}
