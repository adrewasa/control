package com.asa.computer;

import com.asa.computer.transfer.client.Client;
import com.asa.computer.transfer.client.RequestConstant;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.HelpFormatter;
import org.apache.commons.cli.Options;

/**
 * Created by andrew_asa on 2017/7/18.
 */
public class ControlComputer {

    private static String ACTION = "action";

    private static String COMTROL = "control";

    private static String COMTROLLED = "controlled";

    private boolean userUI = false;

    private CommandLine cl;


    public ControlComputer() {

    }

    public Options setOption() {

        Options options = new Options();
        options.addOption(ACTION, true, "control/controlled(控制电脑还是被控制电脑)");
        options.addOption("server", false, "启动服务器");
        options.addOption("client", true, "发送请求 client=cmd");
        options.addOption("ui", false, "是否使用桌面环境");
        options.addOption("h", "帮助文档");
        return options;
    }

    /**
     * 是否使用桌面程序
     *
     * @param userUI
     */
    public void setUserUI(boolean userUI) {

        this.userUI = userUI;
    }

    public boolean isUserUI() {

        return userUI;
    }

    public void start(String[] args) {

        Options options = setOption();
        DefaultParser parser = new DefaultParser();
        try {
            CommandLine cl = parser.parse(options, args);
            if (cl.hasOption("ui")) {
                userUI = true;
            }
            if (cl.hasOption("h")) {
                HelpFormatter f = new HelpFormatter();
                f.printHelp("使用说明", options);
            } else {
                if (cl.hasOption("server")) {
                    System.out.println("start server");
                    //Server server = new Server();
                    //server.run();
                } else if (cl.hasOption("client")) {
                    System.out.println("start client,cmd=" + cl.getOptionValue("client"));
                    short cmd = RequestConstant.cmdNameTocmd(cl.getOptionValue("client"));
                    Client client = new Client(cmd);
                }
            }
        } catch (Exception e) {

        }
    }

    public static void main(String[] args) {

        ControlComputer cc = new ControlComputer();
        cc.start(args);

    }

}
