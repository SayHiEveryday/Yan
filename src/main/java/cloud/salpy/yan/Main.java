package cloud.salpy.yan;

import cloud.salpy.yan.core.Client;

import static cloud.salpy.yan.Constant.token;

public class Main {
    public static void main(String[] args) {
        new Client(token, args).start();
    }
}
