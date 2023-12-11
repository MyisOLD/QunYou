package classsystem;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Director {
    public static void DirectorAdd()throws AWTException {
        while (true) {
            Extents.clearConsole();
            System.out.println(">�׽���>���ܽ���>���������Ϣ\n");
            Scanner sc = new Scanner(System.in);

            System.out.print("����������ID: ");
            int DirectorID=sc.nextInt();
            System.out.print("��������������: ");
            String DirectorName = sc.next();
            System.out.print("������������������(����2003-1-1): ");
            String DirectorDate = sc.next();
            System.out.print("����������: ");
            int DirectorTerm=sc.nextInt();
            System.out.print("������ʵ����ID: ");
            int DirectorLabID=sc.nextInt();




            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\t����ID: " + DirectorID + "\t\t��������: " + DirectorName + "\t\t������������: " + DirectorDate+ "\t\t��������: " + DirectorTerm+ "\t\tʵ����ID: " + DirectorLabID);
            System.out.print("\n\n�Ƿ���Ӹ�������Ϣ�� [Yes(1) / No(0)] ��");
            isAdd( sc,DirectorID,DirectorName,DirectorDate,DirectorTerm,DirectorLabID);
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

    public static void isAdd( Scanner sc,int DirectorID,String DirectorName,String DirectorDate,int DirectorTerm,int DirectorLabID) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
                break;
            } else if (is == 1) {
                Connection connection =null;

                String sql ="INSERT INTO Director (DirectorID , DirectorName , StartDate, Term,LabID  ) VALUES (?, ?, ?, ?,?)";
                PreparedStatement preparedStatement=null;
                try {

                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, DirectorID);
                    preparedStatement.setString(2, DirectorName);
                    preparedStatement.setString(3, DirectorDate);
                    preparedStatement.setInt(4, DirectorTerm);
                    preparedStatement.setInt(5, DirectorLabID);

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

    public static void DirectorQuery()throws AWTException {
        while (true) {
            System.out.println("1.������ID��ѯ");
            System.out.println("2.���������Ʋ�ѯ");
            System.out.println("3.��ѯȫ�� ");
            System.out.print("\n����������ѡ��:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            if (input == 1) {
                int DirectorID0;
                int flag;
                while(true) {
                    System.out.print("������Ҫ��ѯ������ID��");
                    DirectorID0 = sc.nextInt();
                    flag =getDirectorID(DirectorID0);
                    if (flag == -1) {
                        System.out.print("\n�����β����ڣ�����������\n");
                    }
                    else {
                        break;
                    }
                }
                Connection connection = null;
                String sql = "select DirectorID, DirectorName, StartDate, Term,LabID  from Director where DirectorID=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, DirectorID0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int DirectorID = set.getInt("DirectorID");
                        String DirectorName = set.getString("DirectorName");
                        String DirectorDate = set.getString("StartDate");
                        int DirectorTerm = set.getInt("Term");
                        int DirectorLabID = set.getInt("LabID");


                        System.out.println("\t\t����ID: " + DirectorID + "\t\t��������: " + DirectorName + "\t\t������������: " + DirectorDate+ "\t\t��������: " + DirectorTerm+ "\t\tʵ����ID: " + DirectorLabID);

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;

            } else if (input == 2) {
                String DirectorName0;
                String flag;
                while(true) {
                    System.out.print("������Ҫ��ѯ���������֣�");
                    DirectorName0 = sc.next();
                    flag = getDirectorName(DirectorName0);
                    if (flag.equals("-1")) {
                        System.out.print("\n�����β����ڣ�����������\n");
                    }
                    else {
                        break;
                    }
                }
                Connection connection = null;
                String sql = "select DirectorID, DirectorName, StartDate, Term,LabID  from Director where DirectorName=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, DirectorName0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int DirectorID = set.getInt("DirectorID");
                        String DirectorName = set.getString("DirectorName");
                        String DirectorDate = set.getString("StartDate");
                        int DirectorTerm = set.getInt("Term");
                        int DirectorLabID = set.getInt("LabID");


                        System.out.println("\t\t����ID: " + DirectorID + "\t\t��������: " + DirectorName + "\t\t������������: " + DirectorDate+ "\t\t��������: " + DirectorTerm+ "\t\tʵ����ID: " + DirectorLabID);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;
            } else if (input == 3) {
                String tablename = "Director";
                FunctionBlock.printTable(tablename);
                break;
            } else {
                System.out.print("\n�������!���������룺\n");
            }
        }
    }

    public static void deleteDirector() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n������Ҫɾ��������ID��");
            int DirectorID = sc.nextInt();
            sc.nextLine();
            int flag = getDirectorID(DirectorID);
            if (flag == -1) {
                System.out.print("\n�����β����ڣ�����������\n");
            } else {
                System.out.print("\n�Ƿ�ɾ��IDΪ��" + DirectorID + " ��������Ϣ�� [Yes(1) / No(0)] :");
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
                String sql ="delete from Director where DirectorID=?";
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

    public static void updateDirector() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n������Ҫ�޸���Ϣ������ID:");
            int DirectorID = sc.nextInt();
            int flag = getDirectorID(DirectorID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("�����β����ڣ�����������\n\n\n ");
            } else {
                System.out.println(">�׽���>���ܽ���>�޸�������Ϣ\n");
                //����ID������������
                System.out.print("��������������: ");
                String DirectorName = sc.next();
                System.out.print("������������������(����2003-1-1): ");
                String DirectorDate = sc.next();
                System.out.print("����������: ");
                int DirectorTerm=sc.nextInt();
                System.out.print("������ʵ����ID: ");
                int DirectorLabID=sc.nextInt();
                Extents.clearConsole();

                System.out.println(">�׽���>���ܽ���>�޸�������Ϣ");
                System.out.println("�޸ĺ󡪡�>\n");
                System.out.println("\n\n-----------------------------------------------------");
                System.out.println("\t\t����ID: " + DirectorID + "\t\t��������: " + DirectorName + "\t\t������������: " + DirectorDate+ "\t\t��������: " + DirectorTerm+ "\t\tʵ����ID: " + DirectorLabID);

                System.out.print("\n\n�Ƿ��޸ĸ�������Ϣ�� [Yes(1) / No(0)] ��");
                isUpdate( sc,DirectorID,DirectorName,DirectorDate,DirectorTerm,DirectorLabID);
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

    public static void isUpdate( Scanner sc,int DirectorID,String DirectorName,String DirectorDate,int DirectorTerm,int DirectorLabID) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
                break;
            } else if (is == 1) {
                Connection connection =null;
                String sql ="update Director set DirectorID=?,DirectorName=?,StartDate =?,Term =?,LabID =? where DirectorID=?";
                PreparedStatement preparedStatement=null;
                try {
                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement.setInt(1, DirectorID);
                    preparedStatement.setString(2, DirectorName);
                    preparedStatement.setString(3, DirectorDate);
                    preparedStatement.setInt(4, DirectorTerm);
                    preparedStatement.setInt(5, DirectorLabID);
                    preparedStatement.setInt(6, DirectorID);

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
    public static int getDirectorID(int DirectorID) {

        Connection connection =null;
        String sql="select * from Director";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();
            while(set.next()){
                int id  =set.getInt("DirectorID");
                if(DirectorID==id){
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

    public static String getDirectorName(String DirectorName) {

        Connection connection =null;
        String sql="select * from Director";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();

            while(set.next()){
                String name  =set.getString("DirectorName");
                if(DirectorName.equals(name)){
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
