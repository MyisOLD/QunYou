package classsystem;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Collaborator {
    public static void CollaboratorAdd()throws AWTException {
        while (true) {
            Extents.clearConsole();
            System.out.println(">�׽���>���ܽ���>��Ӻ�������Ϣ\n");
            Scanner sc = new Scanner(System.in);

            System.out.print("�����������ID: ");
            int CollaboratorID=sc.nextInt();
            System.out.print("���������������: ");
            String CollaboratorName = sc.next();
            System.out.print("�������������ַ: ");
            String CollaboratorAddress = sc.next();
            System.out.print("���������������������: ");
            String CollaboratorContactName= sc.next();
            System.out.print("����������������˵绰: ");
            String CollaboratorContactPhone = sc.next();
            System.out.print("���������������������: ");
            String CollaboratorContactEmail = sc.next();



            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\t������ID: " + CollaboratorID + "\t\t����������: " + CollaboratorName + "\t\t��������ַ: " + CollaboratorAddress+ "\t\t����������������: " + CollaboratorContactName+ "\t\t�����������˵绰: " + CollaboratorContactPhone+ "\t\t����������������: " + CollaboratorContactEmail);
            System.out.print("\n\n�Ƿ���Ӹú�������Ϣ�� [Yes(1) / No(0)] ��");
            isAdd( sc,CollaboratorID,CollaboratorName,CollaboratorAddress,CollaboratorContactName,CollaboratorContactPhone,CollaboratorContactEmail);
            System.out.println("\n\n\n>�׽���>���ܽ���>��Ӻ�������Ϣ\n");
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

    public static void isAdd( Scanner sc,int CollaboratorID,String CollaboratorName,String CollaboratorAddress,String CollaboratorContactName,String CollaboratorContactPhone,String CollaboratorContactEmail) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
                break;
            } else if (is == 1) {
                Connection connection =null;

                String sql ="INSERT INTO Collaborator (CollaboratorID , CollaboratorName , Address , ContactName,ContactPhone,ContactEmail  ) VALUES (?, ?, ?, ?,?,?)";
                PreparedStatement preparedStatement=null;
                try {

                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, CollaboratorID);
                    preparedStatement.setString(2, CollaboratorName);
                    preparedStatement.setString(3, CollaboratorAddress);
                    preparedStatement.setString(4, CollaboratorContactName);
                    preparedStatement.setString(5, CollaboratorContactPhone);
                    preparedStatement.setString(6, CollaboratorContactEmail);

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("��Ӻ�������Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺");
        }
    }

    public static void CollaboratorQuery()throws AWTException {
        while (true) {
            System.out.println("1.��������ID��ѯ");
            System.out.println("2.�����������Ʋ�ѯ");
            System.out.println("3.��ѯȫ�� ");
            System.out.print("\n����������ѡ��:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            if (input == 1) {
                int CollaboratorID0;
                int flag;
                while(true) {
                    System.out.print("������Ҫ��ѯ�ĺ�����ID��");
                    CollaboratorID0 = sc.nextInt();
                    flag =getCollaboratorID(CollaboratorID0);
                    if (flag == -1) {
                        System.out.print("\n�ú����������ڣ�����������\n");
                    }
                    else {
                        break;
                    }
                }

                Connection connection = null;
                String sql = "select CollaboratorID , CollaboratorName , Address , ContactName,ContactPhone,ContactEmail from Collaborator where CollaboratorID=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, CollaboratorID0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int CollaboratorID = set.getInt("CollaboratorID");
                        String CollaboratorName = set.getString("CollaboratorName");
                        String CollaboratorAddress = set.getString("Address");
                        String CollaboratorContactName = set.getString("ContactName");
                        String CollaboratorContactPhone = set.getString("ContactPhone");
                        String CollaboratorContactEmail = set.getString("ContactEmail");

                        System.out.println("\t\t������ID: " + CollaboratorID + "\t\t����������: " + CollaboratorName + "\t\t��������ַ: " + CollaboratorAddress+ "\t\t����������������: " + CollaboratorContactName+ "\t\t�����������˵绰: " + CollaboratorContactPhone+ "\t\t����������������: " + CollaboratorContactEmail);

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;

            } else if (input == 2) {
                String CollaboratorName0;
                String flag;
                while(true) {
                    System.out.print("������Ҫ��ѯ�ĺ��������֣�");
                    CollaboratorName0 = sc.next();
                    flag = getCollaboratorName(CollaboratorName0);
                    if (flag.equals("-1")) {
                        System.out.print("\n�ú����������ڣ�����������\n");
                    }
                    else {
                        break;
                    }
                }
                Connection connection = null;
                String sql = "select CollaboratorID , CollaboratorName , Address , ContactName,ContactPhone,ContactEmail from Collaborator where CollaboratorName=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, CollaboratorName0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int CollaboratorID = set.getInt("CollaboratorID");
                        String CollaboratorName = set.getString("CollaboratorName");
                        String CollaboratorAddress = set.getString("Address");
                        String CollaboratorContactName = set.getString("ContactName");
                        String CollaboratorContactPhone = set.getString("ContactPhone");
                        String CollaboratorContactEmail = set.getString("ContactEmail");


                        System.out.println("\t\t������ID: " + CollaboratorID + "\t\t����������: " + CollaboratorName + "\t\t��������ַ: " + CollaboratorAddress+ "\t\t����������������: " + CollaboratorContactName+ "\t\t�����������˵绰: " + CollaboratorContactPhone+ "\t\t����������������: " + CollaboratorContactEmail);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;
            } else if (input == 3) {
                String tablename = "Collaborator";
                FunctionBlock.printTable(tablename);
                break;
            } else {
                System.out.print("\n�������!���������룺\n");
            }
        }
    }

    public static void deleteCollaborator() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n������Ҫɾ���ĺ�����ID��");
            int CollaboratorID = sc.nextInt();
            sc.nextLine();
            int flag = getCollaboratorID(CollaboratorID);
            if (flag == -1) {
                System.out.print("\n�ú����������ڣ�����������\n");
            } else {
                System.out.print("\n�Ƿ�ɾ��IDΪ��" + CollaboratorID + " �ĺ�������Ϣ�� [Yes(1) / No(0)] :");
                isDelete(sc, flag);
                System.out.println("\n\n\n>�׽���>���ܽ���>ɾ����������Ϣ\n");
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
                String sql ="delete from Collaborator where CollaboratorID=?";
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
                System.out.println("ɾ����������Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺");
        }
    }

    public static void updateCollaborator() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n������Ҫ�޸���Ϣ�ĺ�����ID:");
            int CollaboratorID = sc.nextInt();
            int flag = getCollaboratorID(CollaboratorID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("�ú����������ڣ�����������\n\n\n ");
            } else {
                System.out.println(">�׽���>���ܽ���>�޸ĺ�������Ϣ\n");
                //������ID������������
                System.out.print("���������������: ");
                String CollaboratorName = sc.next();
                System.out.print("�������������ַ: ");
                String CollaboratorAddress = sc.next();
                System.out.print("���������������������: ");
                String CollaboratorContactName= sc.next();
                System.out.print("����������������˵绰: ");
                String CollaboratorContactPhone = sc.next();
                System.out.print("���������������������: ");
                String CollaboratorContactEmail = sc.next();
                Extents.clearConsole();

                System.out.println(">�׽���>���ܽ���>�޸ĺ�������Ϣ");
                System.out.println("�޸ĺ󡪡�>\n");
                System.out.println("\n\n-----------------------------------------------------");
                System.out.println("\t\t������ID: " + CollaboratorID + "\t\t����������: " + CollaboratorName + "\t\t��������ַ: " + CollaboratorAddress+ "\t\t����������������: " + CollaboratorContactName+ "\t\t�����������˵绰: " + CollaboratorContactPhone+ "\t\t����������������: " + CollaboratorContactEmail);

                System.out.print("\n\n�Ƿ��޸ĸú�������Ϣ�� [Yes(1) / No(0)] ��");
                isUpdate( sc,CollaboratorID,CollaboratorName,CollaboratorAddress,CollaboratorContactName,CollaboratorContactPhone,CollaboratorContactEmail);
                System.out.println("\n\n\n>�׽���>���ܽ���>�޸ĺ�������Ϣ\n");
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

    public static void isUpdate(Scanner sc,int CollaboratorID,String CollaboratorName,String CollaboratorAddress,String CollaboratorContactName,String CollaboratorContactPhone,String CollaboratorContactEmail) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
                break;
            } else if (is == 1) {
                Connection connection =null;
                String sql ="update Collaborator set CollaboratorID=?,CollaboratorName=?,Address=?,ContactName=?,ContactPhone=?,ContactEmail=? where CollaboratorID=?";
                PreparedStatement preparedStatement=null;
                try {
                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, CollaboratorID);
                    preparedStatement.setString(2, CollaboratorName);
                    preparedStatement.setString(3, CollaboratorAddress);
                    preparedStatement.setString(4, CollaboratorContactName);
                    preparedStatement.setString(5, CollaboratorContactPhone);
                    preparedStatement.setString(6, CollaboratorContactEmail);
                    preparedStatement.setInt(7, CollaboratorID);

                    //ִ��
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("�޸ĺ�������Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺\n");
        }
    }
    public static int getCollaboratorID(int CollaboratorID) {

        Connection connection =null;
        String sql="select * from Collaborator";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();
            while(set.next()){
                int id  =set.getInt("CollaboratorID");
                if(CollaboratorID==id){
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

    public static String getCollaboratorName(String CollaboratorName) {

        Connection connection =null;
        String sql="select * from Collaborator";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();

            while(set.next()){
                String name  =set.getString("CollaboratorName");
                if(CollaboratorName.equals(name)){
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
