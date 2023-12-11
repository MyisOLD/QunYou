package classsystem;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class QualityMonitor {
    public static void QualityMonitorAdd()throws AWTException {
        while (true) {
            Extents.clearConsole();
            System.out.println(">�׽���>���ܽ���>���������ⷽ��Ϣ\n");
            Scanner sc = new Scanner(System.in);

            System.out.print("������������ⷽID: ");
            int QualityMonitorID=sc.nextInt();
            System.out.print("������������ⷽ����: ");
            String QualityMonitorName = sc.next();
            System.out.print("������������ⷽ��ַ: ");
            String QualityMonitorAddress = sc.next();
            System.out.print("������������ⷽ����������: ");
            String QualityMonitorContactName= sc.next();
            System.out.print("������������ⷽ�����˵绰: ");
            String QualityMonitorContactPhone = sc.next();
            System.out.print("������������ⷽ����������: ");
            String QualityMonitorContactEmail = sc.next();



            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\t������ⷽID: " + QualityMonitorID + "\t\t������ⷽ����: " + QualityMonitorName + "\t\t������ⷽ��ַ: " + QualityMonitorAddress+ "\t\t������ⷽ����������: " + QualityMonitorContactName+ "\t\t������ⷽ�����˵绰: " + QualityMonitorContactPhone+ "\t\t������ⷽ����������: " + QualityMonitorContactEmail);
            System.out.print("\n\n�Ƿ���Ӹ�������ⷽ��Ϣ�� [Yes(1) / No(0)] ��");
            isAdd( sc,QualityMonitorID,QualityMonitorName,QualityMonitorAddress,QualityMonitorContactName,QualityMonitorContactPhone,QualityMonitorContactEmail);
            System.out.println("\n\n\n>�׽���>���ܽ���>���������ⷽ��Ϣ\n");
            System.out.println("\t                �������                   ������1               ");
            System.out.println();
            System.out.println("\t                �����ϼ�                   ������0               ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.print("\n����������ѡ��");
            while (true) {
                int choose = sc.nextInt();
                if (choose == 1) {
                    break;
                } else if (choose == 0) {
                    Extents.clearConsole();
                    return;
                } else {
                    System.out.print("���������룺  ");
                }
            }
        }
    }

    public static void isAdd( Scanner sc,int QualityMonitorID,String QualityMonitorName,String QualityMonitorAddress,String QualityMonitorContactName,String QualityMonitorContactPhone,String QualityMonitorContactEmail) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
                break;
            } else if (is == 1) {
                Connection connection =null;

                String sql ="INSERT INTO QualityMonitor (MonitorID , MonitorName  , Address , ContactName,ContactPhone,ContactEmail  ) VALUES (?, ?, ?, ?,?,?)";
                PreparedStatement preparedStatement=null;
                try {

                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, QualityMonitorID);
                    preparedStatement.setString(2, QualityMonitorName);
                    preparedStatement.setString(3, QualityMonitorAddress);
                    preparedStatement.setString(4, QualityMonitorContactName);
                    preparedStatement.setString(5, QualityMonitorContactPhone);
                    preparedStatement.setString(6, QualityMonitorContactEmail);

                    //ִ��
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("���������ⷽ��Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺");
        }
    }

    public static void QualityMonitorQuery()throws AWTException {
        while (true) {
            System.out.println("1.��������ⷽID��ѯ");
            System.out.println("2.��������ⷽ���Ʋ�ѯ");
            System.out.println("3.��ѯȫ�� ");
            System.out.print("\n����������ѡ��:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            if (input == 1) {
                int QualityMonitorID0;
                int flag;
                while(true) {
                    System.out.print("������Ҫ��ѯ��������ⷽID��");
                    QualityMonitorID0 = sc.nextInt();
                    flag =getQualityMonitorID(QualityMonitorID0);
                    if (flag == -1) {
                        System.out.print("\n��������ⷽ�����ڣ�����������\n");
                    }
                    else {
                        break;
                    }
                }

                Connection connection = null;
                String sql = "select MonitorID  , MonitorName  , Address , ContactName,ContactPhone,ContactEmail from QualityMonitor where MonitorID =?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, QualityMonitorID0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int QualityMonitorID = set.getInt("MonitorID ");
                        String QualityMonitorName = set.getString("MonitorName ");
                        String QualityMonitorAddress = set.getString("Address");
                        String QualityMonitorContactName = set.getString("ContactName");
                        String QualityMonitorContactPhone = set.getString("ContactPhone");
                        String QualityMonitorContactEmail = set.getString("ContactEmail");

                        System.out.println("\t\t������ⷽID: " + QualityMonitorID + "\t\t������ⷽ����: " + QualityMonitorName + "\t\t������ⷽ��ַ: " + QualityMonitorAddress+ "\t\t������ⷽ����������: " + QualityMonitorContactName+ "\t\t������ⷽ�����˵绰: " + QualityMonitorContactPhone+ "\t\t������ⷽ����������: " + QualityMonitorContactEmail);

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;

            } else if (input == 2) {
                String QualityMonitorName0;
                String flag;
                while(true) {
                    System.out.print("������Ҫ��ѯ��������ⷽ���֣�");
                    QualityMonitorName0 = sc.next();
                    flag = getQualityMonitorName(QualityMonitorName0);
                    if (flag.equals("-1")) {
                        System.out.print("\n��������ⷽ�����ڣ�����������\n");
                    }
                    else {
                        break;
                    }
                }
                Connection connection = null;
                String sql = "select MonitorID  , MonitorName  , Address , ContactName,ContactPhone,ContactEmail from QualityMonitor where MonitorName =?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, QualityMonitorName0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int QualityMonitorID = set.getInt("MonitorID ");
                        String QualityMonitorName = set.getString("MonitorName ");
                        String QualityMonitorAddress = set.getString("Address");
                        String QualityMonitorContactName = set.getString("ContactName");
                        String QualityMonitorContactPhone = set.getString("ContactPhone");
                        String QualityMonitorContactEmail = set.getString("ContactEmail");


                        System.out.println("\t\t������ⷽID: " + QualityMonitorID + "\t\t������ⷽ����: " + QualityMonitorName + "\t\t������ⷽ��ַ: " + QualityMonitorAddress+ "\t\t������ⷽ����������: " + QualityMonitorContactName+ "\t\t������ⷽ�����˵绰: " + QualityMonitorContactPhone+ "\t\t������ⷽ����������: " + QualityMonitorContactEmail);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;
            } else if (input == 3) {
                String tablename = "QualityMonitor";
                FunctionBlock.printTable(tablename);
                break;
            } else {
                System.out.print("\n�������!���������룺\n");
            }
        }
    }

    public static void deleteQualityMonitor() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n������Ҫɾ����������ⷽID��");
            int QualityMonitorID = sc.nextInt();
            sc.nextLine();
            int flag = getQualityMonitorID(QualityMonitorID);
            if (flag == -1) {
                System.out.print("\n��������ⷽ�����ڣ�����������\n");
            } else {
                System.out.print("\n�Ƿ�ɾ��IDΪ��" + QualityMonitorID + " ��������ⷽ��Ϣ�� [Yes(1) / No(0)] :");
                isDelete(sc, flag);
                System.out.println("\n\n\n>�׽���>���ܽ���>ɾ��������ⷽ��Ϣ\n");
                System.out.println("\t                ����ɾ��                   ������1                ");
                System.out.println();
                System.out.println("\t                �����ϼ�                   ������0                ");
                System.out.println("\t ----------------------------------------------------------------");
                System.out.print("\n����������ѡ��: ");
                while (true) {
                    int choose = sc.nextInt();
                    if (choose == 1) {
                        break;
                    } else if (choose == 0) {
                        Extents.clearConsole();
                        return;
                    } else {
                        System.out.print("����������: ");
                    }
                }
            }
        }
    }

    public static void isDelete( Scanner sc, int flag) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
                break;
            } else if (is == 1) {
                Connection connection =null;
                String sql ="delete from QualityMonitor where MonitorID =?";
                PreparedStatement preparedStatement=null;
                try {
                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1,flag);
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("ɾ��������ⷽ��Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺");
        }
    }

    public static void updateQualityMonitor() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n������Ҫ�޸���Ϣ��������ⷽID:");
            int QualityMonitorID = sc.nextInt();
            int flag = getQualityMonitorID(QualityMonitorID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("��������ⷽ�����ڣ�����������\n\n\n ");
            } else {
                System.out.println(">�׽���>���ܽ���>�޸�������ⷽ��Ϣ\n");
                //������ⷽID������������
                System.out.print("������������ⷽ����: ");
                String QualityMonitorName = sc.next();
                System.out.print("������������ⷽ��ַ: ");
                String QualityMonitorAddress = sc.next();
                System.out.print("������������ⷽ����������: ");
                String QualityMonitorContactName= sc.next();
                System.out.print("������������ⷽ�����˵绰: ");
                String QualityMonitorContactPhone = sc.next();
                System.out.print("������������ⷽ����������: ");
                String QualityMonitorContactEmail = sc.next();
                Extents.clearConsole();

                System.out.println(">�׽���>���ܽ���>�޸�������ⷽ��Ϣ");
                System.out.println("�޸ĺ󡪡�>\n");
                System.out.println("\n\n-----------------------------------------------------");
                System.out.println("\t\t������ⷽID: " + QualityMonitorID + "\t\t������ⷽ����: " + QualityMonitorName + "\t\t������ⷽ��ַ: " + QualityMonitorAddress+ "\t\t������ⷽ����������: " + QualityMonitorContactName+ "\t\t������ⷽ�����˵绰: " + QualityMonitorContactPhone+ "\t\t������ⷽ����������: " + QualityMonitorContactEmail);

                System.out.print("\n\n�Ƿ��޸ĸ�������ⷽ��Ϣ�� [Yes(1) / No(0)] ��");
                isUpdate( sc,QualityMonitorID,QualityMonitorName,QualityMonitorAddress,QualityMonitorContactName,QualityMonitorContactPhone,QualityMonitorContactEmail);
                System.out.println("\n\n\n>�׽���>���ܽ���>�޸�������ⷽ��Ϣ\n");
                System.out.println("\t                �����޸�                   ������1              ");
                System.out.println();
                System.out.println("\t                �����ϼ�                   ������0              ");
                System.out.println("\t --------------------------------------------------------------");
                System.out.print("\n����������ѡ��:");
                while (true) {
                    int choose = sc.nextInt();
                    if (choose == 1) {
                        Extents.clearConsole();
                        break;
                    } else if (choose == 0) {
                        Extents.clearConsole();
                        return;
                    } else {
                        System.out.print("������� ����������: ");
                    }
                }
            }
        }
    }

    public static void isUpdate(Scanner sc,int QualityMonitorID,String QualityMonitorName,String QualityMonitorAddress,String QualityMonitorContactName,String QualityMonitorContactPhone,String QualityMonitorContactEmail) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
                break;
            } else if (is == 1) {
                Connection connection =null;
                String sql ="update QualityMonitor set MonitorID =?,MonitorName =?,Address=?,ContactName=?,ContactPhone=?,ContactEmail=? where MonitorID=?";
                PreparedStatement preparedStatement=null;
                try {
                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, QualityMonitorID);
                    preparedStatement.setString(2, QualityMonitorName);
                    preparedStatement.setString(3, QualityMonitorAddress);
                    preparedStatement.setString(4, QualityMonitorContactName);
                    preparedStatement.setString(5, QualityMonitorContactPhone);
                    preparedStatement.setString(6, QualityMonitorContactEmail);
                    preparedStatement.setInt(7, QualityMonitorID);

                    //ִ��
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("�޸�������ⷽ��Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺\n");
        }
    }
    public static int getQualityMonitorID(int QualityMonitorID) {

        Connection connection =null;
        String sql="select * from QualityMonitor";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();
            while(set.next()){
                int id  =set.getInt("MonitorID");
                if(QualityMonitorID==id){
                    return id;
                }
            }
            return -1;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close(set,preparedStatement,connection);
        }
    }

    public static String getQualityMonitorName(String QualityMonitorName) {

        Connection connection =null;
        String sql="select * from QualityMonitor";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();

            while(set.next()){
                String name  =set.getString("MonitorName");
                if(QualityMonitorName.equals(name)){
                    return name;
                }
            }
            return "-1";
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }finally {
            JDBC.close(set,preparedStatement,connection);
        }
    }




}
