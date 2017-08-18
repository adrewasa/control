package com.asa.computer.ui.client.action;

import com.asa.computer.transfer.client.Client;
import com.asa.computer.transfer.client.RequestActionResult;
import com.asa.computer.transfer.client.RequestConstant;
import com.asa.computer.ui.UIConstant;
import com.asa.utils.CommonUtil;
import com.asa.utils.applet.ls.LsConstant;
import com.asa.utils.applet.ls.LsNode;
import com.asa.utils.data.StringUtils;
import com.asa.utils.ui.layout.ChangeLineFlowLayout;
import org.apache.commons.io.FilenameUtils;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

/**
 * Created by andrew_asa on 2017/7/31.
 * 文件操作相关
 */
public class FileListAction implements ActionListener {

    private JFrame jFrame;

    private JPanel panel;

    private JScrollPane scrollPane;

    private JButton lastClickButton;

    private Client client;

    private String rootDir;

    private String currentDir;

    public FileListAction() {

    }

    public FileListAction(JFrame jFrame) {

        this.jFrame = jFrame;
        client = new Client();
        panel = new JPanel();
        scrollPane = new JScrollPane(panel);
        panel.setBackground(Color.white);
        panel.setLayout(new ChangeLineFlowLayout(ChangeLineFlowLayout.LEFT));
        scrollPane.setBounds(100, 100, 100, 300);
        Container contentPane = jFrame.getContentPane();
        // 重新进行绘制
        contentPane.removeAll();
        contentPane.add(scrollPane);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

        paint(getNodes(null));
    }

    private List<LsNode> getNodes(String path) {

        RequestActionResult result;
        if (StringUtils.isNotEmpty(path)) {
            result = client.actionCmd(RequestConstant.CMD_LS, path);
        } else {
            result = client.actionCmd(RequestConstant.CMD_LS);
        }
        // 请求成功才做下面的事情
        if (result.requestSuccess()) {
            LsNode lsNode = (LsNode) result.getResponse();
            if (lsNode != null) {
                currentDir = lsNode.getName();
                if (StringUtils.isEmpty(path)) {
                    // 根目录
                    rootDir = lsNode.getName();
                }
                return lsNode.getChild();
            }
        }
        return null;
    }

    private void paint(List<LsNode> childs) {

        clearPanel();
        // TODO 添加返回父亲的按钮
        if (CommonUtil.isNotEmptyList(childs)) {
            panel.add(getParentPathButton());
            for (LsNode c : childs) {
                JButton button = getButton(c);
                panel.add(button);
            }
            jFrame.validate();
            jFrame.repaint();
        }
    }

    private void clearPanel() {

        if (panel != null) {
            lastClickButton = null;
            panel.removeAll();
        }
    }

    /**
     * 返回上一个文件夹目录
     *
     * @return
     */
    public JButton getParentPathButton() {

        final JButton button = new JButton();
        button.setText("上层目录");
        Icon icon = new ImageIcon(UIConstant.ICON_DIR_PATH + "folder.jpg");
        button.setIcon(icon);
        button.setHorizontalTextPosition(SwingConstants.CENTER);
        button.setVerticalTextPosition(SwingConstants.BOTTOM);
        button.setPreferredSize(new Dimension(UIConstant.FILE_LIST_DISP_WIDTH, UIConstant.FILE_LIST_DISP_HEIGH));
        button.setBorderPainted(false);
        // 点击事件
        button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int clickTime = e.getClickCount();
                if (clickTime == 2) {
                    if (rootDir != null && currentDir != null && !rootDir.equals(currentDir)) {
                        // 文件夹，双击进入
                        paint(getNodes(FilenameUtils.getFullPath(currentDir)));
                    }
                } else if (clickTime == 1) {
                    // 上一个的显示边框去掉
                    if (lastClickButton != null) {
                        lastClickButton.setBorderPainted(false);
                    }
                    lastClickButton = button;
                    button.setBorderPainted(true);
                    // 文件标题换名
                    jFrame.setTitle(rootDir);
                    // 标题栏上面显示文件的名字，防止有些文件的名字过长
                }
            }
        });
        return button;
    }

    public JButton getButton(final LsNode node) {

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
        button.setPreferredSize(new Dimension(UIConstant.FILE_LIST_DISP_WIDTH, UIConstant.FILE_LIST_DISP_HEIGH));
        button.setBorderPainted(false);
        // 点击事件
        button.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent e) {

                int clickTime = e.getClickCount();
                if (clickTime == 2) {
                    // 文件夹，双击进入
                    if (node.getType() == LsConstant.LSNODE_TYPE_DIR) {
                        paint(getNodes(node.getName()));
                    }
                } else if (clickTime == 1) {
                    // 上一个的显示边框去掉
                    if (lastClickButton != null) {
                        lastClickButton.setBorderPainted(false);
                    }
                    lastClickButton = button;
                    button.setBorderPainted(true);
                    // 文件标题换名
                    jFrame.setTitle(node.getName());
                    // 标题栏上面显示文件的名字，防止有些文件的名字过长
                }
            }

            // 右击弹出框
            public void mousePressed(MouseEvent e) {

                JButton button = (JButton) e.getSource();
                int witch = e.getButton();
                if (witch == MouseEvent.BUTTON1) {
                    System.out.println("left");
                } else if (witch == MouseEvent.BUTTON2) {
                    System.out.println("middle");
                } else if (witch == MouseEvent.BUTTON3) {
                    if (node.isFile()) {
                        JPopupMenu popupMenu = new JPopupMenu();
                        JMenuItem download = new JMenuItem("下载");
                        popupMenu.add(download);
                        download.addActionListener(new DownloadAction(client, node));
                        popupMenu.show(e.getComponent(), e.getX(), e.getY());
                    }
                }
            }

            // 鼠标经过事件
            @Override
            public void mouseEntered(MouseEvent e) {

                //System.out.println("mouse entered");
                //button.requestFocus();
            }

        });
        return button;
    }


}
