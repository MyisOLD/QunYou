package classsystem;

import java.awt.*;
import java.util.Scanner;

public class interfaceclass {
    public static void menu() throws AWTException {
        while (true) {
            System.out.println(">首界面\n");
            System.out.println("\t*****************************************************************");
            System.out.println("\t                       欢迎来到科研管理系统!                      ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         1.显示相关表格                         ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         2.管理相关信息                          ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         3.条件查找信息                          ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t   　                    p:退出管理系统                           ");
            System.out.println("\t******************************************************************");
            Scanner sc = new Scanner(System.in);
            System.out.print("\n请输入您的选择:");
            String choose = sc.nextLine();
            switch (choose) {
                case "1":
                    Extents.clearConsole();
                    showtables();
                    break;
                case "2":
                    Extents.clearConsole();
                    mangeTable();
                    break;
                case "3":
                    Extents.clearConsole();
                    queryTable();
                    break;
                case "p":
                    System.out.println("退出成功!");
                    System.exit(0);
                default:
                    Extents.clearConsole();
                    System.out.println("请重新输入\n\n\n");
                    break;
            }
        }
    }

    public static void showtables() throws AWTException {
        String tablename=null;
        while (true) {
            System.out.println(">首界面>相关表信息\n");
            System.out.println("\t****************************************************************");
            System.out.println("\t                         科研管理信息一览                         ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         1.研究室表                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         2.办公场地表                            ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         3.主任表                                ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         4.秘书表                                ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         5.科研人员表                            ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         6.科研项目表                            ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         7.委托方表                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         8.合作方表                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         9.质量检测方表                          ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         10.子课题表                             ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         11.参加项目的科研人员表                  ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         12.科研成果表                           ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         13.专利表表                             ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t   　     q:返回上级菜单                  p:退出管理系统           ");
            System.out.println("\t******************************************************************");
            Scanner sc = new Scanner(System.in);
            System.out.print("\n请输入您的选择:");
            String choose = sc.nextLine();
            tablename=null;
            switch (choose) {
                case "1":
                    tablename="researchlab";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "2":
                    tablename="officelocation";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "3":
                    tablename="Director";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "4":
                    tablename="Secretary";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "5":
                    tablename="Researcher";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "6":
                    tablename="ResearchProject";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "7":
                    tablename="Client";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "8":
                    tablename="Collaborator";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "9":
                    tablename="QualityMonitor";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "10":
                    tablename=" Subtask";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "11":
                    tablename="ResearcherProjectRelation";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "12":
                    tablename="ResearchAchievement";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "13":
                    tablename="Patent ";
                    Extents.clearConsole();
                    FunctionBlock.printTable(tablename);
                    break;
                case "q":
                    Extents.clearConsole();
                    menu();
                    return;
                case "p":
                    System.out.println("退出成功!");
                    System.exit(0);
                default:
                    Extents.clearConsole();
                    System.out.println("请重新输入\n\n\n");
                    break;
            }
        }
    }

    public static void queryTable() throws AWTException {
        while (true) {
            System.out.println(">首界面>条件查找信息\n");
            System.out.println("\t*****************************************************************");
            System.out.println("\t                         可查询表格一览                           ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         1.研究室                                ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         2.科研项目                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         3.科研成果                             ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         4.科研人员                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         5.办公场地                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         6.委托方表                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         7.合作方表                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         8.质量检测方表                          ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         9.主任表                                ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         10.秘书表                                ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t   　     q:返回上级菜单                  p:退出管理系统          ");
            System.out.println("\t******************************************************************");
            Scanner sc = new Scanner(System.in);
            System.out.print("\n请输入您的选择:");
            String choose = sc.nextLine();
            switch (choose) {
                case "1":
                    Extents.clearConsole();
                    ResearchLab.labQuery();
                    break;
                case "2":
                    Extents.clearConsole();
                    ResearchProject.projectQuery();
                    break;
                case "3":
                    Extents.clearConsole();
                    ResearchAchievement.achievementQuery();
                    break;
                case "4":
                    Extents.clearConsole();
                    Researcher.researchQuery();
                    break;
                case "5":
                    Extents.clearConsole();
                    OfficeLocation.OfficeLocationQuery();
                    break;
                case "6":
                    Extents.clearConsole();
                    Client.ClientQuery();
                    break;
                case "7":
                    Extents.clearConsole();
                    Collaborator.CollaboratorQuery();
                    break;
                case "8":
                    Extents.clearConsole();
                    QualityMonitor.QualityMonitorQuery();
                    break;
                case "9":
                    Extents.clearConsole();
                    Director.DirectorQuery();
                    break;
                case "10":
                    Extents.clearConsole();
                    Secretary.SecretaryQuery();
                    break;
                case "q":
                    Extents.clearConsole();
                    menu();
                    return;
                case "p":
                    System.out.println("退出成功!");
                    System.exit(0);
                default:
                    Extents.clearConsole();
                    System.out.println("请重新输入\n\n\n");
                    break;
            }
        }
    }
    public static void mangeTable() throws AWTException {
        String waitTableName=null;

        while (true) {
            System.out.println(">首界面>管理相关表格\n");
            System.out.println("\t*****************************************************************");
            System.out.println("\t                         可管理表格一览                           ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         1.研究室                                ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         2.科研项目                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         3.科研成果                             ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         4.科研人员                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         5.办公场地                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         6.委托方表                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         7.合作方表                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         8.质量检测方表                          ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         9.主任表                                ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         10.秘书表                                ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t   　     q:返回上级菜单                  p:退出管理系统          ");
            System.out.println("\t******************************************************************");
            Scanner sc = new Scanner(System.in);
            System.out.print("\n请输入您的选择:");
            String choose = sc.nextLine();
            waitTableName=null;
            switch (choose) {
                case "1":
                    waitTableName="researchlab";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;
                case "2":
                    waitTableName="researchproject";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;
                case "3":
                    waitTableName="researchachievement";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;
                case "4":
                    waitTableName="researcher";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;
                case "5":
                    waitTableName="OfficeLocation";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;

                case "6":
                    waitTableName="Client";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;
                case "7":
                    waitTableName="Collaborator";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;
                case "8":
                    waitTableName="QualityMonitor";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;
                case "9":
                    waitTableName="Director";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;
                case "10":
                    waitTableName="Secretary";
                    Extents.clearConsole();
                    FunctionChoose(waitTableName);
                    break;
                case "q":
                    Extents.clearConsole();
                    menu();
                    return;
                case "p":
                    System.out.println("退出成功!");
                    System.exit(0);
                default:
                    Extents.clearConsole();
                    System.out.println("请重新输入\n\n\n");
                    break;
            }
        }
    }
    public static void FunctionChoose(String waitTableName) throws AWTException {
        while (true) {
            System.out.println(">首界面>管理相关表格>功能选择\n");
            System.out.println("\t*****************************************************************");
            System.out.println("\t                         可执行功能                            ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         1.添加                                ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         2.编辑                              ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t                         3.删除                             ");
            System.out.println("\t ---------------------------------------------------------------");
            System.out.println("\t   　     q:返回上级菜单                  p:退出管理系统          ");
            System.out.println("\t******************************************************************");
            Scanner sc = new Scanner(System.in);
            System.out.print("\n请输入您的选择:");
            String choose = sc.nextLine();
            switch (choose) {
                case "1":
                    Extents.clearConsole();
                    Extents.goToTableAdd(waitTableName);
                    break;
                case "2":
                    Extents.clearConsole();
                    Extents.goToTableEdit(waitTableName);
                    break;
                case "3":
                    Extents.clearConsole();
                    Extents.goToTableDelete(waitTableName);
                    break;
                case "q":
                    Extents.clearConsole();
                    mangeTable();
                    return;
                case "p":
                    System.out.println("退出成功!");
                    System.exit(0);
                default:
                    Extents.clearConsole();
                    System.out.println("请重新输入\n\n\n");
                    break;
            }
        }
    }


}
