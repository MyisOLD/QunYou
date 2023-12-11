package classsystem;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Client {

    public static void ClientAdd()throws AWTException {
        while (true) {
            Extents.clearConsole();
            System.out.println(">�׽���>���ܽ���>���ί�з���Ϣ\n");
            Scanner sc = new Scanner(System.in);

            System.out.print("������ί�з�ID: ");
            int clientID=sc.nextInt();
            System.out.print("������ί�з�����: ");
            String clientName = sc.next();
            System.out.print("������ί�з���ַ: ");
            String clientAddress = sc.next();
            System.out.print("������ί�з�����������: ");
            String clientContactName= sc.next();
            System.out.print("������ί�з������˵绰: ");
            String clientContactPhone = sc.next();
            System.out.print("������ί�з�����������: ");
            String clientContactEmail = sc.next();



            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\tί�з�ID: " + clientID + "\t\tί�з�����: " + clientName + "\t\tί�з���ַ: " + clientAddress+ "\t\tί�з�����������: " + clientContactName+ "\t\tί�з������˵绰: " + clientContactPhone+ "\t\tί�з�����������: " + clientContactEmail);
            System.out.print("\n\n�Ƿ���Ӹ�ί�з���Ϣ�� [Yes(1) / No(0)] ��");
            isAdd( sc,clientID,clientName,clientAddress,clientContactName,clientContactPhone,clientContactEmail);
            System.out.println("\n\n\n>�׽���>���ܽ���>���ί�з���Ϣ\n");
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

    public static void isAdd( Scanner sc,int clientID,String clientName,String clientAddress,String clientContactName,String clientContactPhone,String clientContactEmail) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
                break;
            } else if (is == 1) {
                Connection connection =null;

                String sql ="INSERT INTO Client (ClientID , ClientName , Address , ContactName,ContactPhone,ContactEmail  ) VALUES (?, ?, ?, ?,?,?)";
                PreparedStatement preparedStatement=null;
                try {

                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, clientID);
                    preparedStatement.setString(2, clientName);
                    preparedStatement.setString(3, clientAddress);
                    preparedStatement.setString(4, clientContactName);
                    preparedStatement.setString(5, clientContactPhone);
                    preparedStatement.setString(6, clientContactEmail);

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("���ί�з���Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺");
        }
    }

    public static void ClientQuery()throws AWTException {
        while (true) {
            System.out.println("1.��ί�з�ID��ѯ");
            System.out.println("2.��ί�з����Ʋ�ѯ");
            System.out.println("3.��ѯȫ�� ");
            System.out.print("\n����������ѡ��:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            if (input == 1) {
                int clientID0;
                int flag;
                while(true) {
                    System.out.print("������Ҫ��ѯ��ί�з�ID��");
                    clientID0 = sc.nextInt();
                    flag =getClientID(clientID0);
                    if (flag == -1) {
                        System.out.print("\n��ί�з������ڣ�����������\n");
                    }
                    else {
                        break;
                    }
                }

                Connection connection = null;
                String sql = "select ClientID , ClientName , Address , ContactName,ContactPhone,ContactEmail from Client where ClientID=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, clientID0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int clientID = set.getInt("ClientID");
                        String clientName = set.getString("ClientName");
                        String clientAddress = set.getString("Address");
                        String clientContactName = set.getString("ContactName");
                        String clientContactPhone = set.getString("ContactPhone");
                        String clientContactEmail = set.getString("ContactEmail");

                        System.out.println("\t\tί�з�ID: " + clientID + "\t\tί�з�����: " + clientName + "\t\tί�з���ַ: " + clientAddress+ "\t\tί�з�����������: " + clientContactName+ "\t\tί�з������˵绰: " + clientContactPhone+ "\t\tί�з�����������: " + clientContactEmail);

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;

            } else if (input == 2) {
                String clientName0;
                String flag;
                while(true) {
                    System.out.print("������Ҫ��ѯ��ί�з����֣�");
                    clientName0 = sc.next();
                    flag = getClientName(clientName0);
                    if (flag.equals("-1")) {
                        System.out.print("\n��ί�з������ڣ�����������\n");
                    }
                    else {
                        break;
                    }
                }
                Connection connection = null;
                String sql = "select ClientID , ClientName , Address , ContactName,ContactPhone,ContactEmail from Client where ClientName=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, clientName0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int clientID = set.getInt("ClientID");
                        String clientName = set.getString("ClientName");
                        String clientAddress = set.getString("Address");
                        String clientContactName = set.getString("ContactName");
                        String clientContactPhone = set.getString("ContactPhone");
                        String clientContactEmail = set.getString("ContactEmail");


                        System.out.println("\t\tί�з�ID: " + clientID + "\t\tί�з�����: " + clientName + "\t\tί�з���ַ: " + clientAddress+ "\t\tί�з�����������: " + clientContactName+ "\t\tί�з������˵绰: " + clientContactPhone+ "\t\tί�з�����������: " + clientContactEmail);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;
            } else if (input == 3) {
                String tablename = "Client";
                FunctionBlock.printTable(tablename);
                break;
            } else {
                System.out.print("\n�������!���������룺\n");
            }
        }
    }

    public static void deleteClient() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n������Ҫɾ����ί�з�ID��");
            int clientID = sc.nextInt();
            sc.nextLine();
            int flag = getClientID(clientID);
            if (flag == -1) {
                System.out.print("\n��ί�з������ڣ�����������\n");
            } else {
                System.out.print("\n�Ƿ�ɾ��IDΪ��" + clientID + " ��ί�з���Ϣ�� [Yes(1) / No(0)] :");
                isDelete(sc, flag);
                System.out.println("\n\n\n>�׽���>���ܽ���>ɾ��ί�з���Ϣ\n");
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
                String sql ="delete from Client where ClientID=?";
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
                System.out.println("ɾ��ί�з���Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺");
        }
    }

    public static void updateClient() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n������Ҫ�޸���Ϣ��ί�з�ID:");
            int clientID = sc.nextInt();
            int flag = getClientID(clientID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("��ί�з������ڣ�����������\n\n\n ");
            } else {
                System.out.println(">�׽���>���ܽ���>�޸�ί�з���Ϣ\n");
                //ί�з�ID������������
                System.out.print("������ί�з�����: ");
                String clientName = sc.next();
                System.out.print("������ί�з���ַ: ");
                String clientAddress = sc.next();
                System.out.print("������ί�з�����������: ");
                String clientContactName= sc.next();
                System.out.print("������ί�з������˵绰: ");
                String clientContactPhone = sc.next();
                System.out.print("������ί�з�����������: ");
                String clientContactEmail = sc.next();
                Extents.clearConsole();

                System.out.println(">�׽���>���ܽ���>�޸�ί�з���Ϣ");
                System.out.println("�޸ĺ󡪡�>\n");
                System.out.println("\n\n-----------------------------------------------------");
                System.out.println("\t\tί�з�ID: " + clientID + "\t\tί�з�����: " + clientName + "\t\tί�з���ַ: " + clientAddress+ "\t\tί�з�����������: " + clientContactName+ "\t\tί�з������˵绰: " + clientContactPhone+ "\t\tί�з�����������: " + clientContactEmail);

                System.out.print("\n\n�Ƿ��޸ĸ�ί�з���Ϣ�� [Yes(1) / No(0)] ��");
                isUpdate( sc,clientID,clientName,clientAddress,clientContactName,clientContactPhone,clientContactEmail);
                System.out.println("\n\n\n>�׽���>���ܽ���>�޸�ί�з���Ϣ\n");
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

    public static void isUpdate(Scanner sc,int clientID,String clientName,String clientAddress,String clientContactName,String clientContactPhone,String clientContactEmail) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
                break;
            } else if (is == 1) {
                Connection connection =null;
                String sql ="update Client set ClientID=?,ClientName=?,Address=?,ContactName=?,ContactPhone=?,ContactEmail=? where ClientID=?";
                PreparedStatement preparedStatement=null;
                try {
                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, clientID);
                    preparedStatement.setString(2, clientName);
                    preparedStatement.setString(3, clientAddress);
                    preparedStatement.setString(4, clientContactName);
                    preparedStatement.setString(5, clientContactPhone);
                    preparedStatement.setString(6, clientContactEmail);
                    preparedStatement.setInt(7, clientID);

                    //ִ��
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("�޸�ί�з���Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺\n");
        }
    }
    public static int getClientID(int clientID) {

        Connection connection =null;
        String sql="select * from Client";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();
            while(set.next()){
                int id  =set.getInt("ClientID");
                if(clientID==id){
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

    public static String getClientName(String clientName) {

        Connection connection =null;
        String sql="select * from Client";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();

            while(set.next()){
                String name  =set.getString("ClientName");
                if(clientName.equals(name)){
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
