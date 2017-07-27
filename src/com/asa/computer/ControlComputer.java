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


    //private Dimension dim;
    //
    //private Robot robot;
    //
    //private volatile boolean stop = false;

    public ControlComputer() {

        //dim = Toolkit.getDefaultToolkit().getScreenSize();
        //System.out.println("computer imformation:\n" + "width:" + dim.width + "\theight:" + dim.height);
        //try {
        //    robot = new Robot();
        //} catch (AWTException ex) {
        //    ex.printStackTrace();
        //}
    }


    public static void main(String[] args) {

        //boolean r = true;
        //if (r) {
        //
        //    //Client s = new Client();
        //    Server server = new Server();
        //    server.run();
        //    return;
        //}


        String s;

        Options options = new Options();
        options.addOption(ACTION, true, "control/controlled(控制电脑还是被控制电脑)");
        options.addOption("server", false, "启动服务器");
        options.addOption("client", true, "发送请求 client=cmd");
        options.addOption("h", "帮助文档");
        Lifecycle life = null;

        DefaultParser parser = new DefaultParser();
        try {

            CommandLine cl = parser.parse(options, args);
            if (cl.hasOption("h")) {
                HelpFormatter f = new HelpFormatter();
                f.printHelp("使用书名", options);
            } else {
                //if (cl.hasOption(ACTION)) {
                //    s = cl.getOptionValue(ACTION);
                //    if (COMTROL.equalsIgnoreCase(s)) {
                //        life = new Control();
                //    } else {
                //        life = new Controlled();
                //    }
                //} else {
                //    System.out.println("输入正确操作");
                //}
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
        if (life != null) {
            life.start();
        }

        //System.out.println();
        //if(args.length<=0){
        //    System.out.println("输入正确的参数");
        //}
        //
        //for (String s : args) {
        //    System.out.println(s);
        //    if(ACTION.equalsIgnoreCase(s)){
        //
        //    }
        //}


        //Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        //System.out.println("computer imformation:\n" + "width:"+dim.width+"\theight:"+dim.height);


        //ControlComputer kc = new ControlComputer();
        //Thread kcThread = new Thread(kc);
        //System.out.println("Key Controller start");
        //kcThread.start();
        //try {
        //    Thread.sleep(60000);
        //} catch (InterruptedException e) {
        //    e.printStackTrace();
        //}
        //kc.stop();
        //System.out.println("Mouse Controller stoped");

        //Robot robot = null;


        try {
            //robot = new Robot();//创建Robot对象
            ////这里是按下和释放alt键，这个键的功能是调用菜单
            //robot.keyPress(KeyEvent.VK_ALT);
            //robot.keyRelease(KeyEvent.VK_ALT);
            //robot.delay(500);//延迟500ms
            ////这里是按下和释放回车键，用于确定是file选项
            //robot.keyPress(KeyEvent.VK_ENTER);
            //robot.keyRelease(KeyEvent.VK_ENTER);
            //robot.delay(500);
            ////选择New
            //robot.keyPress(KeyEvent.VK_ENTER);
            //robot.keyRelease(KeyEvent.VK_ENTER);
            //robot.delay(500);
            ////选择Java Project
            //robot.keyPress(KeyEvent.VK_ENTER);
            //robot.keyRelease(KeyEvent.VK_ENTER);
            //robot.delay(500);

            ////这里是按下和释放回车键，用于确定是file选项
            //robot.keyPress(KeyEvent.VK_ENTER);
            //robot.keyRelease(KeyEvent.VK_ENTER);
            //robot.delay(500);
            //输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);

            //输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            //输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            //
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_0);
            //robot.keyRelease(KeyEvent.VK_0);
            //robot.delay(500);
            //
            //
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_1);
            //robot.keyRelease(KeyEvent.VK_1);
            //robot.delay(500);
            //
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名1aaaaaaaa     robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            //
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_0);
            //robot.keyRelease(KeyEvent.VK_0);
            //robot.delay(500);
            //
            //
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_1);
            //robot.keyRelease(KeyEvent.VK_1);
            //robot.delay(500);
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_A);
            //robot.keyRelease(KeyEvent.VK_A);
            //robot.delay(500);
            //
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_0);
            //robot.keyRelease(KeyEvent.VK_0);
            //robot.delay(500);
            //
            //
            ////输入字母a,即工程名
            //robot.keyPress(KeyEvent.VK_1);
            //robot.keyRelease(KeyEvent.VK_1);
            //robot.delay(500);
            //


            //回车确定创建工程
            //robot.keyPress(KeyEvent.VK_ENTER);
            //robot.keyRelease(KeyEvent.VK_ENTER);
            //robot.delay(500);
            ////将鼠标抵用到刚刚建立的工程上面，这个坐标是作者在自己的电脑上实验获得的，在不同的电脑上可能不一样，可以用
            ////Point point = MouseInfo.getPointerInfo().getLocation();
            ////System.out.println(point);来打印当前鼠标的坐标，从而找到对的位置
            //robot.mouseMove(43, 136);
            ////按下和释放鼠标左键，选定工程
            //robot.mousePress(KeyEvent.BUTTON1_MASK);
            //robot.mouseRelease(KeyEvent.BUTTON1_MASK);
            //
            ////alt键选择菜单并调出newClass的选项
            //robot.keyPress(KeyEvent.VK_ALT);
            //robot.keyRelease(KeyEvent.VK_ALT);
            //robot.delay(500);
            //robot.keyPress(KeyEvent.VK_ENTER);
            //robot.keyRelease(KeyEvent.VK_ENTER);
            //robot.delay(500);
            //robot.keyPress(KeyEvent.VK_ENTER);
            //robot.keyRelease(KeyEvent.VK_ENTER);
            //robot.delay(500);
            //robot.keyPress(KeyEvent.VK_DOWN);
            //robot.keyRelease(KeyEvent.VK_DOWN);
            //robot.delay(500);
            //robot.keyPress(KeyEvent.VK_DOWN);
            //robot.keyRelease(KeyEvent.VK_DOWN);
            //robot.delay(500);
            //robot.keyPress(KeyEvent.VK_DOWN);
            //robot.keyRelease(KeyEvent.VK_DOWN);
            //robot.delay(500);
            //robot.keyPress(KeyEvent.VK_ENTER);
            //robot.keyRelease(KeyEvent.VK_ENTER);
            //robot.delay(500);
            ////下面换回小写
            ////切换成大写
            //robot.keyPress(KeyEvent.VK_CAPS_LOCK);
            //robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
            //robot.delay(500);
            //robot.keyPress(KeyEvent.VK_H);
            //robot.keyRelease(KeyEvent.VK_H);
            //robot.delay(200);
            ////换回小写
            //robot.keyPress(KeyEvent.VK_CAPS_LOCK);
            //robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_E);
            //robot.keyRelease(KeyEvent.VK_E);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_L);
            //robot.keyRelease(KeyEvent.VK_L);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_L);
            //robot.keyRelease(KeyEvent.VK_L);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_O);
            //robot.keyRelease(KeyEvent.VK_O);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_CAPS_LOCK);
            //robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_W);
            //robot.keyRelease(KeyEvent.VK_W);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_CAPS_LOCK);
            //robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_O);
            //robot.keyRelease(KeyEvent.VK_O);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_R);
            //robot.keyRelease(KeyEvent.VK_R);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_L);
            //robot.keyRelease(KeyEvent.VK_L);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_D);
            //robot.keyRelease(KeyEvent.VK_D);
            //robot.delay(200);
            //
            ////移动鼠标选择自动穿件main函数
            //robot.mouseMove(533, 448);
            //robot.mousePress(KeyEvent.BUTTON1_MASK);
            //robot.mouseRelease(KeyEvent.BUTTON1_MASK);
            //robot.delay(500);
            ////回车创建完成
            //robot.keyPress(KeyEvent.VK_ENTER);
            //robot.keyRelease(KeyEvent.VK_ENTER);
            //robot.delay(500);
            ////移动光标到输入代码的行上
            //robot.keyPress(KeyEvent.VK_DOWN);
            //robot.keyRelease(KeyEvent.VK_DOWN);
            //robot.delay(500);
            //robot.keyPress(KeyEvent.VK_DOWN);
            //robot.keyRelease(KeyEvent.VK_DOWN);
            //robot.delay(500);
            //robot.keyPress(KeyEvent.VK_DOWN);
            //robot.keyRelease(KeyEvent.VK_DOWN);
            //robot.delay(500);
            //robot.keyPress(KeyEvent.VK_DOWN);
            //robot.keyRelease(KeyEvent.VK_DOWN);
            //robot.delay(500);
            //robot.keyPress(KeyEvent.VK_DOWN);
            //robot.keyRelease(KeyEvent.VK_DOWN);
            //robot.delay(500);
            //robot.keyPress(KeyEvent.VK_DOWN);
            //robot.keyRelease(KeyEvent.VK_DOWN);
            //robot.delay(500);
            //
            ////输入syso并用alt+/快捷键补全
            //robot.keyPress(KeyEvent.VK_S);
            //robot.keyRelease(KeyEvent.VK_S);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_Y);
            //robot.keyRelease(KeyEvent.VK_Y);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_S);
            //robot.keyRelease(KeyEvent.VK_S);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_O);
            //robot.keyRelease(KeyEvent.VK_O);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_ALT);
            //robot.keyPress(KeyEvent.VK_SLASH);
            //robot.keyRelease(KeyEvent.VK_ALT);
            //robot.keyRelease(KeyEvent.VK_SLASH);
            //robot.delay(500);
            //
            ////输入双引号
            //robot.keyPress(KeyEvent.VK_SHIFT);
            //robot.keyPress(KeyEvent.VK_QUOTE);
            //robot.keyRelease(KeyEvent.VK_SHIFT);
            //robot.keyRelease(KeyEvent.VK_QUOTE);
            //
            ////输入HelloWorld
            //robot.keyPress(KeyEvent.VK_CAPS_LOCK);
            //robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
            //robot.delay(500);
            //robot.keyPress(KeyEvent.VK_H);
            //robot.keyRelease(KeyEvent.VK_H);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_CAPS_LOCK);
            //robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_E);
            //robot.keyRelease(KeyEvent.VK_E);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_L);
            //robot.keyRelease(KeyEvent.VK_L);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_L);
            //robot.keyRelease(KeyEvent.VK_L);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_O);
            //robot.keyRelease(KeyEvent.VK_O);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_CAPS_LOCK);
            //robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_W);
            //robot.keyRelease(KeyEvent.VK_W);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_CAPS_LOCK);
            //robot.keyRelease(KeyEvent.VK_CAPS_LOCK);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_O);
            //robot.keyRelease(KeyEvent.VK_O);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_R);
            //robot.keyRelease(KeyEvent.VK_R);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_L);
            //robot.keyRelease(KeyEvent.VK_L);
            //robot.delay(200);
            //robot.keyPress(KeyEvent.VK_D);
            //robot.keyRelease(KeyEvent.VK_D);
            //robot.delay(200);
            //
            ////ctrl+shift+F 格式化
            //robot.keyPress(KeyEvent.VK_SHIFT);
            //robot.keyPress(KeyEvent.VK_CONTROL);
            //robot.keyPress(KeyEvent.VK_F);
            //robot.keyRelease(KeyEvent.VK_SHIFT);
            //robot.keyRelease(KeyEvent.VK_CONTROL);
            //robot.keyRelease(KeyEvent.VK_F);
            //
            ////ctrl+S保存
            //robot.keyPress(KeyEvent.VK_CONTROL);
            //robot.keyPress(KeyEvent.VK_S);
            //robot.keyRelease(KeyEvent.VK_S);
            //robot.keyRelease(KeyEvent.VK_CONTROL);
            //
            ////F11运行
            //robot.keyPress(KeyEvent.VK_F11);
            //robot.keyRelease(KeyEvent.VK_F11);

        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println();
    }

}
