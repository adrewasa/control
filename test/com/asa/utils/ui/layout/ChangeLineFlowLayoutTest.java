package com.asa.utils.ui.layout;

import com.asa.computer.transfer.Constant;
import com.asa.computer.ui.UIConstant;
import com.asa.utils.applet.ls.Ls;
import com.asa.utils.applet.ls.LsConstant;
import com.asa.utils.applet.ls.LsNode;
import org.apache.commons.io.FilenameUtils;
import org.junit.Test;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * ChangeLineFlowLayout Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>八月 16, 2017</pre>
 */
public class ChangeLineFlowLayoutTest {

    @Test
    public void testChangeLineFlowLayout() throws Exception {

        JFrame jFrame = new JFrame();
        Container contentPane = jFrame.getContentPane();


        jFrame.setSize(600, 400);
        jFrame.setLocation(400, 300);
        // 设置关闭退出
        jFrame.setDefaultCloseOperation(jFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        //scrollPane.removeAll();
        panel.setBackground(Color.white);
        panel.setLayout(new ChangeLineFlowLayout(ChangeLineFlowLayout.LEFT));
        //panel.setPreferredSize();
        JScrollPane scrollPane = new JScrollPane(panel);
        scrollPane.setBounds(100, 100, 100, 300);
        //childs = ls.getSimpleLsNode().getChild();
        Ls ls = new Ls(Constant.getTransportBasePath());
        LsNode lsNode = ls.getSimpleLsNode();
        for (LsNode node : lsNode.getChild()) {
            final JButton button = new JButton();
            button.setText(FilenameUtils.getName(node.getName()));
            Icon icon;
            if (node.getType() == LsConstant.LSNODE_TYPE_FILE) {
                icon = new ImageIcon(UIConstant.ICON_DIR_PATH + "file.jpg");
            } else {
                icon = new ImageIcon(UIConstant.ICON_DIR_PATH + "folder.jpg");
            }
            button.setIcon(icon);
            button.setHorizontalTextPosition(SwingConstants.CENTER);
            button.setVerticalTextPosition(SwingConstants.BOTTOM);
            //button.setSize(90, 90);
            button.setSize(UIConstant.FILE_LIST_DISP_WIDTH, UIConstant.FILE_LIST_DISP_HEIGH);
            button.setPreferredSize(new Dimension(UIConstant.FILE_LIST_DISP_WIDTH, UIConstant.FILE_LIST_DISP_HEIGH));
            button.setBorderPainted(false);
            button.addMouseListener(new MouseAdapter() {

                @Override
                public void mouseClicked(MouseEvent e) {

                    if (e.getClickCount() == 1) {
                        button.setBorderPainted(true);

                    }

                }

                public void mouseExited(MouseEvent e) {

                    button.setBorderPainted(false);
                }
            });
            panel.add(button);
        }
        contentPane.add(scrollPane);
        jFrame.setVisible(true);
    }

}
