package com.it.visitsshop.net;

/**
 * Created by he on 17-7-12.
 */

public interface Api {
    String ip = "http://10.0.2.2";
    String prot = ":8080";
    String serverName = "/visitshop";
    String head = ip+prot+serverName;

    String login = head + "/login";
}
