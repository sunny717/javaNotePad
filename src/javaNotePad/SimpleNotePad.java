/**    
* @Title: SimpleNotePad.java  
* @Package javaNotePad  
* @Description: TODO(��һ�仰�������ļ���ʲô)  
* @author panxiaobo    
* @date 2017��2��28�� ����10:12:34  
* @version V1.0.0    
*/   
package javaNotePad;

/**  
 * @ClassName: SimpleNotePad  
 * @Description: java�汾���±� 
 * @author panxiaobo  
 * @date 2017��2��28�� ����10:12:34  
 *    
 */



	import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Container;
import java.awt.FileDialog;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GraphicsEnvironment;
import java.awt.GridLayout;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.nio.charset.Charset;

import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.KeyStroke;
import javax.swing.ScrollPaneConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

	public class SimpleNotePad extends JFrame {
	    /**
	* 
	*/
	    private static final long serialVersionUID = 1L;
	    private JTextArea ja = new JTextArea(5, 6);
	    String str;
	    private String filePath;
	    private String fileName;

	    // �Զ����к�״̬��
	    int a = 0;
	    boolean flag = false;

	    public SimpleNotePad() {

	        super("���±�");
	        this.setSize(768, 384);
	        this.setLocationRelativeTo(null);
	        this.setDefaultCloseOperation(EXIT_ON_CLOSE);

	        // �˵���
	        JMenu wj = new JMenu();
	        wj.setText("�ļ�");
	        JMenu bj = new JMenu("�༭");
	        JMenu gs = new JMenu("��ʽ");
	        JMenu ck = new JMenu("�鿴");
	        JMenu bz = new JMenu("����");

	        // ������
	        JMenuBar gjl = new JMenuBar();

	        // �˵����
	        final JMenuItem open = new JMenuItem();
	        open.setText("��");
	        JMenuItem New = new JMenuItem();
	        New.setText("�½�");
	        JMenuItem save = new JMenuItem();
	        save.setText("����");
	        final JMenuItem saveas = new JMenuItem();
	        saveas.setText("���Ϊ��");
	        JMenuItem exit = new JMenuItem();
	        exit.setText("�˳�");
	        final JMenuItem copy = new JMenuItem();
	        copy.setText("����");
	        final JMenuItem cut = new JMenuItem();
	        cut.setText("����");
	        JMenuItem paste = new JMenuItem();
	        paste.setText("ճ��");
	        final JMenuItem selectall = new JMenuItem();
	        selectall.setText("ȫѡ");
	        JMenuItem auto = new JMenuItem();
	        auto.setText("�Զ�����");
	        final JMenuItem font = new JMenuItem();
	        font.setText("����");
	        JMenuItem about = new JMenuItem();
	        about.setText("���ڼ��±���");
	        JMenuItem ztl = new JMenuItem();
	        ztl.setText("״̬��");

	        // �˵������(�ļ��˵�)
	        wj.add(open);
	        wj.add(New);
	        wj.add(save);
	        wj.add(saveas);
	        wj.addSeparator();
	        wj.add(exit);

	        // �˵������(�༭�˵�)
	        bj.add(cut);
	        bj.add(copy);
	        bj.add(paste);
	        bj.addSeparator();
	        bj.add(selectall);

	        // �˵������(��ʽ�˵�)
	        gs.add(auto);
	        gs.add(font);

	        // �˵������(�鿴�˵�)
	        ck.add(ztl);

	        // �˵������(�����˵�)
	        bz.add(about);

	        // �Ѹ����˵���ӵ��˵���
	        gjl.add(wj);
	        gjl.add(bj);
	        gjl.add(gs);
	        gjl.add(ck);
	        gjl.add(bz);
	        this.setJMenuBar(gjl);
	        this.add(ja);

	        // ����
	        JLabel lbfont = new JLabel("����");
	        JLabel lbsize = new JLabel("��С");
	        JLabel lbshape = new JLabel("����");
	        JPanel jpfont1 = new JPanel();
	        JPanel jpfont2 = new JPanel();
	        JPanel jpfont3 = new JPanel();
	        JPanel jpfont4 = new JPanel();
	        final JFrame fontMain = new JFrame();
	        final JDialog fontJd = new JDialog(fontMain, "����,��ɫ,��ɫ��", true);
	        JButton ok = new JButton("ȷ��");
	        final JButton canel = new JButton("ȡ��");
	        JButton color = new JButton("��ɫ");
	        final List fontList = new List(10, false);
	        final List fontShape = new List(4, false);
	        final List sizeList = new List(10, false);

	        // ��ϵͳ����
	        String[] fontNames = GraphicsEnvironment.getLocalGraphicsEnvironment()
	                .getAvailableFontFamilyNames();
	        for (int i = 0; i < fontNames.length; i++) {
	            fontList.add(fontNames[i]);
	        }

	        // ������״
	        fontShape.add("����");
	        fontShape.add("����");
	        fontShape.add("б��");
	        fontShape.add("��б��");

	        // ��������Ի���
	        fontJd.setSize(450, 350);
	        jpfont1.add(lbfont);
	        jpfont1.add(fontList);
	        jpfont2.add(lbsize);
	        jpfont2.add(sizeList);
	        jpfont3.add(lbshape);
	        jpfont3.add(fontShape);
	        jpfont4.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
	        jpfont4.add(ok);
	        jpfont4.add(canel);
	        jpfont4.add(color);

	        fontJd.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
	        fontJd.add(jpfont1);
	        fontJd.add(jpfont2);
	        fontJd.add(jpfont3);
	        fontJd.add(jpfont4);
//
//	        fontList.select(a);
//	        sizeList.select(4);
//	        fontShape.select(0);

	        // �����С
	        final String[] size = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
	                "10" };
	        for (int i = 0; i < size.length; i++) {
	            sizeList.add(size[i]);
	        }

	        // ������ɫ��ť������
	        color.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	                JColorChooser jcolor = new JColorChooser();
	                Color fcolor = ja.getForeground();
	                ja.setForeground(jcolor.showDialog(ja, "ѡ��������ɫ", fcolor));
	            }

	        });

	        // ȡ����ť������
	        canel.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	                fontMain.setVisible(false);
	            }

	        });

	        // ȷ����ť������
	        ok.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	                fontMain.setVisible(false);
	            }

	        });

	        // ���������
	        font.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				fontJd.setLocationRelativeTo(SimpleNotePad.this);
				fontJd.setVisible(true);

				int style = 0;
				if (fontShape.getSelectedItem() != null) {
					if (fontShape.getSelectedItem().equals("����") == true) {
						style = Font.PLAIN;
					}
					if (fontShape.getSelectedItem().equals("б��") == true) {
						style = Font.ITALIC;
					}
					if (fontShape.getSelectedItem().equals("����") == true) {
						style = Font.BOLD;
					}
					if (fontShape.getSelectedItem().equals("��б��") == true) {
						style = Font.BOLD + Font.ITALIC;
					}
				}

				// ���������С
				String[] size = { "1", "2", "3", "4", "5", "6", "7", "8", "9",
						"10" };
				if(sizeList.getSelectedItem()!=null){
					for (int i = 0; i < size.length; i++) {
						if (sizeList.getSelectedItem().equals(size[i]) == true) {
							ja.setFont(new Font(String.valueOf(fontList
									.getSelectedItem()), style, (10 - i) * 10));
							fontJd.dispose();
						}
					}
				}

			}
	        });

	        // �ļ��ı��¼�
	        ja.getDocument().addDocumentListener(new DocumentListener() {

	            public void changedUpdate(DocumentEvent e) {
	                flag = true;
	            }

	            public void insertUpdate(DocumentEvent e) {
	                flag = true;
	            }

	            public void removeUpdate(DocumentEvent e) {
	                flag = true;
	            }

	        });

	        // ���ڼ�����
	        this.addWindowListener(new WindowAdapter() {
	            public void windowClosing(WindowEvent w) {
	                fileName = SimpleNotePad.this.getTitle();
	                if (flag == true) {
	                    int returnValue = JOptionPane.showConfirmDialog(null,
	                    		SimpleNotePad.this.getTitle() + "�ļ���û�б��棡��Ҫ���棿", "���±�",
	                            JOptionPane.YES_NO_CANCEL_OPTION);
	                    if (returnValue == JOptionPane.YES_OPTION) {
	                        save_asMethod();

	                    } else if (returnValue == JOptionPane.NO_OPTION) {
	                        System.exit(0);

	                    } else{
	                        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	                    }

	                }
	            }

	            // �˳�ʱ�õ��ı����ļ�����
	            public void save_asMethod() {
	                FileDialog fd = new FileDialog(SimpleNotePad.this, "���Ϊ��",
	                        FileDialog.SAVE);
	                fd.setFile("*.txt");
	                fd.setVisible(true);

	                filePath = fd.getDirectory();
	                fileName = fd.getFile();

	                try {
	                    FileWriter fw = new FileWriter(filePath + fileName);
	                    BufferedWriter bw = new BufferedWriter(fw);
	                    PrintWriter pw = new PrintWriter(bw);

	                    pw.println(ja.getText());
	                    pw.flush();
	                    pw.close();
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }
	        });

	        // �Ҽ���ݷ�ʽ
	        final JPopupMenu jp = new JPopupMenu();
	        final JMenuItem jcopy = new JMenuItem("����");
	        final JMenuItem jpaste = new JMenuItem("ճ��");
	        final JMenuItem jcut = new JMenuItem("����");
	        final JMenuItem jselectall = new JMenuItem("ȫѡ");
	        final JMenuItem jfont = new JMenuItem("����");

	        jp.add(jcopy);
	        jp.add(jpaste);
	        jp.add(jcut);
	        jp.addSeparator();
	        jp.add(jselectall);
	        jp.addSeparator();
	        jp.add(jfont);

	        // �Ҽ��˵�֮������¼�

	        // �Ҽ��˵�֮���Ƶ��¼�
	        jcopy.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	                ja.copy();
	            }

	        });

	        // �Ҽ��˵�֮ճ�����¼�
	        jpaste.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	                ja.paste();
	            }

	        });

	        // �Ҽ��˵�֮���е��¼�
	        jcut.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	                ja.cut();
	            }

	        });

	        // �Ҽ��˵�֮ȫѡ���¼�
	        jselectall.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	                ja.selectAll();
	            }

	        });

	        // �Ҽ��˵�֮��������¼�
	        ja.addMouseListener(new MouseAdapter() {

	            public void mouseReleased(MouseEvent e) {
	                if (e.isPopupTrigger()) {
	                    jp.show((Component) (e.getSource()), e.getX(), e.getY());

	                    String temp = ja.getSelectedText();
	                    if (temp != null) {
	                        copy.setEnabled(true);
	                        cut.setEnabled(true);
	                        jcopy.setEnabled(true);
	                        jcut.setEnabled(true);
	                    } else if (temp == null) {
	                        copy.setEnabled(false);
	                        cut.setEnabled(false);
	                        jcopy.setEnabled(false);
	                        jcut.setEnabled(false);
	                    }
	                    String temp1 = ja.getText();
	                    if (temp1 == null) {
	                        selectall.setEnabled(false);
	                        jselectall.setEnabled(false);
	                    } else if (temp1 != null) {
	                        selectall.setEnabled(true);
	                        jselectall.setEnabled(true);
	                    }
	                }
	            }
	        });

	        // ���Ʋ˵���
	        copy.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	                ja.copy();
	            }

	        });

	        // ճ���˵���
	        paste.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {

	                try {
	                    ja.paste();
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }

	        });

	        // ���в˵���
	        cut.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	                try {
	                    ja.cut();
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }

	        });

	        // ȫѡ�˵���
	        selectall.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	                ja.selectAll();
	            }

	        });

	        // ���в˵���
	        auto.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	                if (a == 0) {
	                    ja.setLineWrap(true);
	                    a = 1;
	                } else if (a == 1) {
	                    ja.setLineWrap(false);
	                    a = 0;
	                }
	            }

	        });

	        // �½��˵���
	        New.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	            	SimpleNotePad.this.setTitle("�ޱ��⡪�����±�.txt");
	                ja.setText(" ");
	            }

	        });

	        // �˳��˵���
	        exit.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	                fileName = SimpleNotePad.this.getTitle();
	                if (flag == true) {
	                    int returnValue = JOptionPane.showConfirmDialog(null,
	                    		SimpleNotePad.this.getTitle() + "�ļ���û�б��棡��Ҫ���棿", "���±�",
	                            JOptionPane.YES_NO_CANCEL_OPTION);
	                    if (returnValue == JOptionPane.YES_OPTION) {
	                        save_asMethod();

	                    } else if (returnValue == JOptionPane.NO_OPTION) {
	                        System.exit(0);

	                    } else{
	                        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	                    }

	                }
	            }

	            public void save_asMethod() {
	                FileDialog fd = new FileDialog(SimpleNotePad.this, "���Ϊ��",
	                        FileDialog.SAVE);
	                fd.setFile("*.txt");
	                fd.setVisible(true);

	                filePath = fd.getDirectory();
	                fileName = fd.getFile();

	                try {
	                    FileWriter fw = new FileWriter(filePath + fileName);
	                    BufferedWriter bw = new BufferedWriter(fw);
	                    PrintWriter pw = new PrintWriter(bw);

	                    pw.println(ja.getText());
	                    pw.flush();
	                    pw.close();
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }

	        });

	        // ����˵���
	        save.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	                fileName = SimpleNotePad.this.getTitle();
	                try {
	                	 File fw= new File(filePath + fileName);
		                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
		                    		new FileOutputStream(fw), "UTF-8"));
		                    PrintWriter pw = new PrintWriter(bw);

		                    pw.println(ja.getText());
		                    pw.flush();
		                    pw.close();
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	                JOptionPane.showMessageDialog(null, "�ļ��Ѿ��ɹ������ˣ�", "�ļ�����",
	                        JOptionPane.INFORMATION_MESSAGE);
	            }

	        });

	        // ���˵���
	        saveas.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent arg0) {
	                FileDialog fd = new FileDialog(SimpleNotePad.this, "���Ϊ��",
	                        FileDialog.SAVE);
	                fd.setFile("*.txt");
	                fd.setVisible(true);

	                filePath = fd.getDirectory();
	                fileName = fd.getFile();

	                try {
	                    File fw= new File(filePath + fileName);
	                    BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
	                    		new FileOutputStream(fw), "UTF-8"));
	                    PrintWriter pw = new PrintWriter(bw);

	                    pw.println(ja.getText());
	                    pw.flush();
	                    pw.close();
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }

	        });

	        // �򿪲˵���
	        open.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	                FileDialog fd1 = new FileDialog(SimpleNotePad.this, "��",
	                        FileDialog.LOAD);
	                fd1.setFile("*.txt");
	                fd1.setVisible(true);

	                fileName = fd1.getFile();
	                filePath = fd1.getDirectory();
	                SimpleNotePad.this.setTitle(fileName);
	                try {
	                    File fr=new File(filePath + fileName);
	                    BufferedReader br = new BufferedReader(new InputStreamReader(
	                    		new FileInputStream(fr), "UTF-8"));
	                    String sinput = "";
	                    ja.setText("");
	                    int lineNum = 0;
	                    boolean isShowConfigWindow=true;
	                    while ((sinput = br.readLine()) != null) {
	                    	if(lineNum>100000&&isShowConfigWindow){
	                    		int returnValue = JOptionPane.showConfirmDialog(null,
	      	                    		SimpleNotePad.this.getTitle() + "�ļ�̫���Ƿ�ֻ��ʾǰ100000�У�", "���±�",
	      	                            JOptionPane.YES_NO_CANCEL_OPTION);
	      	                    if (returnValue == JOptionPane.YES_OPTION) {
	      	                        break;
	      	                    }else{
	      	                    	isShowConfigWindow=false;
	      	                    }
	                    	}
	                        // System.out.println(sinput);
	                        ja.append(sinput + "\r\n");
	                        lineNum++;
	                    }
	                } catch (Exception ex) {
	                    ex.printStackTrace();
	                }
	            }

	        });

	        // ���ڲ˵���
	        final JFrame jfm = new JFrame();
	        JLabel lb1 = new JLabel("�����±���panxibao����\n");
	        JLabel lb2 = new JLabel("��Ȩ���У�����ؾ���\n");
	        JLabel lb3 = new JLabel("ллʹ�ã�\n");

	        jfm.setSize(220, 170);
	        jfm.setTitle("���ڱ����");
	        jfm.add(lb1);
	        jfm.add(lb2);
	        jfm.add(lb3);
	        final JButton qd = new JButton("ȷ��");
	        qd.setSize(20, 17);
	        jfm.add(qd);

	        // ���ڲ˵���
	        about.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	                jfm.setLocationRelativeTo(SimpleNotePad.this);
	                jfm.setVisible(true);
	                jfm.setLayout(new GridLayout(4, 1, 5, 10));
	                jfm.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));

	                qd.addActionListener(new ActionListener() {

	                    public void actionPerformed(ActionEvent e) {
	                        jfm.setVisible(false);
	                    }

	                });
	            }

	        });

	        // ״̬���˵���
	        final JLabel lb = new JLabel();
	        ztl.addActionListener(new ActionListener() {

	            public void actionPerformed(ActionEvent e) {
	                if (a == 1) {
	                    lb.setVisible(false);
	                    a = 0;
	                } else if (a == 0) {
	                	SimpleNotePad.this.add(lb, BorderLayout.SOUTH);
	                    lb.setVisible(true);
	                    lb.setText("��ǰ����: "
	                            + String.valueOf(ja.getText().trim().length())
	                            + " " + "��ǰ����: "
	                            + String.valueOf(ja.getLineCount()));
	                    a = 1;
	                }
	            }

	        });

	        // ��ݼ�
	        open.setAccelerator(KeyStroke.getKeyStroke('O', InputEvent.CTRL_MASK));
	        New.setAccelerator(KeyStroke.getKeyStroke('N', InputEvent.CTRL_MASK));
	        save.setAccelerator(KeyStroke.getKeyStroke('S', InputEvent.CTRL_MASK));
	        exit.setAccelerator(KeyStroke.getKeyStroke('Q', InputEvent.CTRL_MASK));

	        copy.setAccelerator(KeyStroke.getKeyStroke('C', InputEvent.CTRL_MASK));
	        paste.setAccelerator(KeyStroke.getKeyStroke('V', InputEvent.CTRL_MASK));
	        cut.setAccelerator(KeyStroke.getKeyStroke('X', InputEvent.CTRL_MASK));
	        selectall.setAccelerator(KeyStroke.getKeyStroke('A',
	                InputEvent.CTRL_MASK));

	        auto.setAccelerator(KeyStroke.getKeyStroke('H', InputEvent.CTRL_MASK));
	        font.setAccelerator(KeyStroke.getKeyStroke('F', InputEvent.ALT_MASK));

	        // ��ӹ�����
	        JScrollPane jsp = new JScrollPane(ja);
	        Container c = this.getContentPane();
	        c.add(jsp);
	        jsp.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED); // ��Ҫ��ʱ�����
	        jsp.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS); // ���Ǵ���

	        this.setVisible(true);
	    }

	    private static String change(String s) {
	        byte abyte0[] = s.getBytes();
	        char ac[] = new char[s.length()];
	        int i = 0;
	        for (int k = abyte0.length; i < k; i++) {
	            int j = abyte0[i];
	            if (j >= 48 && j <= 57)
	                j = ((j - 48) + 5) % 10 + 48;
	            else if (j >= 65 && j <= 90)
	                j = ((j - 65) + 13) % 26 + 65;
	            else if (j >= 97 && j <= 122)
	                j = ((j - 97) + 13) % 26 + 97;
	            ac[i] = (char) j;
	        }

	        return String.valueOf(ac);
	    }

	    public static void main(String[] args) {

	    	new SimpleNotePad();
	    }

}