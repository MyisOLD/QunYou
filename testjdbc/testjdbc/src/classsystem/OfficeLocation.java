package classsystem;

import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class OfficeLocation {

    public static void OfficeLocationAdd()throws AWTException {
        while (true) {
            Extents.clearConsole();
            System.out.println(">�׽���>���ܽ���>��Ӱ칫������Ϣ\n");
            Scanner sc = new Scanner(System.in);
            System.out.print("������칫����ID: ");
            int locationID=sc.nextInt();
            System.out.print("������칫��������: ");
            String locationName = sc.next();
            System.out.print("������칫���: ");
            int locationArea = sc.nextInt();
            System.out.print("������칫���ص�ַ: ");
            String locationAddress = sc.next();


            System.out.println("\n\n-----------------------------------------------------");
            System.out.println("\t\t�칫����ID: " + locationID + "\t\t�칫��������: " + locationName + "\t\t�칫���: " + locationArea+ "\t\t�칫���ص�ַ: " + locationAddress);
            System.out.print("\n\n�Ƿ���Ӹð칫������Ϣ�� [Yes(1) / No(0)] ��");
            isAdd( sc,locationID,locationName,locationArea,locationAddress);
            System.out.println("\n\n\n>�׽���>���ܽ���>��Ӱ칫������Ϣ\n");
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

    public static void isAdd( Scanner sc,int locationID,String locationName,int locationArea,String locationAddress) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
                break;
            } else if (is == 1) {
                Connection connection =null;

                String sql ="INSERT INTO OfficeLocation (LocationID, LocationName, OfficeArea, Address) VALUES (?, ?, ?, ?)";
                PreparedStatement preparedStatement=null;
                try {

                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, locationID);
                    preparedStatement.setString(2, locationName);
                    preparedStatement.setInt(3, locationArea);
                    preparedStatement.setString(4, locationAddress);

                    //ִ��
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("��Ӱ칫������Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺");
        }
    }

    public static void OfficeLocationQuery()throws AWTException {
        while (true) {
            System.out.println("1.���칫����ID��ѯ");
            System.out.println("2.���칫�������Ʋ�ѯ");
            System.out.println("3.��ѯȫ�� ");
            System.out.print("\n����������ѡ��:");
            Scanner sc = new Scanner(System.in);
            int input = sc.nextInt();
            if (input == 1) {
                int locationID0;
                int flag;
                while(true) {
                    System.out.print("������Ҫ��ѯ�İ칫����ID��");
                    locationID0 = sc.nextInt();
                    flag = getLocationID(locationID0);
                    if (flag == -1) {
                        System.out.print("\n�ð칫���ز����ڣ�����������\n");
                    }
                    else {
                        break;
                    }
                }

                Connection connection = null;
                String sql = "select LocationID, LocationName, OfficeArea, Address from OfficeLocation where LocationID=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, locationID0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int locationID = set.getInt("LocationID");
                        String locationName = set.getString("LocationName");
                        int locationArea = set.getInt("OfficeArea");
                        String locationAddress = set.getString("Address");

                        System.out.println("\t\t�칫����ID: " + locationID + "\t\t�칫��������: " + locationName + "\t\t�칫���: " + locationArea+ "\t\t�칫���ص�ַ: " + locationAddress);

                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;

            } else if (input == 2) {
                String locationName0;
                String flag;
                while(true) {
                    System.out.print("������Ҫ��ѯ�İ칫�������֣�");
                    locationName0 = sc.next();
                    flag = getLocationName(locationName0);
                    if (flag.equals("-1")) {
                        System.out.print("\n�ð칫���ز����ڣ�����������\n");
                    }
                    else {
                        break;
                    }
                }
                Connection connection = null;
                String sql = "select LocationID, LocationName, OfficeArea, Address from OfficeLocation where LocationName=?";
                PreparedStatement preparedStatement = null;
                ResultSet set = null;
                try {
                    connection = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setString(1, locationName0);
                    set = preparedStatement.executeQuery();

                    while (set.next()) {
                        int locationID = set.getInt("LocationID");
                        String locationName = set.getString("LocationName");
                        int locationArea = set.getInt("OfficeArea");
                        String locationAddress = set.getString("Address");

                        System.out.println("\t\t�칫����ID: " + locationID + "\t\t�칫��������: " + locationName + "\t\t�칫���: " + locationArea+ "\t\t�칫���ص�ַ: " + locationAddress);
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                } finally {
                    JDBC.close(set, preparedStatement, connection);
                }
                break;
            } else if (input == 3) {
                String tablename = "OfficeLocation";
                FunctionBlock.printTable(tablename);
                break;
            } else {
                System.out.print("\n�������!���������룺\n");
            }
        }
    }

    public static void deleteOfficeLocation() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n������Ҫɾ���İ칫����ID��");
            int locationID = sc.nextInt();
            sc.nextLine();
            int flag = getLocationID(locationID);
            if (flag == -1) {
                System.out.print("\n�ð칫���ز����ڣ�����������\n");
            } else {
                System.out.print("\n�Ƿ�ɾ��IDΪ��" + locationID + " �İ칫������Ϣ�� [Yes(1) / No(0)] :");
                isDelete(sc, flag);
                System.out.println("\n\n\n>�׽���>���ܽ���>ɾ���칫������Ϣ\n");
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
                String sql ="delete from OfficeLocation where LocationID=?";
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
                System.out.println("ɾ���칫������Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺");
        }
    }

    public static void updateLocation() throws AWTException {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.print("\n\n������Ҫ�޸���Ϣ�İ칫����ID:");
            int locationID = sc.nextInt();
            int flag = getLocationID(locationID);
            Extents.clearConsole();
            if (flag == -1) {
                System.out.print("�ð칫���ز����ڣ�����������\n\n\n ");
            } else {
                System.out.println(">�׽���>���ܽ���>�޸İ칫������Ϣ\n");
                //�칫����ID������������
                System.out.print("������칫��������: ");
                String locationName = sc.next();
                System.out.print("������칫���: ");
                int locationArea = sc.nextInt();
                System.out.print("������칫���ص�ַ: ");
                String locationAddress = sc.next();
                Extents.clearConsole();

                System.out.println(">�׽���>���ܽ���>�޸İ칫������Ϣ");
                System.out.println("�޸ĺ󡪡�>\n");
                System.out.println("\n\n-----------------------------------------------------");
                System.out.println("\t\t�칫����ID: " + locationID + "\t\t�칫��������: " + locationName + "\t\t�칫���: " + locationArea+ "\t\t�칫���ص�ַ: " + locationAddress);

                System.out.print("\n\n�Ƿ���Ӹð칫������Ϣ�� [Yes(1) / No(0)] ��");
                isUpdate( sc,locationID,locationName,locationArea,locationAddress);
                System.out.println("\n\n\n>�׽���>���ܽ���>�޸İ칫������Ϣ\n");
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

    public static void isUpdate(Scanner sc,int locationID,String locationName,int locationArea,String locationAddress) throws AWTException {
        while (true) {
            int is = sc.nextInt();
            if (is == 0) {
                Extents.clearConsole();
                System.out.println("ȡ���ɹ���");
                break;
            } else if (is == 1) {
                Connection connection =null;
                String sql ="update OfficeLocation set LocationID=?,LocationName=?,OfficeArea=?,Address=? where LocationID=?";
                PreparedStatement preparedStatement=null;
                try {
                    connection  = JDBC.getConnection();
                    preparedStatement = connection.prepareStatement(sql);

                    preparedStatement = connection.prepareStatement(sql);
                    preparedStatement.setInt(1, locationID);
                    preparedStatement.setString(2, locationName);
                    preparedStatement.setInt(3, locationArea);
                    preparedStatement.setString(4, locationAddress);
                    preparedStatement.setInt(5, locationID);

                    //ִ��
                    preparedStatement.executeUpdate();
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }finally {
                    JDBC.close(null,preparedStatement,connection);
                }
                Extents.clearConsole();
                System.out.println("�޸İ칫������Ϣ�ɹ�!\n\n");
                break;
            }
            System.out.print("\n�������!���������룺\n");
        }
    }
    public static int getLocationID(int locationID) {

        Connection connection =null;
        String sql="select * from OfficeLocation";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();
            while(set.next()){
                int id  =set.getInt("LocationID");
                if(locationID==id){
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

    public static String getLocationName(String locationName) {

        Connection connection =null;
        String sql="select * from OfficeLocation";
        PreparedStatement preparedStatement=null;
        ResultSet set=null;
        try {
            connection  = JDBC.getConnection();
            preparedStatement = connection.prepareStatement(sql);

            set = preparedStatement.executeQuery();

            while(set.next()){
                String name  =set.getString("LocationName");
                if(locationName.equals(name)){
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
