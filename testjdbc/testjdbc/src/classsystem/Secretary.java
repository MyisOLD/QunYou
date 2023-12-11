package classsystem;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Secretary{
    public static void SecretaryAdd()throws AWTException {
        while (true) {
            Extents.clearConsole();
            System.out.println(">�׽���>���ܽ���>���������Ϣ\n");
            Scanner sc = new Scanner(System.in);

            System.out.print("����������ID: ");
            int SecretaryID=sc.nextInt();
            System.out.print("������Ա��ID: ");
            int SecretaryEmployeeID=sc.nextInt();
            System.out.print("��������������: ");
            String SecretaryName = sc.next();
            System.out.print("�����������Ա�: ");
            String SecretaryGender = sc.next();
            System.out.print("��������������: ");
            int SecretaryAge=sc.nextInt();
            System.out.print("������������������(����2003-1-1): ");
            String SecretaryDate = sc.next();
            System.out.print("����������ְ��: ");
            String SecretaryResponsibilities = sc.next();

            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\t����ID: " + SecretaryID + "\t\tԱ��ID: " + SecretaryEmployeeID + "\t\t��������: " + SecretaryName+ "\t\t�����Ա�: " + SecretaryGender+ "\t\t��������: " + SecretaryAge+ "\t\t������������: " + SecretaryDate+ "\t\t����ְ��: " + SecretaryResponsibilities);
            System.out.print("\n\n�Ƿ���Ӹ�������Ϣ�� [Yes(1) / No(0)] ��");
            isAdd( sc,SecretaryID,SecretaryEmployeeID,SecretaryName,SecretaryGender,SecretaryAge,SecretaryDate,SecretaryResponsibilities);
            System.out.println("\n\n\n>�׽���>���ܽ���>���������Ϣ\n");
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

    public static void isAdd( Scanner sc,int SecretaryID,int SecretaryEmployeeID,String SecretaryName,String SecretaryGender,int SecretaryAge,String SecretaryDate,String SecretaryResponsibilities) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
                break;
            } else if (is == 1) {
                Connection connection =null;

                String sql ="INSERT INTO Secretary (SecretaryID , EmployeeID, SecretaryName , Gender, Age, HireDate, Responsibilities) VALUES (?, ?, ?, ?, ?, ?, ?)";
                PreparedStatement preparedStatement=null;
                try {

                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, SecretaryID);
                    preparedStatement.setInt(2, SecretaryEmployeeID);
                    preparedStatement.setString(3, SecretaryName);
                    preparedStatement.setString(4, SecretaryGender);
                    preparedStatement.setInt(5, SecretaryAge);
                    preparedStatement.setString(6, SecretaryDate);
                    preparedStatement.setString(7, SecretaryResponsibilities);


                    //ִ��
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("���������Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺");
        }
    }

    public static void SecretaryQuery()throws AWTException {
        while (true) {
            System.out.println("1.������ID��ѯ");
            System.out.println("2.���������Ʋ�ѯ");
            System.out.println("3.��ѯȫ�� ");
            System.out.print("\n����������ѡ��:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            if (input == 1) {
                int SecretaryID0;
                int flag;
                while(true) {
                    System.out.print("������Ҫ��ѯ������ID��");
                    SecretaryID0 = sc.nextInt();
                    flag =getSecretaryID(SecretaryID0);
                    if (flag == -1) {
                        System.out.print("\n�����鲻���ڣ�����������\n");
                    }
                    else {
                        break;
                    }
                }
                Connection connection = null;
                String sql = "select SecretaryID, EmployeeID, SecretaryName, Gender, Age, HireDate, Responsibilities from Secretary where SecretaryID=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, SecretaryID0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int SecretaryID = set.getInt("SecretaryID");
                        int SecretaryEmployeeID= set.getInt("EmployeeID");
                        String SecretaryName = set.getString("SecretaryName");
                        String SecretaryGender = set.getString("Gender");
                        int SecretaryAge = set.getInt("Age");
                        String SecretaryDate = set.getString("HireDate");
                        String SecretaryResponsibilities = set.getString("Responsibilities");


                        System.out.println("\t\t����ID: " + SecretaryID + "\t\tԱ��ID: " + SecretaryEmployeeID + "\t\t��������: " + SecretaryName+ "\t\t�����Ա�: " + SecretaryGender+ "\t\t��������: " + SecretaryAge+ "\t\t������������: " + SecretaryDate+ "\t\t����ְ��: " + SecretaryResponsibilities);

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;

            } else if (input == 2) {
                String SecretaryName0;
                String flag;
                while(true) {
                    System.out.print("������Ҫ��ѯ���������֣�");
                    SecretaryName0 = sc.next();
                    flag = getSecretaryName(SecretaryName0);
                    if (flag.equals("-1")) {
                        System.out.print("\n�����鲻���ڣ�����������\n");
                    }
                    else {
                        break;
                    }
                }
                Connection connection = null;
                String sql = "select SecretaryID, EmployeeID, SecretaryName, Gender, Age, HireDate, Responsibilities from Secretary where SecretaryName=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, SecretaryName0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int SecretaryID = set.getInt("SecretaryID");
                        int SecretaryEmployeeID= set.getInt("EmployeeID");
                        String SecretaryName = set.getString("SecretaryName");
                        String SecretaryGender = set.getString("Gender");
                        int SecretaryAge = set.getInt("Age");
                        String SecretaryDate = set.getString("HireDate");
                        String SecretaryResponsibilities = set.getString("Responsibilities");


                        System.out.println("\t\t����ID: " + SecretaryID + "\t\tԱ��ID: " + SecretaryEmployeeID + "\t\t��������: " + SecretaryName+ "\t\t�����Ա�: " + SecretaryGender+ "\t\t��������: " + SecretaryAge+ "\t\t������������: " + SecretaryDate+ "\t\t����ְ��: " + SecretaryResponsibilities);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;
            } else if (input == 3) {
                String tablename = "Secretary";
                FunctionBlock.printTable(tablename);
                break;
            } else {
                System.out.print("\n�������!���������룺\n");
            }
        }
    }

    public static void deleteSecretary() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n������Ҫɾ��������ID��");
            int SecretaryID = sc.nextInt();
            sc.nextLine();
            int flag = getSecretaryID(SecretaryID);
            if (flag == -1) {
                System.out.print("\n�����鲻���ڣ�����������\n");
            } else {
                System.out.print("\n�Ƿ�ɾ��IDΪ��" + SecretaryID + " ��������Ϣ�� [Yes(1) / No(0)] :");
                isDelete(sc, flag);
                System.out.println("\n\n\n>�׽���>���ܽ���>ɾ��������Ϣ\n");
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
                String sql ="delete from Secretary where SecretaryID=?";
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
                System.out.println("ɾ��������Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺");
        }
    }

    public static void updateSecretary() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n������Ҫ�޸���Ϣ������ID:");
            int SecretaryID = sc.nextInt();
            int flag = getSecretaryID(SecretaryID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("�����鲻���ڣ�����������\n\n\n ");
            } else {
                System.out.println(">�׽���>���ܽ���>�޸�������Ϣ\n");
                //����ID������������
                System.out.print("������Ա��ID: ");
                int SecretaryEmployeeID=sc.nextInt();
                System.out.print("��������������: ");
                String SecretaryName = sc.next();
                System.out.print("�����������Ա�: ");
                String SecretaryGender = sc.next();
                System.out.print("��������������: ");
                int SecretaryAge=sc.nextInt();
                System.out.print("������������������(����2003-1-1): ");
                String SecretaryDate = sc.next();
                System.out.print("����������ְ��: ");
                String SecretaryResponsibilities = sc.next();
                Extents.clearConsole();

                System.out.println(">�׽���>���ܽ���>�޸�������Ϣ");
                System.out.println("�޸ĺ󡪡�>\n");
                System.out.println("\n\n-----------------------------------------------------");
                System.out.println("\t\t����ID: " + SecretaryID + "\t\tԱ��ID: " + SecretaryEmployeeID + "\t\t��������: " + SecretaryName+ "\t\t�����Ա�: " + SecretaryGender+ "\t\t��������: " + SecretaryAge+ "\t\t������������: " + SecretaryDate+ "\t\t����ְ��: " + SecretaryResponsibilities);

                System.out.print("\n\n�Ƿ��޸ĸ�������Ϣ�� [Yes(1) / No(0)] ��");
                isUpdate( sc,SecretaryID,SecretaryEmployeeID,SecretaryName,SecretaryGender,SecretaryAge,SecretaryDate,SecretaryResponsibilities);
                System.out.println("\n\n\n>�׽���>���ܽ���>�޸�������Ϣ\n");
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

    public static void isUpdate( Scanner sc,int SecretaryID,int SecretaryEmployeeID,String SecretaryName,String SecretaryGender,int SecretaryAge,String SecretaryDate,String SecretaryResponsibilities) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
                break;
            } else if (is == 1) {
                Connection connection =null;
                String sql ="update Secretary set SecretaryID=?,EmployeeID=?,SecretaryName =?,Gender =?,Age =?, HireDate=?,Responsibilities=?where SecretaryID=?";

                PreparedStatement preparedStatement=null;
                try {
                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, SecretaryID);
                    preparedStatement.setInt(2, SecretaryEmployeeID);
                    preparedStatement.setString(3, SecretaryName);
                    preparedStatement.setString(4, SecretaryGender);
                    preparedStatement.setInt(5, SecretaryAge);
                    preparedStatement.setString(6, SecretaryDate);
                    preparedStatement.setString(7, SecretaryResponsibilities);
                    preparedStatement.setInt(8, SecretaryID);

                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("�޸�������Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺\n");
        }
    }
    public static int getSecretaryID(int SecretaryID) {

        Connection connection =null;
        String sql="select * from Secretary";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();
            while(set.next()){
                int id  =set.getInt("SecretaryID");
                if(SecretaryID==id){
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

    public static String getSecretaryName(String SecretaryName) {

        Connection connection =null;
        String sql="select * from Secretary";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();

            while(set.next()){
                String name  =set.getString("SecretaryName");
                if(SecretaryName.equals(name)){
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
